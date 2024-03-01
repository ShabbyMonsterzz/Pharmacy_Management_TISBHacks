package net.merch.productmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.merch.productmanagement.Product;

public class ProductDAO
{
	private String jdbcURL = "jdbc:mysql://localhost:3306/Pharmacy";
	private String jdbcUsername = "root";
	private String jdbcPassword = "3leCtr!c";
	private static final String SELECT_ALL_PRODUCTS = "SELECT * from medicines";
	private static final String SELECT_PRODUCT_BY_ID = "select id,name,description,price,stock,shelf,category from medicines where id =?";
	private static final String INSERT_PRODUCTS_SQL = "insert into medicines" + " (name, description, price, stock,shelf, category) VALUES" + " (?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_PRODUCTS_SQL = "update medicines set name = ?, description = ?, price= ?, stock =?,shelf =?, category=? where id = ?;";
	private static final String DELETE_PRODUCTS_SQL = "delete from medicines where id = ?;";

	
	public ProductDAO() {}
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	// Select All Products Method (To Display list of products)
		public List<Product> selectAllProducts() {

			List<Product> products = new ArrayList<>();
			// Step 1: Establishing a Connection
			
			try (Connection connection = getConnection();
					
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
				System.out.println(preparedStatement);
				
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();
				
				// Step 4: Process the ResultSet object.
				while (rs.next())
				{
					
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String description = rs.getString("description");
					int price = rs.getInt("price");
					int stock = rs.getInt("stock");
					String shelf = rs.getString("shelf");
					String category = rs.getString("category");
					System.out.println(id + name + description + price + stock + shelf + category);
					
					products.add(new Product(id, name, description, price, stock, shelf, category));
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return products;
		}
		
		public Product selectProduct(int id) {
			Product product = null;
			// Step 1: Establishing a Connection
			try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
				preparedStatement.setInt(1, id);
				System.out.println(preparedStatement);
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					String name = rs.getString("name");
					String description = rs.getString("description");
					int price = rs.getInt("price");
					int stock = rs.getInt("stock");
					String shelf = rs.getString("shelf");
					String category = rs.getString("category");
					product = new Product(id, name, description, price, stock, shelf, category);
				}
			} catch (SQLException e) {
				printSQLException(e);
			}
			return product;
		}
		
		public boolean deleteProduct(int id) throws SQLException {
			boolean rowDeleted;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
				statement.setInt(1, id);
				rowDeleted = statement.executeUpdate() > 0;
			}
			return rowDeleted;
		}
		
		public void insertProduct(Product product) throws SQLException {
			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
				preparedStatement.setString(1, product.getName());
				preparedStatement.setString(2, product.getDescription());
				preparedStatement.setInt(3, product.getPrice());
				preparedStatement.setInt(4, product.getStock());
				preparedStatement.setString(5, product.getShelf());
				preparedStatement.setString(6, product.getCategory());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
			} catch (SQLException e) {
				printSQLException(e);
			}
		}
		public boolean updateProduct(Product product) throws SQLException {
			boolean rowUpdated;
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
				statement.setString(1, product.getName());
				statement.setString(2, product.getDescription());
				statement.setInt(3, product.getPrice());
				statement.setInt(4, product.getStock());
				statement.setString(5, product.getShelf());
				statement.setString(6, product.getCategory());
				statement.setInt(7, product.getId());

				rowUpdated = statement.executeUpdate() > 0;
			}
			return rowUpdated;
		}
		private void printSQLException(SQLException ex) {
			for (Throwable e : ex) {
				if (e instanceof SQLException) {
					e.printStackTrace(System.err);
					System.err.println("SQLState: " + ((SQLException) e).getSQLState());
					System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
					System.err.println("Message: " + e.getMessage());
					Throwable t = ex.getCause();
					while (t != null) {
						System.out.println("Cause: " + t);
						t = t.getCause();
					}
				}
			}
		}
}