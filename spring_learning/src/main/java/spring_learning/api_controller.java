package spring_learning;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//API 전용 컨트롤러 
//API사용시 @Controller를 사용하는게 아니라 @RestController를 사용  
@CrossOrigin(origins="*", allowedHeaders = "*")	
@RestController
public class api_controller {
	
	PrintWriter pw = null;	//front-end가 값을 가져갈 수 있도록 함 
	
	@Resource(name="macbook_member_DTO")
	macbook_member_DTO dto;
	
	@Resource(name="user_DAO")
	user_DAO dao;
	
	
	//1차 배열 
	@GetMapping("/api_data1.do")
	public String api_data(HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		
		/*
		JSONArray : []
		JSONObject : {} => 키를 생성 		
		
		JSONArray 두가지 라이브러리 
			org.json : put 사용 
			org.json.simple : add 사용
			=> 나머지는 똑같이 사용   
		*/
		
		/*
		//1차 배열 형태 : ["a","b","c","d"]
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		
		//api의 출력은 printwriter가 기본! => HttpServletResponse 사용, 언어셋 설정, I/O니까 예외처리 
		this.pw = res.getWriter();
		this.pw.print(ja);
		this.pw.close();
		 */
		
		/*
		//1차 키배열 형태 : {"data":["a","b","c","d"]}
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		
		JSONObject jo = new JSONObject();
		jo.put("data", ja);
		this.pw.print(jo);
		this.pw.close();
		*/
		
		return null;
	}
	
	//2차 배열 
	@GetMapping("/api_data2.do")
	public String api_data2(HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		//JSON만들땐 안에거 먼저 만들고 밖에거 만듦 
		
		/*		
		//2차 배열 [ ["홍길동","강감찬"] ] => 중괄호 2개니까 Array 두개 
		JSONArray ja = new JSONArray();
		ja.put("홍길동");
		ja.put("강감찬");
		
		JSONArray ja2 = new JSONArray();
		ja2.put(ja2);
		this.pw.print(ja2);
		this.pw.close();
		 */
		
		
		//2차 키배열 : [ {"member":["홍길동","강감찬"]} ]
		JSONArray ja = new JSONArray();
		ja.put("홍길동");
		ja.put("강감찬");
		
		JSONObject jo = new JSONObject();
		jo.put("member", ja);
		
		JSONArray ja2 = new JSONArray();
		ja.put(jo);
		this.pw.print(ja2);	//맨 마지막 JSON 객체를 출력  
		this.pw.close();
		
		//객체들 원래 필드에 올려서 써야함  
		
		return null;
	}
	
	//배열의 내용을 JSON으로 만들기
	@GetMapping("/api_data3.do")
	public String api_data3(HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		String db[] = {"hong","홍길동","hong@nate.com","서울","01012345678"};
		
		JSONObject jo = new JSONObject();
		jo.put("id", db[0]);
		jo.put("name", db[1]);
		jo.put("email", db[2]);
		jo.put("area", db[3]);
		jo.put("phone", db[4]);
		this.pw.print(jo);
		
		JSONArray ja = new JSONArray();
		ja.put(jo);
		
		JSONObject jo2 = new JSONObject();
		jo2.put("myinfo", ja);
		this.pw.print(jo2);
		this.pw.close();
		
		return null;
	}

	// 응용문제
	@GetMapping("/api_data4.do")
	//이것이 Spring에서 CORS 해결하는 방식 => 컨트롤러위에 쓰기!!!!(메소드위에 쓰면 그 메소드만 적용됨)
	//Spring, Spring-boot 에서의 CORS 해결 코드 
	@CrossOrigin(origins="*", allowedHeaders = "*")	
	public String api_data4(HttpServletResponse res) throws Exception {
		/*
		//아래 두줄 CORS 해결 : BUT 이것은 Servlet 형태 (구닥다리!)
		res.addHeader("Access-Control-Allow-Origin", "*");	// 모든 아이피 허용
		res.addHeader("Access-Control-Allow-Credentials", "true");
		*/
		
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		String data[][] = { { "모니터", "키보드", "마우스" }, { "NEW", "BEST", "NEW" } };
		int w = 0;
		String keyname = "";	//서브키 (안에있는키) 
		
		JSONObject alldata = new JSONObject();	//대표키 오브젝트
		JSONObject jb = new JSONObject();
		
		while (w < data.length) {
			JSONArray ja = new JSONArray();		//데이터 배열[]
			for (int f = 0; f < data[w].length; f++) {
				ja.put(data[w][f]);
			}
//			System.out.println(jo);
			//데이터 
			if(w==0){
				keyname = "product_name";
			}else {
				keyname = "product_ico";
			}
			jb.put(keyname, ja);
//			System.out.println(jb);			
			w++;
		}
		alldata.put("product",jb);	//대표키 생성은 최종 반복문 다음에 코드를 작성 
		System.out.println(alldata);
		this.pw.print(alldata);
		
		
		this.pw.close();
		return null;
	}
	
	//MySQL DB에서 가져오기
	@GetMapping("/api_data5.do")
	public String api_data5(HttpServletResponse res) throws Exception {

		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		/*
		//이런 형태로 출력하기 
		{"member":
			[
			 {"midx":5,"mid":"user05","mname":"Eve","memail":"eve@example.com"},
			 {"midx":4,"mid":"user04","mname":"Diana","memail":"diana@example.com"},
			 {"midx":3,"mid":"user03","mname":"Charlie","memail":"charlie@example.com"},
			 {"midx":2,"mid":"user02","mname":"Bob","memail":"bob@example.com"},
			 {"midx":1,"mid":"user01","mname":"Alice","memail":"alice@example.com"}
			 ]
		}
		*/

		// 데이터 가져오기
		List<macbook_member_DTO> result = this.dao.all_list();

		// 반복문 돌리기
		int w = 0;
		JSONObject alldata = new JSONObject();	//{}
		JSONArray datas = new JSONArray();		//[]
		JSONObject jo = null;					//{}
		while (w < result.size()) {
			jo =  new JSONObject();
			jo.put("midx", result.get(w).midx);
			jo.put("mid", result.get(w).mid);
			jo.put("mname", result.get(w).mname);
			jo.put("memail", result.get(w).memail);
			datas.put(jo);
			w++;
		}
		alldata.put("member", datas);
		this.pw.print(alldata);

		this.pw.close();
		return null;
	}

}