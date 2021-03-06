package fr.pizzeria.admin.web.dessert;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.DessertService;

/**
 * Contrôleur de la page Liste des desserts.
 */
@WebServlet("/desserts/liste")
public class ListerDessertController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ListerDessertController.class.getName());

	private static final String VUE_LISTER_DESSERTS = "/WEB-INF/views/desserts/listerDesserts.jsp";
	private static final String URL_LISTE = "/desserts/liste";

	@Inject
	private DessertService dessertService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listeDesserts", this.dessertService.findAllAvailable());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_DESSERTS);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		String code = request.getParameter("code");

		this.dessertService.delete(code);

		response.sendRedirect(request.getContextPath() + URL_LISTE);

	}

}