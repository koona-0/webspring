//.js 파일에서 Back-end 코드를 사용할 수 없음
//var subject = "${onedata.class_part}"; 이렇게 쓰면 문자 그대로 출력됨 

function save_class(){
	if(frm.class_name.value==""){
		alert("과정명을 입력하세요");
	}else if(frm.class_day.value==""){
		alert("학습일수를 입력하세요");
	}else if(Number(frm.class_price.value) < 0 || frm.class_price.value==""){
		alert("정가를 입력하세요");
	}else if(Number(frm.class_sales.value) < 0 || frm.class_sales.value==""){
		alert("수강료를 입력하세요");
	}else if(frm.class_info.value==""){
		alert("과정소개를 입력하세요");
	}else if(frm.class_teacher.value==""){
		alert("강사명을 입력하세요");
	}else if(frm.class_object.value==""){
		alert("학습목표를 입력하세요");
	}else {
	frm.submit();
	}
}