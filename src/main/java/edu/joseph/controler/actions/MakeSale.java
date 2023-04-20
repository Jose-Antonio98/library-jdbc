package edu.joseph.controler.actions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.joseph.controler.database.DataBase;
import edu.joseph.controler.database.DbException;
import edu.joseph.model.entites.Sale;

public class MakeSale {
	private Connection connection;

	public MakeSale(Connection connection) {
		this.connection = connection;
	}

	public void insert(Sale sale) {
		PreparedStatement insertComand = null;

		try {
			connection.setAutoCommit(false);
			insertComand = connection.prepareStatement(
					"INSERT INTO sale " + "(sale_Date, sale_Valor, buyer_Id, item_Id) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			insertComand.setDate(1, new java.sql.Date(sale.getSaleDate().getTime()));
			insertComand.setDouble(2, sale.getSaleValor());
			insertComand.setInt(3, sale.getBuyerId());
			insertComand.setInt(4, sale.getItemId());

			int rowsAffected = insertComand.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = insertComand.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					sale.setSaleId(id);
				}

				System.out.println("done! successful sale id: " + sale.getSaleId());
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

	// todos os metodos abaixo terão de pedir senha do adm para serem acessados
	// devido a sensibilidade dps dados
	public Sale findById(Integer id) {
		PreparedStatement findComand = null;
		ResultSet result = null;
		try {
			findComand = connection.prepareStatement("SELECT * FROM sale WHERE sale_Id = ?");

			findComand.setInt(1, id);
			result = findComand.executeQuery();

			if (result.next()) {
				var sale = instantiatiateSale(result);
				return sale;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DataBase.closeStatement(findComand);
			DataBase.closeResultSet(result);
		}
	}

	public List<Sale> findByDate(Date field) {
		PreparedStatement findComand = null;
		ResultSet result = null;

		try {
			findComand = connection.prepareStatement("SELECT * FROM sale WHERE sale_Date = ?");

			findComand.setDate(1, (java.sql.Date) field);
			result = findComand.executeQuery();

			List<Sale> listSales = new ArrayList<>();

			while (result.next()) {
				var book = instantiatiateSale(result);
				listSales.add(book);
			}
			return listSales;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DataBase.closeStatement(findComand);
			DataBase.closeResultSet(result);
		}
	}

	public List<Sale> findAll() {
		PreparedStatement findComand = null;
		ResultSet result = null;

		try {
			findComand = connection.prepareStatement("SELECT * FROM sale");
			result = findComand.executeQuery();

			List<Sale> allSales = new ArrayList<>();

			while (result.next()) {
				var sale = instantiatiateSale(result);
				allSales.add(sale);
			}
			return allSales;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DataBase.closeStatement(findComand);
			DataBase.closeResultSet(result);
		}
	}

	private Sale instantiatiateSale(ResultSet result) throws SQLException {
		var sale = new Sale(result.getInt(1), result.getDate(2), result.getDouble(3), result.getInt(4),
				result.getInt(5));
		return sale;
	}
}
