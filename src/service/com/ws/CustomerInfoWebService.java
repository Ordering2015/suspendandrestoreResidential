package service.com.ws;

import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import service.com.dao.GetCustomerInfoDAO;
import service.com.dto.GetCustomerInfoRequestDTO;
import service.com.dto.Services;
@WebService
public class CustomerInfoWebService {
@WebMethod	
	public Vector<Services> getCustomerInfo(GetCustomerInfoRequestDTO customerRequest) throws Exception{
	Vector<Services> getEmployeeResponseVect= new Vector<Services>();
	GetCustomerInfoDAO customerDao = new GetCustomerInfoDAO();
	getEmployeeResponseVect = customerDao.getCustomerInfo(customerRequest.getCustomerID());
	System.out.println(getEmployeeResponseVect);
	return getEmployeeResponseVect;
	}
}
