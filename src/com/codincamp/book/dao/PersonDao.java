package com.codincamp.book.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.codincamp.book.bean.Person;


public class PersonDao {
	private String url="jdbc.mysql//localhost:3306";
	private String username="root";
	private String password="root";
	
	private static final String insert="INSERT INTO ADDRESS_BOOK (firstname,lastname,address,city,state,zip,phone) VALUES (?,?,?,?,?,?,?);";
	private static final String selectPersonById="SELECT id,firstname,lastname,address,city,state,zip,phone from ADDRESS_BOOK WHERE ID=?";
	private static final String selectAllPerson="SELECT * FROM ADDRESS_BOOK";
	private static final String deletePerson="DELETE FROM ADDRESS_BOOK WHERE ID=?;";
	private static final String updatePerson="UPDATE ADDRESS_BOOK SET FIRSTNAME=?,LASTNAME=?,ADDRESS=?,CITY=?,STATE=?,ZIP=?,PHONE=? WHERE ID=?";
public PersonDao() {
		
	}
/*protected Connection getConnection() {
	Connection con=null;
	try{
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		con=DriverManager.getConnection(url,username,password);
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
	return con;
}*/
 		protected Connection getConnection() throws SQLException {
 			Connection con = null;
 			try {
 				Class.forName("oracle.jdbc.driver.OracleDriver");
 				con = DriverManager.getConnection(url, username, password);
 			}  catch (ClassNotFoundException e) {
 				e.printStackTrace();
 			}
 			return con;
 		}
	public void insertPerson(Person person)throws SQLException{
		System.out.println(insert);
		try(Connection con=getConnection();
			PreparedStatement pstmt=con.prepareStatement(insert)){
			pstmt.setString(1, person.getFirstname());
			pstmt.setString(2, person.getLastname());
			pstmt.setString(3, person.getAddress());
			pstmt.setString(2, person.getCity());
			pstmt.setString(2, person.getState());
			pstmt.setInt(2, person.getZip());
			pstmt.setLong(2, person.getPhone());
			System.out.println(pstmt);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			printSQLException(e);
		}
		}
		public Person selectPerson(int id) {
			Person person = null;
			try (Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(selectPersonById);) {
				pstmt.setInt(1, id);
				System.out.println(pstmt);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					String firstname = rs.getString(2);
					String lastname = rs.getString(3);
					String address= rs.getString(4);
					String city= rs.getString(5);
					String state= rs.getString(6);
					int zip= rs.getInt(7);
					long phone=rs.getLong(8);
					person = new Person(id,firstname,lastname,address,city,state,zip,phone);
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return person;
		}
		public List<Person> selectAllPerson() {
			List<Person> person = new ArrayList<>();
			try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(selectAllPerson);) {
				System.out.println(pstmt);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt(1);
					String firstname = rs.getString(2);
					String lastname = rs.getString(3);
					String address = rs.getString(4);
					String city = rs.getString(5);
					String state= rs.getString(6);
					int zip= rs.getInt(7);
					long phone=rs.getLong(8);
					person.add(new Person(id, firstname, lastname, address,city,state,zip,phone));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return person;
		}
		public boolean deletePerson(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(deletePerson);) {
				pstmt.setInt(1, id);
				rowDeleted = pstmt.executeUpdate() > 0;
			}
			return rowDeleted;
		}
		public boolean updatePerson(Person person) throws SQLException {
			boolean rowUpdated;
			try (Connection con = getConnection();
					PreparedStatement pstmt = con.prepareStatement(updatePerson);) {
				System.out.println("updated USer:"+pstmt);
				pstmt.setString(1, person.getFirstname());
				pstmt.setString(2, person.getLastname());
				pstmt.setString(3, person.getAddress());
				pstmt.setString(4, person.getCity());
				pstmt.setString(5, person.getState());
				pstmt.setInt(6, person.getZip());
				pstmt.setLong(7, person.getPhone());
				rowUpdated = pstmt.executeUpdate() > 0;
			}
			return rowUpdated;
		}
		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
	
		
	
}
