package edu.joseph.controler.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.joseph.controler.database.DataBase;
import edu.joseph.controler.database.DbException;
import edu.joseph.controlerUtils.ControlerInterfece;
import edu.joseph.model.entites.Book;

public class Stock implements ControlerInterfece<Book> {

	private Connection connection;

	public Stock(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Book obj) {
		PreparedStatement insertComand = null;

		try {
			connection.setAutoCommit(false);
			insertComand = connection.prepareStatement("INSERT INTO books "
					+ "(title, author, ISBN, pages, gender, quantity, valor) " + "VALUES (?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			insertComand.setString(1, obj.getTitle());
			insertComand.setString(2, obj.getAuthor());
			insertComand.setString(3, obj.getIsbn());
			insertComand.setInt(4, obj.getPages());
			insertComand.setString(5, obj.getGender());
			insertComand.setInt(6, obj.getQuantity());
			insertComand.setDouble(7, obj.getValor());

			int rowsAffected = insertComand.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = insertComand.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setBook_Id(id);
				}

				System.out.println("done! successfully inserted book id: " + obj.getBook_Id());
				DataBase.closeResultSet(rs);
			} else {
				throw new DbException("Unespected error! No rows affected");
			}
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
				throw new DbException("Transation rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DataBase.closeStatement(insertComand);
		}
	}

	@Override
	public int update(Book obj) {
		PreparedStatement updateComand = null;

		try {
			connection.setAutoCommit(false);
			updateComand = connection.prepareStatement("UPDATE books "
					+ "SET title = ?, author = ?, ISBN = ?, pages = ? , gender = ?, quantity = ?, valor = ?"
					+ "WHERE Book_Id = ?");

			updateComand.setString(1, obj.getTitle());
			updateComand.setString(2, obj.getAuthor());
			updateComand.setString(3, obj.getIsbn());
			updateComand.setInt(4, obj.getPages());
			updateComand.setString(5, obj.getGender().toString());
			updateComand.setInt(6, obj.getQuantity());
			updateComand.setDouble(7, obj.getValor());
			updateComand.setInt(8, obj.getBook_Id());

			int rowsAffected = updateComand.executeUpdate();

			connection.commit();
			return rowsAffected;

		} catch (SQLException e) {
			try {
				connection.rollback();
				throw new DbException("Transation rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DataBase.closeStatement(updateComand);
		}
	}

	@Override
	public int update(int id, int column, String field) {
		PreparedStatement updateComand = null;

		try {
			connection.setAutoCommit(false);
			updateComand = connection
					.prepareStatement("UPDATE books " + "SET " + choiceColumns(column) + " = ? " + "WHERE Book_Id = ?");

			if (column == 5 || column == 6) {
				int intField = Integer.parseInt(field);
				updateComand.setInt(1, intField);
				updateComand.setInt(2, id);

			} else if (column == 4) {
				double doubleField = Double.parseDouble(field);
				updateComand.setDouble(1, doubleField);
				updateComand.setInt(2, id);

			} else {
				updateComand.setString(1, field);
				updateComand.setInt(2, id);
			}
			int rowsAffected = updateComand.executeUpdate();

			connection.commit();
			return rowsAffected;
		} catch (SQLException e) {
			try {
				connection.rollback();
				throw new DbException("Transation rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DataBase.closeStatement(updateComand);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement deleteComand = null;

		try {
			deleteComand = connection.prepareStatement("DELETE FROM books WHERE book_Id = ?");
			deleteComand.setInt(1, id);
			int rowsAffected = deleteComand.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Done! Successfully Deleted Book");
			} else {
				throw new DbException("Unespected error! No rows affected");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DataBase.closeStatement(deleteComand);
		}
	}

	@Override
	public Book findById(Integer id) {
		PreparedStatement findComand = null;
		ResultSet result = null;
		try {
			findComand = connection.prepareStatement("SELECT * FROM books WHERE book_Id = ?");

			findComand.setInt(1, id);
			result = findComand.executeQuery();

			if (result.next()) {
				var book = instantiatiateBook(result);
				return book;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DataBase.closeStatement(findComand);
			DataBase.closeResultSet(result);
		}
	}

	@Override
	public List<Book> findByString(int column, String field) {
		PreparedStatement findComand = null;
		ResultSet result = null;

		try {
			findComand = connection.prepareStatement("SELECT * FROM books WHERE " + choiceColumns(column) + " = ?");

			findComand.setString(1, field);
			result = findComand.executeQuery();

			List<Book> listBooks = new ArrayList<>();

			while (result.next()) {
				var book = instantiatiateBook(result);
				listBooks.add(book);
			}
			return listBooks;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DataBase.closeStatement(findComand);
			DataBase.closeResultSet(result);
		}
	}

	@Override
	public List<Book> findAll() {
		PreparedStatement findComand = null;
		ResultSet result = null;

		try {
			findComand = connection.prepareStatement("SELECT * FROM books");
			result = findComand.executeQuery();

			List<Book> allBooks = new ArrayList<>();

			while (result.next()) {
				var book = instantiatiateBook(result);
				allBooks.add(book);
			}
			return allBooks;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DataBase.closeStatement(findComand);
			DataBase.closeResultSet(result);
		}
	}

	private Book instantiatiateBook(ResultSet result) throws SQLException {
		var obj = new Book(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),
				result.getInt(5), result.getString(6), result.getInt(7), result.getDouble(8));
		return obj;
	}

	private String choiceColumns(int column) {
		switch (column) {
		case 1: {
			return "title";
		}
		case 2: {
			return "author";
		}
		case 3: {
			return "gender";
		}
		case 4: {
			return "valor";
		}
		case 5: {
			return "quantity";
		}
		case 6: {
			return "pages";
		}
		case 7: {
			return "ISBN";
		}
		default:
			throw new IllegalArgumentException("Unexpected value");
		}
	}
}
