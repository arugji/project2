package com.project.app;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.app.entity.Colour;
import com.project.app.manager.EntityManagerUtil;

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/NavigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavigationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		String act = request.getParameter("btn-op");
		String path = "/ViewAllNameServlet";

		if (act.equals("delete")) {
			String userId = request.getParameter("id");
			if (userId != null && !userId.trim().isEmpty()) {
				entityManager.getTransaction().begin();
				Colour clr = entityManager.find(Colour.class, Long.parseLong(userId));
				entityManager.remove(clr);
				entityManager.getTransaction().commit();
			} else {
				path = "/error.jsp";
			}
		} else if (act.equals("edit")) {
			path = "/edit.jsp";
			String userId = request.getParameter("id");
			if (userId != null && !userId.trim().isEmpty()) {
				entityManager.getTransaction().begin();
				Colour clr = entityManager.find(Colour.class, Long.parseLong(userId));
				entityManager.getTransaction().commit();
				request.setAttribute("colour", clr);
			} else {
				path = "/error.jsp";
			}
		} else if (act.equals("add")) {
			path = "/index.jsp";
		}
		getServletContext().getRequestDispatcher(path).forward(request, response);
	}

}
