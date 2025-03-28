package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//실무처럼 쓴 컨트롤러!! 캬~ 
@Controller
public class banner_controller {
	
	List<String> listdata = null;
	Map<String, String> mapdata = null;
	PrintWriter pw = null;
	String result = null;	//select 결과 
	int callback = 0;		//update, delete, insert 결과 
	ModelAndView mv = null;

	//Field(this. 사용)의 dto와 매개변수(그냥사용)의 dto는 다름 !!!
	@Resource(name="banner_DTO")
	banner_DTO dto;
	
	@Resource(name="banner_DAO")
	banner_DAO dao;
	
	@Resource(name="file_rename")
	m_file_rename fname;	//파일명ㅇㄹ 개발자가 원하는 형태로 변경 
	
	//배너 등록 (.do안써도됨) do쓰는 이유는 그냥 암묵적인룰? 2~3년차되면 잘 안씀 
	@PostMapping("/banner/bannerok")	//경로 잘 맞추기 
	public String bannerok(
			@ModelAttribute(name="dto") banner_DTO dto,
			MultipartFile bfile,
			HttpServletRequest req
			) throws Exception{
		//@RequestParam(name="dto", required = false) 이거는 잘못된 코드 => int + String 섞여있어서 안됨
		//=> 
		//@ModelAttribute : dto 전용 어노테이션 (근데 안써도됨 ㅋ) 부트에서 많이 쓰임 Spring에서는 딱히 .. 
		//			장점 : 1대1 매칭 => name과 DTO 자료형 변수가 같은것이 있으면 무조건 값을 setter 발동 
		
		String file_new = null;

		//날짜
		if(bfile.getSize() > 0) {
			String url = req.getServletContext().getRealPath("/upload/");
//			System.out.println(url);
			
			file_new = this.fname.rename(bfile.getOriginalFilename());
			FileCopyUtils.copy(bfile.getBytes(), new File(url + file_new));
			
			dto.setFile_url("/upload/" + file_new);		//웹디렉토리경로 및 파일명  
			dto.setFile_new(file_new);		//개발자가 원하는 방식으로 변경한 파일명 
			dto.setFile_ori(bfile.getOriginalFilename());	//사용자가 적용한 파일명 
		}
		
		this.callback = this.dao.new_banner(dto);	//this.dto와 dto는 다름 주의!!!
		System.out.println(this.callback);
		
		return null;
	}
	
	//search 검색에 관련사항은 필수조건은 아니며, 또한 null일경우 공백처리 
	@GetMapping("/banner/bannerlist")	//경로 잘 맞추기 
	public String bannerlist(Model m, 
			@RequestParam(name="search", defaultValue = "", required = false) String search,
			@RequestParam(name="pageno", defaultValue = "1", required = false) Integer pageno) {
		
		//페이징
		//페이징한다고 쿼리문 따로 만들지않고 전체출력을 꾸며서 쓰는것이 좋음 !!!!!
		
		//리스트 총개수확인 
		int total = this.dao.banner_total();
		System.out.println(total);
		
		//사용자가 클릭한 페이지 번호에 맞는 순차번호 계산값 
		int userpage = (pageno - 1) * 5;
		m.addAttribute("userpage",userpage);
		
		
		//검색 
		//search 의 값이 없을때 디폴트 "", 필수 X로 받겠다는 뜻 => 안쓰면 400! 
		List<banner_DTO> all = null;
		
		if(search.equals("")) {		//연산기호 X, equals
			System.out.println("검색어없음");
			all = this.dao.all_banner(pageno);	//사용자가 클릭한 페이지 번호값 전달 
		}else {
			all = this.dao.banner_search(search);
		}
		
		m.addAttribute("total",total);
		m.addAttribute("search",search);
		m.addAttribute("all",all);
		
		return null;
	}
	
}