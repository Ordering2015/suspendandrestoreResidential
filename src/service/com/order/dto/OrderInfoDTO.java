package service.com.order.dto;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class OrderInfoDTO {
	
	private String customerID;
	private String serviceID;
	private String orderType;
	private String orderStatus;
	private String currentStatus;
	@WebMethod
	public String getCustomerID() {
		return customerID;
	}
	@WebMethod
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	@WebMethod
	public String getServiceID() {
		return serviceID;
	}
	@WebMethod
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	@WebMethod
	public String getOrderType() {
		return orderType;
	}
	@WebMethod
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	@WebMethod
	public String getOrderStatus() {
		return orderStatus;
	}
	@WebMethod
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@WebMethod
	public String getCurrentStatus() {
		return currentStatus;
	}
	@WebMethod
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
}
