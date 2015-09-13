<%-- 
    Document   : user-detail.jsp
    Created on : Jul 12, 2015, 11:37:29 AM
    Author     : alexander-ilkun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../layout/taglib.jsp"%>

<div style="padding:20px">

    <c:if test="${param.success eq true}">
        <div><h4>Action successful!</h4></div>
    </c:if>

    <c:if test="${isOwner eq true}">
        <script>
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
		url : "/CoreModule  /addItemRequest",
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

        </script>
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

        <%--<img src="getImage/222"/>--%>

        <input type="button" value="GET!" onclick="restGet()" />

        <span id="jsonMessage"></span>
        <span id="jsonGetMessage"></span>
        <div id="result"></div>

<!--        <a href="account">Account</a>-->
    </c:if>

    <h5>Login: ${user.login}</h5>
    <h5>Rating: ${userRating}</h5>
    <h5>Vote:<a href="<spring:url value="/users/${user.id}/voteUser.html" />">vote</a></h5>
    <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
        <thead>
            <tr>
                <th class="mdl-data-table__cell--non-numeric">Review:</th>
                <th class="mdl-data-table__cell--non-numeric">Author:</th>
                <th class="mdl-data-table__cell--non-numeric">Date:</th>
                <th>Rating:</th>
                <th class="mdl-data-table__cell--non-numeric">Vote:</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${userReviews}" var="userReview" varStatus="status">
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">
                        ${userReview.review.text}
                    </td>
                    <td class="mdl-data-table__cell--non-numeric">
                        <a href="<spring:url value="/users/${userReview.review.author.id}.html" />">${userReview.review.author.login}</a>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric">
                        ${userReview.review.date}
                    </td>
                    <td>
                        ${userReviewsRatings[status.index]}
                    </td>
                    <td class="mdl-data-table__cell--non-numeric">
            <center>
                <a href="<spring:url value="/users/${user.id}/voteReview/${userReview.review.id}.html" />">vote</a>
            </center>
            </td>
            </tr>
        </c:forEach>
        <tbody>
    </table>
    <form:form commandName="userReview" cssClass="">
        <div class="mdl-textfield mdl-js-textfield">
            <form:textarea path="review.text" cssClass="mdl-textfield__input" rows="3" id="sample1" ></form:textarea>
                <label class="mdl-textfield__label" for="sample1">Review:</label>
            </div>
            <br>
            <input type="submit" value="Submit review" class="btn mdl-button mdl-js-button mdl-button--raised mdl-button--accent mdl-js-ripple-effect" />
    </form:form>
</div>