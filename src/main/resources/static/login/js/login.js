
/**
* 页面初始化
* @author; ChenBo
* @datetime: 2019/5/11:23:05
*/
$(function () {


    /**
     * 验证登录表单并登录
     * @author; ChenBo
     * @datetime: 2019/5/11:23:00
     */
    $('#loginForm').bootstrapValidator({
        excluded: [':disabled',":hidden",':not(:visible)'],//不校验
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        // live: 'enabled', //disabled,submitted 当点击提交时验证并展示错误信息
        // threshold: 1, //输入一个字符后开始验证
        // trigger: 'blur',
        message: '请输入',
        fields:{
            username: {
                enabled: true,
                trigger: 'blur', // focus keyup 触发事件
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 18,
                        message: "用户名长度必须在6到18位之间"
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名只能包含大写、小写、数字和下划线'
                    },
                    threshold: 2,//有2个字符以上才发送ajax请求
                    // remote: {
                    //     url: basePath +'/user/checkUserName',
                    //     message: '用户名已存在,请重新输入',
                    //     delay: 1000,
                    //     type: 'post',
                    //     data: function () {
                    //         return {
                    //             username: $('#loginForm').find('input[name = username]').val()
                    //         }
                    //     }
                    // }
                }
            },
            password: {
                enabled: true,
                // trigger: 'live', // focus keyup live 触发事件
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 18,
                        message: "用户名长度必须在6到18位之间"
                    }
                }
            }
        },
        submitButtons: 'button[type="submit"]',
        submitHandler: function (validator, form, submitButton) {
            // $.post('/user/login',form.serializeObject(),function (res) {
            //     if(res && res.code === 200){
            //         window.location.href = basePath+'/';
            //     }
            // });
            layer.msg('输入的用户名或密码不正确');
            $('#loginForm').find('input[name=password]').val('');
            validator.updateStatus("password",  "VALIDATING",  null );
        }
    });

});




