package com.assignment.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.*;
import com.dao.*;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		PrintWriter out = response.getWriter();
//		out.print("Hello World");
//		User u = new User();
//		u.setUsername("admin");
//		u.setHashedPassword("admin");
////		u.setId(10);
//		Configuration con = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Product.class);
//		SessionFactory sf = con.buildSessionFactory();
//		Session s = sf.openSession();
//		Transaction tx = s.beginTransaction();
//		long id = (long) s.save(u);
//		System.out.println(id);
//		User u2 = new User();
//		u2.setUsername("root");
//		u2.setHashedPassword("root");
////		u2.setId(11);
//		id = (long) s.save(u2);
//		
//		System.out.println(id);
////		PrintWriter out = response.getWriter();
////		out.print("Hello World");
//		tx.commit();
//		s.close();
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+" "+password);
		Configuration con = new Configuration().configure().addAnnotatedClass(User.class).addAnnotatedClass(Product.class);
		SessionFactory sf = con.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery("FROM User WHERE username = :username AND hashedPassword =: hashedPassword");
		q.setParameter("username", username);
		q.setParameter("hashedPassword", password);
		System.out.println(q.toString());
		User result = (User) q.uniqueResult();
		tx.commit();
		s.close();
		HttpSession session = request.getSession();
		
		if (result!=null)
		{	session.setAttribute("username", result.getUsername());
			session.setAttribute("id", result.getId());
			response.sendRedirect("View");
		}
		else
		{
//			request.setAttribute("error", "Invalid Username and Password");
			session.setAttribute("error", "Invalid Username and Password");
			response.sendRedirect("index.jsp");
		}
		
		
	}

}
