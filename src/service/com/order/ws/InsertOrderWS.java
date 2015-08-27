package service.com.order.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import service.com.order.dao.OrderInfoDAO;
import service.com.order.dto.OrderInfoDTO;
@WebService
public class InsertOrderWS {
	
	OrderInfoDAO orderDao = new OrderInfoDAO();
	@WebMethod
	public  void insertOrder(OrderInfoDTO sAndROrder) throws Exception{
		
		orderDao.insertOrderInfo(sAndROrder.getCustomerID(), sAndROrder.getServiceID(), sAndROrder.getOrderType(), sAndROrder.getOrderStatus());
		
	}
	public void updateCustService(OrderInfoDTO sAndROrder) throws Exception{
		orderDao.updateCustomerServices(sAndROrder.getCustomerID(), sAndROrder.getServiceID(), sAndROrder.getCurrentStatus());
	}
}
