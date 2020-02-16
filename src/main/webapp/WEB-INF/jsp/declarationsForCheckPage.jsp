<%@ include file="../includes/languageChangeBlock.jsp"%>
<%@ include file="../includes/logoutButton.jsp"%>

<html>
<head>
    <title>Tax Declaration app - <fmt:message key="declarations.for.check.header" /></title>
    <link rel="icon" href="data:,">
</head>
<body>

<h2><fmt:message key="declarations.for.check.header" /></h2>

<div>
    <h4><fmt:message key="inspector.menu.logged.as" /></h4>
    <p><c:out value="${sessionScope.user.firstName}" />&nbsp;<c:out value="${sessionScope.user.secondName}" /></p>
</div>
<hr>

<p style="color: green; font-weight: 700">${sessionScope.service_message}</p>
<c:remove var="service_message" scope="session" />

<table style="border: 1px solid;">
    <tr style="background-color: #dadada; height: 30px;">
        <th style="min-width: 150px;"><fmt:message key="submitted.declarations.table.id" /></th>
        <th style="min-width: 150px;"><fmt:message key="submitted.declarations.table.author" /></th>
        <th style="min-width: 150px;"><fmt:message key="submitted.declarations.table.year" /></th>
        <th style="min-width: 150px;"><fmt:message key="submitted.declarations.table.status" /></th>
        <th style="min-width: 150px;"><fmt:message key="declarations.for.check.check" /></th>
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
                <form action="${pageContext.request.contextPath}/checkDeclaration" method="POST">
                    <input type="hidden" name="decIndexInArray" value="${varStatus.index}">
                    <button type="submit" id="button-submit"><fmt:message key="declarations.for.check.check.button" /></button>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
