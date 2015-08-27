

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.com.dto.Services;
import service.com.order.dto.OrderInfoDTO;
import service.com.order.ws.InsertOrderWS;
/**
 * Servlet implementation class SuspendRestore
 */
public class SuspendRestore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuspendRestore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	SuspendRestore SR;
	String serviceID;
	OrderInfoDTO newOrder=new OrderInfoDTO();
	public String setOrderInXML(String serviceID){
		System.out.println("from setOrderInXML method entered...");
		newOrder.setServiceID(serviceID);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1024); 
		XMLEncoder xmlEncoder = new XMLEncoder(baos);
		xmlEncoder.writeObject(newOrder);
		//xmlEncoder.close();
		String xml = baos.toString();
		System.out.println("from setOrderXML = "+xml); 
		return xml;
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		serviceID=(request.getParameter("check"));
		System.out.println("output serviceID "+serviceID);
		System.out.println("service id is "+ serviceID);
		String custid=(String)request.getSession().getAttribute("customerid");
		List<Services> list = (List<Services>)request.getSession(false).getAttribute("serviceobj");
		System.out.println("entered suspendrestore");
		
		String status=null;
		int count=1;
		Iterator<Services> attribute=list.iterator();
		while(attribute.hasNext()){
			Services service = (Services) attribute.next();
			if(service.getServiceID().equals(serviceID)){
			status=service.getStatus();
				System.out.println(count);
				count++;
		
			}
		}
		System.out.println("status is "+status);
		//SR.setOrderInXML(serviceID);
		//the following code is copied from the method setOrderInXML for testing
		
		newOrder.setServiceID(serviceID);
		/*ByteArrayOutputStream baos = new ByteArrayOutputStream(1024); 
		XMLEncoder xmlEncoder = new XMLEncoder(baos);
		xmlEncoder.writeObject(newOrder);*/
		//xmlEncoder.close();
		
		/*String xml = baos.toString();
		System.out.println("xml value = "+xml); */
		//newOrder.setOrderXML("<services><service_id>"+newOrder.getServiceID()+"</service_id></services>");  
		
		//newOrder.setOrderXML(SR.setOrderInXML(serviceID));
		newOrder.setCustomerID(custid);
		
		newOrder.setOrderStatus("PR");
		
		//System.out.println("the xml contains "+newOrder.getOrderXML());
	   if(status.equals("Active")){
			newOrder.setOrderType("Suspend");
			newOrder.setCurrentStatus("Suspended");
			System.out.println(newOrder.getOrderType());
			
		}else
		{
			newOrder.setOrderType("Restore");
			newOrder.setCurrentStatus("Active");
			System.out.println(newOrder.getOrderType());
		}
		InsertOrderWS orderservice=new InsertOrderWS();
		try {
			orderservice.insertOrder(newOrder);
		orderservice.updateCustService(newOrder);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().println("<html>");
		request.getRequestDispatcher("Header.jsp").include(request, response);
		response.getWriter().println("<title></title><head><link rel='stylesheet' type='text/css' href='styles.css'>");
		response.getWriter().println("<link rel='stylesheet' href='theme.css'>");
		response.getWriter().println("<meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1'></head>");
	    response.getWriter().println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>");
	   
	   response.getWriter().println("<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script>");
	   response.getWriter().println("<div class=\"layout\">");
		response.getWriter().println("<body><div align='center'> <h1 class='vzh1'> Order Confirmation Page </h1></div>");
		if(newOrder.getOrderType().equals("Suspend")){
			response.getWriter().println(" <body><div align='center'> </br><h3 class=vzh3>Your Suspension</h3> </div> ");
		}
		else{
			response.getWriter().println("<body><div align='center'></br><h3 class=vzh3> Your Restoration</h3> </div> ");
		}
		response.getWriter().println("<div align='center'><h3 class=vzh3> order has been placed successfully </h3></br></div><div align='center'> <form action=''><input type='submit' value='Return to Home' class='vzbtnmedium'></form></div>");
		response.getWriter().println("</div>");
		request.getRequestDispatcher("Header2.jsp").include(request, response);
		response.getWriter().println("</body></html>");
	}
	
}
