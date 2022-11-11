$(function () {

    //根据id查看职位信息url
    var getPositionInfoUrl = '/position/queryPositionById'
    //编辑职位url
    var editPositionInfoUrl = '/position/editPosition'
    //新增职位的url
    var addPositionInfoUrl = '/position/insertPosition'

    var positionId = getQueryString("positionId")
    var is_edit = getQueryString("edit")
    console.log(positionId)


    if (positionId){
        $.post(getPositionInfoUrl,{positionId:positionId},function (data) {
            console.log(data)
            if (data.success){
                var position = data.data
                $('#position-name').val(position.positionName)
                // $('#position-address').val(position.address)
                if (position.status === 1){
                    $('#status-on').attr('checked',true)
                    $('#status-off').removeAttr('checked')
                }else {
                    $('#status-off').attr('checked',true)
                    $('#status-on').removeAttr('checked')
                }
            }else {
                //TODO:如果后台没有正确返回数据,则需要......
                document.write("后台没有正确返回数据")
            }
        })
    }

    $('#status-on').click(function () {
        $('#status-on').attr('checked',true);
        $('#status-off').removeAttr('checked')
    })

    $('#status-off').click(function () {
        $('#status-off').attr('checked',true);
        $('#status-on').removeAttr('checked')
    })

    $('#submit').click(function () {
        var position = {}
        console.log($('input[name="status"][checked]').val())
        if (is_edit){
            position.positionId = positionId
        }
        position.positionName = $('#position-name').val()
        // position.address = $('#dep-address').val()
        position.status = $('input[name="status"][checked]').val()
        var formData = new FormData();
        formData.append('positionStr',JSON.stringify(position))
        $.ajax({
            url:is_edit?editPositionInfoUrl:addPositionInfoUrl,
            type:'POST',
            dataType:'json',
            //一般用作表单提交涉及到文件上传
            contentType:false,
            processData:false,
            catch:false,
            async:false,
            data:formData,
            success:function (data) {
                console.log(data)
                if (data.success){
                    lightyear.notify("操作成功", 'success', 1000, 'mdi mdi-emoticon-happy', 'top', 'center','/position/toList')
                }else{
                    lightyear.notify(data.errMsg, 'danger', 1000, 'mdi mdi-emoticon-sad', 'top', 'center')
                }
            }
        })
    })
})