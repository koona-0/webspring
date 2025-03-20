package spring_learning;

import java.util.List;

import org.springframework.stereotype.Service;

/*
@Service : @Mapper의 interface와 macbook_Service의 interface를 연결해주는 역할 

@Service : Controller에서 받은 값을 Mapper로 전달하고 
			Mapper에서 작업된 값을 다시 받아서 Controller에게 전달해줌
			xml과 interface 사이 낑긴 놈   

여기가 중앙통제실 스까줌 
----------

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
	
----------


*/

@Service
public class macbook_ServiceImp implements macbook_Service {

	@Override
	public int macbook_insert(macbook_DTO dto) {
		return 0;
	}

	@Override
	public List<macbook_DTO> macbook_select() {
		return null;
	}

	@Override
	public int macbook_delete(int midx) {
		return 0;
	}

}
