<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ include file="commen/tab.jsp" %>
<%@ include file="commen/head.jsp" %>
<head>
    <title></title>
</head>

<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h2>秒杀列表</h2>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <th>名称</th>
                <th>库存</th>
                <th>开始时间</th>
                <th>结束时间</th>
                <th>创建时间</th>
                <th>详情页</th>
                </thead>
            <tbody>
            <c:forEach var="sk" items="${list}">
                <tr>
                    <td>${sk.name}</td>
                    <td>${sk.number}</td>
                    <td>
                        <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                    </td>
                    <td>
                        <a class="btn btn-info" href="/seckill/${sk.seckillId}/detail" target="_blank">link</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            </table>

        </div>
    </div>

</div>
</body>
</html>
