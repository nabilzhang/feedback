/**
 * Created by louqibin on 14-3-22.
 */
/**
 * ER (Enterprise RIA)
 * Copyright 2013 Baidu Inc. All rights reserved.
 *
 * @ignore
 * @file AJAX相关方法
 * @author otakustay
 */
define(
    function (require) {

        /**
         * @class ajax
         *
         * AJAX模块
         *
         * @mixins mini-event.EventTarget
         * @singleton
         */
        var ajax = {};
        require('mini-event/EventTarget').enable(ajax);

        /**
         * 每次请求流程的勾子，可通过重写其中的函数来影响AJAX的行为
         *
         * @type {meta.AjaxHook}
         */
        ajax.hooks = {};

        ajax.hooks.serializeArray = function (prefix, array) {
            var encodedKey = prefix ? encodeURIComponent(prefix) : '';
            var encoded = [];
            for (var i = 0; i < array.length; i++) {
                var item = array[i];
                encoded[i] = ajax.hooks.serializeData('', item);
            }
            return encodedKey
                ? encodedKey + '=' + encoded.join(',')
                : encoded.join(',');
        };

        ajax.hooks.serializeData = function (prefix, data) {
            if (arguments.length === 1) {
                data = prefix;
                prefix = '';
            }

            if (data == null) {
                data = '';
            }
            var getKey = ajax.hooks.serializeData.getKey;
            var encodedKey = prefix ? encodeURIComponent(prefix) : '';

            var type = Object.prototype.toString.call(data);
            switch (type) {
                case '[object Array]':
                    return ajax.hooks.serializeArray(prefix, data);
                case '[object Object]':
                    var result = [];
                    for (var name in data) {
                        var propertyKey = getKey(name, prefix);
                        var propertyValue =
                            ajax.hooks.serializeData(propertyKey, data[name]);
                        result.push(propertyValue);
                    }
                    return result.join('&');
                default:
                    return encodedKey
                        ? encodedKey + '=' + encodeURIComponent(data)
                        : encodeURIComponent(data);
            }
        };

        ajax.hooks.serializeData.getKey = function (propertyName, parentKey) {
            return parentKey ? parentKey + '.' + propertyName : propertyName;
        };

        /**
         * AJAX的全局配置
         *
         * @type {meta.AjaxOption}
         */
        ajax.config = {
            cache: false,
            timeout: 0,
            charset: ''
        };
        /**
         * 发起`XMLHttpRequest`请求
         *
         * @param {meta.AjaxOption} options 相关配置
         * @return {meta.FakeXHR}
         */
        ajax.request = function (options) {
            if (typeof ajax.hooks.beforeExecute === 'function') {
                ajax.hooks.beforeExecute(options);
            }

            var assert = require('./assert');
            assert.hasProperty(options, 'url', 'url property is required');

            var defaults = {
                method: 'POST',
                data: {},
                cache: ajax.config.cache,
                timeout: ajax.config.timeout,
                charset: ajax.config.charset
            };
            var util = require('./util');
            options = util.mix(defaults, options);

            var Deferred = require('./Deferred');
            var requesting = new Deferred();

            if (typeof ajax.hooks.beforeCreate === 'function') {
                var canceled = ajax.hooks.beforeCreate(options, requesting);
                if (canceled === true) {
                    var fakeXHR = requesting.promise;
                    fakeXHR.abort = function () {};
                    fakeXHR.setRequestHeader = function () {};
                    return fakeXHR;
                }
            }

            var xhr = window.XMLHttpRequest
                ? new XMLHttpRequest()
                : new ActiveXObject('Microsoft.XMLHTTP');

            var fakeXHR = requesting.promise;
            var xhrWrapper = {
                abort: function () {
                    // 有些浏览器`abort()`就会把`readyState`变成4，
                    // 这就会导致进入处理函数变成**resolved**状态，
                    // 因此事先去掉处理函数，然后直接进入**rejected**状态
                    xhr.onreadystatechange = null;
                    xhr.abort();
                    if (!fakeXHR.status) {
                        fakeXHR.status = 0;
                    }
                    fakeXHR.readyState = xhr.readyState;
                    fakeXHR.responseText = '';
                    fakeXHR.responseXML = '';
                    requesting.reject(fakeXHR);
                },
                setRequestHeader: function (name, value) {
                    xhr.setRequestHeader(name, value);
                }
            };
            util.mix(fakeXHR, xhrWrapper);

            fakeXHR.then(
                function () {
                    /**
                     * @event done
                     *
                     * 任意一个请求成功时触发
                     *
                     * @param {meta.FakeXHR} xhr 请求对象
                     */
                    ajax.fire('done', { xhr: fakeXHR });
                },
                function () {
                    /**
                     * @event fail
                     *
                     * 任意一个请求失败时触发
                     *
                     * @param {meta.FakeXHR} xhr 请求对象
                     */
                    ajax.fire('fail', { xhr: fakeXHR });
                }
            );

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    var status = fakeXHR.status || xhr.status;
                    // `file://`协议下状态码始终为0
                    if (status === 0) {
                        status = 200;
                    }
                    // IE9会把204状态码变成1223
                    else if (status === 1223) {
                        status = 204;
                    }

                    fakeXHR.status = fakeXHR.status || status;
                    fakeXHR.readyState = xhr.readyState;
                    fakeXHR.responseText = xhr.responseText;
                    fakeXHR.responseXML = xhr.responseXML;

                    if (typeof ajax.hooks.afterReceive === 'function') {
                        ajax.hooks.afterReceive(fakeXHR, options);
                    }

                    // 如果请求不成功，也就不用再分解数据了，直接丢回去就好
                    if (status < 200 || (status >= 300 && status !== 304)) {
                        requesting.reject(fakeXHR);
                        return;
                    }

                    var data = xhr.responseText;
                    if (options.dataType === 'json') {
                        try {
                            data = util.parseJSON(data);
                        }
                        catch (ex) {
                            // 服务器返回的数据不符合JSON格式，认为请求失败
                            fakeXHR.error = ex;
                            requesting.reject(fakeXHR);
                            return;
                        }
                    }

                    if (typeof ajax.hooks.afterParse === 'function') {
                        try {
                            data =
                                ajax.hooks.afterParse(data, fakeXHR, options);
                        }
                        catch (ex) {
                            fakeXHR.error = ex;
                            requesting.reject(fakeXHR);
                            return;
                        }
                    }

                    // 数据处理成功后，进行回调
                    requesting.resolve(data);
                }
            };

            var method = options.method.toUpperCase();
            var data = {};
            if (method === 'GET') {
                util.mix(data, options.data);
            }
            if (options.cache === false) {
                data['_'] = +new Date();
            }
            var query = ajax.hooks.serializeData(
                '', data, 'application/x-www-form-urlencoded');
            var url = options.url;
            if (query) {
                var delimiter = url.indexOf('?') >= 0 ? '&' : '?';
                url += delimiter + query;
            }

            xhr.open(method, url, true);

            if (typeof ajax.hooks.beforeSend === 'function') {
                ajax.hooks.beforeSend(fakeXHR, options);
            }

            if (method === 'GET') {
                xhr.send();
            }
            else {
                var contentType =
                    options.contentType || 'application/x-www-form-urlencoded';
                var query = ajax.hooks.serializeData(
                    '', options.data, contentType, fakeXHR);
                if (options.charset) {
                    contentType += ';charset=' + options.charset;
                }
                xhr.setRequestHeader('Content-Type', contentType);
                xhr.send(query);
            }

            if (options.timeout > 0) {
                var tick = setTimeout(
                    function () {
                        fakeXHR.status = 408; // HTTP 408: Request Timeout
                        fakeXHR.abort();
                    },
                    options.timeout
                );
                fakeXHR.ensure(function () { clearTimeout(tick); });
            }

            return fakeXHR;
        };
        /**
         * 发起一个`POST`请求
         *
         * @param {string} url 请求的地址
         * @param {Object} [data] 请求的数据
         * @param {string} [dataType="json"] 指定响应的数据格式
         * @return {meta.FakeXHR}
         */
        ajax.post = function (url, data, dataType) {
            var options = {
                method: 'POST',
                url: url,
                data: data,
                dataType: dataType || 'json'
            };
            return ajax.request(options);
        };
        return ajax;
    }
);
