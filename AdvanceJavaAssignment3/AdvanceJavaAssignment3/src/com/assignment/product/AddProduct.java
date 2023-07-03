package com.assignment.product;

import java.io.IOException;
import java.util.List;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
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
import org.hibernate.criterion.Restrictions;

import com.dao.Product;
import com.dao.User;

import javax.servlet.annotation.MultipartConfig;

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
@MultipartConfig(maxFileSize=1048576)
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
		Configuration con = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Product.class);
		SessionFactory sf = con.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		HttpSession session = request.getSession(false);
		if(session.getAttribute("id")==null)
		{
			response.sendRedirect("index.jsp");
		}
		Part Image = null;
		try
		{
			Image = request.getPart("Image");
			long id = (long) session.getAttribute("id");
			String title = request.getParameter("Title");
			String Size=request.getParameter("Size");
			System.out.println(Size);
			int size = (int)Integer.parseInt(Size);
			String Quantity = request.getParameter("Quantity");
			long quantity = (long)Long.parseLong(Quantity);
			InputStream is = Image.getInputStream();
			byte[] byteImage = IOUtils.toByteArray(is);
			long allSize = (long)session.getAttribute("totalSize");
			allSize+=byteImage.length;
			if(allSize>10*1024*1024)
			{
				session.setAttribute("error", "Max Size of Image uploaded exceeded 10MB");
				response.sendRedirect("View");
				
			}
			Product p = new Product();
			p.setImage(byteImage);
			p.setQuantity(quantity);
			p.setSize(size);
			p.setTitle(title);
			User u = (User)s.get(User.class, id);
			p.setUser(u);
			s.save(p);
			tx.commit();
			s.close();
			response.sendRedirect("View");
		}
		catch(Exception e)
		{
			PrintWriter out = response.getWriter();
			session.setAttribute("error", "Image size should be 1MB");
			response.sendRedirect("View");
		}
		
		
		
	}

}
