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
<input type="button" value="수정" onclick="macbook_modify('${cdata.midx}')">
<input type="button" value="삭제" onclick="macbook_del('${cdata.midx}')">
<br>
</cr:forEach>

<!-- 
POST : 해당 게시물의 고유값을 POST Backend 전송
		따로 폼만들어서 보내기
		이것도 보안뚫림 : 개발자도구에서 히든값 바꿀수있음 
		=> innerHtml 만들기
 -->
 
 <!-- 
 form으로 post전송
 form하나로 함수 두개 사용 => js에서 action값 넣기 
 -->
<form id="frm" method="post" action="">
<input type="hidden" name="midx" value="">
</form>
 
 <!-- 
 <div id="msg"></div>
  -->

</body>
<script>
function macbook_modify(n){
	//Post 통신 (form)
	frm.midx.value=n;
	frm.action="./macbook_modify.do";
	frm.submit();
	
	//실무용 POST 전송 
	//해커가 수정할 틈 없이 전송됨 -> 보안 good
	/*
	var m = document.getElementById("msg");
	m.innerHTML = `<form id="frm" action="./macbook_modify.do">
        <input type="hidden" name="midx" value="`+n+`">
        </form>`;
	frm.submit();
	*/
	
	//GET : 빠르지만 눈에 보임 
	//location.href='./macbook_modify.do?midx='+n;
	//데이터를 여러개 붙일 경우 (+'&data='+n1+...); 실수마니생김 
	//=> 그냥 form 으로 hidden 써서 get으로 넘겨도 됨 
}
function macbook_del(n){
	if (confirm('해당 과정을 삭제하시겠습니까?\n삭제시 데이터는 복구되지 않습니다.')) {
			frm.midx.value = n;
			frm.action = "./macbook_delete.do";
			frm.submit();
		}
	}
</script>
</html>