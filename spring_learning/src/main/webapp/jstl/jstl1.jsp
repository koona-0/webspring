<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl 엔진 -->
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jstl 각종함수 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- jstl Database 관련사항 (컨트롤러없이 sql실행가능) -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!-- jstl 부가옵션 (날짜찍기,금액쉼표찍기, 시간, 통화기호 등등) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
prefix 맘대로 써도됨 
uri 컨스페써서 넣으면됨 

페이지에 해당하는 필요한것만 가져오기 핵심은 core는 꼭 가져오기 (원본엔진)
나머지는 부가엔진 써도되고 안써도됨 
 --%>
 
 <%
 String user = "홍길동";	//JSP 변수
 
 //jsp 세션 생성 
 HttpSession hs = request.getSession();
 hs.setAttribute("ssdata", "15881004");
 
 //jsp 세션 출력 
 String se = (String)hs.getAttribute("ssdata");
 out.print(se);
 
 String tels = (String)hs.getAttribute("tel");
 out.print(tels);
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초 문법1 (set + session)</title>
</head>
<body>
<!-- 
JSTL 태그 형태
core 태그 속성 중 set 은 setAttribute 와 동일한 역할 
					var : 변수 생성 | value : 값 
-->
<cr:set var="a" value="강감찬"/>
<input type="text" name="mname" value="${a}">
<cr:set var="nm" value="<%=user %>"/>
고객명 : ${nm}
<cr:out value="값 출력합니다"/>	<!-- out.print() 역할 (실무에서 안씀) -->
<br><br><br>

<p>JSP Session 값</p>
<!-- JSTL로 세션을 생성하는 방식 scope : session, request, page -->
<cr:set var="tel" value="0210041004" scope="session"/>
JSP 세션 데이터 : ${ssdata} <br>
JSTL 세션 데이터 : ${tel}

<!-- 
세션만들때 주의할 점
같은 이름 주의
JSP의 세션, JSLT의 세션 이름 안겹치게 조심하기 
 -->
 
 <br><br><br>
 <%
 String money = "50000";
 %>
<cr:set var="kk" scope="request"><%=money %></cr:set>
${kk}
<%--
아래네줄은 다 같은 코드임 ! 
디폴트값이 request임 
<cr:set var="kk" scope="request"><%=money %></cr:set> 
<cr:set var="kk"><%=money %></cr:set>
<cr:set var="kk" value="<%=money %>"></cr:set>
<cr:set var="kk" value="<%=money %>"/>  
--%>
</body>
</html>