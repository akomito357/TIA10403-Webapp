<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ordermaster.model.*"%>

<% OrderMasterVO order = (OrderMasterVO)request.getAttribute("orderMasterVO"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>add a new order</title>
	</head>
<body>
	<h4><a href="select_page.jsp">回首頁</a></h4>
	<h3>訂單新增：</h3>
	
	<!--     	errorMsgs -->
		<ul>
			<c:forEach  var="msgs" items="${errorMsgs}">
				<li style="color: red">${msgs}</li>
			</c:forEach>
		</ul>
	
	<form method="post" action="order.do" name="orderForm">
		<table>
			<tr>
				<td>訂單桌位ID：</td>
				<td><input type="text" name="orderTableId" value="<%= (order == null)? "" : order.getOrderTableId() %>">
				</td>
			</tr>
			<tr>
				<td>餐廳ID：</td>
				<td><input type="text" name="restId" value="<%= (order==null) ? "" : order.getRestId() %>"/></td>
			</tr>
			<tr>
				<td>消費者ID：</td>
				<td><input type="text" name="memberId" value="<%=(order==null) ? "" : order.getMemberId() %>"/></td>
			</tr>
			<tr>
				<td>結帳狀態：</td>
				<td><select name="orderStatus">
					<option value=0 ${order.orderStatus == 0 ? 'selected' : ''}>未結帳</option>
					<option value=1 ${order.orderStatus == 1 ? 'selected' : ''}>已結帳</option>
				</select></td>
			</tr>
			<tr>
				<td>小計：</td>
				<td><input type="text" name="subtotalPrice" value="<%=(order==null) ? "0" : order.getSubtotalPrice() %>"></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>最後結帳金額：</td> -->
<!-- 				<td><input type="number" name="totalPrice"></td> -->
<!-- 			</tr> -->
			<tr>
				<td>入座時間：</td>
				<td><input type="datetime-local" name="servedDatetime" value="<%=(order==null) ? "" : order.getServedDatetime() %>"></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>獲得折扣點數：</td> -->
<!-- 				<td><input type="number" name="pointEarned"></td> -->
<!-- 			</tr> -->
			<tr>
				<td>花費折扣點數：</td>
				<td><input type="text" name="pointUsed" value="<%=(order==null) ? "0" : order.getPointUsed() %>"></td>
			</tr>
			<tr>
				<td>結帳時間：</td>
				<td><input type="datetime-local" name="checkoutDatetime" value="<%=(order==null) ? "" : order.getCheckoutDatetime() %>"></td>
			</tr>
		</table>
		<br>
		<input type="hidden" name="action" value="addOrder">
		<input type="submit" value="送出新增">
	</form>
</body>
</html>