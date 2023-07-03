package com.assignment.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dao.Product;
import com.dao.User;

/**
 * Servlet implementation class RemoveProduct
 */
@WebServlet("/RemoveProduct")
public class RemoveProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveProduct() {
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
			s.remove(p);
			tx.commit();
			s.close();
			response.sendRedirect("View");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			s.remove(p);
			response.sendRedirect("View");
		}
	}

}
