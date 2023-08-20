package edu.kh.jdbc.run;

import java.util.Scanner;

import edu.kh.jdbc.model.service.TestService;

public class Run4 {

	public static void main(String[] args) {
		
		TestService service = new TestService();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("삭제할 번호 입력 : ");
		int deleteNum = sc.nextInt();
		sc.nextLine();
		
		try {
			
			int result = service.delete(deleteNum);
			
			if(result > 0) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
		} catch(Exception e) {
			System.out.println("삭제 중 예외 발생");
			e.printStackTrace();
		}
	}
}
