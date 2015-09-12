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