package com.codincamp.book.web;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.codincamp.book.bean.Person;
import com.codincamp.book.dao.PersonDao;

@WebServlet("/")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonDao persondao = new PersonDao();

	public void init(ServletConfig config) throws ServletException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try{
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertPerson(request, response);
				break;
			case "/delete":
				deletePerson(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updatePerson(request, response);
				break;
			default:
				listPerson(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("PersonForm.jsp");
		dispatcher.forward(request, response);
	}

	private void insertPerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int zip = Integer.parseInt(request.getParameter("zip"));
		long phone = Long.parseLong(request.getParameter("phone"));
		Person newPerson = new Person(firstname, lastname, address, city, state, zip, phone);
		persondao.insertPerson(newPerson);
		response.sendRedirect("list");
	}

	private void deletePerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		persondao.deletePerson(id);
		response.sendRedirect("list");

	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Person existingPerson = persondao.selectPerson(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("PersonForm.jsp");
		request.setAttribute("person", existingPerson);
		dispatcher.forward(request, response);

	}

	private void listPerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Person> listPerson = persondao.selectAllPerson();
		request.setAttribute("listPerson", listPerson);
		RequestDispatcher dispatcher = request.getRequestDispatcher("PersonList.jsp");
		dispatcher.forward(request, response);
	}

	private void updatePerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		int zip = Integer.parseInt(request.getParameter("zip"));
		long phone = Long.parseLong(request.getParameter("phone"));
		Person book = new Person(id, firstname, lastname, address, city, state, zip, phone);
		persondao.updatePerson(book);
		response.sendRedirect("list");
	}

}
