package edu.joseph.controler.menuaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.joseph.controler.actions.MakeSale;
import edu.joseph.controler.actions.Reader;
import edu.joseph.controlerUtils.Starter;
import edu.joseph.model.entites.Book;
import edu.joseph.model.entites.Sale;

public class ActionsMenuSale {

	MakeSale makeSale;

	public ActionsMenuSale() {
		makeSale = Starter.createSale();
	}

	public void insertSale(List<Book> books, int id) {
		List<Book> selectBook = books;
		double valor = 0;
		double totalValue = 0;
		for (Book book : selectBook) {
			Date dateSale = new Date();
			valor = book.getValor();
			totalValue = totalValue + book.getValor();
			int itemId = book.getBook_Id();
			var sale = new Sale(null, dateSale, valor, id, itemId);
			makeSale.insert(sale);
		}
		System.out.println("The total amount is: " + totalValue);
	}

	public void findByDate() {

		List<Sale> listSales = new ArrayList<>();

		System.out.print("Insira qual a data que deseja visualizar(aaaa-MM-dd): ");
		java.sql.Date date = java.sql.Date.valueOf(Reader.readeData());

		listSales = makeSale.findByDate(date);

		for (Sale sale : listSales) {
			System.out.println(sale);
		}
	}

	public void findAll() {
		List<Sale> allSales = new ArrayList<>();

		allSales = makeSale.findAll();

		for (Sale sale : allSales) {
			System.out.println(sale);
		}
	}
}
