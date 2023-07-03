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
import org.hibernate.criterion.Restrictions;

import com.dao.*;
import java.util.*;

/**
 * Servlet implementation class View
 */
@WebServlet("/View")
public class View extends HttpServlet {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		else
		{
			long id = (long) session.getAttribute("id");
			List<Product> result = s.createCriteria(Product.class)
					.add(Restrictions.eq("user_obj.id",id)).list();
			session.removeAttribute("totalSize");
			long allSize =0;
			for(Product p: result)
			{
				allSize+=p.getImageByteSize();
			}
			session.setAttribute("totalSize", allSize);
			tx.commit();
			s.close();
			request.setAttribute("products", result);
			request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
