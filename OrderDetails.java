package com.Hexaware.CMS.Model;

public class OrderDetails {
	private int orderNo;
	private int venderId;
	private String customerId;
	private int foodId;
	private int quantity;
	private String datetime;
	private int orderValue;
	private String orderStatus;

	public OrderDetails(int orderNo, int venderId, String customerId, int foodId, int quantity, String datetime,
			int orderValue, String orderStatus) {
		this.orderNo = orderNo;
		this.venderId = venderId;
		this.customerId = customerId;
		this.foodId = foodId;
		this.quantity = quantity;
		this.datetime = datetime;
		this.orderValue = orderValue;
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderDetails---> Customer Id : " + customerId + ", Date and Time : " + datetime + ", Food Id : "
				+ foodId + ", Order No : " + orderNo + ", Order Status : " + orderStatus + ", Order Value : "
				+ orderValue + ", Quantity : " + quantity + ", Vendor Id " + venderId + "]";
	}

	public void setCustId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustId() {
		return customerId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setOrderId(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderId() {
		return orderNo;
	}

	public void setVenId(int venderId) {
		this.venderId = venderId;
	}

	public Object getVenId() {
		return venderId;
	}

	public void setOrderAmount(int orderValue) {
		this.orderValue = orderValue;
	}

	public int getOrderAmount() {
		return orderValue;
	}

	public void setDateTime(String dateTime) {
		this.datetime = dateTime;
	}

	public String getDateTime() {
		return datetime;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

}