<%--
  Created by IntelliJ IDEA.
  User: WFX
  Date: 2018-02-26
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title></title>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script>
    <script type="text/javascript">
        var websocket = null;
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://localhost:8080/websocket/socketServer.do");
        }
        else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://localhost:8080/websocket/socketServer.do");
        }
        else {
            websocket = new SockJS("http://localhost:8080/sockjs/socketServer.do");
        }
        websocket.onopen = onOpen;
        websocket.onmessage = onMessage;
        websocket.onerror = onError;
        websocket.onclose = onClose;

        function onOpen(openEvt) {
            console.log(openEvt);
        }

        //服务器返回的数据
        function onMessage(message) {
            console.log(message.data);
        }

        function onError() {
            alert("错误");
        }

        function onClose() {}

        function doSendUser() {
            if (websocket.readyState == websocket.OPEN) {
                var msg = document.getElementById("inputMsg").value;
                websocket.send("#anyone#"+msg);//调用后台handleTextMessage方法
                console.log("发送成功!");
            } else {
                alert("连接失败!");
            }
        }

        function doSendUsers() {
            if (websocket.readyState == websocket.OPEN) {
                var msg = document.getElementById("inputMsg").value;
                websocket.send("#everyone#"+msg);//调用后台handleTextMessage方法
                console.log("发送成功!");
            } else {
                alert("连接失败!");
            }
        }
        window.close=function()
        {
            websocket.onclose();
        }
    </script>
</head>
<body>

请输入：<textarea rows="5" cols="10" id="inputMsg" name="inputMsg"></textarea>
<button onclick="doSendUser();">发送</button>
<button onclick="doSendUsers();">群发</button>
</body>
</html>