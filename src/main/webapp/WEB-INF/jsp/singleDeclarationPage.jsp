<%@ include file="../includes/lang_change_block.jsp"%>

<html>
<head>
    <title>SingleDeclarationPage</title>
    <link rel="icon" href="data:,">
</head>
<body>

<h2><fmt:message key="single.declaration.page.header" /></h2>

<div>
    <h4><fmt:message key="client.menu.logged.as" /></h4>
    <p><c:out value="${sessionScope.user.firstName}" />&nbsp;<c:out value="${sessionScope.user.secondName}" /></p>
</div>
<hr>

<table border="0">
    <tr>
        <td><fmt:message key="submitted.declarations.table.id" />:</td>
        <td><c:out value="${sessionScope.declarationToShow.id}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="registration.first.name" />:</td>
        <td><c:out value="${sessionScope.declarationToShow.author.firstName}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="registration.second.name" />:</td>
        <td><c:out value="${sessionScope.declarationToShow.author.secondName}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="new.declaration.declaration.year" />:</td>
        <td><c:out value="${sessionScope.declarationToShow.declarationYear.year}"/> </td>
    </tr>

    <tr>
        <td><fmt:message key="new.declaration.tax.category" />:</td>
        <td><fmt:message key="${sessionScope.declarationToShow.taxCategory.resourceBundleKey}" /></td>
    </tr>
    <tr>
        <td><fmt:message key="new.declaration.income" />:</td>
        <td><c:out value="${sessionScope.declarationToShow.income}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="new.declaration.tax.sum.declared" />:</td>
        <td><c:out value="${sessionScope.declarationToShow.taxSumDeclared}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="submitted.declarations.table.status" />:</td>
        <td><fmt:message key="${sessionScope.declarationToShow.status.resourceBundleKey}" /></td>
    </tr>
</table>

</body>
</html>
