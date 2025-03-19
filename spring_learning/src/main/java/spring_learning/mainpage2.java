package spring_learning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.mybatis.logging.*;

@Controller
public class mainpage2 {
	
	//WEB-INF 파일 : Controller, Model이 접근할 수 있는 디렉토리 (브라우저에서는 접근불가) 
	//return 사용시 WEB-INF/디렉토리명/파일명 형태로 구성하게됨 => 진짜 MVC
	
	/*
	DTO로 Front-end의 값을 받을 수 있음 (lombok)
	별도의 값을 받아서 처리해야 할 경우는 Servlet 형태의 request로 받으면 됨 
	⭐Front의 name값과 동일하겨 DTO가 작성되어야 함⭐
	
	DTO 활용 : Front-end 값 이관, Model에 값을 이관, Database에서 사용 
	*/
	@GetMapping("/login.do")
	public String login(user_DTO dto, HttpServletRequest req, Model m) {
		String ck = req.getParameter("mcheck");
		System.out.println(ck);
		
		//Model로 해당 jsp에 변수를 이관함 출력은 jstl 변수 선언으로 출력 
		m.addAttribute("mid",dto.getMid());
		
//		System.out.println(dto.getMid());
//		System.out.println(dto.getMpass());
		System.out.println(dto.getMemail());
		return "WEB-INF/view/login";	//=> login.jsp는 강제실행이 불가능함! Controller가 해줘야함 
	}
	
	
	
	//@Autowired : java에서 사용하는 class 또는 interface의 값을 xml에 있는 id 기준으로 대체하는 형태 
	//문서파일을 자바파일에 주입시키는거 (의존성 주입) 
	@Autowired
	BasicDataSource dbinfo;
	
	//DB Query문 작성 및 데이터를 가져오기 위한 interface
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	//Database + XML + Connection + Controller
	@GetMapping("/event_list.do")
	public String event_list(HttpServletRequest req) {
		 try {
			 //db_config.xml에 있는 정보를 Connection으로 이관 
	         this.con = this.dbinfo.getConnection();
	         String sql="select * from event order by eidx desc";
	         this.ps = this.con.prepareStatement(sql);
	         this.rs = this.ps.executeQuery();
	         
	         /*
	         ResultSet을 JSP로 전송 (올바른 코드가 아님)
	         단점 : this.ps, this.rs close()가 불가능함 
	         */
	         req.setAttribute("rs", this.rs);	
	      } catch (Exception e) {
	         
	      }finally {
	         try {
	        	 this.rs.close();
	        	 this.ps.close();
	         }catch (Exception e) {
				
			}
	      }
	      return null;
	   }
	
	/*
	 RequestMapping : GET POST PUT 등등 모든 통신을 다 받을 수 있음 (기본) => 보안이 굉장히 약함
	 value 속성 : 가상의 경로 
	 method 속성 : 통신방법(Front-end 데이터 이관 방법)
	 이런 속성들을 알고있으면 써도 되기는 함 
	 */
//	@RequestMapping("/event_infook.do") : 이렇게 쓰면 보안 다뚫림 쓰레기 
	@RequestMapping(value="/event_infook.do",method=RequestMethod.POST)	//이렇게 쓰면 POST만 받는다는 의미 잘쓴 RequestMapping
	public String eventok(event_DTO dto) {
//		System.out.println(dto.getEmail());
//		System.out.println(dto.getEtel());
		try {
			this.con = this.dbinfo.getConnection();
			String sql = "insert into event values ('0',?,?,?,?,?,?,now())";
			this.ps = this.con.prepareStatement(sql);
			this.ps.setString(1, dto.getEname());
			this.ps.setString(2, dto.getEtel());
			this.ps.setString(3, dto.getEmail());
			this.ps.setString(4, dto.getInfo1());
			this.ps.setString(5, dto.getInfo2());
			this.ps.setString(6, dto.getEmemo());
			int result = this.ps.executeUpdate();
			System.out.println(result);
			
			
		}catch (Exception e) {
			
		}finally {
			try {
				this.ps.close();
			}catch (Exception e) {
				
			}
		}
		
		
		return null;
	}
}


















