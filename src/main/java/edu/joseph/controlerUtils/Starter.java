package edu.joseph.controlerUtils;

import edu.joseph.controler.actions.ClientDB;
import edu.joseph.controler.actions.MakeSale;
import edu.joseph.controler.actions.Stock;
import edu.joseph.controler.database.DataBase;
import edu.joseph.model.entites.Book;
import edu.joseph.model.entites.Client;

public class Starter {

	public static ControlerInterfece<Book> createStock() {
		return new Stock(DataBase.getConnection());
	}

	public static ControlerInterfece<Client> createClientDB() {
		return new ClientDB(DataBase.getConnection());
	}

	public static MakeSale createSale() {
		return new MakeSale(DataBase.getConnection());
	}
}
