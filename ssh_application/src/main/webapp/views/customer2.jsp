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
            $("#customerId").datagrid({
                url: "${pageContext.request.contextPath}/customer_dataGridPaging.action",//返回json数据action
                columns:[[
                    {field:"custName",title:'客户名称',width:200},
                    {field:"custPhone",title:'客户电话',width:200},
                    {field:"custSource",title:'客户来源',width:200}

                ]],
                pagination:true //是否显示分页

            });


        })


    </script>

</head>

<body>
    <table id="customerId"></table>
</body>
</html>
