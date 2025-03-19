package spring_learning;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//Spring Controller + View 기초 

//Controller : 여러개의 컨트롤을 한번에 다 때려박을수 있음 
//@Controller : 해당 일반 class를 web에서 작동할 수 있도록 변경하도록 함 
@Controller
public class mainpage {
	
	PrintWriter pw = null;

	//@GetMapping : doGet
	//@PostMapping : doPost
	//@RequestMapping : doService 실무에서는 잘 안씀 (get+post) 보안쓰레기
	
	//throws + HttpServletRequest + HttpServletResponse : View를 사용하지 않음 
	@GetMapping("/abc.do")	//get, post를 어노테이션으로 사용 
	public void abc(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//인자 있으면 바로 이거실행 view없이 작동 
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		this.pw.write("<script>alert('어렵다 ㅎ..')</script>");
		this.pw.close();
		System.out.println("abc페이지");
	}

	//View 무조건 사용하는 메소드를 말함 
	//bbb.jsp를 찾음 없으면 404 뜸 
	//컨트롤 찾으면서 View를 찾아라 
	//인자 비어있으면 do찾으면서 view찾음 
	@PostMapping("/bbb.do")	
	public void bbb(HttpServletRequest req) {	
		//Front-end 값을 이관받음 
		String pdnm = req.getParameter("pdnm");
		//View(bbb.jsp)로 이관함 
		req.setAttribute("pdnm", pdnm);
	}
	
	//return 형태의 메소드는 View 파일명을 다르게 사용할 수 있음
	//기본은 return null : do와 이름을 같은 jsp를 찾게 됨
	//return "어쩌고"; : do와 다른 이름의 jsp를 찾게 됨 
	@PostMapping("/ccc.do")
	public String ccc(HttpServletRequest req) {
		String pdnm = req.getParameter("pdnm");
		req.setAttribute("pdnm", pdnm);
		return "/product_list";	//subfix 설정해둬서 .jsp붙이지 않아도 붙은것처럼 작동 	
		//return null이면 ccc.jsp를 찾음 
	}
	
	//request로 view(jsp)로 전달하는 방식이 아님 
	@PostMapping("/ddd.do")
	public ModelAndView ddd(HttpServletRequest req) {	//ModelAndView : 모델에 있는 값을 뷰로 전달		
		String pdnm = req.getParameter("pdnm");			
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		//ModelAndView (기본 자료형 Object 배열)
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("pdnm",pdnm);			//addObject : 키배열 형태로 값을 저장시킴 
		mv.addObject("pcode",pcode);
		mv.addObject("pmoney",pmoney);
		
		//setView : null은 Mapping 이름과 동일한 jsp를 찾음
		//mv.setView(null);
		//null이면 ddd.do 로 감 => 코드안쓴거랑 동일 
		//이거로 경로지정하려면 View 인터페이스를 사용햐야함 그래서 실무에서안씀 아래코드 사용 
		
		//다른 view로도 이동할 수 있음 
		//Mapping과 다른 이름을 사용하고 싶을 경우 
		mv.setViewName("bbb");	
		 
		return mv;	//무조건 ModelAndView 객체명을 사용해야함 
	}
	
	@PostMapping("/eee.do")
	public String eee(HttpServletRequest req, Model m) {
		String pdnm = req.getParameter("pdnm");			
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		//Model(interface)를 이용하여 JSP로 값을 전달 (JSTL형태로 값 출력)
		m.addAttribute("pdnm",pdnm);
		m.addAttribute("pcode",pcode);
		m.addAttribute("pmoney",pmoney);
		
		return "ddd";
	}
	
}
