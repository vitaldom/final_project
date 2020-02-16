<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%--<%@ page isELIgnored="false"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="language" value="${not empty param.lang ? param.lang : sessionScope.sessionLang}" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="web interface"/>

<form id="language-form">
    <button	name="lang" type="submit" value="uk" ${lang == 'uk' ? 'selected' : ' '}>Українська</button> |
    <button name="lang" type="submit" value="en" ${lang == 'en' ? 'selected' : ' '}>English</button>
</form>
<hr>
