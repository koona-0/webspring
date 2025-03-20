package spring_learning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
@Service : Controller에서 받은 값을 Mapper로 전달하고 
			Mapper에서 작업된 값을 다시 받아서 Controller에게 전달해줌
			xml과 interface 사이 낑긴 놈   

여기가 중앙통제실 스까줌  
@Service
public class koo_imp implements koo_service {
	
	//얘는 맵퍼 
	@Autowired
	public koo koo;
	
	//맵퍼야 이놈을 작동시켜라
	//1이 찍히면 값이 들어간거 다른게 찍히면 뭔가 잘못된거 
	@Override
	public int macbook_insert(macbook_DTO dto) {
		int result = koo.macbook_insert(dto);
		return 0;
	}
}

 */