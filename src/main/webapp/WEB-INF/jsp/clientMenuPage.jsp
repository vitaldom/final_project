<%@ include file="../includes/lang_change_block.jsp"%>

<html>
<head>
    <title>UserMenuPage</title>
    <link rel="icon" href="data:,">
</head>
<body>

<h2><fmt:message key="client.menu.introduction" /></h2>

<fmt:message key="label.welcome" />
<div>
    <h4><fmt:message key="client.menu.logged.as" /></h4>
    <p><c:out value="${sessionScope.user.firstName}" />&nbsp;<c:out value="${sessionScope.user.secondName}" /></p>
</div>

<hr>
<p style="color: green; font-weight: 700">${service_message}</p>
<c:remove var="service_message" scope="session" />

<div>
    <form action="${pageContext.request.contextPath}/newDeclaration">
        <p><input type="submit" value="<fmt:message key="client.menu.new.report.button" />"></p>
    </form>
</div>
<hr>

<div>
    <form action="${pageContext.request.contextPath}/submittedDeclarations">
        <p><input type="submit" value="<fmt:message key="client.menu.submitted.declarations.button" />"></p>
    </form>
</div>

</body>
</html>
