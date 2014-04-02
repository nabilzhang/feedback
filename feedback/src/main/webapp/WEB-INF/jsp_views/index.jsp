<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <script src="./js/jquery.1.11.0.min.js"></script>
  <style type="text/css">
    html,body{
      height: 100%;
      overflow: hidden;
      position: relative;
      padding:0;
      margin:0;
    }
    body{
      min-width: 0;
    }
    img{
      width:auto;
    }
    .background{
      width: 100%;
      height: auto;
      position: absolute;
      left: 0; 
      top: 0;
    }
    .background img{
      width: 100%;
      height: auto;
      display: block;
    }
    .content{
      position: absolute;
      left: 0;
      top: 60px;
      text-align: center;
      width: 100%;
    }
    .content .box{
      width: 50.3%;
      min-height: 79px;
      margin: 0 auto 0;
    }

    .content .content1{
      margin-top: 0;
    }
    .imgbox{position:relative;top:10%; padding-bottom: 20px;}
    .content1 .body{
      width: 60%;
      margin: 0 auto;
    }

    .arrow-down{
      background: url(./img/intro/arrow-down.png) no-repeat left top;
      background-size: 100% 100%;
      width: 41px;
      height: 41px;
      max-width: 41px;
      max-height: 41px;
      width: 59px\9;
      height: 59px\9;
      max-width: 59px\9;
      max-height: 59px\9;
      margin: 0 auto;
      position: relative;
      bottom: 0;
      cursor: pointer;
      /*
      animation: scrollup 3s infinite;
      -webkit-animation: scrollup 3s infinite;
      -moz-animation: scrollup 3s infinite;
      -o-animation: scrollup 3s infinite;*/
    }
    @keyframes scrollup{
      0%, 50%, 100% {top: 90%;}
      25%, 75% {top: 95%;}
    }
    @-webkit-keyframes scrollup{
      0%, 50%, 100% {top: 90%;}
      25%, 75% {top: 95%;}
    }
    @-moz-keyframes scrollup{
      0%, 50%, 100% {top: 90%;}
      25%, 75% {top: 95%;}
    }
    @-o-keyframes scrollup{
      0%, 50%, 100% {top: 90%;}
      25%, 75% {top: 95%;}
    }
    .content .wrapper{
      position: relative;
      height: auto;
      padding-bottom: 60px;
      width:auto;
    }
    .goSystem{
      cursor: pointer;
      position:relative;
      top:10%;
    }
    .btn-go{
      width: 25%;
      margin-top: 35px;
    }
    .myButton {
	    -moz-box-shadow:inset 0px 1px 0px 0px #97c4fe;
	    -webkit-box-shadow:inset 0px 1px 0px 0px #97c4fe;
	    box-shadow:inset 0px 1px 0px 0px #97c4fe;
	    background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #3d94f6), color-stop(1, #1e62d0));
	    background:-moz-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	    background:-webkit-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	    background:-o-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	    background:-ms-linear-gradient(top, #3d94f6 5%, #1e62d0 100%);
	    background:linear-gradient(to bottom, #3d94f6 5%, #1e62d0 100%);
	    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#3d94f6', endColorstr='#1e62d0',GradientType=0);
	    background-color:#3d94f6;
	    -moz-border-radius:6px;
	    -webkit-border-radius:6px;
	    border-radius:6px;
	    border:1px solid #337fed;
	    display:inline-block;
	    cursor:pointer;
	    color:#ffffff;
	    font-family:arial;
	    font-size:15px;
	    font-weight:bold;
	    padding:6px 24px;
	    text-decoration:none;
	    text-shadow:0px 1px 0px #1570cd;
	}
	.myButton:hover {
	    background:-webkit-gradient(linear, left top, left bottom, color-stop(0.05, #1e62d0), color-stop(1, #3d94f6));
	    background:-moz-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	    background:-webkit-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	    background:-o-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	    background:-ms-linear-gradient(top, #1e62d0 5%, #3d94f6 100%);
	    background:linear-gradient(to bottom, #1e62d0 5%, #3d94f6 100%);
	    filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#1e62d0', endColorstr='#3d94f6',GradientType=0);
	    background-color:#1e62d0;
	}
	.myButton:active {
	    position:relative;
	    top:1px;
	}
    .backtop{
      background: url(./img/intro/backtop.png) no-repeat;
      width: 57px;
      height: 57px; 
      text-indent: -999em;
      position: fixed;
      bottom: 20px;
      right: 10%;
      cursor: pointer;
      display: none;
      z-index: 2;
    } 
    .footer{border-top: 1px solid #cacbd6; height: 56px; text-align: center; width: 100%; background: #FFF;  position: absolute; bottom: 0; display: none;}
    .footer a{ display: inline-block; margin:10px 5px;}
    @media all and (max-width: 480px){
      .content .box{
        width: 100%;
      }
    }
  </style>
  <title>百度-集智</title>
</head>
<body>
  <div id="viewScroll">
    <div class="background">
      <div class="block1"><img src="./img/intro/block1.jpg" /></div>
      <div class="block2"><img src="./img/intro/block1.jpg" /></div>
      <div class="block3"><img src="./img/intro/block1.jpg" /></div>
    </div>
    <div class="content">
      <div class="content1 box">
        <div class="wrapper">
          <div class="imgbox">
            <img src="./img/intro/content1.png"/>
          </div>
          <div class="arrow-down"></div>
        </div>
      </div>
      <div class="content2 box">
        <div class="wrapper">
          <div class="imgbox">
            <img src="./img/intro/content2.png" />
          </div>
          <div class="arrow-down"></div>
        </div>
      </div>
      <div class="content3 box">
        <div class="wrapper">
          <div class="imgbox">
            <img src="./img/intro/content3.png" />
          </div>
          <div><a href="/project" class="myButton btn-go">进入系统</a></div>
        </div>
      </div>
    </div>
  </div>
  <div class="backtop" id="backTop">返回顶部</div>
  <div class="footer">
  </div>
  <script>

(function (factory) {
    if ( typeof define === 'function' && define.amd ) {
        // AMD. Register as an anonymous module.
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS style for Browserify
        module.exports = factory;
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {

    var toFix  = ['wheel', 'mousewheel', 'DOMMouseScroll', 'MozMousePixelScroll'],
        toBind = ( 'onwheel' in document || document.documentMode >= 9 ) ?
                    ['wheel'] : ['mousewheel', 'DomMouseScroll', 'MozMousePixelScroll'],
        slice  = Array.prototype.slice,
        nullLowestDeltaTimeout, lowestDelta;

    if ( $.event.fixHooks ) {
        for ( var i = toFix.length; i; ) {
            $.event.fixHooks[ toFix[--i] ] = $.event.mouseHooks;
        }
    }

    var special = $.event.special.mousewheel = {
        version: '3.1.9',

        setup: function() {
            if ( this.addEventListener ) {
                for ( var i = toBind.length; i; ) {
                    this.addEventListener( toBind[--i], handler, false );
                }
            } else {
                this.onmousewheel = handler;
            }
            // Store the line height and page height for this particular element
            $.data(this, 'mousewheel-line-height', special.getLineHeight(this));
            $.data(this, 'mousewheel-page-height', special.getPageHeight(this));
        },

        teardown: function() {
            if ( this.removeEventListener ) {
                for ( var i = toBind.length; i; ) {
                    this.removeEventListener( toBind[--i], handler, false );
                }
            } else {
                this.onmousewheel = null;
            }
        },

        getLineHeight: function(elem) {
            return parseInt($(elem)['offsetParent' in $.fn ? 'offsetParent' : 'parent']().css('fontSize'), 10);
        },

        getPageHeight: function(elem) {
            return $(elem).height();
        },

        settings: {
            adjustOldDeltas: true
        }
    };

    $.fn.extend({
        mousewheel: function(fn) {
            return fn ? this.bind('mousewheel', fn) : this.trigger('mousewheel');
        },

        unmousewheel: function(fn) {
            return this.unbind('mousewheel', fn);
        }
    });


    function handler(event) {
        var orgEvent   = event || window.event,
            args       = slice.call(arguments, 1),
            delta      = 0,
            deltaX     = 0,
            deltaY     = 0,
            absDelta   = 0;
        event = $.event.fix(orgEvent);
        event.type = 'mousewheel';

        // Old school scrollwheel delta
        if ( 'detail'      in orgEvent ) { deltaY = orgEvent.detail * -1;      }
        if ( 'wheelDelta'  in orgEvent ) { deltaY = orgEvent.wheelDelta;       }
        if ( 'wheelDeltaY' in orgEvent ) { deltaY = orgEvent.wheelDeltaY;      }
        if ( 'wheelDeltaX' in orgEvent ) { deltaX = orgEvent.wheelDeltaX * -1; }

        // Firefox < 17 horizontal scrolling related to DOMMouseScroll event
        if ( 'axis' in orgEvent && orgEvent.axis === orgEvent.HORIZONTAL_AXIS ) {
            deltaX = deltaY * -1;
            deltaY = 0;
        }

        // Set delta to be deltaY or deltaX if deltaY is 0 for backwards compatabilitiy
        delta = deltaY === 0 ? deltaX : deltaY;

        // New school wheel delta (wheel event)
        if ( 'deltaY' in orgEvent ) {
            deltaY = orgEvent.deltaY * -1;
            delta  = deltaY;
        }
        if ( 'deltaX' in orgEvent ) {
            deltaX = orgEvent.deltaX;
            if ( deltaY === 0 ) { delta  = deltaX * -1; }
        }

        // No change actually happened, no reason to go any further
        if ( deltaY === 0 && deltaX === 0 ) { return; }

        // Need to convert lines and pages to pixels if we aren't already in pixels
        // There are three delta modes:
        //   * deltaMode 0 is by pixels, nothing to do
        //   * deltaMode 1 is by lines
        //   * deltaMode 2 is by pages
        if ( orgEvent.deltaMode === 1 ) {
            var lineHeight = $.data(this, 'mousewheel-line-height');
            delta  *= lineHeight;
            deltaY *= lineHeight;
            deltaX *= lineHeight;
        } else if ( orgEvent.deltaMode === 2 ) {
            var pageHeight = $.data(this, 'mousewheel-page-height');
            delta  *= pageHeight;
            deltaY *= pageHeight;
            deltaX *= pageHeight;
        }

        // Store lowest absolute delta to normalize the delta values
        absDelta = Math.max( Math.abs(deltaY), Math.abs(deltaX) );

        if ( !lowestDelta || absDelta < lowestDelta ) {
            lowestDelta = absDelta;

            // Adjust older deltas if necessary
            if ( shouldAdjustOldDeltas(orgEvent, absDelta) ) {
                lowestDelta /= 40;
            }
        }

        // Adjust older deltas if necessary
        if ( shouldAdjustOldDeltas(orgEvent, absDelta) ) {
            // Divide all the things by 40!
            delta  /= 40;
            deltaX /= 40;
            deltaY /= 40;
        }

        // Get a whole, normalized value for the deltas
        delta  = Math[ delta  >= 1 ? 'floor' : 'ceil' ](delta  / lowestDelta);
        deltaX = Math[ deltaX >= 1 ? 'floor' : 'ceil' ](deltaX / lowestDelta);
        deltaY = Math[ deltaY >= 1 ? 'floor' : 'ceil' ](deltaY / lowestDelta);

        // Add information to the event object
        event.deltaX = deltaX;
        event.deltaY = deltaY;
        event.deltaFactor = lowestDelta;
        // Go ahead and set deltaMode to 0 since we converted to pixels
        // Although this is a little odd since we overwrite the deltaX/Y
        // properties with normalized deltas.
        event.deltaMode = 0;

        // Add event and delta to the front of the arguments
        args.unshift(event, delta, deltaX, deltaY);

        // Clearout lowestDelta after sometime to better
        // handle multiple device types that give different
        // a different lowestDelta
        // Ex: trackpad = 3 and mouse wheel = 120
        if (nullLowestDeltaTimeout) { clearTimeout(nullLowestDeltaTimeout); }
        nullLowestDeltaTimeout = setTimeout(nullLowestDelta, 200);

        return ($.event.dispatch || $.event.handle).apply(this, args);
    }

    function nullLowestDelta() {
        lowestDelta = null;
    }

    function shouldAdjustOldDeltas(orgEvent, absDelta) {
        // If this is an older event and the delta is divisable by 120,
        // then we are assuming that the browser is treating this as an
        // older mouse wheel event and that we should divide the deltas
        // by 40 to try and get a more usable deltaFactor.
        // Side note, this actually impacts the reported scroll distance
        // in older browsers and can cause scrolling to be slower than native.
        // Turn this off by setting $.event.special.mousewheel.settings.adjustOldDeltas to false.
        return special.settings.adjustOldDeltas && orgEvent.type === 'mousewheel' && absDelta % 120 === 0;
    }

}));
$(function(){  
  var count=1;
  var len = 3;//页数
  var timer;
  var step = 1; 
  var defaultHeight;
  /* 屏幕变化时初始化函数 */
  function init(){
    defaultHeight = $('body').height();
  $('#viewScroll').height($('.background').height() - $('.header-warp').height()-$('.footer').height());
    $('.content .box').height(defaultHeight * step);
    if($('.content .box').height() < 655){
      $('.pop').css('top', '0.9%');
    }else{
      $('.pop').css('top', '1.9%');
    }
    if($('.content .box').height() < 740){
      $('.content .box').each(function(){
        $(this).find('.imgbox img').css({'max-width': 'auto', 'max-height': $(this).height()*3/4 });
      });
    }else{
      $('.content .box').each(function(){
        $(this).find('.imgbox img').css({'max-width': '100%', 'max-height': 'auto'});
        $(this).find('.wrapper').css({'top': '10%'});
      });
    }
  }
  init();   
  
  var freeze = false;
  $('.content .box').height(defaultHeight * step);  
  var background = $('.background'),
      content = $('.content'),
      newBgTop = 0, 
      newCtTop = 0;
  $('#viewScroll').mousewheel(function(event, delta){

   var defaultHeight = $('body').height();  
    if(freeze){
      return;
    }
    var bgTop = -parseInt(background.css('top')),
        ctTop = -parseInt(content.css('top'));
    if(delta < 0){
      count++;
      if(count > len * step){
        count = len * step;
      };
    }else{
      count --;
      if(count < 1){
        count = 1;
      }
    }
    space = parseInt(background.height() / (len*step) * (4/5) * (count-1));
    newBgTop = space;
    if(count == 1){
      newBgTop=0;
      newCtTop = '0px';
    }
    newCtTop = ''+ (count-1)*100+'%';
    $('.background').animate({'top': '-'+ newBgTop + 'px'}, 3000);
    $('.content').animate({'top': '-' + newCtTop}, 3000, function(){
      if(newCtTop >= '100%'){
        $('.backtop').show();
        if(newCtTop == '400%' && defaultHeight < 400 ){  
          $('.content').css('margin-top', '-116px');
          $('.footer').slideUp(300);
        }
      }else{
        $('.backtop').hide();
      }
    });
    freeze = true;
    timer = window.setTimeout(function(){
      freeze = false;
    }, 2000);
  });
  /* 窗口变化时重新init */
  $(window).resize(function(){
    init();
  });
  
  $('.arrow-down').click(function(){
    $('.content').trigger('mousewheel',['-90','event']);
  });

  
  /* 回到顶部 */
  $('.backtop').click(function(){
    count = 1;
    $('.background').animate({'top': '0px'}, 2000);
    $('.content').animate({'top': '0px'}, 2000);
  });
});
  </script>
</body>
</html>