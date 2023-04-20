package edu.joseph.controler.menuaction;

import java.util.ArrayList;
import java.util.List;

import edu.joseph.controler.actions.Reader;
import edu.joseph.controler.database.DbException;
import edu.joseph.controlerUtils.ControlerInterfece;
import edu.joseph.controlerUtils.Starter;
import edu.joseph.model.entites.Book;

public class ActionsMenuBook {

	ControlerInterfece<Book> stock;

	public ActionsMenuBook() {
		stock = Starter.createStock();
	}

	public void insertBook() {
		System.out.print("Digite o titulo do livro: ");
		String title = Reader.readeData();

		System.out.print("Entre com o autor do livro: ");
		String autor = Reader.readeData();

		System.out.print("Entre com o codigo ISBN: ");
		String isbn = Reader.readeData();

		System.out.print("Entre com a quantidade de paginas: ");
		String pages = Reader.readeData();

		System.out.print("Entre com o genero: ");
		String gender = Reader.readeData();

		System.out.print("Entre com a quantidade disponivel: ");
		String quantity = Reader.readeData();

		System.out.print("Entre com o preço: ");
		String valor = Reader.readeData();

		var book = new Book(null, title, autor, isbn, Integer.parseInt(pages), gender, Integer.parseInt(quantity),
				Double.parseDouble(valor));
		stock.insert(book);
	}

	public void updateBook() {
		int option = 0;

		System.out.println("Para atualizar apenas um item digite 1");
		System.out.println("Para atualizar todos os campos de um item do sistema digite 2");
		option = Integer.parseInt(Reader.readeData());

		if (option == 1) {
			System.out.print("Insira o id do livro a ser alterado: ");
			int id = Integer.parseInt(Reader.readeData());

			System.out.println("1 - Titulo");
			System.out.println("2 - Autor");
			System.out.println("3 - Genero");
			System.out.println("4 - Preço");
			System.out.println("5 - Quantidade disponivel");
			System.out.println("6 - Quantidade de paginas");
			System.out.println("7 - ISBN");
			System.out.print("Insira qual atributo deseja atualizar: ");
			int column = Integer.parseInt(Reader.readeData());

			System.out.print("Entre com o novo valor: ");
			String field = Reader.readeData();

			int rowsAffected = stock.update(id, column, field);
			if (rowsAffected > 0) {
				System.out.println("done! successfully update book");
			} else {
				throw new DbException("Unespected error! No rows affected");
			}
		} else {
			System.out.println("Insira o id do livro a ser alterado");
			String id = Reader.readeData();
			Book book = stock.findById(Integer.parseInt(id));

			System.out.print("Digite o titulo do livro: ");
			book.setTitle(Reader.readeData());

			System.out.print("Entre com o autor do livro: ");
			book.setAuthor(Reader.readeData());

			System.out.print("Entre com o codigo ISBN: ");
			book.setIsbn(Reader.readeData());

			System.out.print("Entre com a quantidade de paginas: ");
			book.setPages(Integer.parseInt(Reader.readeData()));

			System.out.print("Entre com o codigo genero: ");
			book.setGender(Reader.readeData());

			System.out.print("Entre com a quantidade disponivel: ");
			book.setQuantity(Integer.parseInt(Reader.readeData()));

			System.out.print("Entre com o preço: ");
			book.setValor(Double.parseDouble(Reader.readeData()));
			int rowsAffected = stock.update(book);
			if (rowsAffected > 0) {
				System.out.println("done! successfully update book");
			} else {
				throw new DbException("Unespected error! No rows affected");
			}
		}
	}

	public void deleteBook() {
		System.out.print("Insira o id do item a ser excluido: ");
		int id = Integer.parseInt(Reader.readeData());
		stock.deleteById(id);
	}

	public void findBook() {
		System.out.print("Insira o id do item: ");
		int id = Integer.parseInt(Reader.readeData());
		var book = stock.findById(id);
		System.out.println(book);
	}

	public void findByField() {

		System.out.println("1 - Titulo");
		System.out.println("2 - Autor");
		System.out.println("3 - Genero");
		System.out.print("Insira qual atributo deseja atualizar: ");
		int column = Integer.parseInt(Reader.readeData());

		System.out.print("Entre com o valor do atributo: ");
		String field = Reader.readeData();

		List<Book> listBooks = new ArrayList<>();

		listBooks = stock.findByString(column, field);

		for (Book book : listBooks) {
			System.out.println(book);
		}
	}

	public void findAllBooks() {
		List<Book> allBooks = new ArrayList<>();

		allBooks = stock.findAll();

		for (Book book : allBooks) {
			System.out.println(book);
		}
	}

	public List<Book> booksForSale() {
		List<Book> forSale = new ArrayList<>();

		for (Book book : stock.findAll()) {
			System.out.println(book);
		}

		String resp = "";
		do {
			System.out.print("Entre com o id do livro escolhido: ");
			int id = Integer.parseInt(Reader.readeData());
			var book = stock.findById(id);
			forSale.add(book);
			System.out.print("Deseja adicionar mais itens ao carrinho(s/n): ");
			resp = Reader.readeData();
		} while (resp.equals("s"));

		// atualiza a quantidade
		for (Book book : forSale) {
			String newQuantity = "" + (book.getQuantity() - 1);
			stock.update(book.getBook_Id(), 5, newQuantity);
		}
		return forSale;
	}
}
