<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link type="text/css" rel="stylesheet" href="./style.css" />
</head>

<body>
	<h1 style="text-align: center; line-height: 30vh;">Login to continue</h1>
	<div class="col-sm-4" style="margin:0 auto;">
		<form action="LoginCheck" method="post">
			<div class="mb-3">
				<h4>Email</h4>
				<input type="email" class="form-control" id="user-email" name="user-email" required>
			</div>
			<div class="mb-3">
				<h4>Password</h4>
				<input type="password" class="form-control" id="user-pwd" name="user-pwd" required>
			</div>

			<button type=" submit" class="btn btn-dark" id="submit-btn">Login</button>
		</form>
	</div>
	<script>

		const urlParams = new URLSearchParams(window.location.search);
		const errorMessage = urlParams.get('error');

		if (errorMessage) {
			const errorDiv = document.createElement('div');
			errorDiv.textContent = errorMessage;
			errorDiv.style.color = 'red';
			errorDiv.style.textAlign = 'center';
			errorDiv.style.fontSize = '20px';
			document.body.prepend(errorDiv);
		}
	</script>
</body>

</html>