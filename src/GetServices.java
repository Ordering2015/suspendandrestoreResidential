

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.com.dto.GetCustomerInfoRequestDTO;
import service.com.dto.Services;
import service.com.ws.*;

/**
 * Servlet implementation class GetServices
 */
public class GetServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetServices() {
    	
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custid= request.getParameter("custid");
		HttpSession hs = request.getSession(true);
		hs.setAttribute("customerid", custid);
		Vector<Services> servicelist;
		GetCustomerInfoRequestDTO getdet=new GetCustomerInfoRequestDTO ();
		CustomerInfoWebService obj=new CustomerInfoWebService();
		getdet.setCustomerID(custid);
		try {
			System.out.println("entered try block ");
			List<Services> list=new ArrayList<Services>();
			servicelist= obj.getCustomerInfo(getdet);
			for (Iterator iterator = servicelist.iterator(); iterator.hasNext();) {
				Services services = (Services) iterator.next();
				list.add(services);
				log(""+services);
				
			}
			Iterator<Services> it= servicelist.iterator();
			hs.setAttribute("serviceobj",list);
			int idt=1;
			int count=1;
			
			response.getWriter().println("<html> ");
			request.getRequestDispatcher("Header.jsp").include(request, response);
			response.getWriter().println("<title></title><head><link rel='stylesheet' type='text/css' href='styles.css'>");
		    response.getWriter().println("<link rel='stylesheet' href='theme.css'>");
		    response.getWriter().println("<meta charset='utf-8'><meta name='viewport' content='width=device-width, initial-scale=1'> "); 
		
		    response.getWriter().println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>");
		    response.getWriter().println("<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js'></script> </head>");
		   
		    response.getWriter().println("<div class=\"layout\">");
			response.getWriter().println("<body><div align='center'><h1 class='vzh1' >Customer Services</h1></br><h3  class='vzh3' >Select one service to Suspend or Restore</h3></br> <form name='myForm' action='SuspendRestore' method='post'><table border='1'  class='vztable'>");
			response.getWriter().println("<tr><td>Serial Number</td><td>ServiceName</td><td>Current Status</td></tr>");
			while(it.hasNext()){
				Services services = (Services) it.next();
				response.getWriter().println("<tr><td><input type='radio' id="+idt+" name='check'  value="+services.getServiceID()+" required='required' ><label for="+idt+">"+count+"</label></td><td>  "+services.getServiceName()+" </td><td> "+services.getStatus()+"</td></tr>");
				count++;
				idt++;
			}
			response.getWriter().println("</table></br><div align='center'><input type='submit' value='Suspend/Restore'   class='vzbtnmedium' /></div>");
			response.getWriter().println("</form></div>");
			request.getRequestDispatcher("Header2.jsp").include(request, response);
			response.getWriter().println("</body> </html>");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
