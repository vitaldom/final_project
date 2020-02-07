<%@ include file="../includes/lang_change_block.jsp"%>

<html>
<head>
    <title>CorrectDeclarationPage</title>
    <link rel="icon" href="data:,">
</head>

<body>

<h2><fmt:message key="correct.declaration.page.header" /></h2><br>
<hr>

<h4><fmt:message key="correct.declaration.inspectors.reason" /></h4>
<c:out value="${sessionScope.declarationToShow.correctionMessage}" />
<hr>

<form class="form" method="post" action="${pageContext.request.contextPath}/correctDeclaration">

    <table border="0">
<%--        <p style='color: red;'>${error_message}</p>--%>
        <tr style="background-color: #dadada; height: 30px;">
            <th style="min-width: 150px;"><fmt:message key="correct.declaration.field.name" /></th>
            <th style="min-width: 150px;"><fmt:message key="correct.declaration.new.data" /></th>
            <th style="min-width: 150px;"><fmt:message key="correct.declaration.old.data" /></th>
        </tr>
        <tr>
            <td><label for="firstName"><fmt:message key="registration.first.name" />:</label></td>
            <td><input required type="text" id="firstName" name="firstName" value="${sessionScope.declarationToShow.author.firstName}"/> </td>
            <td><c:out value="${sessionScope.declarationToShow.author.firstName}"/> </td>
        </tr>
        <tr>
            <td><label for="secondName"><fmt:message key="registration.second.name" />:</label></td>
            <td><input required type="text" id="secondName" name="secondName" value= "${sessionScope.declarationToShow.author.secondName}"/> </td>
            <td><c:out value="${sessionScope.declarationToShow.author.secondName}"/> </td>
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
            <td><c:out value="${sessionScope.declarationToShow.declarationYear.year}"/> </td>
        </tr>
        <tr>
            <td><label for="taxCategory"><fmt:message key="new.declaration.tax.category" />:</label></td>
            <td><select required id="taxCategory" name="taxCategory">
                <option value="PREFERENTIAL"><fmt:message key="new.declaration.preferential.tax" /></option>
                <option value="EMPLOYEE"><fmt:message key="new.declaration.employee.tax" /></option>
                <option value="ENTREPRENEUR"><fmt:message key="new.declaration.entrepreneur.tax" /></option>
            </select>
            </td>
            <td><fmt:message key="${sessionScope.declarationToShow.taxCategory.resourceBundleKey}" /></td>
        </tr>
        <tr>
            <td><label for="income"><fmt:message key="new.declaration.income" />:</label></td>
            <td><input required type="number" id="income" name="income" value="${sessionScope.declarationToShow.income}"/> </td>
            <td><c:out value="${sessionScope.declarationToShow.income}"/> </td>

        </tr>
        <tr>
            <td><label for="taxSumDeclared"><fmt:message key="new.declaration.tax.sum.declared" />:</label></td>
            <td><input required type="number" id="taxSumDeclared" name="taxSumDeclared" value="${sessionScope.declarationToShow.taxSumDeclared}"/> </td>
            <td><c:out value="${sessionScope.declarationToShow.taxSumDeclared}"/> </td>
        </tr>
        <tr>
            <td colspan ="2">
                <fmt:message key="correct.declaration.submit.button" var="buttonValue" />
                <input type="submit" name="submit" value="${buttonValue}">
            </td>
        </tr>
    </table>
</form>
<hr>

<form action="${pageContext.request.contextPath}/inspectorChangeRequest" method="post">
    <input style="width: 500px; height: 100px" required type="text" name="inspectorChangeComment"
           placeholder="<fmt:message key="correct.declaration.inspector.change.comment" />" />
    <button type="submit" id="button-decline"><fmt:message key="correct.declaration.inspector.change.button" /></button>
</form>
<hr>

</body>
</html>
