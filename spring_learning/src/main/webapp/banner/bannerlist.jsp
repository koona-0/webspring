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
				<th><input type="checkbox" id="allck" onclick="check_all(this.checked)"></th>
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
		
		<!-- 반복문 안에는 절대 id로 같은 이름 사용 불가능 => class(ajax전송), name(form전송) 사용 -->
		<!-- 고수는 data-value 이런 맘대로 쓴 속성으로 핸들링 -->
		
			<cr:forEach var="bn" items="${all}" varStatus="idx">
				<tr height="50">
				<!-- 게시판번호 x DB에 저장된 auto_increment 값 -->
					<td><input type="checkbox" name="ckbox" value="${bn.bidx}" onclick="checkdata()"></td>
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
	<!-- 
	1. forEach => form => 동일한 name => post 전송 => 배열로 받음
	2. form => 하나의 hidden을 이용 => post 전송 => 자료형 한개로 받음 
	주의 : 반복문 속 form, 반복문 속 id => 절대 X
	 -->
	<form id="dform" method="post" action="./bannerdel">
	<input type="hidden" name="ckdel" value="">
	</form>
	<input type="button" value="선택삭제" onclick="check_del()">
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

//체크박스 전체선택 함수 
//getElements : name // class getElement : id
function check_all(ck){	//ck : true, false
	var ea = document.getElementsByName("ckbox");
	
	var w= 0;
	while(w < ea.length){
		ea[w].checked = ck;
		w++;
	}
	//위에거 길게쓰면 아래 코드 
	/*
	if(ck == true){	//전체선택한 경우 
		var w= 0;
		while(w < ea.length){
			ea[w].checked = true;
			w++;
		}
	}else{	//전체선택 해제한 경우 
		var w= 0;
		while(w < ea.length){
			ea[w].checked = false;
			w++;
		}
	}
	*/
}

//하나라도 체크 해제시 전체선택 체크 해제 
function checkdata(){
	
}

//선택삭제 버튼 클릭시 리스트에서 체크된 값을 확인 후 배열화하여 hidden에 값을 적용하여 Back-end에 문자열로 전달 
function check_del(){
	var ar = new Array();	//script 배열 
	
	var ob = document.getElementsByName("ckbox");
	var w = 0;
	while(w < ob.length){
		if(ob[w].checked == true){
			ar.push(ob[w].value);	
		}
		w++;
	}
	dform.ckdel.value = ar;	//배열이 자동으로 문자열로 변해서 들어감 value="9,8,7,6,5"
	
	if(confirm('해당 데이터를 삭제시 복구되지 않습니다.')){
		dform.submit();
	}
	
}
</script>
</html>