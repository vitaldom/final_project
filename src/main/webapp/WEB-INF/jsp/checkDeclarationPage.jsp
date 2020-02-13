<%@ include file="../includes/lang_change_block.jsp"%>

<html>
<head>
    <title>Tax Declaration app - <fmt:message key="check.declaration.page.header" /></title>
    <link rel="icon" href="data:,">
</head>
<body>

<h2><fmt:message key="check.declaration.page.header" /></h2>

<div>
    <h4><fmt:message key="inspector.menu.logged.as" /></h4>
    <p><c:out value="${sessionScope.user.firstName}" />&nbsp;<c:out value="${sessionScope.user.secondName}" /></p>
</div>
<hr>

<table border="0">
    <tr>
        <td><fmt:message key="submitted.declarations.table.id" />:</td>
        <td><c:out value="${sessionScope.declarationToProceed.id}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="registration.first.name" />:</td>
        <td><c:out value="${sessionScope.declarationToProceed.author.firstName}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="registration.second.name" />:</td>
        <td><c:out value="${sessionScope.declarationToProceed.author.secondName}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="new.declaration.declaration.year" />:</td>
        <td><c:out value="${sessionScope.declarationToProceed.declarationYear.year}"/> </td>
    </tr>

    <tr>
        <td><fmt:message key="new.declaration.tax.category" />:</td>
        <td><fmt:message key="${sessionScope.declarationToProceed.taxCategory.resourceBundleKey}" /></td>
    </tr>
    <tr>
        <td><fmt:message key="new.declaration.income" />:</td>
        <td><c:out value="${sessionScope.declarationToProceed.income}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="new.declaration.tax.sum.declared" />:</td>
        <td><c:out value="${sessionScope.declarationToProceed.taxSumDeclared}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="submitted.declarations.table.status" />:</td>
        <td><fmt:message key="${sessionScope.declarationToProceed.status.resourceBundleKey}" /></td>
    </tr>
</table>
<hr>

<form action="${pageContext.request.contextPath}/approveDeclaration">
    <button type="submit" id="button-approve"><fmt:message key="check.declaration.approve.button" /></button>
</form>
<hr>

<form action="${pageContext.request.contextPath}/declineDeclaration" method="post">
    <input style="width: 500px; height: 100px" required type="text" name="declineMessage"
           placeholder="<fmt:message key="check.declaration.decline.reason" />" />
    <button type="submit" id="button-decline"><fmt:message key="check.declaration.decline.button" /></button>
</form>
<hr>

</body>
</html>
