package edu.kh.jdbc.run;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.model.service.StudentService;
import edu.kh.jdbc.model.vo.StudentVO;

public class Run5 {

	
	static StudentService service = new StudentService();
	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		int input = 0;
		
		do {
			
			try {
				System.out.println("---------------------------------------------------------");
				System.out.println("----- 사원 관리 프로그램 -----");
				System.out.println("1. 전체 학생 정보 조회");
				System.out.println("2. 새로운 학생 추가");
				System.out.println("3. 학번이 일치하는 학생 정보 조회");
				System.out.println("4. 학번이 일치하는 학생 정보 수정");
				System.out.println("5. 학번이 일치하는 학생 정보 삭제");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 >> ");
				input = sc.nextInt();
				sc.nextLine(); //  추가!
				
				
				System.out.println();				
				
				
				switch(input) {
				case 1:  selectAll();   break;
				case 2:  insertStudent();  break;
				case 3:  selectStudentId();   break;
				case 4:  updateStudent();   break;
				case 5:  deleteStudent();   break;
				case 0:  System.out.println("프로그램을 종료합니다...");   break;
				default: System.out.println("메뉴에 존재하는 번호만 입력하세요.");
				}
				
				
			}catch(InputMismatchException e) {
				System.out.println("정수만 입력해주세요.");
				input = -1; // 반복문 첫 번째 바퀴에서 잘못 입력하면 종료되는 상황을 방지
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못 입력된 문자열 제거해서
							   // 무한 반복 방지
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} while(input != 0);
		
	}

	private static void deleteStudent() throws Exception{
		
		System.out.println("학번이 일치하는 학생 정보 삭제");
		
		System.out.print("삭제할 학번 : ");
		String deleteStudentNo = sc.next();
		
		int result = service.deleteStudent(deleteStudentNo);
		
		if(result > 0) {
			System.out.println("학생 정보 삭제 성공");
		} else {
			System.out.println("학생 정보 삭제 실패");
		}
		
	}

	private static void updateStudent() throws Exception{
		
		System.out.println("학번이 일치하는 학생 정보 수정");
		
		System.out.print("수정할 학번 : ");
		String updateStudentNo = sc.next();
		
		System.out.print("수정할 학생 이름 : ");
		String updateStudentNm = sc.next();
		
		System.out.print("수정할 학생 주민번호 : ");
		String updateStudentSsn = sc.next();
		
		StudentVO std = new StudentVO(updateStudentNo, updateStudentNm, updateStudentSsn);
		
		int result = service.updateStudent(std);
		
		if(result > 0) {
			System.out.println("학생 정보 수정 성공");
		} else {
			System.out.println("학생 정보 수정 실패");
		}
		
	}

	private static void insertStudent() throws Exception{
		
		System.out.println("<새로운 학생 추가>");
		
		System.out.print("학번 : ");
		String studentNo = sc.next();
		
		System.out.print("학과번호 : ");
		String departmentNo = sc.next();
		
		System.out.print("학생이름 : ");
		String studentNm = sc.next();
		
		System.out.print("주민번호 : ");
		String studentSsn = sc.next();
		
		StudentVO std = new StudentVO(studentNo, departmentNo, studentNm, studentSsn);
		
		int result = service.insertStudent(std);
		
		if(result > 0) {
			System.out.println("학생 추가 성공");
		} else {
			System.out.println("학생 추가 실패");
		}
		
		
		
	}

	private static void selectStudentId() throws Exception{

		System.out.println("<학번이 일치하는 학생 정보 조회>");
		
		System.out.print("학번 : ");
		String studentNo = sc.next();
		
		StudentVO std = service.selectStudentId(studentNo);
		
		printOne(std);
		
	}

	private static void selectAll() throws Exception{
		
		System.out.println("<전체 학생 정보 조회>");
		
	    List<StudentVO> list = service.selectAll();
	    
	    printAll(list);
		
	}
	
	public static void printAll(List<StudentVO> list) {
		
		if(list.isEmpty()) {
			System.out.println("조회된 학생 정보가 없습니다.");
			
		} else {
			System.out.println("학번 |   학과번호  | 학생이름 |        주민번호        |      주소      |    입학일    | 휴학여부 |  코치교수 " );
			System.out.println("------------------------------------------------------------------------------------------------");
			for(StudentVO stu : list) { 
				System.out.printf(" %2s  | %4s | %s | %20s | %s | %s | %s | %s\n",
						stu.getStudentNo(), stu.getDepartmentNo(), stu.getStudentName(), stu.getStudentSsn(), 
						stu.getStudentAddress(), stu.getEntranceDate(), stu.getAbsenceYn(), stu.getCoachProfessorNo());
			}
		
		}
		
		return;

	}
	
	public static void printOne(StudentVO std) {
		
		if(std == null) {
			System.out.println("조회된 학생 정보가 없습니다.");
			
		} else {
			System.out.println("학번 |   학과번호  | 학생이름 |        주민번호        |      주소      |    입학일    | 휴학여부 |  코치교수 " );
			System.out.println("------------------------------------------------------------------------------------------------");
			
			System.out.printf(" %2s  | %4s | %s | %20s | %s | %s | %s | %s\n",
					std.getStudentNo(), std.getDepartmentNo(), std.getStudentName(), std.getStudentSsn(), 
					std.getStudentAddress(), std.getEntranceDate(), std.getAbsenceYn(), std.getCoachProfessorNo());
		}


	}

}
