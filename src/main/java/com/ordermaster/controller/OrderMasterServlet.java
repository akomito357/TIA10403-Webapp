package com.ordermaster.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ordermaster.model.OrderMasterService;
import com.ordermaster.model.OrderMasterService_interface;
import com.ordermaster.model.OrderMasterVO;

//@WebServlet("/ordermaster/order.do")
public class OrderMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderMasterService_interface svc;
	
	public void init() {
		svc = new OrderMasterService();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 處理編碼
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//		DateFormat timestampFormat = new
		String forwardPath = "";
		
		switch(action){
			case "getOneForDisplay":
				forwardPath = getOneForDisplay(req, res);
//				System.out.println(forwardPath);
				break;
			case "addOrder":
				forwardPath = addOrder(req, res);
				break;
			case "getOneForUpdate":
				forwardPath = getOneForUpdate(req, res);
				break;
			case "updateOrder":
				forwardPath = updateOrder(req, res);
				break;
			case "deleteOrder":
				forwardPath= deleteOrder(req, res);
				break;
			default:
				forwardPath = "/ordermaster/select_page.jsp";	
		}
		
//		res.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = res.getWriter();
		
		RequestDispatcher forwardView = req.getRequestDispatcher(forwardPath);
		forwardView.forward(req, res);
	}

	
	private String getOneForDisplay(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<String> errorMsgs = new LinkedList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		// 1. 接收請求參數 - 輸入格式的錯誤處理
		String orderIdStr = req.getParameter("orderId");
		
		if (orderIdStr == null || orderIdStr.trim().isEmpty()) {
			errorMsgs.add("請輸入訂單編號");
		}
		if (!errorMsgs.isEmpty()) {
			return "/ordermaster/select_page.jsp";
		}
		
		Integer orderId = null;
		try {			
			orderId = Integer.valueOf(orderIdStr);
		} catch (NumberFormatException e) {
			errorMsgs.add("訂單編號格式不正確");
		}
		
		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req.getRequestDispatcher("");
//			failureView.forward(req, res);

			return "/ordermaster/select_page.jsp";
		}
		
		// 2. 開始查詢資料
		OrderMasterService_interface svc = new OrderMasterService();
		OrderMasterVO orderVO = svc.getOneOrder(orderId);
		
		if (orderVO == null) {
			errorMsgs.add("查無訂單！");
		}
		if (!errorMsgs.isEmpty()) {
			return "/ordermaster/select_page.jsp";
		}
		
		// 3. 查詢完成，準備轉交（Send the Success view）
		req.setAttribute("orderMasterVO", orderVO);
		return "/ordermaster/listOneOrder.jsp";
		
	}
	
	
	private String getOneForUpdate(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new LinkedList<>();
		req.setAttribute(getServletName(), errorMsgs);
		
		// 1. 接收請求參數
		String orderIdStr = req.getParameter("orderId"); // 這是從listAllOrder的修改按鈕傳過來的
		Integer orderId = Integer.valueOf(orderIdStr);
		
		// 2. 開始查詢
		OrderMasterVO orderVO = svc.getOneOrder(orderId);
		
		// 3. 查詢完成轉交
		req.setAttribute("orderMasterVO", orderVO);
		return "/ordermaster/updateOrderInput.jsp";
		
	}
	
	public String updateOrder(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new LinkedList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		// 1. 接收請求參數
		// orderId
		Integer orderId = null;
		try {
			orderId = Integer.valueOf(req.getParameter("orderId").trim());			
		} catch(NumberFormatException ne) {
			System.out.println(orderId);
			errorMsgs.add("訂單號碼！請輸入數字");
		}
		
		// orderTableId
		Integer orderTableId = null;
		try {			
			orderTableId = Integer.valueOf(req.getParameter("orderTableId").trim());
		} catch (NumberFormatException ne) {
			orderTableId = 0;
			errorMsgs.add("點餐桌位號碼請輸入數字");
		}
		
		// 餐廳編號
		Integer restId = null;
		try {			
			restId = Integer.valueOf(req.getParameter("restId").trim());
		} catch (NumberFormatException ne) {
			restId = 0;
			errorMsgs.add("餐廳ID請輸入數字");
		}
		
		// 消費者編號
		Integer memberId = null;
		try {
			memberId = Integer.valueOf(req.getParameter("memberId").trim());
		} catch (NumberFormatException e) {
			memberId = 0;
			errorMsgs.add("消費者ID請輸入數字");
		}
		
		// 結帳狀態
		Integer orderStatus = Integer.valueOf(req.getParameter("orderStatus").trim());
		
		// subtotalPrice
		Double subtotalPrice = null;
		try {
			subtotalPrice = Double.valueOf(req.getParameter("subtotalPrice").trim());
		} catch (NumberFormatException e) {
			subtotalPrice = 0.0;
			errorMsgs.add("小計請輸入數字");
		}
		
		// totalPrice 不用（service計算）
		
		// servedDatetime
		Timestamp servedDatetime = null;
//		try {			
//			servedDatetime = Timestamp.valueOf(req.getParameter("servedDatetime").trim().replace("T", " "));
//		} catch (IllegalArgumentException e) {
////			servedDatetime = new Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入入座日期與時間");
//		}
		
		try {			
			String serveTimeStr = req.getParameter("servedDatetime").trim().replace("T", " ");
//			原本是如2024-12-07T14:40的形式，因此拿掉T加上秒數
//			System.out.println(serveTimeStr);
			
			servedDatetime = Timestamp.valueOf(serveTimeStr + ":00");
			System.out.println(servedDatetime);
				
		} catch (IllegalArgumentException ie) {
			ie.printStackTrace();
//			servedDatetime = new Timestamp(System.currentTimeMillis());
			errorMsgs.add("請輸入入座日期與時間");
		} 
		
		// pointEarned 不用
		
		// pointUsed
		Integer pointUsed = null;
		try {
			pointUsed = Integer.valueOf(req.getParameter("pointUsed").trim());
		} catch (NumberFormatException e) {
			pointUsed = 0;
			errorMsgs.add("使用折扣點數請輸入數字");
		}
		
		// checkoutDatetime
//		Timestamp checkoutDatetime = null;
//		try {
//			checkoutDatetime = Timestamp.valueOf(req.getParameter("checkoutDatetime").trim().replace("T", " "));
//		} catch (IllegalArgumentException e) {
////			checkoutDatetime = new Timestamp(System.currentTimeMillis());
//			errorMsgs.add("請輸入結帳日期與時間");
//		}
		
		Timestamp checkoutDatetime = null;
		
		try {
//			原本是如2024-12-07T14:40的形式，因此拿掉T加上秒數
			String checkoutTimestr = req.getParameter("checkoutDatetime").trim().replace("T"," ");
			checkoutDatetime = Timestamp.valueOf(checkoutTimestr + ":00");			
		} catch (IllegalArgumentException ie) {
			ie.printStackTrace();
//			checkoutDatetime = new Timestamp(System.currentTimeMillis());
			errorMsgs.add("請輸入結帳日期與時間");
		}
		
		// 如果有填錯，顯示錯誤訊息&保留剛才填對的東西
		OrderMasterVO order = new OrderMasterVO();
		order.setOrderId(orderId);
		order.setOrderTableId(orderTableId);
		order.setRestId(restId);
		order.setMemberId(memberId);
		order.setOrderStatus(orderStatus);
		order.setSubtotalPrice(subtotalPrice);
		order.setServedDatetime(servedDatetime);
		order.setPointUsed(pointUsed);
		order.setCheckoutDatetime(checkoutDatetime);
		
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("orderMasterVO", order);
			return "/ordermaster/updateOrderInput.jsp";
		}
		
		// 2. 呼叫service修改
		order = svc.updateOrder(orderId, orderTableId, restId, memberId, orderStatus, subtotalPrice, servedDatetime, pointUsed, checkoutDatetime);
		
		// 3. 導頁面
		req.setAttribute("orderMasterVO", order);
		req.setAttribute("orderId", orderId);
		return "/ordermaster/listOneOrder.jsp";
	}
	
	private String addOrder(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new LinkedList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		/** 1. 接收請求參數 **/
		// 點餐桌位號碼
//		String orderTableIdStr = req.getParameter("orderTableId"); 
//		if (orderTableIdStr == null || orderTableIdStr.trim().isEmpty()) {
//			errorMsgs.add("點餐桌位號碼請勿空白");
//		}
		Integer orderTableId = null;
		try {
			orderTableId = Integer.valueOf(req.getParameter("orderTableId").trim()); 			
		} catch (NumberFormatException ne) {
			orderTableId = 0;
			errorMsgs.add("點餐桌位號碼請輸入數字");
		}
		
		// 餐廳編號
		Integer restId = null;
		try {
			restId = Integer.valueOf(req.getParameter("restId").trim()); 			
		} catch (NumberFormatException ne) {
			restId = 0;
			errorMsgs.add("餐廳ID請輸入數字");
		}
		
		// 消費者編號
		Integer memberId = null;
		try {
			memberId = Integer.valueOf(req.getParameter("memberId").trim()); 			
		} catch (NumberFormatException ne) {
			memberId = 0;
			errorMsgs.add("消費者ID請輸入數字");
		}
		
		// 結帳狀態不用檢查（因為是下拉選單）
		Integer orderStatus = Integer.valueOf(req.getParameter("orderStatus").trim());
		
		// 小計
		Double subtotalPrice = null;
		try {
			subtotalPrice = Double.valueOf(req.getParameter("subtotalPrice").trim());
		} catch (NumberFormatException ne) {
			subtotalPrice = 0.0;
			errorMsgs.add("小計請輸入數字");
		}
		
		// 入座時間
		Timestamp servedDatetime = null;

		try {			
			String serveTimeStr = req.getParameter("servedDatetime").trim().replace("T", " ");
//			原本是如2024-12-07T14:40的形式，因此拿掉T加上秒數
//			System.out.println(serveTimeStr);
			
			servedDatetime = Timestamp.valueOf(serveTimeStr + ":00");
			System.out.println(servedDatetime);
				
		} catch (IllegalArgumentException ie) {
			ie.printStackTrace();
//			servedDatetime = new Timestamp(System.currentTimeMillis());
			errorMsgs.add("請輸入入座日期與時間");
		} 
		
		// 花費點數
		Integer pointUsed = null;
		try {
			pointUsed = Integer.valueOf(req.getParameter("pointUsed").trim()); 			
		} catch (NumberFormatException ne) {
			pointUsed = 0;
			errorMsgs.add("花費折扣點數請輸入數字");
		}
		
		// 結帳時間
		Timestamp checkoutDatetime = null;
		
		try {
//			原本是如2024-12-07T14:40的形式，因此拿掉T加上秒數
			String checkoutTimestr = req.getParameter("checkoutDatetime").trim().replace("T"," ");
			checkoutDatetime = Timestamp.valueOf(checkoutTimestr + ":00");			
		} catch (IllegalArgumentException ie) {
			ie.printStackTrace();
//			checkoutDatetime = new Timestamp(System.currentTimeMillis());
			errorMsgs.add("請輸入結帳日期與時間");
		}
	
		// 是否可以把如優惠點數的計算方法寫在service
		
		OrderMasterVO order = new OrderMasterVO();
		order.setOrderTableId(orderTableId);
		order.setRestId(restId);
		order.setMemberId(memberId);
		order.setOrderStatus(orderStatus);
		order.setSubtotalPrice(subtotalPrice);

//		// totalPrice = subtotalPrice - pointUsed * 10
//		Double totalPrice = subtotalPrice - pointUsed * 10;
//		order.setTotalPrice(totalPrice);
		
		order.setServedDatetime(servedDatetime);
		
//		// pointEarned = totalPrice / 10
//		Double pointEarnedDouble = (totalPrice / 10.0);
//		Integer pointEarned = pointEarnedDouble.intValue();
//		order.setPointEarned(pointEarned);
		
		order.setPointUsed(pointUsed);
		order.setCheckoutDatetime(checkoutDatetime);
		
		// 如果有錯誤就傳到錯誤頁(搜尋&顯示錯誤)，但已填寫資料回傳
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("orderMasterVO", order);
			return "/ordermaster/addOrder.jsp";
		}
		
		// 2. 開始新增資料
		svc.addOrder(orderTableId, restId, memberId, orderStatus, subtotalPrice, servedDatetime, pointUsed, checkoutDatetime);
		
		// 3. 轉送
		return "/ordermaster/listAllOrder.jsp";
		
	}
	
	private String deleteOrder(HttpServletRequest req, HttpServletResponse res) {
		List<String> errorMsgs = new LinkedList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		Integer orderId = Integer.valueOf(req.getParameter("orderId").trim());
		svc.deleteOrder(orderId);
		return "/ordermaster/listAllOrder.jsp";
	}
	
	
}
