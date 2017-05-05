package User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import User.TechDAO;
import User.TechDAOuser;
import User.TechTalk;

public class ControllerServletuser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TechDAOuser techDAOuser;
	private TechDAO techDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		techDAOuser = new TechDAOuser(jdbcURL, jdbcUsername, jdbcPassword);
		techDAO=new TechDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/unew":
				showNewForm(request, response);
				break;
			case "/uinsert":
				inserttechu(request, response);
				break;
			case "/uregister":
				registeru(request, response);
				break;
			case "/ulist":
				listtechu(request,response);
				break;
			case "/anew":
				showNewForma(request, response);
				break;
			case "/ainsert":
				inserttecha(request, response);
				break;
			case "/adelete":
				deletetecha(request, response);
				break;
			case "/aedit":
				showEditForma(request, response);
				break;
			case "/aupdate":
				updateBooka(request, response);
				break;
			case "/arequest":
				requesttechtalk(request, response);
				break;
			case "/adecline":
				declinereqtechtalk(request, response);
				break;
			case "/aaccept":
				 accepttechtalk(request,response);
				 break;
			case "/alist":
				listtecha(request, response);
				break;
			case "/areg":
				listreg(request, response);
				break;
				
			
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}



	private void listtechu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<TechTalk> listBook = techDAOuser.listAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TechListu.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listreg(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<TechTalk> listBook = techDAOuser.listAllemp();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
		dispatcher.forward(request, response);
	}

	
	private void listtecha(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<TechTalk> listBook = techDAOuser.listAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TechList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("TechFormu.jsp");
		dispatcher.forward(request, response);
	}



	private void inserttechu(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String date=request.getParameter("date");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String presentor = request.getParameter("presenter");

		TechTalk newBook = new TechTalk(date,title, description,presentor);
		techDAOuser.insertBook(newBook);
		response.sendRedirect("ulist");
	}

	private void registeru(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		String Uname=request.getParameter("username");
		System.out.println("enterd");
		techDAOuser.register(id, Uname);
		response.sendRedirect("ulist");

	}
 
	
	private void showNewForma(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("TechForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForma(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		TechTalk existingBook = techDAO.getBook(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TechForm.jsp");
		request.setAttribute("techTalk", existingBook);
		dispatcher.forward(request, response);

	}

	private void inserttecha(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String date=request.getParameter("date");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String presentor = request.getParameter("presenter");

		TechTalk newBook = new TechTalk(date,title, description,presentor);
		techDAO.insertBook(newBook);
		response.sendRedirect("alist");
	}

	private void updateBooka(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String date=request.getParameter("date");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String presentor = request.getParameter("presenter");

		TechTalk techTalk = new TechTalk(id, date, title, description, presentor);
		techDAO.updateBook(techTalk);
		response.sendRedirect("alist");
	}

	private void deletetecha(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		TechTalk techTalk = new TechTalk(id);
		techDAO.deleteBook(techTalk);
		response.sendRedirect("alist");

	}
	
	private void declinereqtechtalk(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		TechTalk techTalk = new TechTalk(id);
		techDAO.deletereqtechtalk(techTalk);
		response.sendRedirect("alist");

	}
	
	private void accepttechtalk(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		TechTalk techTalk = techDAO.getreqtechtalk(id);
		techDAO.accepttechtalk(techTalk);
		response.sendRedirect("alist");
	}

	private void requesttechtalk(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException 
	{
	      List<TechTalk> reqlist=techDAO.listreqtechtalks();
			request.setAttribute("reqlist", reqlist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Reqlist.jsp");
			dispatcher.forward(request, response);
	      
		
	}

	


	
}


