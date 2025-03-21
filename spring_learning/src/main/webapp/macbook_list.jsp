<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과정 개설 리스트</title>
</head>
<body>
<p>개설된 과목 갯수 : ${ea }</p>
<!-- DTO에 있는 변수명으로 JSTL로 출력하는 형태 -->
<cr:forEach var="cdata" items="${classList }">
과정명 : ${cdata['class_name']}, 
강사명 : ${cdata.class_teacher}, 
수강료 : ${cdata.class_price}
<input type="button" value="삭제">
<br>

</cr:forEach>

</body>
</html>