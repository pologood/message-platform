/*	@description: 消息推送平台公用脚本
*	@author: jiangfeng
*	@update: jiangfeng (2015-09-11)*/

var /*isIE8 = $.browser.version == 8.0 || $.browser.version == 9.0,*/  //不支持css3部分属性的判断布尔值
	isFF = /firefox/.test(navigator.userAgent.toLowerCase());  //判断是否firefox浏览器

	function setMainHeight(){   //设置内容块的高度
      var winHeight = $(window).height(),
            mainHeight = $('.main').outerHeight(),
            topHeight = $('.header').outerHeight()+34;
        if(winHeight > mainHeight+topHeight){
          $('.main').css('min-height',winHeight - topHeight);
        }
    }
    setMainHeight();
    $(window).on('resize',setMainHeight);

    $('.nav-wrap>.nav-item>a').off().on('click',function(){
      var nav=$(this).parent().find('ul');
      if(nav.is(':hidden')){
        nav.slideDown(300);
      }else{
        nav.slideUp(300);
      }
    });

  ;(function($){
    $.fn.inputChecked = function(options){  //radio,checkbox自定义选中
      $.fn.inputChecked.defaults = {
        toggleClass:'checked',      //自定义控制选中样式的class名
        callback:null               //回调函数
          };
      var opts = $.extend({}, $.fn.inputChecked.defaults, options);
      return this.each(function(){
        var $this = $(this),
          callback = function(obj){
            if(typeof opts.callback === 'function')opts.callback(obj);
          },
          name = $this.attr('name'),
          type = $this.attr('type'),
          toggleClass = opts.toggleClass,
          $next = $this.next('label'),
          $labelinput;
        if($this.is(':checked')){
          $next.addClass('checked');
        }else{
          $next.removeClass('checked');
        };
        callback($this);
        if(isFF){
          $next.click(function(e){ //如果label内含有一些输入表单，则该输入表单获取焦点或者点击事件阻止冒泡
            if(e.target.nodeName === 'INPUT' || e.target.nodeName === 'TEXTAREA' ){
              e.preventDefault();
            }       
          });
        }
        $this.change(function(){
          var $self = $(this),$name = $('[name="'+name+'"]'),id = $self.attr('id') ;
          type = $self.attr('type');
          $next = $self.next('label');
          if($self.is(':checked')){
            if(type == 'radio'){
              $name.each(function(){
                var label = $(this).next('label');
                if($(this).attr('id') !== id){
                  label.removeClass(toggleClass);
                  label.find('input[type="text"]').removeAttr('required').attr({'readonly':'readonly','disabled':'disabled'});
                  if(label.find('input[type="text"]').attr('id') !=='startTime' && label.find('input[type="text"]').attr('id') !=='endTime'){
                    label.find('input[type="text"]').val('').trigger('blur');
                  }
                  if(label.find('a.selectPro').length > 0){
                    var _id = label.find('a.selectPro').attr('id');
                    $('#pop-tools'+_id).hide();
                  }
                }else{
                  $next.addClass(toggleClass);
                }
              })
            }else{
              $next.addClass(toggleClass);
            }
            $next.find('input[type="text"]').attr({'required':'required'}).removeAttr('readonly');
            callback($self);
          }else{
            $next.removeClass(toggleClass);
            $next.find('input[type="text"]').val('').removeAttr('required').attr({'readonly':'readonly'});
            callback($self);
          };
        });     
      });
    }
  })(jQuery);


  function PopTipsShow(tips,speed,delay){  //错误提示公共方法  tips = 提示内容, speed = 弹层显示速度, delay = 弹层显示停留时间
    var t = tips || '加载失败',
      s = speed || 300,
      d = delay || 2000;
    $('.error_msg').html(t).css({'margin-left':-$('.error_msg').outerWidth()/2+'px'}).fadeIn(s);
    if(typeof tipsId !== 'undefined')clearTimeout(tipsId);
    tipsId = setTimeout(function(){
      $('.error_msg').fadeOut(s);
    },d);
  }

    /* 分页 */
    var all,now,url
    $('.paging p').each(function(){
        var self = $(this),
            html = ''
        all = parseInt(self.parent().data('all')),
        now = parseInt(self.parent().data('now')),
        url = self.parent().data('url')
        if(now==1){
            html += '<span class="paging_span1"></span>'
        }else{
            html += '<a href="'+url+'currentPage='+(now-1)+'" class="paging_left"></a> '
        }
        if(all<11){
            html += render_page(1, all, url, now)
        }else{
            if(now<=3 || now>=all-2){
                html += render_page(1, 3)
                html += '<span class="paging_span">…</span>'
                html += render_page(all-2, all)
            }else if(now==4 || now==5){
                html += render_page(1, now)
                html += '<span class="paging_span">…</span>'
                html += render_page(all-2, all)
            }else if(now==all-3 || now==all-4){
                html += render_page(1, 3)
                html += '<span class="paging_span">…</span>'
                html += render_page(now, all)
            }else{
                html += render_page(1, 3)
                html += '<span class="paging_span">…</span>'
                html += render_page(now-1, now+1)
                html += '<span class="paging_span">…</span>'
                html += render_page(all-2, all)
            }
        }
        if(now==all){
            html += '<span class="paging_span2"></span>'
        }else{
            html += '<a href="'+url+'currentPage='+(now+1)+'" class="paging_right"></a> '
        }
        self.append(html)
    })

    function render_page(from, to){
        var html = ""
        for(var i=from;i<=to;i++){
            if(i==now){
                html += '<a href="javascript:" class="choice">'+i+'</a> '
            }else{
                html += '<a href="'+url+'currentPage='+i+'">'+i+'</a> '
            }
        }
        return html
    }
