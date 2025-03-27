package spring_learning;

import org.springframework.stereotype.Repository;

import lombok.Data;

//@Getter
//@Setter
@Data	//@Data = @Getter + @Setter 한방에 적용하는 어노테이션
@Repository("banner_DTO")
//DTO 생성 후 꼭 config.xml에 추가~!
public class banner_DTO {
	//파일은 DTO에 쓰지않음!!! 절대안됨!!! I/O는 따로 핸들링함!!!
	int bidx;
	String bname, file_ori, file_new, file_url, bdate;
}