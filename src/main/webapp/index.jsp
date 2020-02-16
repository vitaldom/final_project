<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.lang ? param.lang : sessionScope.sessionLang}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="web interface"/>

<html>
<head>
    <title>Tax Declaration app - <fmt:message key="label.page.title" /></title>
    <link rel="icon" href="data:,">
</head>

<body>

<h4>Оберіть мову / Choose language</h4>
<form id="language-form">
    <button	name="lang" type="submit" value="uk" ${lang == 'uk' ? 'selected' : ' '}>Українська</button> |
    <button name="lang" type="submit" value="en" ${lang == 'en' ? 'selected' : ' '}>English</button>
</form>
<br>
<hr>
<br>

<p style="color:red; font-weight: 700;">${error_message}</p>
<c:remove var="error_message" scope="session" />

<p style="color: green; font-weight: 700;">${service_message}</p>
<c:remove var="service_message" scope="session" />

<h2><fmt:message key="label.login" /></h2>
<form action="login" method="post" >
    <input type="text" required placeholder="<fmt:message key="label.loginPlaceholder" />" name="login"><br>
    <input type="password" required placeholder="<fmt:message key="label.passwordPlaceholder" />" name="password"><br><br>
    <input class="button" type="submit" value="<fmt:message key="label.loginButton" />">
</form>
<br>
<hr>
<br>

<form action="${pageContext.request.contextPath}/registration">
    <p><input type="submit" value="<fmt:message key="label.newUserRegistrationButton" />"></p>
</form>

</body>
</html>
