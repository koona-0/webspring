package spring_learning;

import org.apache.ibatis.annotations.Mapper;

/*
@Mapper : mapper.xml에 있는 namespace와 연동, 메소드를 실행시키는 id와 연동 시킴 
인터페이스 안 이름 macbook_insert는 mapper.xml의 id를 따라감
=>mapper는 연결고리

sql 쿼리문을 실행시킬 수 있도록 적용된 interface
쿼리문 안에 #{}때문에 dto를 인자로 넣음 
mapper.xml의 sql 쿼리문들을 @Mapper로 연결시켜 아이디로 작동시키는 인터페이스

이녀석은 mapper.xml만 연결됨
누군가 이녀석에게 값을 던져줘야 쿼리문에 넣을 값이 들어감 => koo_service.java가 줌 
 */
@Mapper
public interface koo {
	
	public int macbook_insert(macbook_DTO dto);

}
