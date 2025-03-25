package spring_learning;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//java개발자는 DAO, DTO(setter,getter), VO(getter) 필수 

//DAO : 데이터를 Access를 하는 역할 
@Repository("macbook_DAO") // @Repository : Model을 Controller에 호출시킴
public class macbook_DAO implements macbook_mapper {
	// implements macbook_mapper
	// 쓰고 컨스페로 자동으로 넣으면 짱좋음 기존에쓰던게있으면 @Override만 붙이면 됨

	// v맵퍼 인터페이스 로드하여 DAO작성

	// mybatis로 db연결
	@Resource(name = "template")
	public SqlSessionTemplate st;

	// 하나의 데이터만 가져오는 메소드
	@Override
	public macbook_DTO macbook_one(String midx) {
		// setter형태로 DB에 있는 데이터를 이관
		// selectOne("mapper.xml 의 id", 매개변수)
		macbook_DTO onedata = this.st.selectOne("macbook_one", midx); // mapper.xml 의 id
		return onedata;
		// 하나만 가져오지만 아래 메소드처럼 selectList로 써도됨
	}

	//과정 생성 메소드 
	@Override
	public int macbook_insert(macbook_DTO dto) {
		int result = this.st.insert("macbook_insert", dto); // st. 컨스페 > sqlsesstion template용 선택
		return result;
	}

	// 전체 리스트 출력 메소드
	@Override
	public List<macbook_DTO> macbook_select() {
		// selectOne : 데이터 한개만 가져올 때 (dto, List배열, ArrayList, Map 등등 다 가능)
		// selectList : 데이터 여러개를 가져올 때 (List배열로 가져옴)
		List<macbook_DTO> classList = this.st.selectList("macbook_select");
		return classList;
	}

	// 데이터 수정 메소드
	@Override
	public int macbook_update(macbook_DTO dto) {
		int result = this.st.update("macbook_update", dto);
		return result;
	}

	// 데이터 삭제 메소드
	@Override
	public int macbook_delete(int midx) {
		int result = this.st.delete("macbook_delete", midx);
		return result;
	}

}