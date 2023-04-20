package edu.joseph.view;

import java.util.List;

import edu.joseph.controler.actions.Reader;
import edu.joseph.controler.menuaction.ActionsMenuSale;
import edu.joseph.model.entites.Book;
import edu.joseph.model.entites.User;
import edu.joseph.view.submenu.BookMenu;
import edu.joseph.view.submenu.ClientMenu;

public class LibraryApplication {

	public static void main(String[] args) {
		var bookMenu = new BookMenu();
		var clientMenu = new ClientMenu();
		var salesMenu = new ActionsMenuSale();
		var user = new User();

		while (true) {

			user.verificarCredenciais();

			System.out.println("=======================================");
			System.out.println("=======================================");
			System.out.println("BEM VINDO A LIVRARIA Livros & Companhia");
			System.out.println("=======================================");
			System.out.println("=======================================");
			System.out.println("================OPÇÔES=================");
			System.out.println("1 - Menu de livros");
			System.out.println("2 - Cadastro de clientes");
			System.out.println("3 - cadastrar venda");
			System.out.println("4 - Filtrar vendas por data");
			System.out.println("5 - Mostrar todas as vendas cadastradas");
			System.out.println("6 - sair");
			System.out.print("Escolha uma opção: ");
			int option = Integer.parseInt(Reader.readeData());

			switch (option) {
			case 1: {
				bookMenu.menuOptions();
				break;
			}
			case 2: {
				clientMenu.menuOptions();
				break;
			}
			case 3: {
				System.out.println("Entre com o id do cliente");
				int id = Integer.parseInt(Reader.readeData());
				List<Book> books = bookMenu.itens();
				salesMenu.insertSale(books, clientMenu.getIdBuyer(id));
				break;
			}
			case 4: {
				user.verificarCredenciais();
				salesMenu.findByDate();
				break;
			}
			case 5: {
				user.verificarCredenciais();
				salesMenu.findAll();
				break;
			}
			case 6: {
				System.exit(0);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + option);
			}
		}
	}
}
