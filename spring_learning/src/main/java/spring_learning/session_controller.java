package spring_learning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//Spring Session 사용방법 

//컨트롤러의 모든 메소드에 session 적용 (DTO있을 경우 사용) 
//@SessionAttributes("mid")	
//@SessionAttributes : Controller에서 셋팅된 값이며, DTO가 있어야 정상적으로 핸들링 가능 
//DTO형태가 Session 형태의 DTO, @SessionAttributes => API Server
//해당 세션이 생성되었을 경우 모든 메소드에 세션값을 Model로 전송가능  
public class session_controller {
	
	/*
	//이런식으로 사용 
	@ModelAttribute("mid")
	public macbook_DTO mid_data(HttpSession hs) {
		return null;
	}
	*/
	
	//Session을 의존성 주입 형태로 interface를 필드에 선언하여 모든 메소드에 session 적용 가능 
	@Autowired
	HttpSession hs;
	
	@GetMapping("/session1.do")
	public String session1(HttpSession se) {	//HttpSession(Spring)
//	public String session1(HttpServletRequest res) {
//		HttpSession se = res.getSession();	//고전, servlet형태 
		
		String userid = "kim";
		se.setAttribute("mid", userid);
		
		return null;
	}
	
	//HttpSession : Controller, DAO, DTO, VO 모든곳에서 사용 가능 
	//해당 세션을 생성 후 문자열로 변환하여 모델로 전달 => jsp에 출력 
	@GetMapping("/session2.do")
	public String session2(HttpSession se, Model m) {
		String id = (String)se.getAttribute("mid");
		System.out.println(id);
		
		m.addAttribute("mid", id);
		return "session";
	}
	
	//@SessionAttribute = session.getAttribute과 동일한 성능을 가진 어노테이션 
	//해당 어노테이션 사용시 주의사항 : null일경우 400에러 발생
	//name 속성, required = false 속성을 핸들링하기  
	@GetMapping("/session3.do")
	public String session3(@SessionAttribute(name = "mid", required = false)String mid) {	//어노테이션으로 가져온경우 문제 (required없으면 ): 삭제된경우 에러남 
		System.out.println(mid);
		String test = (String)this.hs.getAttribute("mid");	//필드에 있는 세션 
		System.out.println(test);
		
		return null;
	}
	
	@GetMapping("/session4.do")
	public String session4(@SessionAttribute("mid")String mid) {
		this.hs.invalidate();	//세션 초기화	//필드에 올려놓은 session을 로드하여 세션 초기화
		
		this.hs.removeAttribute("mid");	// 등록된 특정 session 키만 삭제 
		System.out.println(this.hs.getAttribute("mid"));
		return null;
	}

}
