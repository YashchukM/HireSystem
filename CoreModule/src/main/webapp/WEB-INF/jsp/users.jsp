<%-- 
    Document   : users
    Created on : Jul 12, 2015, 10:17:01 AM
    Author     : alexander-ilkun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../layout/taglib.jsp"%>

<div style="padding:20px">
    <table class="mdl-data-table mdl-js-data-table">
        <thead>
            <tr>
                <th class="mdl-data-table__cell--non-numeric">User name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">
                        <a href="<spring:url value="/users/${user.id}.html" />">${user.name}</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>