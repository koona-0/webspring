<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%
//String data[] = new String[] {"hong","kim","park"};

//request.setAttribute("data", data);

ArrayList <String> data = new ArrayList();
data.add("a");
data.add("b");
data.add("c");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초 문법3 (반복문 - foreach)</title>
</head>
<body>

<table border="1">
<tr>
<!-- 
forEach : JSTL 유일하게 배열 및 반복문으로 사용하는 속성 
var : 변수 
begin : 시작값
end : 종료값
-->

<%--
forEach 기본 형태 
<cr:forEach var="z" begin="1" end="5">
<td>${z}</td>
</cr:forEach>
--%>
 
<%--
items : 배열값을 받는 속성
	${data} setAttribute의 값
	<%=data%> : jsp 변수명
	
배열값을 사용하여 출력하는 역할
	begin = "0" 으로 시작!
	end 범위 더 크게 잡아도 에러 안남! 오 놀라워라 
--%> 
<cr:forEach var="z" items="<%=data%>" begin="0" end="5">

<cr:if test="${z !='b' }">
<td>${z}</td>
</cr:if>

</cr:forEach>

<%--
setAttribute로 가져온 경우 
<cr:forEach var="z" items="${data}">
<td>${z}</td>
</cr:forEach>
 --%>

</tr>
</table>

</body>
</html>