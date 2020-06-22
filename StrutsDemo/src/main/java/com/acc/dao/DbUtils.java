package com.acc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.acc.form.EmployeeForm;

public class DbUtils {

	public static int saveEmployee(EmployeeForm addEmpForm) {
		Connection conn=DbConnection.getConnection();
		Statement  st=null;
		ResultSet rs=null;
		int generatedEmpId=-1;
		if(conn!=null) {
			try {
				st=conn.createStatement();
				String insertSql="insert into EMP(EMP_NAME,EMP_SAL,DEPT) "
						+" values('" + addEmpForm.getName() + "','" + addEmpForm.getSalary()+ "','" + addEmpForm.getDept() + "')";
				int m =st.executeUpdate(insertSql);
				if (m> 0) {
					rs=st.getGeneratedKeys();
					if (rs.next()) {
						generatedEmpId=rs.getInt(1);
					}
				}
				
			}
			catch(SQLException se) {
				
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					if (st != null) {
						st.close(); // close statement
					}
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){

			}
		}
		
	}
		return generatedEmpId;

}

	public static EmployeeForm getEmployee(int employeeId) {
		Connection conn=DbConnection.getConnection();
		Statement  st=null;
		ResultSet rs=null;
		EmployeeForm ef=new EmployeeForm();
		int generatedEmpId=-1;
		if(conn!=null) {
			try {
				st=conn.createStatement();
				String Sql="Select EMP_NAME,EMP_SAL,DEPT from employee where EMP_ID='"+employeeId+"'";
				rs = st.executeQuery(Sql);
				while (rs.next()) {
					String name = rs.getString("EMP_NAME");
					String salary = rs.getString("EMP_SAL");
					String dept = rs.getString("DEPT");
					ef.setEmployeeId(employeeId);
					ef.setName(name);
					ef.setSalary(salary);
					ef.setDept(dept);
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					if (st != null) {
						st.close(); // close statement
					}
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
		return ef;

}

	public static boolean updateEmployee(EmployeeForm updatedEmpForm) {
		Connection conn=DbConnection.getConnection();
		Statement  st=null;
		ResultSet rs=null;
		//int generatedEmpId=-1;
		if(conn!=null) {
			try {
				st=conn.createStatement();
				String updateSql="update EMP"
						+" set EMP_NAME='" + updatedEmpForm.getName() + "'," +"EMP_SAL='"+updatedEmpForm.getSalary()+ "',"+"DEPT='"+updatedEmpForm.getDept()+"' where emp_id="+updatedEmpForm.getEmployeeId();
				
				System.out.println(updateSql);
				
				if (st.executeUpdate(updateSql) > 0) {
					return true;
				}
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					if (st != null) {
						st.close(); // close statement
					}
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
		return false;

}
	public static List<EmployeeForm> getALLEmployee() {
		Connection conn=DbConnection.getConnection();
		Statement  st=null;
		ResultSet rs=null;
	
		List<EmployeeForm> listOfemp=new ArrayList<EmployeeForm>();
		if(conn!=null) {
			try {
				st=conn.createStatement();
				String Sql="Select EMP_ID,EMP_NAME,EMP_SAL,DEPT from employee";
				rs = st.executeQuery(Sql);
				while (rs.next()) {
					EmployeeForm ef=new EmployeeForm();
					int employeeId=Integer.parseInt(rs.getString("EMP_ID"));
					String id=String.valueOf(employeeId);
					String name = rs.getString("EMP_NAME");
					String salary = rs.getString("EMP_SAL");
					String dept = rs.getString("DEPT");
					ef.setEmployeeId(employeeId);
					ef.setName(name);
					ef.setSalary(salary);
					ef.setId(id);
					ef.setDept(dept);
					listOfemp.add(ef);
				}
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			finally {
				try {
					if (rs != null) {
						rs.close(); // close result set
					}
					if (st != null) {
						st.close(); // close statement
					}
					if (conn != null) {
						conn.close(); // close connection
					}
				}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
		return listOfemp;

}
}
