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
import edu.joseph.model.entites.Client;

public class ClientDB implements ControlerInterfece<Client> {

	private Connection connection;

	public ClientDB(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Client obj) {
		PreparedStatement insertComand = null;

		try {
			connection.setAutoCommit(false);
			insertComand = connection.prepareStatement("INSERT INTO client (name, cpf) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			insertComand.setString(1, obj.getName());
			insertComand.setString(2, obj.getCpf());

			int rowsAffected = insertComand.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet result = insertComand.getGeneratedKeys();

				if (result.next()) {
					int id = result.getInt(1);
					obj.setClient_Id(id);
				}

				System.out.println("done! successfully inserted client id: " + obj.getClient_Id());
				DataBase.closeResultSet(result);
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
	public int update(Client obj) {
		PreparedStatement updateComand = null;

		try {
			connection.setAutoCommit(false);
			updateComand = connection
					.prepareStatement("UPDATE client " + "SET name = ?, cpf = ? " + "WHERE client_Id = ?");

			updateComand.setString(1, obj.getName());
			updateComand.setString(2, obj.getCpf());
			updateComand.setInt(3, obj.getClient_Id());

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
			updateComand = connection.prepareStatement(
					"UPDATE client " + "SET " + choiceColumns(column) + " = ? " + "WHERE client_Id = ?");

			updateComand.setString(1, field);
			updateComand.setInt(2, id);

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
			deleteComand = connection.prepareStatement("DELETE FROM client WHERE client_Id = ?");
			deleteComand.setInt(1, id);
			int rowsAffected = deleteComand.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Done! Successfully Deleted client");
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
	public Client findById(Integer id) {
		PreparedStatement findComand = null;
		ResultSet result = null;
		try {
			findComand = connection.prepareStatement("SELECT * FROM client WHERE client_Id = ?");

			findComand.setInt(1, id);
			result = findComand.executeQuery();

			if (result.next()) {
				var client = instantiatiateClient(result);
				return client;
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
	public List<Client> findByString(int column, String field) {
		PreparedStatement findComand = null;
		ResultSet result = null;

		try {
			findComand = connection.prepareStatement("SELECT * FROM client WHERE " + choiceColumns(column) + " = ?");

			findComand.setString(1, field);
			result = findComand.executeQuery();

			List<Client> listClients = new ArrayList<>();

			while (result.next()) {
				var client = instantiatiateClient(result);
				listClients.add(client);
			}
			return listClients;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DataBase.closeStatement(findComand);
			DataBase.closeResultSet(result);
		}
	}

	@Override
	public List<Client> findAll() {
		PreparedStatement findComand = null;
		ResultSet result = null;

		try {
			findComand = connection.prepareStatement("SELECT * FROM client");
			result = findComand.executeQuery();

			List<Client> allClient = new ArrayList<>();

			while (result.next()) {
				var client = instantiatiateClient(result);
				allClient.add(client);
			}
			return allClient;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DataBase.closeStatement(findComand);
			DataBase.closeResultSet(result);
		}
	}

	private Client instantiatiateClient(ResultSet result) throws SQLException {
		var obj = new Client(result.getInt(1), result.getString(2), result.getString(3));
		return obj;
	}

	private String choiceColumns(int column) {
		switch (column) {
		case 1: {
			return "name";
		}
		case 2: {
			return "cpf";
		}
		default:
			throw new IllegalArgumentException("Unexpected value");
		}
	}
}
