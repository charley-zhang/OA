<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
    <title>查看职位</title>
    <link rel="icon" href="/favicon.ico" type="image/ico">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/materialdesignicons.min.css" rel="stylesheet">
    <!--标签插件-->
<#--    <link rel="stylesheet" href="/js/jquery-tags-input/jquery.tagsinput.min.css">-->
    <link href="/css/style.min.css" rel="stylesheet">
</head>

<body>
<div class="lyear-layout-web">
    <div class="lyear-layout-container">
        <!--左侧导航-->
        <#include "aside.ftl" />
        <!--End 左侧导航-->

        <!--头部信息-->
        <#include "header.ftl" />
        <!--End 头部信息-->

        <!--页面主要内容-->
        <main class="lyear-layout-content">

            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">

                                <div class="row">

                                    <div class="form-group col-md-12">
                                        <label for="employee-name">员工名称</label>
                                        <input type="text" class="form-control" id="employee-name" name="employee-name" disabled />
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="employee-loginName">手机号(账号)</label>
                                        <input type="text" class="form-control" id="employee-loginName" name="employee-loginName" disabled/>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="department-select">所属部门</label>
                                        <select class="form-control" id="department-select" name="department-select" disabled>

                                        </select>
                                    </div>
                                    <div class="form-group col-md-12">
                                        <label for="position-select">职位</label>
                                        <select class="form-control" id="position-select" name="position-select" disabled>

                                        </select>
                                    </div>

                                    <div class="form-group col-md-12">
                                        <label for="status">状态</label>
                                        <div class="clearfix">
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input id="status-off" type="radio" name="status" value="0" disabled><span>禁用</span>
                                            </label>
                                            <label class="lyear-radio radio-inline radio-primary">
                                                <input id="status-on" type="radio" name="status" value="1" disabled><span>启用</span>
                                            </label>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-12">
<#--                                        <button type="submit" class="btn btn-primary ajax-post" target-form="add-form">确 定</button>-->
                                        <button type="button" class="btn btn-primary" onclick="javascript:history.back(-1);return false;">返 回</button>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>

            </div>

        </main>
        <!--End 页面主要内容-->
    </div>
</div>

<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/perfect-scrollbar.min.js"></script>
<!--标签插件-->
<script src="/js/jquery-tags-input/jquery.tagsinput.min.js"></script>
<script type="text/javascript" src="/js/main.min.js"></script>
<script type="text/javascript" src="/js/admin/employee-operation.js"></script>
<script type="text/javascript" src="/js/admin/logout.js"></script>
<script type="text/javascript" src="/js/admin/common.js"></script>
</body>
</html>