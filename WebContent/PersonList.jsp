<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>    
<head>
<title>ADDRESS BOOK</title>
<link rel="stylesheet" 
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a  class="navbar-brand"> AddressBook
				</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Person</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List Of Person</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New User</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>FIRSTNAME</th>
						<th>LASTNAME</th>
						<th>ADDRESS</th>
						<th>CITY</th>
						<th>STATE</th>
						<th>ZIP</th>
						<th>PHONE</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="person" items="${PersonList}">

						<tr>
							<td><c:out value="${person.id}" /></td>
							<td><c:out value="${person.firstname}" /></td>
							<td><c:out value="${person.lastname}" /></td>
							<td><c:out value="${person.address}" /></td>
							<td><c:out value="${person.city}" /></td>
							<td><c:out value="${person.state}" /></td>
							<td><c:out value="${person.zip}" /></td>
							<td><c:out value="${person.phone}" /></td>
							<td><a href="edit?id=<c:out value='${person.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${person.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>