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
                        <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable
                             mdl-textfield--floating-label mdl-textfield--align-right">
                            <label class="mdl-button mdl-js-button mdl-button--icon"
                                   for="fixed-header-drawer-exp">
                                <i class="material-icons">search</i>
                            </label>
                            <div class="mdl-textfield__expandable-holder">
                                <input class="mdl-textfield__input" type="text" name="sample"
                                       id="fixed-header-drawer-exp" />
                            </div>
                        </div>
                        <nav class="mdl-navigation">
                            <a class="mdl-navigation__link is-active" href="<spring:url value="/" />">Home</a>
                            <security:authorize access="hasRole('ROLE_ADMIN')">
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
