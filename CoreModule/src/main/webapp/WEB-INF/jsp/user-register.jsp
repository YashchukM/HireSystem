<%-- 
    Document   : user-register
    Created on : Jul 12, 2015, 12:16:47 PM
    Author     : alexander-ilkun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../layout/taglib.jsp"%>

<div style="padding:20px">
    <c:if test="${param.success eq true}">
        <div><h4>Registration successful!</h4></div>
    </c:if>

    <form:form commandName="user" cssClass="">
        <div class="mdl-textfield mdl-js-textfield textfield-demo">
            <form:input path="login" cssClass="mdl-textfield__input" id="sample1" />
            <label class="mdl-textfield__label" for="sample1">Name:</label>
        </div>
        <br>
        <div class="mdl-textfield mdl-js-textfield textfield-demo">
            <form:password path="password" cssClass="mdl-textfield__input" id="sample2" />
            <label class="mdl-textfield__label" for="sample2">Password:</label>
        </div>
        <br>
        <input type="submit" value="Register" class="btn mdl-button mdl-js-button mdl-button--raised mdl-button--accent mdl-js-ripple-effect" />
    </form:form>
</div>