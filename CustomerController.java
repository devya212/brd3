package com.nucleus.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nucleus.model.Customer;
import com.nucleus.model.Validation;
import com.nucleus.service.CustomerServiceImpl;
import com.nucleus.service.ICustomerService;

/**
 * Servlet implementation class CustomerController
 */
@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static Logger log=Logger.getLogger(com.nucleus.controller.CustomerController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerController() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session=request.getSession(false); 

		boolean check,check1;
		Customer customer=new Customer();
		Validation validation=new Validation();
		
		PrintWriter out=response.getWriter();
		DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
        ICustomerService ics=new CustomerServiceImpl();
        RequestDispatcher rd=null;
        
        
       
		
		if(request.getParameter("b1").equals("add"))
        {
			 customer.setCustomerCode(request.getParameter("code"));
				customer.setCustomerName(request.getParameter("cname"));
				customer.setCustomerAddress1(request.getParameter("caddress1"));
				customer.setCustomerAddress2(request.getParameter("caddress2"));
				customer.setCustomerPinCode(request.getParameter("pin"));
				customer.setEmail(request.getParameter("email"));
				customer.setContactNo(request.getParameter("contactno"));
				customer.setPrimaryContactPerson(request.getParameter("cperson"));
				customer.setFlag(request.getParameter("flag"));
                   customer.setRecordStatus("N");
		        customer.setCreateDate(dateobj);
		       customer.setCreatedBy((String)session.getAttribute("uid"));
		
		 check=validation.validationFunction(customer);
		 check1=validation.primaryKeyValidation(request.getParameter("code"));
		out.print(check);
		if(check==true && check1==true)
		{
			boolean c=ics.insert(customer);
			if(c)
			{
				request.setAttribute("addmsg","customer details added successfully");
                rd=request.getRequestDispatcher("makerHome.jsp");
                rd.forward(request,response);

			}
			else
			{
				request.setAttribute("addmsg","customer details not  added successfully");
                rd=request.getRequestDispatcher("makerHome.jsp");
                rd.forward(request,response);

			}
		}
		else
		{
			String msg1="";
			String msg=validation.errorType(customer);
			if(check1==false)
			{
				msg1=msg+"primary key violation";
			}
			else
				msg1=msg;
			log.error(msg1);
			request.setAttribute("validationerr", msg1);
			rd=request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		}
        }
        if(request.getParameter("b1").equals("update"))
        {
        	
        	List<Customer> list1=ics.getCustomerDetailsById(request.getParameter("code1"));
        	request.setAttribute("customer", list1);
        	rd=request.getRequestDispatcher("update.jsp");
        	rd.forward(request, response);
        	
        }
        if(request.getParameter("b1").equals("delete"))
        {
        	boolean ck=ics.delete(request.getParameter("code"));
        	if(ck==true)
        	{
        		request.setAttribute("delmsg","customer details deleted successfully");
                rd=request.getRequestDispatcher("makerHome.jsp");
                
                rd.forward(request,response);

        	}
        	else
        	{
        		request.setAttribute("delmsg","customer details not deleted successfully");
                rd=request.getRequestDispatcher("makerHome.jsp");
                
                rd.forward(request,response);

        		
        	}
        	
        }
        if(request.getParameter("b1").equals("toupdate"))
        {
        	customer.setCustomerCode(request.getParameter("code"));
     		customer.setCustomerName(request.getParameter("cname"));
     		customer.setCustomerAddress1(request.getParameter("caddress1"));
     		customer.setCustomerAddress2(request.getParameter("caddress2"));
     		customer.setCustomerPinCode(request.getParameter("pin"));
     		customer.setEmail(request.getParameter("email"));
     		customer.setContactNo(request.getParameter("contactno"));
     		customer.setPrimaryContactPerson(request.getParameter("cperson"));
     		customer.setFlag(request.getParameter("flag"));
        	customer.setRecordStatus("N");
        	customer.setModifiedDate(dateobj);
        	customer.setModifiedBy((String)session.getAttribute("uid"));
        	DateFormat df1=new SimpleDateFormat("yyyy-mm-dd");
        	Date date;
			try {
				date = df1.parse(request.getParameter("createdate"));
				customer.setCreateDate(date);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
        	
        	customer.setCreatedBy(request.getParameter("createdby"));
        	check=validation.validationFunction(customer);
        	if(check==true)
        	{
        		boolean c=ics.update(customer,request.getParameter("code1"));
        		if(c)
        		{
        		request.setAttribute("updatemsg","customer details updated successfully");
                rd=request.getRequestDispatcher("makerHome.jsp");
                
               rd.forward(request,response);
        		}
        		else
        		{
        			request.setAttribute("updatemsg","customer details not updated successfully");
                    rd=request.getRequestDispatcher("makerHome.jsp");
                    rd.forward(request,response);
        		}

        	}
        	 else
        	 {
        		
        	 }
        }
        if(request.getParameter("b1").equals("vid"))
        {
        	List<Customer> list1=ics.viewCustomerDetailsById(request.getParameter("code"));
        	request.setAttribute("list",list1);
        	log.info("Log Info");
        	rd=request.getRequestDispatcher("view1.jsp");
        	rd.forward(request, response);
        	
        }
        if(request.getParameter("b1").equals("vname"))
        {
        	List<Customer> list1=ics.viewCustomerDetailsByName(request.getParameter("cname"));
        	request.setAttribute("list",list1);
        	rd=request.getRequestDispatcher("view1.jsp");
        	rd.forward(request, response);
        }
        if(request.getParameter("b1").equals("1"))
        {
        	List<Customer> list1=ics.viewAllCustomerDetails();
        	request.setAttribute("list",list1);
        	rd=request.getRequestDispatcher("view1.jsp");
        	rd.forward(request, response);
        }
        if(request.getParameter("b1").equals("4"))
        {
        	List<Customer> list1=ics.viewAllCustomerDetails();
        	request.setAttribute("list",list1);
        	rd=request.getRequestDispatcher("view2.jsp");
        	rd.forward(request, response);
        }
        if(request.getParameter("b1").equals("2"))
        {
        	List<Customer> list1=ics.viewAllCustomerDetailsAsc();
        	request.setAttribute("list",list1);
        	rd=request.getRequestDispatcher("view1.jsp");
        	rd.forward(request, response);
        }
        if(request.getParameter("b1").equals("3"))
        {
        	List<Customer> list1=ics.viewAllCustomerDetailsDesc();
        	request.setAttribute("list",list1);
        	rd=request.getRequestDispatcher("view1.jsp");
        	rd.forward(request, response);
        }
        if(request.getParameter("b1").equals("logout"))
        {
        	
        	if(session.getAttribute("uid")!=null)
        	{
        		
        		session.removeAttribute("uid");
        		
        	     session.invalidate();
        	    response.sendRedirect("login.jsp");
        	}
        }
        if(request.getParameter("b1").equals("checkerview"))
        {
        	List<Customer> list1=ics.viewAllCustomerDetails();
        	request.setAttribute("list",list1);
        	rd=request.getRequestDispatcher("checkerview.jsp");
        	rd.forward(request, response);
        }
        if(request.getParameter("b1").equals("approve1"))
        {
        	List<Customer> list1=ics.viewCustomerDetailsById(request.getParameter("code"));
        	ics.updateMasterTable(list1);
        	boolean check2=ics.updateStatus(request.getParameter("code"), "A");
        	out.print(check2);
        }
        if(request.getParameter("b1").equals("reject"))
        {
        	boolean check2=ics.updateStatus(request.getParameter("code"), "R");
        	out.print(check2);
        	
        	
        }

		
	}
	}

