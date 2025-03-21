package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//java개발자는 DAO, DTO(setter,getter), VO(getter) 필수 

//DAO : 데이터를 Access를 하는 역할 
@Repository("macbook_DAO")	//@Repository : Model을 Controller에 호출시킴
public class macbook_DAO {
   
   //mybatis로 db연결
   @Resource(name="template")
   public SqlSessionTemplate st;
   
   public List<macbook_DTO> macbook_select(){      
	      //selectOne : 데이터 한개만 가져올 때 (List배열, ArrayList, Map 등등 다 가능)
	      //selectList : 데이터 여러개를 가져올 때 (List배열로 가져옴)
	      List<macbook_DTO> classList = this.st.selectList("macbook_select");
	      
	      return classList;
	   }
   
   public int macbook_in(macbook_DTO dto) {
      int result = this.st.insert("macbook_insert", dto); //st. 컨스페 > sqlsesstion template용 선택
      return result;
   }
}