function eventok(){
	if(f.ename.value==""){
		alert("고객명을 입력하세요!");
	}else if(f.etel.value==""){
		alert("연락처를 입력하세요");
	}else if(f.email.value==""){
		alert("이메일을 입력하세요");
	}else if(f.ememo.value==""){
		alert("이벤트 참여 이유를 입력하세요");
	}else if(f.info1.checked == false){
		alert("개인정보활용에 동의하셔야 이벤트참여가 가능합니다");
	}else if(f.info2.checked == false){
		alert("제 3자의 정보제공에 동의하셔야 이벤트참여가 가능합니다");
	}else{
		let ck = /^\d{2,3}\d{3,4}\d{4}$/;	//슷자 외에 다른 문자,길이 9~11이 아닐 경우 false
		if(ck.test(f.etel.value)==false){
			alert("연락처를 정상적으로 입력하세요");
		}else{
			f.submit();		
		}		
	}
}