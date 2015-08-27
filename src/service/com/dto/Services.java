package service.com.dto;

import java.io.Serializable;

import javax.jws.WebMethod;
import javax.jws.WebService;

@SuppressWarnings("serial")
@WebService
public class Services implements Serializable{
    private String serviceID;
    private String serviceName;
    private String status;
    public Services(){
    	
    }
    public Services(String serviceID, String serviceName, String status) {
		super();
		this.serviceID = serviceID;
		this.serviceName = serviceName;
		this.status = status;
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
	public String getServiceName() {
		return serviceName;
	}
    @WebMethod
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
    @WebMethod
	public String getStatus() {
		return status;
	}
    @WebMethod
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	@WebMethod
	public String toString() {
		return "Services [serviceID=" + serviceID + ", serviceName="
				+ serviceName + ", status=" + status + "]";
	}  
	
	

}
