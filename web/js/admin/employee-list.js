
$(function(){

    //查看有效部门列表的url
    var queryActiveDepartmentListUrl = '/department/getActiveList'
    //查看有效职位列表的url
    var queryActivePositionListUrl = '/position/getActiveList'

    //请求参数封装的对象
    var request_data = {}
    //是否需要对分页插件进行初始化
    var flag = true

    getList()

    //部门下拉列表
    $.post(queryActiveDepartmentListUrl,function (data) {
        console.log(data)
        var departmentList = data.data
        var optionHtml = '<option data-value="">全部</option>'
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
        var optionHtml = '<option data-value="">全部</option>'
        positionList.map(function (item,index) {
            optionHtml +='<option data-value="'
                +   item.positionId + '">'
                +   item.positionName+'</option>'
        })
        $('#position-select').html(optionHtml)
    })

    $('#department-select').change(function (e) {
        console.log("部门下拉列表事件被触发")
        //双重否定表肯定
        var depId = $('#position-select').find('option').not(function () {
            return !this.selected
        }).data('value')
        console.log("depId"+depId)
        request_data.depId = depId
        flag = true
        request_data.pageNum = 1
        getList(request_data)
    })

    $('#position-select').change(function (e) {
        console.log("部门下拉列表事件被触发")
        //双重否定表肯定
        var PositionId = $('#position-select').find('option').not(function () {
            return !this.selected
        }).data('value')
        console.log("PositionId"+PositionId)
        request_data.PositionId = PositionId
        flag = true
        request_data.pageNum = 1
        getList(request_data)
    })


    $('.search-bar .dropdown-menu a').click(function() {
        var field = $(this).data('field') || '';
        $('#search-field').val(field);
        $('#search-btn').html($(this).text() + ' <span class="caret"></span>');
    });

    //获取部门列表
    function getList(data) {
        data = data===undefined?{}:data
        $.ajax({
            url:'/employee/getList',
            type:'POST',
            cache:'false',
            datatype:'json',
            contentType:'application/json;charset=utf-8',
            data:JSON.stringify(data),
            success:function (data) {
                // console.log("data"+data)
                if (data.success){
                    //做渲染
                    handleList(data.data)
                    if (flag){
                        getPageInfo(data.data)
                        flag = false
                    }
                }else {
                    lightyear.notify(data.errMsg, 'danger', 1000, 'mdi mdi-emoticon-sad', 'top', 'center')
                }
            }
        })
    }
    function handleList(data) {
        var i = 1
        var html = ''
        data.list.map(function (item,index) {
            html += '<tr>'
              +  '<td>'+(i++)+'</td>'
              +  '<td>'+(item.name)+'</td>'
              +  '<td data-toggle="tooltip" title='+(item.loginName)+'>'+(item.loginName)+'</td>'
              +  '<td>'+(item.department.name)+'</td>'
              +  '<td>'+(item.position.positionName)+'</td>'
              +     handleStatus(item.status)
              +   '<td>'
              +   '<div class="btn-group">'
              +      '<a class="btn btn-xs btn-default" href="/employee/toEmployeeEdit?edit=true&emId='+(item.emId)+'" title="编辑" data-toggle="tooltip"><i class="mdi mdi-pencil"></i></a>'
              +      '<a class="btn btn-xs btn-default" href="/employee/toEmployeeInfo?emId='+(item.emId)+'" title="查看" data-toggle="tooltip"><i class="mdi mdi-eye"></i></a>'
              +      showEmployeeStatus(item.status,item.emId)
              +  '</div>'
              +  '</td>'
           + '</tr>'
        })

        $('.employee-wrap').html(html)

    }
    //状态文字化处理
    function handleStatus(status) {
        if (status===1){
            return '<td><font class="text-success">在职</font></td>'
        }
        return '<td><font class="text-danger">离职</font></td>'
    }
    
    
    //显示员工状态（主要是存数据和修改图标）
    function showEmployeeStatus(status, emId) {
        if (status===1){
            return '<a data-id='+emId+' data-status='+status+' class="btn btn-xs btn-default employee-status-btn" href="#!" title="状态" data-toggle="tooltip"><i class="mdi mdi-toggle-switch"></i></a>'
        }
        return '<a data-id='+emId+' data-status='+status+' class="btn btn-xs btn-default employee-status-btn" href="#!" title="状态" data-toggle="tooltip"><i class="mdi mdi-toggle-switch-off"></i></a>'

    }

    //修改状态
    $('.employee-wrap').on('click','a',function (e) {
        console.log("a标签被点击了")
        var target = $(e.currentTarget)
        if(target.hasClass('employee-status-btn')){
            console.log("定位到代表状态的那一组a标签")
            var emId = e.currentTarget.dataset.id
            var status = e.currentTarget.dataset.status
            //如果选择只显示有效状态的列表,那么如果吧某个条目的状态改为无效可能会造成总页数减少,
            //所以需要重新生成页面插件,而且最好跳转到第一页
            if (request_data.status === 1) {
                flag = true
                request_data.pageNum=1
            }
            console.log("id:status==="+emId+":"+status)
            $.ajax({
                url:'/employee/toggleEmployeeStatus',
                type:'POST',
                cache: false,
                datatype: 'json',
                contentType:'application/json;charset=utf-8',
                data:JSON.stringify({emId:emId,status:status}),
                success:function (data) {
                    if (data.success){
                        lightyear.notify("修改成功", 'success', 1000, 'mdi mdi-emoticon-happy', 'top', 'center')
                    }else {
                        lightyear.notify(data.errMsg, 'danger', 1000, 'mdi mdi-emoticon-sad', 'top', 'center')
                    }
                    flag = true
                    request_data.pageNum = 1
                    getList(request_data)
                }
            })
        }

    })

    //切换状态触发的事件
    $('#status-condition').change(function () {
        console.log("checkbox被触发了")
        console.log($('#status-condition').is(":checked"))
        //只要对状态进行筛选就要重新分页
        flag = true
        request_data.pageNum=1
        if ($('#status-condition').is(":checked")){
            request_data.status = 1
        }else {
            request_data.status = null
        }
        getList(request_data)
    })


    //搜索框回车事件
    $('#search-input').keydown(function (e) {
        // console.log("键盘事件被触发"+e.keyCode)
        if (e.keyCode===13){
            console.log("回车键被触发了")
            var keyword = $('#search-input').val()
            var type = $('#search-field').val();
            console.log("关键字：类型==="+keyword+":"+type)
            if ('name' === type){
                request_data.name=keyword
                request_data.loginName=null
            }else {
                request_data.loginName = keyword
                request_data.name=null
            }
        flag = true
        request_data.pageNum = 1
        getList(request_data)
        }
    })

    // getPageInfo()

    //分页数据
    function getPageInfo(data) {
        console.log("初始化分页插件")
        $('.jq_pagination').pagination({
            pageCount:data.pages,
            coping:true,
            callback:function (e) {
                console.log("分页插件的回调函数")
                //跳转到具体的页面，对列表进行渲染
                console.log("想要跳转到"+e.getCurrent())
                request_data.pageNum = e.getCurrent()
                getList(request_data)
            }
        })
    }
});