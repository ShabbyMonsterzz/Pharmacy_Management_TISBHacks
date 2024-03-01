package net.merch.productmanagement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.merch.productmanagement.*;
/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

	public void init() {
		productDAO = new ProductDAO();
	}   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/list":
				listProducts(request, response);
				break;
			case "/insert":
				insertProduct(request, response);
				break;
			case "/update":
				updateProduct(request, response);
				break;
			case "/newproduct":
				showNewForm(request, response);
				break;
			case "/delete":
				deleteProduct(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			default:
				listProduct(request, response);
				//listProduct(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}
	
	private void listProducts(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
	{
		// Call the User Database Access Object
		List<Product> listProduct = productDAO.selectAllProducts();
		request.setAttribute("listProduct", listProduct);
		// Forward to the JSP page
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/admin-dash.jsp");
		dispatcher.forward(request, response);
	}
	
	private void listProduct(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Product> listProduct = productDAO.selectAllProducts();
		request.setAttribute("listProduct", listProduct);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/admin-dash.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String shelf = request.getParameter("shelf");
		String category = request.getParameter("category");
		Product newProduct = new Product(name, description, price, stock, shelf, category);
		productDAO.insertProduct(newProduct);
		System.out.println("Finished insertProduct method");
		response.sendRedirect("list");
		System.out.println("Succesfully executed redirect statement");
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productform.jsp");
		dispatcher.forward(request, response);
	}
	
	private void deleteProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		productDAO.deleteProduct(id);
		response.sendRedirect("list");
	}
	
	private void updateProduct(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String shelf = request.getParameter("shelf");
		String category = request.getParameter("category");

		Product product = new Product(id, name, description, price, stock, shelf, category);
		productDAO.updateProduct(product);
		response.sendRedirect("products");
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Product existingUser = productDAO.selectProduct(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/productform.jsp");
		request.setAttribute("product", existingUser);
		dispatcher.forward(request, response);

	}
}
