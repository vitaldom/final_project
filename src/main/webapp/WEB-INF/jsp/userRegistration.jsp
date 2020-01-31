<%@ include file="../includes/lang_change_block.jsp"%>

<html>
<head>
    <title>UserRegistration</title>
    <link rel="icon" href="data:,">
</head>
<body>
<hr>
<h3>Session lang: </h3><c:out value="${sessionScope.sessionLang}" />
<hr>
<h2><fmt:message key="registration.page.header" />:</label></h2><br>

<form class="form" method="post" action="${pageContext.request.contextPath}/registration">

    <table border="0">
        <p style='color: red;'>${error_message}</p>
        <tr>
            <td><label for="firstName"><fmt:message key="registration.first.name" />:</label></td>
            <td><input required type="text" id="firstName" name="firstName" value="${user.firstName}"/> </td>
        </tr>
        <tr>
            <td><label for="secondName"><fmt:message key="registration.second.name" />:</label></td>
            <td><input required type="text" id="secondName" name="secondName" value= "${user.secondName}"/> </td>
        </tr>
        <tr>
            <td><label for="login"><fmt:message key="registration.login" />:</label></td>
            <td><input required type="text" id="login" name="login" value= "${user.login}"/> </td>
        </tr>
        <tr>
            <td><label for="password"><fmt:message key="registration.password" />:</label></td>
            <td><input required type="password" id="password" name="password" value="${user.password}"/> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <fmt:message key="registration.register.button" var="buttonValue" />
                <input type="submit" name="submit" value="${buttonValue}">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
