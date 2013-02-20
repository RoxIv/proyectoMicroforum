package com.microforum.gestorencuestaweb.servlets;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.microforum.gestorencuestaweb.entities.Usuario;


/**
 * Servlet implementation class ListadoUsuarios
 */
@WebServlet("/ListadoUsuarios")
public class ListadoUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Writer writer=response.getWriter();
		Configuration conf=new Configuration();
		SessionFactory sf=conf.configure().buildSessionFactory();
		Session session=sf.openSession();
		Query query=session.createQuery("from Usuario");
		//Transaction tr=session.beginTransaction();//no hace falta definir transaccion porque solo vamos a hacer consultas
		Criteria criteria=session.createCriteria(Usuario.class);
		//Collection<Usuario>usuarios=(ArrayList<Usuario>) criteria.list();
		request.setAttribute("listaUsuarios", criteria.list());
		request.setAttribute("query", query);
		RequestDispatcher rd=
				request.getRequestDispatcher("/listadoUsuarios.jsp");
		rd.forward(request, response);
		session.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
