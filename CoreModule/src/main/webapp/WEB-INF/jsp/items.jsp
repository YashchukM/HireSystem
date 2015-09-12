<%-- 
    Document   : items
    Created on : Sep 12, 2015, 1:13:52 PM
    Author     : alexander-ilkun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../layout/taglib.jsp"%>

<div style="padding:20px">
    <table class="mdl-data-table mdl-js-data-table">
        <thead>
            <tr>
                <th class="mdl-data-table__cell--non-numeric">Item name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="item">
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">
                        <a href="<spring:url value="/items/${item.id}.html" />">${item.name}</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>