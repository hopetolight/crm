

var common = {};

/**
* 动态获取根目录路径
* @author; ChenBo
* @datetime: 2019/5/10:22:47
*/
/*&lt;![CDATA[*/
    const basePath = /*[[@{/}]]*/ '';
    const contextPath = /*[[ ${#httpServletRequest.getContextPath()} ]]*/ '';
/*]]>*/

/**
* jq 扩展方法
* @author; ChenBo
* @datetime: 2019/5/10:22:50
*/
$.fn.extend({
    /**
    * 获取form表单提交的数据对象
    * @author; ChenBo
    * @datetime: 2019/5/10:22:50
    */
    serializeObject:function () {
        let o = {};
        $.each(this.serializeArray(),function () {
            if (o[this.name] !== undefined){
                if(! o[this.name].push){
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            }else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    }
    
});

/**
* 弹出框初始化
* @author; ChenBo
* @datetime: 2019/5/12:2:13
*/
toastr.options = {
    closeButton: true,
    debug: false,
    progressBar: true,
    positionClass: 'toast-top-center',
    onclick: null,
    showDuration: '300',
    hideDuration: '400',
    timeOut: '3500',
    extendedTimeOut: '1000',
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};


