
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Pharmacy Management</title>
		<link rel="stylesheet"
			href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
			integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
			crossorigin="anonymous">
	</head>
	
    <style>
        body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-image: url("https://media.istockphoto.com/id/660133122/photo/get-the-treatment-you-need.jpg?s=612x612&w=0&k=20&c=FH0lyzJLHahIXo9IUoEiItjgZaFnO0S90ufXu2p9QmI=");
        background-size: 100%;
        }

        .d1{
        background-color: #f0f0f0;
        padding: 20px;
        text-align: center;
        }

        footer {
        background-color: #333;
        color: #fff;
        text-align: center;
        padding: 10px 0;
        position: fixed;
        bottom: 0;
        width: 100%;
        }
    </style>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: darkblue">
			<div>
				<a href="welcome.jsp" class="navbar-brand"> Pharmacy Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list" class="nav-link">Medicines</a></li>
			</ul>
		</nav>
	</header>

    <div class="d1">
        <h1>Welcome to Our Pharmacy Management System</h1>
        <p>Efficiently Manage Your Pharmacy Operations</p>
    </div>  


    <footer>
        <p>&copy; 2024 Pharmacy Management System</p>
    </footer>
</body>
</html>