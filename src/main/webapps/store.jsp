<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Прайс-лист</title>
  </head>
  <body>
    <form>
      <table>
        <tr>
          <td>Категория:</td>
          <td>Наименование:</td>
          <td>Цена от:</td>
          <td>Цена до:</td>
          <td> </td>
        </tr>
        <tr>
          <td><input class="textbox" type="text" value="${requestScope.category_name}" id="category_name" name="category_name" placeholder="Категория"></td> 
          <td><input class="textbox" type="text" value="${requestScope.product_name}" id="product_name" name="product_name" placeholder="Продукт"></td> 
          <td><input class="textbox" type="text" value="${requestScope.min_price}" id="min_price" name="min_price" placeholder="Мин.цена" pattern="([0-9]{1,8})(\.[0-9]{0,2})?"></td> 
          <td><input class="textbox" type="text" value="${requestScope.max_price}" id="max_price" name="max_price"  placeholder="Макс.цена" pattern="([0-9]{1,8})(\.[0-9]{0,2})?"></td> 
          <td><input class="button" type="submit" value="Найти"></td>
        </tr>
      </table>
    </form>
    <div>
      <table class="table_blur">
        <tr>
          <th>Категория</th>
          <th>Наименование</th>
          <th>Цена</th>
        </tr>
        <c:if test="${not empty requestScope.prodList}">
	        <c:forEach var="prod" items="${requestScope.prodList}" >
	        	<tr>
	       			<td>${prod.cat.name}</td>
	       			<td>${prod.name}</td>
	       			<td><fmt:formatNumber pattern="#######0.00" type="number" value="${prod.price}"/></td>
	       		</tr>
	   		</c:forEach>
        </c:if>
      </table> 
    </div>  
  </body>
</html>