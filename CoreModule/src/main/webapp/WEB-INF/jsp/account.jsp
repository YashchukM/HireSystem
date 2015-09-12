<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>

<head>
    <title>Account</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>  
    <script type="text/javascript" src="../scripts/scripts.js"></script>
</head>

<body>

	<form id="usersInfoForm" method="post">
	    <input type="text" name="name" value="${user.userDetails.name }" /> <br>
	    <input type="text" name="surname" value="${user.userDetails.surname }" /> <br>
	    <input type="date" name="bdate" value="${birthDate }" > <br>
	    <input type="text" name="email" value="${user.userDetails.email }" /> <br>
	    <input type="text" name="phone" value="${user.userDetails.phone }" /> <br>
	    Upload new Avatar:
	    <input type="file" name="avatar" id="avatar"/>
	    <input type="button" value="Save" onclick="changeUsersInfo()" />
	</form>

</body>
</html>