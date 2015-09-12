<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>

<head>
    <title>Home</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
    
    <script type="text/javascript" src="resources/scripts/scripts.js"></script>
    
</head>


<body>
	<form id="addItemForm" method="post" enctype="multipart/form-data">
	    <input type="text" id="name2" name="name2" />
	    <input type="text" name="to" />
	    <input type="date" name="bday" value="1995-08-23" >
	    <input type="hidden" name="h" value="010" />
	    <input type="file" name="file2" id="file2"/>
	    <input type="button" value="Do it!" onclick="uploadFormData()" />
	</form>
	
	<form id="imageForm" method="post" enctype="multipart/form-data">
		<input type="file" name="file3" id="file3"/>
    	<input type="button" value="upload" onclick="uploadFormData()" />
	</form>
	
	<img src="getImage/222"/>
	
	<input type="button" value="GET!" onclick="restGet()" />
	
	<span id="jsonMessage"></span>
	<span id="jsonGetMessage"></span>
	<div id="result"></div>
		
	<a href="account">Account</a>
	
</body>

</html>
