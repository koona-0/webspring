package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import etc_model.md5_pass;

//md5_pass : abstract(추상 클래스이며, 해당 Controller에 직접 핸들링하여 사용)
//extends md5_pass
@Controller
public class macbook_controller {	//추상클래스는 속도가 매우 빠름! 가상의 클래스여서 공격도 못들어옴 
	
	@Resource(name="macbook_member_DTO")
	public macbook_member_DTO dto;
	
	@Resource(name="user_DAO")
	public user_DAO dao;
	
	@Resource(name="md5_pass")
	private md5_pass md;
	
	
	//전체 데이터 리스트 가져오기(회원정보)
	@GetMapping("/macbook_user.do")
	public String macbook_user() {
		List<macbook_member_DTO> all = this.dao.all_list();
		System.out.println(all.get(0).memail);
		return null;
	}
	
	//아이디 찾기 (체크박스 : 체크된 경우(Y), 체크 안된경우(N)
	/*
	@RequestParam : DTO에 없는 name을 처리할 때 주로 사용 
		defaultValue 속성 : null로 전송되었을 경우 발동되는 속성
		required 속성 : false일때 front에서 name값을 보내지 않아도 처리가 되도록 설정 
						true일때 (기본값) 필수로 무조건 name값 처리함 
	*/
	@PostMapping("/idsearch.do")
	public String idsearch(Model m, macbook_member_DTO dto, 
			@RequestParam(defaultValue = "N", required = false) String mcheck) {
		System.out.println(mcheck);
		
		try {
			String data = this.dao.user_search(dto.mname, dto.memail);
			System.out.println(data);
			
			String msg="";	//출력할 결과 전달할 변수 
			if(data == null) {
				msg = "찾고자하는 아이디가 확인되지 않습니다.";
			}else {
				msg = data;
			}
			m.addAttribute("msg", msg);
		}catch (Exception e) {
			System.out.println("에라");
		}

		return "/WEB-INF/info/idsearch";
	}
	
	//MD5로 데이터를 변환하는 형태의 Controller
	//GetMapping이랑 메소드 이름이랑 맞추는 이유 : 충돌 방지, 메소드 빨리 찾기  
	@GetMapping("/macbook_login.do")
	public String macbook_login() {
		String pw = "a123456";
		//md5로 사용자 패스워드를 변환하여 회신받음 
		
		//@Resource 사용한 경우 
		String result = this.md.md5_make(pw);
		
		//추상클래스를 사용한 경우 
//		String result = this.md5_make(pw);
		System.out.println(result);
		
		
		return null;
	}
}











