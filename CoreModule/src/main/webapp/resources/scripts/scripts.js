function changeUsersInfoOld() {
	var methodURL = "/CoreModule/changeUsersInfoRequest";
	$.ajax({
		type : "POST",
		url : methodURL,
		data : $("#usersInfoForm").serialize(),
		async : true,
		success : function(data) {
			$("#jsonMessage").text(data);
		},
		error : function(e) {

		}
	});
}

function changeUsersInfo() {
	$('#result').html('');
	var usersInfo = new FormData();	
	usersInfo.append("name", $('#usersInfoForm input[name="name"]').val());
	usersInfo.append("surname", $('#usersInfoForm input[name="surname"]').val());
	usersInfo.append("bdate", $('#usersInfoForm input[name="bdate"]').val());
	usersInfo.append("email", $('#usersInfoForm input[name="email"]').val());
	usersInfo.append("phone", $('#usersInfoForm input[name="phone"]').val());
	usersInfo.append("file", avatar.files[0]);

	$.ajax({
		url : "/CoreModule/changeUsersInfoRequest",
		data : usersInfo,
		dataType : 'text',
		processData : false,
		contentType : false,
		type : 'POST',
		success : function(data) {
			$('#result').html(data);
		}
	});
}

function changeItemsInfo() {
	var methodURL = "/CoreModule/changeItemsInfoRequest";
	$.ajax({
		type : "POST",
		url : methodURL,
		data : $("#goodsInfoForm").serialize(),
		async : true,
		success : function(data) {
			$("#jsonMessage").text(data);
		},
		error : function(e) {

		}
	});
}

function sendImage() {
	var methodURL = "/CoreModule/image";
	$.ajax({
		type : "POST",
		url : methodURL,
		data : $("#imageForm").serialize(),
		async : true,
		success : function(data) {
			$("#imageMess").text(data);
		},
		error : function(e) {

		}
	});
}

//using FormData() object
function uploadFormData() {
	$('#result').html('');

	var itemsInfo = new FormData();
	itemsInfo.append("file", file2.files[0]);
	itemsInfo.append("name", $('#addItemForm input[name="name2"]').val());

	$.ajax({
		url : "/CoreModule/addItemRequest",
		data : itemsInfo,
		dataType : 'text',
		processData : false,
		contentType : false,
		type : 'POST',
		success : function(data) {
			$('#result').html(data);
		}
	});
}

function restGet() {
	var methodURL = "/CoreModule/restGet";
	$.ajax({
		type : "GET",
		url : methodURL,
		data : "json",
		success : function(data) {
			$("#jsonGetMessage").text(data);
		},
		error : function(e) {

		}
	});
}
