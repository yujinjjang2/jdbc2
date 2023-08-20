package edu.kh.jdbc.run;

import java.sql.SQLException;

import edu.kh.jdbc.model.service.TestService;
import edu.kh.jdbc.model.vo.TestVO;

public class Run {
	
	public static void main(String[] args) {
		
		TestService service = new TestService();
		
		// TB_TEST 테이블에 INSERT 수행
		TestVO vo1 = new TestVO(2, "제목2", "내용2");
		
		// TB_TEST 테이블에 INSERT를 수행하는 서비스 메서드를 호출 후
		// 결과 반환받기
		try {
			int result = service.insert(vo1); // 1 / 0
			
			if(result > 0) {
				System.out.println("insert 성공");
			} else {
				System.out.println("insert 실패");
			}
			
		} catch(SQLException e) {
			System.out.println("SQL 수행 중 오류 발생");
			
			e.printStackTrace();
		}
		
	}

}
