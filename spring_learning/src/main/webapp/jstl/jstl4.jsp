<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- JSTL은 배열을 만드는 애가 없음 => JSP 이용 --%>
<%
String member[][] ={
		{"홍길동","강감찬","유관순"},
		{"A형","B형","O형"},
		{"24","43","15"}
};
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초 문법4 - (2차 반복문)</title>
</head>
<body>

<%-- v
arStatus : 배열번호 0~ => 이름.index 
--%>
<cr:set var="bb" value="<%=member[2]%>"></cr:set>
<cr:set var="aa" value="<%=member[1]%>"></cr:set>	<%-- 각 배열의 그룹번호 --%>
<cr:forEach var="cc" items="<%=member[0]%>" varStatus="no">
고객명 : ${cc}, 혈액형 : ${aa[no.index]}, 나이 : ${bb[no.index]}<br>
</cr:forEach>

</body>
</html>