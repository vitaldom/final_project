<%@ include file="../includes/lang_change_block.jsp"%>

<html>
<head>
    <title>SubmittedDeclarationsPage</title>
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
    </tr>

    <c:forEach items="${sessionScope.declarationList}" var="declaration">
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
                <c:out value="${declaration.status}" />
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <form action="${pageContext.request.contextPath}/singleDeclaration" method="POST">
                    <input type="hidden" name="declaration_id" value="${declaration.id}">
                    <button type="submit" id="button-submit"><fmt:message key="submitted.declarations.view.declaration.button" /></button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
