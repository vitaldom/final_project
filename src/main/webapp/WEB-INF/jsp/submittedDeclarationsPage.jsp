<%@ include file="../includes/lang_change_block.jsp"%>

<html>
<head>
    <title>Tax Declaration app - <fmt:message key="submitted.declarations.page.header" /></title>
    <link rel="icon" href="data:,">
</head>
<body>

<h2><fmt:message key="submitted.declarations.page.header" /></h2>

<div>
    <h4><fmt:message key="client.menu.logged.as" /></h4>
    <p><c:out value="${sessionScope.user.firstName}" />&nbsp;<c:out value="${sessionScope.user.secondName}" /></p>
</div>
<hr>

<table style="border: 1px solid;">
    <tr style="background-color: #dadada; height: 30px;">
        <th style="min-width: 150px;"><fmt:message key="submitted.declarations.table.id" /></th>
        <th style="min-width: 150px;"><fmt:message key="submitted.declarations.table.author" /></th>
        <th style="min-width: 150px;"><fmt:message key="submitted.declarations.table.year" /></th>
        <th style="min-width: 150px;"><fmt:message key="submitted.declarations.table.status" /></th>
        <th style="min-width: 150px;"><fmt:message key="submitted.declarations.table.view" /></th>
        <th style="min-width: 150px;"><fmt:message key="submitted.declarations.table.correction" /></th>

    </tr>

    <c:forEach items="${sessionScope.declarationList}" var="declaration" varStatus="varStatus">
        <tr style="padding-bottom: 20px; text-align: center">
            <td style="padding: 15px 0; border: 1px solid;">
                <c:out value="${declaration.id}" />
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <c:out value="${declaration.author.firstName}" />
                <c:out value="${declaration.author.secondName}" />
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <c:out value="${declaration.declarationYear.year}" />
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <fmt:message key="${declaration.status.resourceBundleKey}" />
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <form action="${pageContext.request.contextPath}/singleDeclaration" method="POST">
                        <input type="hidden" name="decIndexInArray" value="${varStatus.index}">
                    <button type="submit" id="button-submit"><fmt:message key="submitted.declarations.view.declaration.button" /></button>
                </form>
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <c:if test="${declaration.status == 'UNDER_CORRECTION'}">
                    <form action="${pageContext.request.contextPath}/declinedDeclaration" method="POST">
                        <input type="hidden" name="decIndexInArray" value="${varStatus.index}">
                        <button type="submit" ><fmt:message key="submitted.declarations.correct.button" /></button>
                    </form>
                </c:if>
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
