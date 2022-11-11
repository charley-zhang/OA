$(function () {
    $('#logout').click(function () {
        $.ajax({
            url:'/logout',
            type:'POST',
            catch:false,
            datatype:'json',
            success:function (data) {
                if (data.success){
                    window.location.href='/login'
                }
            },
            error:function (data) {
                alert("失败的回调函数")
            }
        })
    })
})