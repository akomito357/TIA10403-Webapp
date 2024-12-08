<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="java.util.*"%>


<%
	OrderMasterService_interface ordSvc = new OrderMasterService();
	List<OrderMasterVO> orderList = ordSvc.getAllOrder();
	pageContext.setAttribute("orderList", orderList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listAllOrder</title>
<style>
	table.resultTable {
		border: 2px solid black;
		table-layout: auto;
		border-collapse: collapse;
	}
	table tr, table th, table td{
		border: 1px solid black;
		text-align: center;
		padding: 3px 3px;
		
	}
	table td{
		border: 1px dotted black;
	}
/* 	table th{ */
/* 		border: 2px solid black; */
/* 	} */
	
	.btn{
 		padding:3px 12px;
	}
/* 	a.page{ */
/* 		margin: auto 3px; */
/* 	} */
</style>
</head>
	<body>
		<h4><a href="select_page.jsp">回首頁</a></h4>
	
		<h3>全部訂單資料</h3>
		<%@include file="page1.file" %>
		<table class="resultTable">
			<tr>
				<th>訂單編號</th>		
				<th>訂單桌位ID</th>
				<th>餐廳ID</th>
				<th>消費者ID</th>
				<th>結帳狀態</th>
				<th>小計</th>
				<th>最後結帳金額</th>
				<th>入座時間</th>
				<th>獲得折扣點數</th>
				<th>花費折扣點數</th>
				<th>結帳時間</th>	
				<th>修改</th>	
				<th>刪除</th>	
			</tr>
			
			<c:forEach var="orderMasterVO" items="${orderList}" begin="<%= pageIndex %>" end="<%= pageIndex + rowPerPage - 1 %>">
				<tr>
					<td>${orderMasterVO.orderId}</td>		
					<td>${orderMasterVO.orderTableId}</td>
					<td>${orderMasterVO.restId}</td>
					<td>${orderMasterVO.memberId}</td>
					<td>${orderMasterVO.orderStatus}</td>
					<td>${orderMasterVO.subtotalPrice}</td>
					<td>${orderMasterVO.totalPrice}</td>
<%-- 					<td>${orderMasterVO.servedDatetime}</td> --%>
					<td><fmt:formatDate value="${orderMasterVO.servedDatetime}" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>${orderMasterVO.pointEarned}</td>
					<td>${orderMasterVO.pointUsed}</td>
					<td><fmt:formatDate value="${orderMasterVO.checkoutDatetime}" pattern="yyyy-MM-dd HH:mm"/></td>	
					<td class="btn"><form method="post" action="<%=request.getContextPath()%>/ordermaster/order.do">
							<input type="submit" value="修改">
							<input type="hidden" name="orderId" value="${orderMasterVO.orderId}">
							<input type="hidden" name="action" value="getOneForUpdate">
					</form></td>
					<td class="btn"><form method="post" action="<%=request.getContextPath()%>/ordermaster/order.do">
							<input type="submit" value="刪除">
							<input type="hidden" name="orderId" value="${orderMasterVO.orderId}">
							<input type="hidden" name="action" value="deleteOrder">
					</form></td>
				</tr>
			</c:forEach>
		</table>
		<br>
		
		<%@ include file="page2.file" %>
	</body>

</html>