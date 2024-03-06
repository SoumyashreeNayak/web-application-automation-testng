<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="app.com.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<%
if (session.getAttribute("user") == null)
	response.sendRedirect("index.jsp");
%>
<meta charset="UTF-8">
<title>Cart</title>
<link rel="stylesheet" href="./style.css">
</head>
<body>

	<div class="container mt-5">
		<div
			style="display: flex; justify-content: flex-end; align-items: right;">
			<div>
				<a href="./index.jsp"><button class="btn btn-danger">Logout</button></a>
			</div>
		</div>
		<h1 class="text-center">Your cart</h1>

		<h2>Items in your cart:</h2>
		<table class="table table-bordered table-dark">
			<thead>
				<tr>
					<th>Image</th>
					<th>Name</th>
					<th>Price</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				int count = 0;
				List<Item> items = (List<Item>) request.getAttribute("items");
				for (Item item : items) {
				%>
				<tr>
					<td><img src="<%=item.getImageUrl()%>"
						alt="<%=item.getName()%>" style="height: 100px; width: 100px;"></td>
					<td><%=item.getName()%></td>
					<td><%=item.getPrice()%></td>
					<td><a href="removeFromCart?no=<%=count++%>"
						class="btn btn-danger">Remove</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>


</body>
</html>