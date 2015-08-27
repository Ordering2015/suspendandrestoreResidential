package service.com.dto;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class GetCustomerInfoRequestDTO{
    private String customerID;
@WebMethod
    public String getCustomerID() {
            return customerID;
    }
@WebMethod
    public void setCustomerID(String customerID) {
            this.customerID = customerID;
    }
}

