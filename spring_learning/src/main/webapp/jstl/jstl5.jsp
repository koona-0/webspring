<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초 문법5 - 데이터베이스 연결</title>
</head>
<body>

<!-- JSTL DB는 보안 다뚫려서 안씀 -->

<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver" 
url="jdbc:mysql://localhost:3306/mrp" user="project" password="a123456"/>
<!-- ddl1 -->
<%-- 
<sql:query var="ps" sql="select * from event" dataSource="${db}"></sql:query>
--%>

<!-- 
ddl2 
게시판의 형태가 모두 같고 테이블만 다르다면 아래처럼 사용 (인자값을 이용)
-->
<cr:set vat="tables" value="event"/>
<sql:query var="ps" dataSource="${db}">
select * from ${tables}
</sql:query>

<!-- 
rows : Database의 rows의 전체값 (DB select 결과뜰때 밑에 뜨던거)
fn : 결과값에 옵션 형태로 함수를 적용하여 결과에 대한 사항을 변화 시킴 (태그가 없음) 
	fn:substring() 이런식으로 fn 함수는 $안에 직접 사용 !!
-->
<cr:forEach var="row" items="${ps.rows}">
고객명 : ${row['ename']}, 이메일 : ${row['email']}, 등록일 : ${fn:substring(row['ejoin'],0,10)}<br>
</cr:forEach>

</body>
</html>