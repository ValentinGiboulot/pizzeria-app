package fr.pizzeria.admin.web.client;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientService;
import fr.pizzeria.model.Client;

@WebServlet("/clients/new")
public class AjouterClientController extends HttpServlet {

	private static final String VUE_AJOUTER_CLIENTS = "/WEB-INF/views/clients/ajouterClients.jsp";
	private static final String VUE_LISTER_CLIENTS = "/clients/list";

	@Inject
	private ClientService clientService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_AJOUTER_CLIENTS).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		String adresse = request.getParameter("adresse");
		clientService.save(new Client(nom, prenom, email, motDePasse, adresse));
		response.sendRedirect(request.getContextPath()+VUE_LISTER_CLIENTS);
	}

}