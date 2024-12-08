package com.ordermaster.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrderMasterVO implements Serializable{
	private Integer orderId;
	private Integer orderTableId;
	private Integer restId;
	private Integer memberId;
	private Integer orderStatus;
	private Double subtotalPrice;
	private Double totalPrice;
	private Timestamp servedDatetime;
	private Integer pointEarned;
	private Integer pointUsed;
	private Timestamp checkoutDatetime;
	
	public OrderMasterVO() {
		
	}
	
	public Integer getOrderId(){
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getOrderTableId() {
		return orderTableId;
	}
	public void setOrderTableId(Integer orderTableId) {
		this.orderTableId = orderTableId;
	}
	
	public Integer getRestId() {
		return restId;
	}
	public void setRestId(Integer restId) {
		this.restId = restId;
	}
	
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Double getSubtotalPrice() {
		return subtotalPrice;
	}
	public void setSubtotalPrice(Double subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Timestamp getServedDatetime() {
		return servedDatetime;
	}

	public void setServedDatetime(Timestamp servedDatetime) {
		this.servedDatetime = servedDatetime;
	}
	
	public Integer getPointEarned() {
		return pointEarned;
	}

	public void setPointEarned(Integer pointEarned) {
		this.pointEarned = pointEarned;
	}

	public Integer getPointUsed() {
		return pointUsed;
	}

	public void setPointUsed(Integer pointUsed) {
		this.pointUsed = pointUsed;
	}

	public Timestamp getCheckoutDatetime() {
		return checkoutDatetime;
	}
	public void setCheckoutDatetime(Timestamp checkoutDatetime) {
		this.checkoutDatetime = checkoutDatetime;
	}
	
	public String toString() {
		return "[order_id = " + orderId 
				+ ", order_table_id = " + orderTableId 
				+ ", rest_id = " + restId
				+ ", member_id = " + memberId
				+ ", order_status = " + orderStatus
				+ ", subtotal_price = " + subtotalPrice
				+ ", total_price = " + totalPrice
				+ ", served_datetime = " + servedDatetime
				+ ", point_earned = " + pointEarned
				+ ", point_used = " + pointUsed
				+ ", checkout_datetime = " + checkoutDatetime
				+ "]";
	}

}
