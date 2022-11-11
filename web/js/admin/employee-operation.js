$(function () {

    //根据id查看部门信息url
    var getEmployeeInfoUrl = '/employee/queryEmployeeById'
    //编辑部门url
    var editEmployeeInfoUrl = '/employee/editEmployee'
    //新增部门的url
    var addEmployeeInfoUrl = '/employee/insertEmployee'

    //查看有效部门列表的url
    var queryActiveDepartmentListUrl = '/department/getActiveList'
    //查看有效职位列表的url
    var queryActivePositionListUrl = '/position/getActiveList'

    var emId = getQueryString("emId")
    var is_edit = getQueryString("edit")
    console.log(emId)




    if (emId){
        $.post(getEmployeeInfoUrl,{emId:emId},function (data) {
            console.log(data)
            if (data.success){
                var employee = data.data.employeeDTO
                $('#employee-name').val(employee.name)
                $('#employee-loginName').val(employee.loginName)

                var departmentList = data.data.departmentList
                var positionList = data.data.positionList

                //部门列表渲染
                var depHtml = ''
                departmentList.map(function (item) {
                    var isSelected = employee.depId  === item.depId?'selected':''
                        depHtml +='<option data-value="'
                        +   item.depId +'"'
                        +   isSelected +'>'
                        +   item.name+'</option>'
                })
                $('#department-select').html(depHtml)

                //渲染职位
                var posHtml = ''
                positionList.map(function (item) {
                    var isSelected = employee.positionId  === item.positionId?'selected':''
                    posHtml +='<option data-value="'
                        +   item.positionId + '"'
                        +   isSelected +'>'
                        +   item.positionName+'</option>'
                })
                $('#position-select').html(posHtml)

                if (employee.status === 1){
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
    }else {
        //部门下拉列表
        $.post(queryActiveDepartmentListUrl,function (data) {
            console.log(data)
            var departmentList = data.data
            var optionHtml = ''
            departmentList.map(function (item,index) {
                optionHtml +='<option data-value="'
                    +   item.depId + '">'
                    +   item.name+'</option>'
            })
            $('#department-select').html(optionHtml)
        })

        //职位下拉列表
        $.post(queryActivePositionListUrl,function (data) {
            console.log(data)
            var positionList = data.data
            var optionHtml = ''
            positionList.map(function (item,index) {
                optionHtml +='<option data-value="'
                    +   item.positionId + '">'
                    +   item.positionName+'</option>'
            })
            $('#position-select').html(optionHtml)
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
        var employee = {}
        console.log($('input[name="status"][checked]').val())
        if (is_edit){
            employee.emId = emId
        }
        employee.name = $('#employee-name').val()
        employee.loginName = $('#employee-loginName').val()
        employee.depId = $('#department-select').find('option').not(function () {
            return !this.selected
        }).data('value')
        employee.positionId = $('#position-select').find('option').not(function () {
            return !this.selected
        }).data('value')
        employee.status = $('input[name="status"][checked]').val()
        var formData = new FormData();
        formData.append('employeeStr',JSON.stringify(employee))
        $.ajax({
            url:is_edit?editEmployeeInfoUrl:addEmployeeInfoUrl,
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
                    lightyear.notify("操作成功", 'success', 1000, 'mdi mdi-emoticon-happy', 'top', 'center','/employee/toList')
                }else{
                    lightyear.notify(data.errMsg, 'danger', 1000, 'mdi mdi-emoticon-sad', 'top', 'center')
                }
            }
        })
    })
})