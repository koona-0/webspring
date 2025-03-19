<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초 문법2 (if문)</title>
</head>
<body>

<!-- result : true, false 결과 -->
<cr:if test="${5 > 10}" var="result"></cr:if>	
${result}
<br>

<%--
jstl if문 (lt(<), gt(>), le(<=), ge(>=)

지금 이 변수는 String 형태임 core에서는 숫자비교 불가능
fmt 불러와서 조절해야함
아래 줄로 숫자로 변경 가능  

<fmt:formatNumber> : 숫자를 양식에 맞춰 문자열로 변환
<fmt:parseNumber> : 문자열을 숫자(Number 타입)로 변환
--%>
<cr:set var="a" value="30"/>
<fmt:parseNumber value="${a}" type="number" var="aa"/>
 
<cr:set var="b" value="200"/>
<fmt:parseNumber value="${b}" type="number" var="bb"/>

<cr:if test="${aa gt bb}">
a 값이 큽니다.
</cr:if>
<cr:if test="${aa lt bb}">
b 값이 큽니다.
</cr:if>
<cr:if test="${aa eq bb}">
동일한 값입니다.
</cr:if>
<br><br><br>

<cr:set var="product" value="그래픽카드"/>
<cr:set var="product2" value="모니터"/>

<%-- 
아래 두줄은 같은 코드 
<cr:if test="${product == '그래픽카드'}">
<cr:if test="${product eq '그래픽카드'}">

eq(==), ne(!=), or(||), and(&&) 모두 쓸 수 있음 
걍 연산기호 다 때려도됨 
--%>


<cr:if test="${product eq '그래픽카드' and product2 eq '모니터'}">
가격미정
</cr:if>
<br><br><br>

<%-- 
choose, when, otherwise
--%>
<cr:set var="agree" value="Y"/>

<!-- 조건문 시작 설정 choose안에 주석쓰면 고장남;;when안은 ㄱㅊ -->
<cr:choose> 
<cr:when test="${agree eq 'Y'}"> <!-- if -->
약관에 동의함  
</cr:when>
<cr:when test="${agree eq 'N'}"> <!-- else if -->

약관에 동의 안함 
</cr:when>
<cr:otherwise> <!-- else -->
해당 약관정보를 확인못함 
</cr:otherwise>
</cr:choose>



</body>
</html>














