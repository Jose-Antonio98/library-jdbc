package edu.joseph.view.submenu;

import java.util.List;

import edu.joseph.controler.actions.Reader;
import edu.joseph.controler.menuaction.ActionsMenuBook;
import edu.joseph.model.entites.Book;
import edu.joseph.model.entites.User;

public class BookMenu {

	ActionsMenuBook menuBook;
	User user = new User();

	public BookMenu() {
		menuBook = new ActionsMenuBook();
	}

	public void menuOptions() {
		String anser = "";
		String choice = "";
		while (true) {
			System.out.println("=======================================");
			System.out.println("=======================================");
			System.out.println("      BEM VINDO AO MENU DE LIVROS      ");
			System.out.println("=======================================");
			System.out.println("=======================================");
			System.out.println();
			System.out.println("=======================================");
			System.out.println("1 - Cadastrar livro");
			System.out.println("2 - Atualizar livro cadastrado");
			System.out.println("3 - Remover livro do registro");
			System.out.println("4 - Pesquisar livro por id");
			System.out.println("5 - Pesquisar livro por categoria");
			System.out.println("6 - Mostrar todos os livros cadastrados");
			System.out.println("7 - voltar ao menu principal");
			System.out.print("Escolha uma opção: ");
			choice = Reader.readeData();

			switch (choice) {
			case "1": {
				do {
					user.verificarCredenciais();
					menuBook.insertBook();
					System.out.print("Deseja continuar: ");
					anser = Reader.readeData();
					System.out.println();
				} while (anser.equals("s"));
				break;
			}
			case "2": {
				do {
					user.verificarCredenciais();
					menuBook.updateBook();
					System.out.print("Deseja continuar: ");
					anser = Reader.readeData();
					System.out.println();
				} while (anser.equals("s"));
				break;
			}
			case "3": {
				do {
					user.verificarCredenciais();
					menuBook.deleteBook();
					System.out.print("Deseja continuar: ");
					anser = Reader.readeData();
					System.out.println();
				} while (anser.equals("s"));
				break;
			}
			case "4": {
				do {
					menuBook.findBook();
					System.out.print("Deseja continuar: ");
					anser = Reader.readeData();
				} while (anser.equals("s"));
				break;
			}
			case "5": {
				menuBook.findByField();
				break;
			}
			case "6": {
				menuBook.findAllBooks();
				break;
			}
			case "7": {
				return;
			}
			default:
				throw new IllegalArgumentException("Unexpected value ");
			}
		}
	}

	public List<Book> itens() {
		return menuBook.booksForSale();
	}
}
