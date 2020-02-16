<%@ include file="../includes/languageChangeBlock.jsp"%>
<%@ include file="../includes/logoutButton.jsp"%>

<html>
<head>
    <title>Tax Declaration app - <fmt:message key="new.declaration.page.header" /></title>
    <link rel="icon" href="data:,">
</head>

<body>

<h2><fmt:message key="new.declaration.page.header" /></h2><br>

<form class="form" method="post" action="${pageContext.request.contextPath}/newDeclaration">

    <table border="0">
        <p style='color: red;'>${error_message}</p>
        <c:remove var="error_message" scope="session" />
        <tr>
            <td><label for="firstName"><fmt:message key="registration.first.name" />:</label></td>
            <td><input required type="text" id="firstName" name="firstName" value="${sessionScope.user.firstName}"/> </td>
        </tr>
        <tr>
            <td><label for="secondName"><fmt:message key="registration.second.name" />:</label></td>
            <td><input required type="text" id="secondName" name="secondName" value= "${sessionScope.user.secondName}"/> </td>
        </tr>
        <tr>
            <td><label for="declarationYear"><fmt:message key="new.declaration.declaration.year" />:</label></td>
            <td><select required id="declarationYear" name="declarationYear">
                    <option value="2020">2020</option>
                    <option value="2019">2019</option>
                    <option value="2018">2018</option>
                    <option value="2017">2017</option>
                    <option value="2016">2016</option>
                    <option value="2015">2015</option>
                </select>
            </td>
        </tr>
        <tr>
            <td><label for="taxCategory"><fmt:message key="new.declaration.tax.category" />:</label></td>
            <td><select required id="taxCategory" name="taxCategory">
                <option value="preferential"><fmt:message key="new.declaration.preferential.tax" /></option>
                <option value="employee"><fmt:message key="new.declaration.employee.tax" /></option>
                <option value="entrepreneur"><fmt:message key="new.declaration.entrepreneur.tax" /></option>
            </select>
            </td>
        </tr>
        <tr>
            <td><label for="income"><fmt:message key="new.declaration.income" />:</label></td>
            <td><input required type="number" id="income" name="income" value=""/> </td>
        </tr>
        <tr>
            <td><label for="taxSumDeclared"><fmt:message key="new.declaration.tax.sum.declared" />:</label></td>
            <td><input required type="number" id="taxSumDeclared" name="taxSumDeclared" value=""/> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <fmt:message key="new.declaration.submit.button" var="buttonValue" />
                <input type="submit" name="submit" value="${buttonValue}">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
