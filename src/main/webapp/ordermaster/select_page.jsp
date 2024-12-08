<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>訂單查詢</title>
    </head>
    <body>
        <h2>訂單資料查詢</h2>
    
<!--     	errorMsgs -->
		<c:if test="${!errorMsgs.isEmpty()}">
			<ul>
				<c:forEach  var="msgs" items="${errorMsgs}">
					<li style="color: red">${msgs}</li>
				</c:forEach>
			</ul>
		</c:if>

        <ul>
            <li>
                <a href="listAllOrder.jsp">查詢所有訂單</a>
            </li>
            <br>
            <li>
                <form method="post" action="<%=request.getContextPath()%>/ordermaster/order.do">
                    <b>輸入訂單編號（如：1）：</b>
                    <input type="text" name="orderId">
                    <input type="hidden" name="action" value="getOneForDisplay">
                    <input type="submit" value="送出">
                </form>
            </li>
            <br>
            
            <jsp:useBean id="odrSvc" scope="page" class="com.ordermaster.model.OrderMasterService" />
            
            <li>
            	<form method="post" action="<%=request.getContextPath()%>/ordermaster/order.do">
            		<b>查詢訂單編號：</b>
            		<select size=1 name="orderId">
<!--             			<option value=0>請選擇</option> -->
            			<c:forEach var="orderVO" items="${odrSvc.allOrder}">
            				<option value="${orderVO.orderId}">${orderVO.orderId}
            			</c:forEach>
            		</select>
            		<input type="hidden" name="action" value="getOneForDisplay">
            		<input type="submit" value="送出">
            	</form>
            </li>
            <br>
            <li>
            	<a href="addOrder.jsp">增加新訂單</a>
            </li>
        </ul>

    </body>
</html>