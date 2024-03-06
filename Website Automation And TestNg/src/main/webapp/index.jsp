<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link type="text/css" rel="stylesheet" href="./style.css" />
</head>
<body>
	<h1 style="text-align: center; line-height: 30vh;">Welcome to ABC
		Application</h1>

	<h4 style="text-align: center;">Choose one of the following
		options to proceed</h4>
	<br>
	<div class="col-sm-3" style="margin: 0 auto; text-align: center;">
		<a href="./login.jsp"><button type="button"
				class="btn btn-success">Login</button></a> <br> <br> <a
			href="./register.jsp"><button type="button"
				class="btn btn-success">Register</button></a>
	</div>
	<script>
		const urlParams = new URLSearchParams(window.location.search);
		const successMessage = urlParams.get('success');

		if (successMessage) {
			const successDiv = document.createElement('div');
			successDiv.textContent = successMessage;
			successDiv.style.color = 'green';
			successDiv.style.textAlign = 'center';
			successDiv.style.fontSize = '20px';
			document.body.prepend(successDiv);
		}
	</script>
</body>
</html>