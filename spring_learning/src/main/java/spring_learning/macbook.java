package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class macbook {
	/*
	//xml정보 끌고오는 애들 Autowired, Inject
	 
	@Autowired @Inject : 의존성 주입 XML=>Java, Java=>XML

	@Autowired
	SqlSessionFactory sqlfact;
	 */
	
	/*
	// ibatis로 연결 
	@Inject
	SqlSessionFactory sqlfact;
	*/
	
	/*
	컨트롤러의 리소스 네임과 DAO의 레포지토리 이름이 같으면 됨 !
	*/
	//@Resource : new 클래스명 호출과 동일하게 작동, @Repository의 이름을 가져오는 역할 
	//@Resource와 @Repository는 영혼의 단짝친구
	@Resource(name="macbook_DAO")	//resource는 new의 역할 
	private macbook_DAO dao;	//하나의 모델인데 new 안써도 됨 
	
	//그럼 이렇게 DTO를 쓰면 this.를 이용해서 DTO를 사용 가능하게됨 !
	//대신 매개변수와 변수 이름이 같으면 에러가 날 수도 있음 주의 !!
	@Resource(name="macbook_DTO") 
	private macbook_DTO mdto;
	
	//과정 리스트 출력
	@GetMapping("/macbook_list.do")
	   public String macbook_list(Model m) {
		//List<macbook_DTO> : DTO 형태의 배열로 생성하여, JSP로 전달  
	      List<macbook_DTO> classList = this.dao.macbook_select();
	      System.out.println(classList.get(0).class_name);
	      m.addAttribute("ea", classList.size());
	      m.addAttribute("classList",classList);
	      return null;
	   }
	
	
	//개설된 과정을 삭제하는 메소드 
	
		
	//과정개설 메소드 
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto, Model m) throws Exception{		
		try {
			//dao : 쿼리문을 작동시키는 놈 
			int result = this.dao.macbook_in(dto);
			String msg = "";
			if(result > 0) {
				msg = "alert('과정이 올바르게 개설되었습니다.');"
						+ "location.href='macbook_list.do';";
			}
			m.addAttribute("msg", msg);			
		}catch (Exception e) {
		}
		return "load";
	}
}