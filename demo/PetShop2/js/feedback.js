/*
 feedback.js <http://experiments.hertzen.com/jsfeedback/>
 Copyright (c) 2012 Niklas von Hertzen. All rights reserved.
 http://www.twitter.com/niklasvh

 Released under MIT License
*/
(function (window, document, undefined) {
    if (window.Feedback !== undefined) {
        return;
    }

    // log proxy function
    var log = function (msg) {
        window.console.log(msg);
    },
    // function to remove elements, input as arrays
    removeElements = function (remove) {
        for (var i = 0, len = remove.length; i < len; i++) {
            var item = Array.prototype.pop.call(remove);
            if (item !== undefined) {
                if (item.parentNode !== null) { // check that the item was actually added to DOM
                    item.parentNode.removeChild(item);
                }
            }
        }
    },
    loader = function () {
        var div = document.createElement("div"), i = 3;
        div.className = "feedback-loader";

        while (i--) { div.appendChild(document.createElement("span")); }
        return div;
    },
    getBounds = function (el) {
        return el.getBoundingClientRect();
    },
    emptyElements = function (el) {
        var item;
        while (((item = el.firstChild) !== null ? el.removeChild(item) : false)) { }
    },
    element = function (name, text, css) {
        var el = document.createElement(name);
        if (text) {
            el.appendChild(document.createTextNode(text));
        }
        if(css) {
            el.className = css;
        }
        return el;
    },
    // script onload function to provide support for IE as well
    scriptLoader = function (script, func) {

        if (script.onload === undefined) {
            // IE lack of support for script onload

            if (script.onreadystatechange !== undefined) {

                var intervalFunc = function () {
                    if (script.readyState !== "loaded" && script.readyState !== "complete") {
                        window.setTimeout(intervalFunc, 250);
                    } else {
                        // it is loaded
                        func();
                    }
                };

                window.setTimeout(intervalFunc, 250);

            } else {
                log("ERROR: We can't track when script is loaded");
            }

        } else {
            return func;
        }

    },
    nextButton,
    H2C_IGNORE = "data-html2canvas-ignore",
    currentPage,
    modalBody = document.createElement("div");

    window.Feedback = function (options) {

        options = options || {};

        // default properties
        options.label = options.label || "意见反馈";
        options.header = options.header || "意见反馈 ";
        options.url = options.url || "/";
        options.adapter = options.adapter || new window.Feedback.XHR(options);

        options.nextLabel = options.nextLabel || "下一步";
        options.reviewLabel = options.reviewLabel || "预览";
        options.sendLabel = options.sendLabel || "发送";
        options.closeLabel = options.closeLabel || "关闭";

        options.messageSuccess = options.messageSuccess || "发送成功！";
        options.messageError = options.messageError || "发送失败";


        if (options.pages === undefined) {
            options.pages = [
                new window.Feedback.Form(),
                new window.Feedback.Screenshot(options),
                new window.Feedback.Review()
            ];
        }

        var button,
        modal,
        currentPage,
        glass = document.createElement("div"),
        returnMethods = {

            // open send feedback modal window
            open: function () {
                var len = options.pages.length;
                currentPage = 0;
                for (; currentPage < len; currentPage++) {
                    // create DOM for each page in the wizard
                    if (!(options.pages[currentPage] instanceof window.Feedback.Review)) {
                        options.pages[currentPage].render();
                    }
                }

                var a = element("a", "x"),
                modalHeader = document.createElement("div"),
                // modal container
                modalFooter = document.createElement("div");

                modal = document.createElement("div");
                document.body.appendChild(glass);

                // modal close button
                a.className = "feedback-close";
                a.onclick = returnMethods.close;
                a.href = "#";

                button.disabled = true;

                // build header element
                modalHeader.appendChild(a);
                modalHeader.appendChild(element("h3", options.header));
                modalHeader.className = "feedback-header";

                modalBody.className = "feedback-body";

                emptyElements(modalBody);
                currentPage = 0;
                modalBody.appendChild(options.pages[currentPage++].dom);


                // Next button
                nextButton = element("button", options.nextLabel);

                nextButton.className = "feedback-btn";
                nextButton.onclick = function () {

                    if (currentPage > 0) {
                        if (options.pages[currentPage - 1].end(modal) === false) {
                            // page failed validation, cancel onclick
                            return;
                        }
                    }

                    emptyElements(modalBody);

                    if (currentPage === len) {
                        returnMethods.send(options.adapter);
                    } else {

                        options.pages[currentPage].start(modal, modalHeader, modalFooter, nextButton);

                        if (options.pages[currentPage] instanceof window.Feedback.Review) {
                            // create DOM for review page, based on collected data
                            options.pages[currentPage].render(options.pages);
                        }

                        // add page DOM to modal
                        modalBody.appendChild(options.pages[currentPage++].dom);

                        // if last page, change button label to send
                        if (currentPage === len) {
                            nextButton.firstChild.nodeValue = options.sendLabel;
                        }

                        // if next page is review page, change button label
                        if (options.pages[currentPage] instanceof window.Feedback.Review) {
                            nextButton.firstChild.nodeValue = options.reviewLabel;
                        }


                    }

                };

                modalFooter.className = "feedback-footer";
                modalFooter.appendChild(nextButton);


                modal.className = "feedback-modal";
                modal.setAttribute(H2C_IGNORE, true); // don't render in html2canvas


                modal.appendChild(modalHeader);
                modal.appendChild(modalBody);
                modal.appendChild(modalFooter);

                document.body.appendChild(modal);
            },


            // close modal window
            close: function () {

                button.disabled = false;

                // remove feedback elements
                removeElements([modal, glass]);

                // call end event for current page
                if (currentPage > 0) {
                    options.pages[currentPage - 1].end(modal);
                }

                // call close events for all pages    
                for (var i = 0, len = options.pages.length; i < len; i++) {
                    options.pages[i].close();
                }

                return false;

            },

            // send data
            send: function (adapter) {

                // make sure send adapter is of right prototype
                if (!(adapter instanceof window.Feedback.Send)) {
                    throw new Error("Adapter is not an instance of Feedback.Send");
                }

                // fetch data from all pages   
                for (var i = 0, len = options.pages.length, data = [], p = 0, tmp; i < len; i++) {
                    if ((tmp = options.pages[i].data()) !== false) {
                        data[p++] = tmp;
                    }
                }

                nextButton.disabled = true;

                emptyElements(modalBody);
                modalBody.appendChild(loader());

                // send data to adapter for processing
                adapter.send(data, function (success) {

                    emptyElements(modalBody);
                    nextButton.disabled = false;

                    nextButton.firstChild.nodeValue = options.closeLabel;

                    nextButton.onclick = function () {
                        returnMethods.close();
                        return false;
                    };

                    if (success === true) {
                        modalBody.appendChild(document.createTextNode(options.messageSuccess));
                    } else {
                        modalBody.appendChild(document.createTextNode(options.messageError));
                    }

                });

            }
        };

        glass.className = "feedback-glass";
        glass.style.pointerEvents = "none";
        glass.setAttribute(H2C_IGNORE, true);

        options = options || {};

        button = element("button", options.label);
        button.className = "feedback-btn feedback-bottom-right";

        button.setAttribute(H2C_IGNORE, true);

        button.onclick = returnMethods.open;

        if (options.appendTo !== null) {
            ((options.appendTo !== undefined) ? options.appendTo : document.body).appendChild(button);
        }

        return returnMethods;
    };
    window.Feedback.Page = function () { };
    window.Feedback.Page.prototype = {

        render: function (dom) {
            this.dom = dom;
        },
        start: function () { },
        close: function () { },
        data: function () {
            // don't collect data from page by default
            return false;
        },
        review: function () {
            return null;
        },
        end: function () { return true; }

    };
    window.Feedback.Send = function () { };
    window.Feedback.Send.prototype = {

        send: function () { }

    };

    window.Feedback.Form = function (elements) {

        this.elements = elements || [
            {
                type: "input",
                name: "UserName",
                label: "称呼",
                required: true
            },
            {
                type: "input",
                name: "Email",
                label: "联系邮件",
                required: true
            },
            {
                type: "input",
                name: "Title",
                label: "标题",
                required: false
            },
            {
                type: "textarea",
                name: "Issue",
                label: "您想说点什么？",
                required: true
            }
        ];

        this.dom = document.createElement("div");

    };

    window.Feedback.Form.prototype = new window.Feedback.Page();

    window.Feedback.Form.prototype.render = function () {

        var i = 0, len = this.elements.length, item;
        emptyElements(this.dom);
        for (; i < len; i++) {
            item = this.elements[i];

            switch (item.type) {
                case "textarea":
                    this.dom.appendChild(element("label", item.label + ":" + ((item.required === true) ? "" : ""), 'block-label'));
                    this.dom.appendChild((item.element = document.createElement("textarea")));
                    break;
                case "input":
                    this.dom.appendChild(element("label", item.label + ":" + ((item.required === true) ? "" : ""),'block-label'));
                    this.dom.appendChild((item.element = document.createElement("input")));
                    item.element.type = 'text';
                    break;
            }
            if (this.data[item.name]) {
                item.element.value = item.name;
            }
        }

        return this;

    };

    window.Feedback.Form.prototype.end = function () {
        // form validation  
        var i = 0, len = this.elements.length, item;
        for (; i < len; i++) {
            item = this.elements[i];

            // check that all required fields are entered
            if (item.required === true && item.element.value.length === 0) {
                item.element.className = "feedback-error";
                return false;
            } else {
                item.element.className = "";
            }
        }

        return true;

    };

    window.Feedback.Form.prototype.data = function () {

        if ( this._data !== undefined ) {
            // return cached value
            return this._data;
        }
    
        var i = 0, len = this.elements.length, item, data = {};

        for (; i < len; i++) {
            item = this.elements[i];
            data[item.name] = item.element.value;
        }

        // cache and return data
        return (this._data = data);
    };


    window.Feedback.Form.prototype.review = function( dom ) {

        var i = 0, item, len = this.elements.length;

        for (; i < len; i++) {
            item = this.elements[ i ];
            
            if (item.element.value.length > 0) {
                dom.appendChild( element("label", item.label + ":") );
                dom.appendChild( document.createTextNode( item.element.value) );
                dom.appendChild( document.createElement( "hr" ) );
            }
        }

        return dom;
         
    };
    window.Feedback.Review = function() {

            this.dom = document.createElement("div");
            this.dom.className = "feedback-review";
    };

    window.Feedback.Review.prototype = new window.Feedback.Page();

    window.Feedback.Review.prototype.render = function (pages) {

        var i = 0, len = pages.length, item;
        emptyElements(this.dom);

        for (; i < len; i++) {
            // get preview DOM items
            pages[i].review(this.dom);
        }

        return this;

    };




    window.Feedback.Screenshot = function (options) {
        this.options = options || {};

        this.options.blackoutClass = this.options.blackoutClass || 'feedback-blackedout';
        this.options.highlightClass = this.options.highlightClass || 'feedback-highlighted';

        this.h2cDone = false;
    };

    window.Feedback.Screenshot.prototype = new window.Feedback.Page();

    window.Feedback.Screenshot.prototype.end = function (modal) {
        modal.className = modal.className.replace(/feedback\-animate\-toside/, "");

        // remove event listeners
        //document.body.removeEventListener("mousemove", this.mouseMoveEvent, false);
        //document.body.removeEventListener("click", this.mouseClickEvent, false);

        removeElements([this.h2cCanvas]);
        removeElements([document.getElementById('fb_canvas_container')]);
        this.h2cDone = false;

    };

    window.Feedback.Screenshot.prototype.close = function () {
        removeElements([this.blackoutBox, this.highlightContainer, this.highlightBox, this.highlightClose]);

        removeElements(document.getElementsByClassName(this.options.blackoutClass));
        removeElements(document.getElementsByClassName(this.options.highlightClass));

    };

    window.Feedback.Screenshot.prototype.start = function (modal, modalHeader, modalFooter, nextButton) {

        if (this.h2cDone) {
            emptyElements(this.dom);
            nextButton.disabled = false;

            var $this = this,
                action = true,
                boxButton_id = 'active_fb_box',
                textButton_id = 'active_fb_text',
                arrowButton_id = 'active_fb_arrow',
                freeDrawButton_id = 'active_fb_free',
                activeTool = '',
                black_id = 'active_fb_black';
            white_id = 'active_fb_white';
            red_id = 'active_fb_red';
            yellow_id = 'active_fb_yellow';
            blue_id = 'active_fb_blue';
            activeColor = black_id,
            clearActive = function (buttonCls) {
                var buttons = $this.dom.getElementsByClassName(buttonCls);

                for (var i = 0, l = buttons.length; i < l; i++) {
                    buttons[i].className = 'feedback-btn feedback-btn-small ' + buttonCls;
                }
            },
            buttonClickFunction = function (e) {
                var t = e.target;
                activeTool = t.id;
                clearActive('tool-btn');
                t.className += ' active';
                //console.log(activeTool);
                if (fbCanvasInstance.isDrawingMode && t.id != freeDrawButton_id) {
                    fbCanvasInstance.isDrawingMode = false;
                }
                if (t.id == freeDrawButton_id) {
                    fbCanvasInstance.isDrawingMode = true;
                }
            },
            boxButton = element("button", "框"),
            textButton = element("button", "文字"),
            arrowButton = element('button', '箭头'),
            freeDrawButton = element('button', '画笔'),
            fbEditorContainer = document.createElement('div'),
            fbCanvasEditor = document.createElement('canvas'),
            fbStyle = fbEditorContainer.style;


            modal.className += ' feedback-animate-toside';
            this.h2cCanvas.className = 'feedback-canvas';
            document.body.appendChild(this.h2cCanvas);


            fbStyle.position = 'absolute';
            fbStyle.left = fbStyle.top = '0px';
            fbStyle.zIndex = '2000';

            fbEditorContainer.id = 'fb_canvas_container';
            fbCanvasEditor.id = 'fb_canvas';
            fbCanvasEditor.width = this.h2cCanvas.width;
            fbCanvasEditor.height = this.h2cCanvas.height;

            fbEditorContainer.appendChild(fbCanvasEditor);
            document.body.appendChild(fbEditorContainer);

            var fbCanvasInstance = new fabric.Canvas('fb_canvas'),
                startPoint,
                selectionShown = false,
                endPoint;

            fbCanvasInstance.freeDrawingBrush = new fabric['PencilBrush'](fbCanvasInstance);

            fbCanvasInstance.freeDrawingBrush.color = 'yellow';
            fbCanvasInstance.freeDrawingBrush.width = 20;
            //canvas.freeDrawingBrush.shadowBlur = parseInt(drawingShadowWidth.value, 10) || 0

            document.addEventListener('keydown', function (e) {
                var activeObject;
                e.preventDefault();
                if (e.keyCode == 46 || e.keyCode == 8) {
                    activeObject = fbCanvasInstance.getActiveObject();
                    if (activeObject) {
                        fbCanvasInstance.remove(activeObject);
                    }
                }
                if (e.keyCode == 27) {
                    fbCanvasInstance.isDrawingMode = false;
                }

            });

            fbCanvasInstance.on('mouse:down', function (e) {
                var mouseEvent = e.e,
                    objectToAdd;
                if (selectionShown) {
                    // selection is not cleared use previous point.
                    return;
                }

                selectionShown = true;
                startPoint = { pageX: mouseEvent.pageX, pageY: mouseEvent.pageY };

                switch (activeTool) {
                    case textButton_id:
                        objectToAdd = new fabric.IText(
                           '修改文本, \r\n请双击文本框',
                            {
                                top: startPoint.pageY,
                                left: startPoint.pageX,
                                fill: activeColor,
                                width: 300,
                                
                                cornerColor: 'yellow',
                                cornerSize: 12,
                                transparentCorners: false
                            }
                       );
                        break;
                    case arrowButton_id:
                        objectToAdd = new fabric.Polygon([
                            { x: 30, y: 0 },
                            { x: 0, y: 30 },
                            { x: 20, y: 30 },
                            { x: 20, y: 100 },
                            { x: 40, y: 100 },
                            { x: 40, y: 30 },
                            { x: 60, y: 30 },
                        ], {
                            top: startPoint.pageY,
                            left: startPoint.pageX,
                            fill: activeColor,
                            angle:45,
                            cornerColor: 'yellow',
                            cornerSize: 12,
                            transparentCorners: false
                        });

                        break;
                }
                if (objectToAdd) {
                    activeTool = '';
                    fbCanvasInstance.add(objectToAdd);
                    clearActive('tool-btn');
                }
            });
            fbCanvasInstance.on('mouse:up', function (e) {
                selectionShown = false;
                var mouseEvent = e.e, height, width,
                    objectToAdd;

                endPoint = { pageX: mouseEvent.pageX, pageY: mouseEvent.pageY };
                height = endPoint.pageY - startPoint.pageY;
                width = endPoint.pageX - startPoint.pageX;

                switch (activeTool) {
                    case boxButton_id:
                        objectToAdd = new fabric.Rect({
                            top: startPoint.pageY,
                            left: startPoint.pageX,
                            width: width,
                            height: height,
                            stroke: activeColor,
                            strokeWidth: 12,
                            fill: 'rgba(0,0,0,0)',
                            cornerColor: 'yellow',
                            cornerSize: 12,
                            transparentCorners: false
                        });
                        break;
                }
                activeTool = '';
                if (objectToAdd) {
                    fbCanvasInstance.add(objectToAdd);
                    clearActive('tool-btn');
                }
            });


            this.fbCanvas = fbCanvasEditor;

            boxButton.id = boxButton_id;
            textButton.id = textButton_id;
            arrowButton.id = arrowButton_id;
            freeDrawButton.id = freeDrawButton_id;
            var buttonItem = [boxButton, textButton, arrowButton, freeDrawButton],
                paletteContainer = element('div'),
                palette = element('div', ''),
                black = element('a', 'B'),
                white = element('a', 'W'),
                red = element('a', 'R'),
                yellow = element('a', 'Y'),

                blue = element('a', 'B'),
                tools = element('div'),
                colors = [black, white, red, yellow, blue],
                colorClick = function (e) {
                    var activeObjectf;
                    e.preventDefault();
                    activeColor = e.target.id.replace('active_fb_', '');
                    clearActive('color-btn');
                    e.target.className += ' active';
                    activeObject = fbCanvasInstance.getActiveObject();
                    if (activeObject) {
                        if (activeObject instanceof fabric.Rect) {
                            activeObject.setStroke(activeColor);
                        }
                        else {
                            activeObject.setFill(activeColor);
                        }
                        fbCanvasInstance.renderAll();
                    }
                    else {
                        if (fbCanvasInstance.isDrawingMode) {
                            fbCanvasInstance.freeDrawingBrush.color = activeColor;
                        }
                    }
                };
            black.id = black_id;
            black.style.background = black.style.color = 'black';
            white.id = white_id;
            white.style.background = white.style.color = 'white',
            red.id = red_id;
            red.style.background = red.style.color = 'red';
            yellow.id = yellow_id;
            yellow.style.background = yellow.style.color = 'yellow';
            blue.id = blue_id;
            blue.style.background = blue.style.color = 'blue';


            for (var i = 0, l = colors.length, btn; i < l; i++) {
                btn = colors[i];
                btn.href = '#';
                btn.onclick = colorClick;
                btn.className = 'feedback-btn feedback-btn-small color-btn';
                palette.appendChild(colors[i]);
            }
            paletteContainer.appendChild(element('label', '颜色'));
            paletteContainer.appendChild(palette);
            this.dom.appendChild(paletteContainer);
            this.dom.appendChild(element("p", "画图工具"));

            for (i = 0, l = buttonItem.length; i < l; i++) {
                buttonItem[i].className = 'feedback-btn feedback-btn-small tool-btn';

                buttonItem[i].href = "#";
                buttonItem[i].onclick = buttonClickFunction;
                this.dom.appendChild(buttonItem[i]);
                //this.dom.appendChild( document.createTextNode(" ") );
            }

        } else {
            // still loading html2canvas
            var args = arguments,
            $this = this;

            if (nextButton.disabled !== true) {
                this.dom.appendChild(loader());
            }

            nextButton.disabled = true;

            window.setTimeout(function () {
                $this.start.apply($this, args);
            }, 500);
        }

    };

    window.Feedback.Screenshot.prototype.render = function () {

        this.dom = document.createElement("div");

        // execute the html2canvas script
        var script,
        $this = this,
        options = this.options,
        runH2c = function () {
            try {

                options.onrendered = options.onrendered || function (canvas) {
                    $this.h2cCanvas = canvas;
                    $this.h2cDone = true;
                };

                window.html2canvas([document.body], options);

            } catch (e) {
                $this.h2cDone = true;
                log("Error in html2canvas: " + e.message);
            }
        };

        if (window.html2canvas === undefined && script === undefined) {

            // let's load html2canvas library while user is writing message

            script = document.createElement("script");
            script.src = options.h2cPath || "libs/html2canvas.js";
            script.onerror = function () {
                log("Failed to load html2canvas library, check that the path is correctly defined");
            };

            script.onload = (scriptLoader)(script, function () {

                if (window.html2canvas === undefined) {
                    log("Loaded html2canvas, but library not found");
                    return;
                }

                window.html2canvas.logging = window.Feedback.debug;
                runH2c();


            });

            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(script, s);

        } else {
            // html2canvas already loaded, just run it then
            runH2c();
        }

        return this;
    };

    window.Feedback.Screenshot.prototype.data = function () {
        if (this.h2cCanvas !== undefined) {

            var ctx = this.h2cCanvas.getContext("2d"),
            canvasCopy,
            copyCtx,
            radius = 5;
            ctx.fillStyle = "#000";

            ctx.drawImage(this.fbCanvas, 0, 0);

            // to avoid security error break for tainted canvas   
            try {
                // cache and return data
                return (this._data = this.h2cCanvas.toDataURL('image/jpeg',0.5));
            } catch (e) { }

        }
    };


    window.Feedback.Screenshot.prototype.review = function (dom) {

        var data = this.data();
        if (data !== undefined) {
            var img = new Image();
            img.src = data;
            img.style.width = "300px";
            dom.appendChild(img);
        }

    };
    window.Feedback.XHR = function (options) {

        this.xhr = window.XMLHttpRequest
            ? new XMLHttpRequest()
            : (window.window.XDomainRequest ? new window.XDomainRequest() : new ActiveXObject('Microsoft.XMLHTTP'));
        this.url = options.url;
        this.apikey = options.apikey;
    };

    window.Feedback.XHR.prototype = new window.Feedback.Send();

    window.Feedback.XHR.prototype.send = function (data, callback) {

        var xhr = this.xhr;

        function formatData(data) {
            var query = [];
            for (var key in data) {
                query.push(encodeURIComponent(key) + '=' + encodeURIComponent(data[key]));
            }
            return query.join('&');
        };

        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4) {
                callback((xhr.status === 200));
            }
        };
        data.apikey = this.apikey;
        //xhr.open("POST", this.url, true);
        //xhr.setRequestHeader("Content-type", "application/json");
        console.log(data);
        //xhr.send("data=" + encodeURIComponent(window.JSON.stringify(data)));
        //xhr.send(window.JSON.stringify());
        xhr.open('POST', this.url, true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//        xhr.send( "data=" + encodeURIComponent( window.JSON.stringify(data) ) );
        var formData = data[0];
        xhr.send(formatData({
            title: formData.Title,
            content: formData.Issue,
            token: this.apikey,
            email: formData.Email,
            nick: formData.UserName,
            image: data[1]
        }));
//        window.open(data[1]);
    };
})(window, document);

