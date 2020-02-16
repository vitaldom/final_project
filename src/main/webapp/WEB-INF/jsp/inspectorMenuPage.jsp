<%@ include file="../includes/languageChangeBlock.jsp"%>
<%@ include file="../includes/logoutButton.jsp"%>

<html>
<head>
    <title>Tax Declaration app - <fmt:message key="inspector.menu.introduction" /></title>
    <link rel="icon" href="data:,">
</head>
<body>
<h2><fmt:message key="inspector.menu.introduction" /></h2>

<fmt:message key="label.welcome" />

<div>
    <h4><fmt:message key="inspector.menu.logged.as" /></h4>
    <p><c:out value="${sessionScope.user.firstName}" />&nbsp;<c:out value="${sessionScope.user.secondName}" /></p>
</div>
<hr>

<div>
    <form action="${pageContext.request.contextPath}/declarationsForCheck">
        <p><input type="submit" value="<fmt:message key="inspector.menu.check.reports.button" />"></p>
    </form>
</div>
<hr>

</body>
</html>
