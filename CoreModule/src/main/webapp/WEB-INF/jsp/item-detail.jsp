<%-- 
    Document   : item-detail
    Created on : Sep 12, 2015, 1:16:21 PM
    Author     : alexander-ilkun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="../layout/taglib.jsp"%>

<div style="padding:20px">

    <c:if test="${param.success eq true}">
        <div><h4>Action successful!</h4></div>
    </c:if>

    <h5>Name: ${item.name}</h5>
    ${item.itemDetails.owner.login}
    <h5>Rating: ${itemRating}</h5>
    <h5>Vote:<a href="<spring:url value="/items/${item.id}/voteItem.html" />">vote</a></h5>
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
            <c:forEach items="${itemReviews}" var="itemReview" varStatus="status">
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">
                        ${itemReview.review.text}
                    </td>
                    <td class="mdl-data-table__cell--non-numeric">
                        <a href="<spring:url value="/users/${itemReview.review.author.id}.html" />">${itemReview.review.author.login}</a>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric">
                        ${itemReview.review.date}
                    </td>
                    <td>
                        ${itemReviewsRatings[status.index]}
                    </td>
                    <td class="mdl-data-table__cell--non-numeric">
            <center>
                <a href="<spring:url value="/items/${item.id}/voteReview/${itemReview.review.id}.html" />">vote</a>
            </center>
            </td>
            </tr>
        </c:forEach>
        <tbody>
    </table>
    <form:form commandName="itemReview" cssClass="">
        <div class="mdl-textfield mdl-js-textfield">
            <form:textarea path="review.text" cssClass="mdl-textfield__input" rows="3" id="sample1" ></form:textarea>
                <label class="mdl-textfield__label" for="sample1">Review:</label>
            </div>
            <br>
            <input type="submit" value="Submit review" class="btn mdl-button mdl-js-button mdl-button--raised mdl-button--accent mdl-js-ripple-effect" />
    </form:form>
</div>