<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Прайс-лист</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<form method="post">
    <table>
        <tr>
            <td>Категория:</td>
            <td></td>
        </tr>
        <tr>
            <td><input class="textbox" type="text" value="${requestScope.category_name}" id="category_name"
                       name="category_name" placeholder="Ниаменование новой категории"></td>
            <td><input class="button" type="submit" value="Добавить"></td>
        </tr>
    </table>
</form>
<div>
    <table class="table_blur">
        <tr>
            <th>Id</th>
            <th>Наименование</th>
        </tr>
        <c:if test="${not empty requestScope.catList}">
            <c:forEach var="category" items="${requestScope.catList}">
                <tr>
                    <td>${category.id}</td>
                    <td>${category.name}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>