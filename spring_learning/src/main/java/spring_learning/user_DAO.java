package spring_learning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//mapper interface를 사용하지 않는 경우 => 따옴표안이 변함 
@Repository("user_DAO")
public class user_DAO {

	@Resource(name = "template")
	public SqlSessionTemplate st;

	// @Mapper interface 없이 member_mapper.xml에서 namespace + id를 결합하여 쿼리문 작동시킴
	public List<macbook_member_DTO> all_list() {
		List<macbook_member_DTO> all = this.st.selectList("macbook_user.user_all");

		return all;
	}

	public String user_search(String name, String mail) {
		
		Map<String, String> code = new HashMap<String, String>();
		code.put("mname", name);
		code.put("memail", mail);
		
		String result = this.st.selectOne("macbook_user.search_userid",code);
		return result;
	}

}
