package edu.kh.jdbc.model.dao;

import java.io.Closeable;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static edu.kh.jdbc.common.JDBCTemplate1.*;
import edu.kh.jdbc.model.vo.StudentVO;

public class StudentDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public StudentDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("student.xml"));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<StudentVO> selectAll(Connection conn) throws Exception{

		// 결과 저장용 변수 선언
		List<StudentVO> studentList = new ArrayList<StudentVO>();
		
		try {
		
		String sql = prop.getProperty("selectAll");
		
		pstmt = conn.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String studentNo = rs.getString("STUDENT_NO");
			String departmentNo = rs.getString("DEPARTMENT_NO");
			String studentName = rs.getString("STUDENT_NAME");
			String studentSsn = rs.getString("STUDENT_SSN");
			String studentAddress = rs.getString("STUDENT_ADDRESS");
			String entranceDate = rs.getString("ENTRANCE_DATE");
			String absenceYn = rs.getString("ABSENCE_YN");
			String coachProfessorNo = rs.getString("COACH_PROFESSOR_NO");
			
			studentList.add( new StudentVO(studentNo, departmentNo, studentName, studentSsn,
					studentAddress, entranceDate, absenceYn, coachProfessorNo) );
		}
		} finally {
			
			close(pstmt);
			
		}
		
		return studentList;
	}

	public StudentVO selectStudentId(Connection conn, String studentNo) throws Exception{
		
		StudentVO std = null;
		
		try {
			
			String sql = prop.getProperty("selectStudentId");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, studentNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String studentNo1 = rs.getString("STUDENT_NO");
				String departmentNo = rs.getString("DEPARTMENT_NO");
				String studentName = rs.getString("STUDENT_NAME");
				String studentSsn = rs.getString("STUDENT_SSN");
				String studentAddress = rs.getString("STUDENT_ADDRESS");
				String entranceDate = rs.getString("ENTRANCE_DATE");
				String absenceYn = rs.getString("ABSENCE_YN");
				String coachProfessorNo = rs.getString("COACH_PROFESSOR_NO");
				
				std = new StudentVO(studentNo1, departmentNo, studentName, studentSsn,
						studentAddress, entranceDate, absenceYn, coachProfessorNo);
				
			}
			
			
		} finally {
			
			close(pstmt);
			
		}
		
		return std;
	}

	public int insertStudent(Connection conn, StudentVO std) throws Exception{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertStudent");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, std.getStudentNo());
			pstmt.setString(2, std.getDepartmentNo());
			pstmt.setString(3, std.getStudentName());
			pstmt.setString(4, std.getStudentSsn());
			
			result = pstmt.executeUpdate();
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}

	public int updateStudent(Connection conn, StudentVO std) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateStudent");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, std.getStudentName());
			pstmt.setString(2, std.getStudentSsn());
			pstmt.setString(3, std.getStudentNo());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			
			close(pstmt);
			
		}
		
		return result;
	}

	public int deleteStudent(Connection conn, String deleteStudentNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deleteStudent");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, deleteStudentNo);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
}
