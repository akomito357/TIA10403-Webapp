<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ordermaster.model.*"%>

<% OrderMasterVO orderMasterVO = (OrderMasterVO)request.getAttribute("orderMasterVO"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>listOneOrder</title>
	<style>
		table.resultTable {
			border: 1px solid black;
			table-layout: auto;
		}
		table tr, table th, table td{
			border: 1px solid black;
			text-align: center;
			padding: 3px 3px;
		}
	</style>

</head>
	<body>
		<h4><a href="select_page.jsp">回首頁</a></h4>
		
		<h3>訂單資料</h3>
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
			</tr>
			<tr>
				<td>${orderMasterVO.orderId}</td>		
				<td>${orderMasterVO.orderTableId}</td>
				<td>${orderMasterVO.restId}</td>
				<td>${orderMasterVO.memberId}</td>
				<td>${orderMasterVO.orderStatus}</td>
				<td>${orderMasterVO.subtotalPrice}</td>
				<td>${orderMasterVO.totalPrice}</td>
<%-- 				<td>${orderMasterVO.servedDatetime}</td> --%>
				<td><%= (orderMasterVO.getServedDatetime()).toString().substring(0, 19)%></td>
				<td>${orderMasterVO.pointEarned}</td>
				<td>${orderMasterVO.pointUsed}</td>
				<td><%= (orderMasterVO.getCheckoutDatetime()).toString().substring(0, 19)%></td>
<%-- 				<td>${orderMasterVO.checkoutDatetime}</td>	 --%>
			</tr>
		</table>
	</body>
</html>