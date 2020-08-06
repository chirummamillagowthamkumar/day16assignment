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
			style="background-color: blue">
			<div>
				<a class="navbar-brand"> AddressBook </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Person</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${person != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${person == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${person != null}">
            			Edit Person
            		</c:if>
						<c:if test="${person == null}">
            			Add New Person
            		</c:if>
					</h2>
				</caption>

				<c:if test="${person != null}">
					<input type="hidden" name="id" value="<c:out value='${person.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Person FirstName</label> <input type="text"
						value="<c:out value='${person.FirstName}' />" class="form-control"
						name="FirstName" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Person LastName</label> <input type="text"
						value="<c:out value='${person.LastName}' />" class="form-control"
						name="LastName" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Person Address</label> <input type="text"
						value="<c:out value='${person.Address}' />" class="form-control"
						name="Address" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Person City</label> <input type="text"
						value="<c:out value='${person.City}' />" class="form-control"
						name="city" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Person State</label> <input type="text"
						value="<c:out value='${person.State}' />" class="form-control"
						name="State" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Person Zip</label> <input type="text"
						value="<c:out value='${person.Zip}' />" class="form-control"
						name="Zip" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Person Phone</label> <input type="text"
						value="<c:out value='${person.Phone}' />" class="form-control"
						name="Phone">
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>