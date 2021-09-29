package com.project.app;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class SaveData
 */
@WebServlet("/SaveData")
public class SaveData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveData() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();
		String colour = request.getParameter("colour");
		String contrast = request.getParameter("contrast");
		long id = Long.parseLong(request.getParameter("id"));

		entityManager.getTransaction().begin();
		Colour clr = entityManager.find(Colour.class, id);

		if (id > 0) {
			clr.setColour(colour);
			clr.setContrast(contrast);
			entityManager.merge(clr);
		} else {
			clr = new Colour();
			clr.setColour(colour);
			clr.setContrast(contrast);
			entityManager.persist(clr);
		}
		entityManager.getTransaction().commit();
		entityManager.refresh(clr);
		getServletContext().getRequestDispatcher("/ViewAllNameServlet").forward(request, response);
	}

}
