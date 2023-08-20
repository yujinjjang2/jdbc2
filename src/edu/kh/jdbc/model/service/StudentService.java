package edu.kh.jdbc.model.service;

import edu.kh.jdbc.model.dao.StudentDAO;
import edu.kh.jdbc.model.vo.StudentVO;

import static edu.kh.jdbc.common.JDBCTemplate1.*;

import java.sql.Connection;
import java.util.List;

public class StudentService {
	
	private StudentDAO dao = new StudentDAO();

	public List<StudentVO> selectAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<StudentVO> list = dao.selectAll(conn);
		
		close(conn);
		
		return list;
	}

	public StudentVO selectStudentId(String studentNo) throws Exception{
		
		Connection conn = getConnection();
		
		StudentVO std = dao.selectStudentId(conn, studentNo);
		
		close(conn);
		
		return std;
	}

	public int insertStudent(StudentVO std) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.insertStudent(conn, std);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int updateStudent(StudentVO std) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateStudent(conn, std);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	public int deleteStudent(String deleteStudentNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.deleteStudent(conn, deleteStudentNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
}
