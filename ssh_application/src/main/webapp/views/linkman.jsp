<%--
  Created by IntelliJ IDEA.
  User: Oxygen
  Date: 2018/11/13
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理系统</title>
    <link href="${pageContext.request.contextPath}/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/themes/icon.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.1.2.6.min.js"></script>

    <script type="text/javascript">
        $(function () {
            $("#linkmanId").datagrid({
                url: "${pageContext.request.contextPath}/linkMan_findAllLinkManByJson.action",//返回json数据action
                columns:[[
                    {field:"lkmName",title:'联系人名称',width:200},
                    {field:"lkmPhone",title:'联系人电话',width:200},
                    {field:"lkmId",title:'联系人Id',width:200},
                    {field:"customerEntity",title:'所属客户',width:200,formatter:function (value, row, index) {

                        if(row.customerEntity){
                            return row.customerEntity.custName;
                        }


                    }}

                ]],
                pagination:true //是否显示分页

            });


        })


    </script>

</head>

<body>
    <table id="linkmanId"></table>
</body>
</html>
