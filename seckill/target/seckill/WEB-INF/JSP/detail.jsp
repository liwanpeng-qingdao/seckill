<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="commen/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default text-center">
        <div class="panel-heading ">
            <h1> ${seckill.name}</h1>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <span class="glyphicon glyphicon-time"></span>
                <span class="glyphicon" id="seckill-box"></span>
            </h2>
        </div>
    </div>
</div>
<div class="modal fade" id="killPhoneMod">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title text-center">请填入手机号
                    <span class="glyphicon glyphicon-phone"></span>
                </h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" name="killPhone" id="killPhoneKey" placeholder="填手机号"
                               class="form-group "/>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <span id="killPhoneMessage" class="glyphicon">

                </span>
                <button type="button" id="killPhoneBtn" class="btn btn-success">
                    <span class="glyphicon"></span>
                    Sumbmit
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<script src="//cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>

<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<%--jquery中的cookie操作插件--%>
<script src="//cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<%--jquery 倒计时插件--%>
<script src="//cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>
<%--交互逻辑--%>
<script src="/resource/script/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        seckill.deatail.init({
                seckillId :${seckill.seckillId},
                startTime:${seckill.startTime.time},
                endTime:${seckill.endTime.time}
            });
    })
</script>
</html>
