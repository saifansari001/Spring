package com.assignment.product;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dao.Product;
import com.dao.User;

/**
 * Servlet implementation class EditProduct
 */
@WebServlet("/EditProduct")
@MultipartConfig(maxFileSize=1048576)
public class EditProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		long product_id = (long)Long.parseLong(request.getParameter("product_id"));
		Configuration con = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Product.class);
		SessionFactory sf = con.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		HttpSession session = request.getSession(false);
		if(session.getAttribute("id")==null)
		{
			response.sendRedirect("index.jsp");
		}
		Product p = (Product)s.get(Product.class, product_id);
		if(p.getUser().getId()!=(long)session.getAttribute("id"))
		{
			response.sendRedirect("View");
		}
		else
		{
		request.setAttribute("product", p);
		request.getRequestDispatcher("/edit-page.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		Configuration con = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Product.class);
		SessionFactory sf = con.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		HttpSession session = request.getSession(false);
		if(session.getAttribute("id")==null)
		{
			response.sendRedirect("index.jsp");
		}
		try
		{
		
		long id =(long) Long.parseLong(request.getParameter("id"));
		String title = request.getParameter("title");
		String Size=request.getParameter("size");
		System.out.println(Size);
		int size = (int)Integer.parseInt(request.getParameter("size"));
		
		String Quantity = request.getParameter("quantity");
		long quantity = (long)Long.parseLong(Quantity);
		Part Image = null;
		Image=request.getPart("image");
		Product p =(Product)s.get(Product.class, id);
		p.setTitle(title);
		p.setSize(size);
		p.setQuantity(quantity);	
		InputStream is = Image.getInputStream();
		byte[] byteImage = IOUtils.toByteArray(is);
		
		if(byteImage.length!=0)
		{
		p.setImage(byteImage);
		}
	
		s.update("Product", p);
		tx.commit();
		s.close();
		response.sendRedirect("View");
		}
		catch(Exception e)
		{
			session.setAttribute("error", "Image size should be 1MB");
			response.sendRedirect("View");
		}
	}

}
