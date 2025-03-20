package spring_learning;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/*
@Mapper : mapper.xml에 있는 namespace와 연동, 메소드를 실행시키는 id와 연동 시키는 interface
⭐️Mapper.xml에서 사용하는 id기준으로 메소드 이름을 설정 

인터페이스 안 이름 macbook_insert는 mapper.xml의 id를 따라감
=>mapper는 연결고리

sql 쿼리문을 실행시킬 수 있도록 적용된 interface
쿼리문 안에 #{}때문에 dto를 인자로 넣음 
mapper.xml의 sql 쿼리문들을 @Mapper로 연결시켜 아이디로 작동시키는 인터페이스

이녀석은 mapper.xml만 연결됨
누군가 이녀석에게 값을 던져줘야 쿼리문에 넣을 값이 들어감 => koo_service.java가 줌

mapper.xml 먼저 쿼리문 만들고 그 id를 이용해서 인터페이스에 올리기 
*/
@Mapper
public interface macbook_mapper {
	public int macbook_insert(macbook_DTO dto);
	List<macbook_DTO> macbook_select();
	public int macbook_delete(int midx);
}
