<%-- 
    Document   : classic
    Created on : Jul 11, 2015, 7:19:15 PM
    Author     : alexander-ilkun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://storage.googleapis.com/code.getmdl.io/1.0.0/material.indigo-pink.min.css">
        <script src="https://storage.googleapis.com/code.getmdl.io/1.0.0/material.min.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <%--<script type="text/javascript" src="resources/scripts/scripts.js"></script>--%>
        <script>
            function changeUsersInfoOld() {
                var methodURL = "/CoreModule/changeUsersInfoRequest";
                $.ajax({
                    type: "POST",
                    url: methodURL,
                    data: $("#usersInfoForm").serialize(),
                    async: true,
                    success: function (data) {
                        $("#jsonMessage").text(data);
                    },
                    error: function (e) {

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
                    url: "/CoreModule/changeUsersInfoRequest",
                    data: usersInfo,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function (data) {
                        $('#result').html(data);
                    }
                });
            }

            function changeItemsInfo() {
                var methodURL = "/CoreModule/changeItemsInfoRequest";
                $.ajax({
                    type: "POST",
                    url: methodURL,
                    data: $("#goodsInfoForm").serialize(),
                    async: true,
                    success: function (data) {
                        $("#jsonMessage").text(data);
                    },
                    error: function (e) {

                    }
                });
            }

            function sendImage() {
                var methodURL = "/CoreModule/image";
                $.ajax({
                    type: "POST",
                    url: methodURL,
                    data: $("#imageForm").serialize(),
                    async: true,
                    success: function (data) {
                        $("#imageMess").text(data);
                    },
                    error: function (e) {

                    }
                });
            }

//using FormData() object
            function uploadFormData() {
                $('#result').html('');

                var itemsInfo = new FormData();
                itemsInfo.append("file", file2.files[0]);
                itemsInfo.append("name", $('#addItemForm input[name="name2"]').val());
                itemsInfo.append("category", $('#addItemForm input[name="category"]').val());
                itemsInfo.append("price", $('#addItemForm input[name="price"]').val());
                itemsInfo.append("description", $('#addItemForm input[name="description"]').val());
                itemsInfo.append("minHireTime", $('#addItemForm input[name="minHireTime"]').val());
                itemsInfo.append("maxHireTime", $('#addItemForm input[name="maxHireTime"]').val());
                //console.log($('#addItemForm input[name="description"]').val());

                $.ajax({
                    url: "/CoreModule/addItemRequest.html",
                    data: itemsInfo,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function (data) {
//                        $('#result').html(data);
                        location.reload();
                    },
                    error: function (data) {
                        location.reload();
                    }
                });
            }

            function restGet() {
                var methodURL = "/CoreModule/restGet";
                $.ajax({
                    type: "GET",
                    url: methodURL,
                    data: "json",
                    success: function (data) {
                        $("#jsonGetMessage").text(data);
                    },
                    error: function (e) {

                    }
                });
            }
        </script>
        <title><tiles:getAsString name="title" /></title>
    </head>
    <body>

        <div class="mdl-layout mdl-js-layout mdl-layout--overlay-drawer-button">
            <div class="mdl-layout mdl-js-layout mdl-layout--overlay-drawer-button">
                <header class="mdl-layout__header">
                    <div class="mdl-layout-icon"></div>
                    <div class="mdl-layout__header-row">
                        <span class="mdl-layout-title">Hire System</span>
                        <div class="mdl-layout-spacer"></div>
                        <security:authorize access="isAuthenticated()">
                            <form action="<spring:url value="/search.html" />" method="GET">
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable
                                     mdl-textfield--floating-label mdl-textfield--align-right">
                                    <label class="mdl-button mdl-js-button mdl-button--icon"
                                           for="fixed-header-drawer-exp">
                                        <i class="material-icons">search</i>
                                    </label>
                                    <div class="mdl-textfield__expandable-holder">
                                        <input class="mdl-textfield__input" type="text" name="search"
                                               id="fixed-header-drawer-exp" />
                                    </div>
                                </div>
                                <!--                            <input type="submit" value="Search" class="btn mdl-button mdl-js-button mdl-button--raised mdl-button--accent mdl-js-ripple-effect" />-->
                            </form>
                        </security:authorize>
                        <nav class="mdl-navigation">
                            <a class="mdl-navigation__link is-active" href="<spring:url value="/" />">Index</a>
                            <security:authorize access="isAuthenticated()">
                                <a class="mdl-navigation__link" href="<spring:url value="/home.html" />">Home</a>
                                <a class="mdl-navigation__link" href="<spring:url value="/users.html" />">Users</a>
                                <a class="mdl-navigation__link" href="<spring:url value="/items.html" />">Items</a>
                            </security:authorize>
                            <security:authorize access="! isAuthenticated()">
                                <a class="mdl-navigation__link" href="<spring:url value="/login.html" />">Login</a>
                                <a class="mdl-navigation__link" href="<spring:url value="/register.html" />">Register</a>
                            </security:authorize>
                            <security:authorize access="isAuthenticated()">
                                <a class="mdl-navigation__link" href="<spring:url value="/logout" />">Logout</a>
                            </security:authorize>
                        </nav>
                    </div>
                </header>
                <div class="mdl-layout__drawer">
                    <span class="mdl-layout-title">Menu</span>
                    <nav class="mdl-navigation">
                        <a class="mdl-navigation__link" href="#">Nav link 2</a>
                        <a class="mdl-navigation__link" href="#">Nav link 2</a>
                        <a class="mdl-navigation__link" href="#">Nav link 3</a>
                    </nav>
                </div>
                <main class="mdl-layout__content">
                    <tiles:insertAttribute name="body" />
                    <tiles:insertAttribute name="footer" />
                </main>

            </div>
        </div>
    </body>
</html>
