package spring_learning;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

// I/O Controller
//원래 서블릿할땐 파일업로드용 어노테이션 썼었는데 지금은 commons-fileupload 라이브러리 불러와서 안써도 가능 
@Controller
public class mainpage3 {

	// MultipartFile : Spring I/O = xml 환경설정과 연결
	@PostMapping("/fileok.do")
	public String fileupload(MultipartFile mfile) {
		if (mfile.getSize() > 2097152) {
			System.out.println("test");
		}

		System.out.println(mfile.getOriginalFilename());
		return "load";
	}

	
	// 여러개의 첨부파일을 받는 메소드 => 파일을 "배열"로 받기
	/*
	MultipartFile[] : Interface로 파일을 Front-end에서 받을 경우
	반복문으로 처리시 multiple로 전송할 경우는 별도의 조건문 없이 저장이 가능
	단, 같은 name으로 여러개의 파일전송 속성을 사용하였을 경우 반복문 사용시
	조건문이 없을 경우 500 에러로 인하여 문제 발생함 
	FileCopyUtils.copy : 파일 전송 관련 I/O이며, Spring, Spring-boot에서 사용 
	 */
	@PostMapping("/fileok2.do")
	public String fileupok(MultipartFile[] mfile, HttpServletRequest req) throws Exception{
		String url = req.getServletContext().getRealPath("/upload/");
//		System.out.println(url);
//		/Users/nayeong/Documents/projects/webspring/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/spring_learning/upload/
		
		int w = 0;
		while( w <mfile.length) {
			//지금 프론트에서 멀티로 보낸게 아니라 따로 3개를 보내서 null이면 오류남
			//=> 이 안에 null이 아닐때 파일카피유틸 사용하기 
			
			//멀티플로 보내면 에러 안남 
			
			//스프링 전용 갱장희 빠르게 저장하는 라이브러리 !
			FileCopyUtils.copy(mfile[w].getBytes(), new File(url + mfile[w].getOriginalFilename())); 
			w++;
		}
		return "load";
	}

	/*
	// DTO를 사용하여 여러개의 첨부파일을 받는 메소드 => 막코드
	// 굳이 안씀 DTO로 받을수도있긴한데 다시 MultipartFile 써야해서 DTO 의미가 없음 => 막코드
	@PostMapping("/fileok2.do")
	public String fileupok(file_DTO dto) {
		MultipartFile[] m = dto.getMfile(); // 이거 또써야됨 => 의미없음
		System.out.println(m[0].getOriginalFilename());
		return "load";
	}
	*/
	
	
	//웹디렉토리에 있는 파일 리스트를 출력하는 Contoller
	@GetMapping("/filelist.do")
	public String filelist(HttpServletRequest req) throws Exception {
		//웹 디렉토리 경로 
		String url = req.getServletContext().getRealPath("/upload/");
		//웹 디렉토리에 저장되어있는 모든 파일명을 담는 클래스배열 (링크드로하면 개오래걸림)
		File f = new File(url);
		String f_list[] = f.list();
		ArrayList<String> filenm = new ArrayList<String>(Arrays.asList(f_list));
		req.setAttribute("filenm", filenm);	//Model로 전달하면 좋은데 req로 전달함 (아직 jstl잘몰라서)
		
		
		return null;
	}

	// 파일 삭제 메소드	
	/* 아래 메소드와 같음!
	@PostMapping("/filedel.do")
	public String filedel(String fnm) {
		System.out.println(fnm);
		return null;
	}
	*/
	
	// @RequestParam : Front-end 전달된 값 request.getParameter()
	@PostMapping("/filedel.do")
	public String filedel(@RequestParam("fnm") String filenm, HttpServletRequest req, Model m) throws Exception {
//		System.out.println(filenm);
		
		String url = req.getServletContext().getRealPath("/upload/");
		File f = new File(url + filenm);
		f.delete();	//파일 삭제 메소드
		
		//js 메세지를 작성 후 Model로 JSP로 전달을 하게 됨
		String msg = "alert('정상적으로 삭제 되었습니다.');"
				+ "location.href='./filelist.do';";
		m.addAttribute("msg",msg);
		
		return "load";
	}
	
	/*==========jstl6.jsp 로드============*/
	//jstl로 로드 후 값 전달 
	@GetMapping("/jstl/jstl6.do")
	public String jstl6(Model m) {
		//Model을 이용하여 jstl6.jsp로 값을 전달
		//출력 top.jsp에서 ${}로 변수 출력함 
		String level = "일반수강생";
		String corp ="(주)구나영회사";
		String tel = "02-940-9940";
		
		m.addAttribute("level",level);
		m.addAttribute("corp",corp);
		m.addAttribute("tel",tel);
		return null;
	}
	
	
	
}