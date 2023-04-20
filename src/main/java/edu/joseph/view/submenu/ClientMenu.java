package edu.joseph.view.submenu;

import edu.joseph.controler.actions.Reader;
import edu.joseph.controler.menuaction.ActionsMenuClient;
import edu.joseph.model.entites.User;

public class ClientMenu {

	ActionsMenuClient menuClient;
	User user = new User();

	public ClientMenu() {
		menuClient = new ActionsMenuClient();
	}

	public void menuOptions() {
		String anser = "";
		String choice = "";
		while (true) {
			System.out.println("=======================================");
			System.out.println("=======================================");
			System.out.println("   BEM VINDO AO CADASTRO DE CLIENTES   ");
			System.out.println("=======================================");
			System.out.println("=======================================");
			System.out.println();
			System.out.println("=======================================");
			System.out.println("1 - Cadastrar cliente");
			System.out.println("2 - Atualizar cliente cadastrado");
			System.out.println("3 - Remover cliente do registro");
			System.out.println("4 - Pesquisar cliente por id");
			System.out.println("5 - Pesquisar cliente por categoria");
			System.out.println("6 - Mostrar todos os cliente cadastrados");
			System.out.println("7 - voltar ao menu principal");
			System.out.print("Escolha uma opção: ");
			choice = Reader.readeData();

			switch (choice) {
			case "1": {
				do {
					System.out.println();
					menuClient.insertClient();
					System.out.print("Deseja continuar: ");
					anser = Reader.readeData();
					System.out.println();
				} while (anser.equals("s"));
				break;
			}
			case "2": {
				do {
					user.verificarCredenciais();
					System.out.println();
					menuClient.updateClient();
					System.out.print("Deseja continuar: ");
					anser = Reader.readeData();
					System.out.println();
				} while (anser.equals("s"));
				break;
			}
			case "3": {
				do {
					user.verificarCredenciais();
					System.out.println();
					menuClient.deleteClient();
					System.out.print("Deseja continuar: ");
					anser = Reader.readeData();
					System.out.println();
				} while (anser.equals("s"));
				break;
			}
			case "4": {
				do {
					System.out.println();
					menuClient.findCliente();
					System.out.print("Deseja continuar: ");
					anser = Reader.readeData();
				} while (anser.equals("s"));
				break;
			}
			case "5": {
				menuClient.findByField();
				break;
			}
			case "6": {
				user.verificarCredenciais();
				menuClient.findAllClients();
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

	public int getIdBuyer(int id) {
		var client = menuClient.buyer(id);
		return client.getClient_Id();
	}
}
