package spring_learning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("banner_DAO")
public class banner_DAO {

	@Resource(name = "template")
	public SqlSessionTemplate st;
	
	Integer page_ea = 5;	//한페이지에 출력할 게시물 수  

	//신규 배너 등록 메소드 
	public int new_banner(banner_DTO dto) {
		int result = this.st.insert("macbook_user.banner_in",dto);	
		return result;
	}
	
	//배너 전체 데이터 출력 + 페이징 
	public List<banner_DTO> all_banner(Integer pgno){	//Integer pgno : Controller에서 사용자가 클릭한 페이지 번호를 받는 역할 
		//limit을 사용하기 위해 Map 형태로 구성하여 Mapper로 전달 
		Map<String,Integer> data = new HashMap<String,Integer>();
		data.put("spage", this.page_ea * (pgno - 1));	//limit 첫번째 번호 (페이지 번호에 맞는 시작 게시물 번호)
		data.put("epage", this.page_ea);	//limit 두번째 번호 (출력 개수)
		
		List<banner_DTO> all = this.st.selectList("macbook_user.banner_all",data);
		return all;
	}
	
	public int banner_total() {
		int total = this.st.selectOne("macbook_user.banner_total");
		return total;
	}
	
	
	//배너명으로 검색된 데이터를 가져오는 메소드 (DAO)
	public List<banner_DTO> banner_search(String search) {
		List<banner_DTO> sel = this.st.selectList("macbook_user.banner_search",search);
		return sel;
	}
	//똑같은거 많으면 그냥 필드에 올리는게 더 좋음 List<banner_DTO> 이런거 필드에 올리기 
	
	//배너 삭제 메소드 
	public int banner_del(String no) {
		int result = this.st.delete("macbook_user.banner_del", no);
		return result;
	}
	
}