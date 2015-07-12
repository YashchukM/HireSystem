<%-- 
    Document   : login
    Created on : Jul 12, 2015, 3:03:35 PM
    Author     : alexander-ilkun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../layout/taglib.jsp"%>

<div style="padding:20px">
    <form action="<spring:url value="/login" />" method="POST">

    <!--
        TODO:Should add support for csrf? Check security.xml for <csrf disabled="">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    -->
        <div class="mdl-textfield mdl-js-textfield textfield-demo">
            <input type="text" name="username" class="mdl-textfield__input" id="sample1" />
            <label class="mdl-textfield__label" for="sample1">Name:</label>
        </div>
        <br>
        <div class="mdl-textfield mdl-js-textfield textfield-demo">
            <input type="password" name="password" class="mdl-textfield__input" id="sample2" required/>
            <label class="mdl-textfield__label" for="sample2">Password:</label>
        </div>
        <br>
        <input name="submit" type="submit" value="Sign in" class="btn mdl-button mdl-js-button mdl-button--raised mdl-button--accent mdl-js-ripple-effect" />
    </form>
</div>
