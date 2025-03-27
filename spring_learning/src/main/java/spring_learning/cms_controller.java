package spring_learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class cms_controller {
	
	@Resource(name = "template")
	public SqlSessionTemplate st;
	
	@PostMapping("/cmsok.do")
	public String cmsok(
			@RequestParam String csubject,
			@RequestParam String cuser,
			@RequestParam (name="cate", required = false) ArrayList<String> cate
			) throws Exception{	
		//여기서 cate는 배열로 [~,~,~] 들어오는데 DB에는 ~,~,~ 이런식으로 들어가야함 
		
//		String catein = cate.toString();	//toString 해도 배열형임 => 포스트그레는 그냥 들어가짐 
		
		//ArrayList<String> 클래스 배열로 동일한 체크박스 처리함 
		//체크박스가 동일한 이름에 여러개가 있을 경우 배열로 값을 받으며 DB에 저장시
		//String 변환하여 String.join() 클래스를 이용하여 DB set이라는 자료형으로 저장 
		String catein = String.join(",", cate);		//이렇게하면 우리가 원하던 DB에 넣을수있는 형태로 만들어짐 
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("csubject", csubject);
		data.put("cuser", cuser);
		data.put("cate", catein);
		
		//mapper.xml에 다른 Table을 사용하더라도 문제없음
		//단점 : 유지보수쓰레기
		int result = this.st.insert("macbook_user.cms_in", data);
		System.out.println(result);
		
		return null;
	}
	
	// CMS 상담신청 내역 상세페이지
	@GetMapping("/cms/cmsview.do")
	public String cmsview(Model m) {
		//데이터 그룹 한개만 가져옴 
		//mapper에서 resultType String으로 처리했을 경우는 하나의 컬럼 값에 대해서만 처리할 때 사용가능 
		//단, 여러개의 컬럼으로 처리시에는 첫번째 컬럼 외에 모두 loss남 (예시 : count, avg, sum, max, min)
		
		//결과 하나 받을 때 
		//String result = this.st.selectOne("macbook_user.cms_views",null);
		
		//여러 컬럼 결과 받을 때 
		//Map<String, Object> result = this.st.selectOne("macbook_user.cms_views",null);

		//mapper.xml에 Map으로 받는다써둬도 리스트로 받기 가능 
		//selectList 결과가 List라서 하위리스트들로 못받음 => 리스트로 받기 
		//List<String> result = this.st.selectList("macbook_user.cms_views");
		
		//이런 형식도 가능 
		List<Map<String, String>> result = this.st.selectList("macbook_user.cms_views");
		System.out.println(result.get(0).get("cate"));
		//맵을 사용하면 JSON처럼 보내져서 바로 사용가능함 프론트조아해 
		m.addAttribute("csubject",result.get(0).get("csubject"));
		m.addAttribute("cuser",result.get(0).get("cuser"));
		m.addAttribute("cate",result.get(0).get("cate"));
		
		return null;
	}
	
}