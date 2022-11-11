$(function () {

    //登录校验的路径
    var checkLogin = '/checkLogin'

    var loginCount = 0

    $('#submit').click(function () {
        //用户名
        var username = $('#username').val()
        //密码
        var password = $('#password').val()

        var captcha = $('#captcha').val()
        //验证码可以不输入
        var needVerify = false
        if (loginCount >= 3) {
            if (!captcha) {
                lightyear.notify("验证码不能为空!", 'danger', 500, 'mdi mdi-emoticon-sad', 'top', 'center');
                return;
            } else {
                needVerify = true
            }
        }


        $.ajax({
            url:checkLogin,
            type:'POST',
            cache:false,
            async:false,
            //期望后端传过来的数据类型
            dataType:'json',
            contentType:'application/json;charset=utf-8',
            data:JSON.stringify({username:username,password:password,verifyCode:captcha,needVerify:needVerify}),
            success:function (data) {
                if (data.success){
                    console.log("登录成功")
                    lightyear.notify('登录成功','success',1000,'mdi mdi-emoticon-happy','top','center','/main')
                }
                else {
                    lightyear.notify(data.errMsg, 'danger', 1000, 'mdi mdi-emoticon-sad', 'top', 'center')
                    loginCount++
                    if (loginCount>=3){
                        $('#verifyCode').show()
                        $('#j_captcha').click()
                    }
                }
            }
        })
    })
})

function changeVerifyCode(img) {
    img.src = '/kaptcha?'+Math.floor(Math.random()*100)
}