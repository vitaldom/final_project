<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.sessionLang}"/>
<fmt:setBundle basename="web interface"/>


<html>
<head>
    <title>InspectorMenu</title>
    <link rel="icon" href="data:,">
</head>
<body>
<h2>Inspector Menu page.</h2>

<fmt:message key="label.welcome" />

</body>
</html>
