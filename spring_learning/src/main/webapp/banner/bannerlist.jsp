<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배너 리스트 페이지</title>
</head>
<body>

<!-- 
서브밋 Ajax 안씀! 위험 더블이벤트 발생 입력없는데 통신 이런거 발생 절대 X
버튼에만 Ajax 씀! 
 -->
<!-- 하나의 컨트롤러 메소드에 다해도됨 -->
<form id="sform" method="get" action="./bannerlist" onsubmit="return spage()">
<p>
배너명 검색 : <input type="text" name="search" value="${search}">
<input type="submit" value="검색">
<input type="button" value="전체목록" onclick="location.href='./bannerlist';">
</p>
</form>
	<p>전체 등록된 배너 개수 : ${total} 개</p>
	<table border="1" cellpadding="0" cellspacing="0">
		<thead>
			<tr>
				<th><input type="checkbox"></th>
				<th width="80">번호</th>
				<th width="300">배너명</th>
				<th width="100">이미지</th>
				<th width="150">파일명</th>
				<th width="150">등록일</th>
			</tr>
		</thead>
		<%-- 
		배열 값을 조건문으로 jstl에 처리시 functions 이용하여 length로 검토하여 처리함 
		<cr:if test ="${fn:length(all)==0}">
		아래와 동일한 코드 
		 --%>
		<cr:if test ="${all.size()==0}">
		<tbody>
		<tr>
		<td colspan="6" align="center">검색된 데이터가 없습니다.</td>
		</tr>
		</tbody>
		</cr:if>
		
		<tbody>
		<cr:set var="ino" value="${total - userpage}"/>	<!-- 게시물 일련번호 셋팅 -->
			<cr:forEach var="bn" items="${all}" varStatus="idx">
				<tr height="50">
					<td><input type="checkbox"></td>
					<td align="center">
					${ino - idx.index}
					
					</td>
					<td>${bn.bname }</td>
					<td>
					<cr:if test="${bn.file_url == null}">
						NO IMAGE					
					</cr:if> 
					<cr:if test="${bn.file_url != null}">
						<img width="100" src="..${bn.file_url }">
					</cr:if>
					</td>
					<td align="center">
					<a href="../upload/${bn.file_new}" target="_blank" title="${bn.file_new}">${bn.file_ori}</a>
					</td>
					<td align="center">${fn:substring(bn.bdate,0,10)}</td>
				</tr>
			</cr:forEach>
			<!-- 
			이미지 엑박뜨는 이유 : 경로 다름
			
			http://localhost:8080/spring_learning/banner/bannerlist
			여기가 현주소인데 여기서 img src="bn.file_url" 이래쓰면 
			http://localhost:8080/upload/20250327311.jpeg  
			여기로옴
			
			근데 여기로 와야함 
			http://localhost:8080/spring_learning/upload/20250327311.jpeg
			
			img src="..bn.file_url"이래 쓰기 
			 -->
		</tbody>
	</table>
	<br><br>
	
	<!-- form 전송으로 선택된 값을 삭제하는 프로세서 -->
	<form id="dform">
	<input type="hidden">
	</form>
	<input type="button" value="선택삭제">
	<br><br>
	
	<!-- pageing -->
	<table border="1" cellpadding="0" cellspacing="0">
	<tbody>
	<tr height="30">
	
	
	<!-- 
	Controller에서 데이터의 전체 갯수를 받음 
	해당 값을 한페이지당 5개씩 출력하는 구조 
	 -->
	<!-- 
	total / 5 + (1 - ((total / 5) % 1)) % 1
		- total / 5 → 몇 페이지가 필요한지 계산 (소수점 포함)
		- (total / 5) % 1 → 남은 소수점이 있는지 확인
		- 1 - ... → 소수점이 있으면 1을 더하기 위한 처리
		- % 1 → 0 또는 1로 만들기 위한 트릭
		//(1 - ((total / 5) % 1)) % 1 => 나머지가있으면 1페이지 추가 
	 -->
	<cr:set var="pageidx" value="${total / 5 + (1-((total / 5) % 1)) % 1}"/>
		<cr:forEach var="no" begin="1" end="${pageidx}" step="1">
		<td width="30" align="center" onclick="pg('${no}')">${no}</td>
		</cr:forEach>
	
	</tr>
	</tbody>
	
	</table>
	
	
	
</body>
<script>
var spage = function(){
	if(sform.search.value==""){
		alert("배너명을 입력하세요");
		return false;
	}else{
		return;	
	}
}

function pg(no){
	location.href='./bannerlist?pageno='+no;
}
</script>
</html>