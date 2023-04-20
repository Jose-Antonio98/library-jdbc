package edu.joseph.controler.menuaction;

import java.util.ArrayList;
import java.util.List;

import edu.joseph.controler.actions.Reader;
import edu.joseph.controler.database.DbException;
import edu.joseph.controlerUtils.ControlerInterfece;
import edu.joseph.controlerUtils.Starter;
import edu.joseph.model.entites.Client;

public class ActionsMenuClient {

	ControlerInterfece<Client> clientDB;

	public ActionsMenuClient() {
		clientDB = Starter.createClientDB();
	}

	public void insertClient() {
		System.out.print("Digite o nome do cliente: ");
		String nome = Reader.readeData();

		System.out.print("Entre com o CPF com pontos e traso: ");
		String cpf = Reader.readeData();

		var client = new Client(null, nome, cpf);
		clientDB.insert(client);
	}

	public void updateClient() {
		int option = 0;

		System.out.println("Para atualizar apenas um item digite 1");
		System.out.println("Para atualizar todos os campos de um item do sistema digite 2");
		option = Integer.parseInt(Reader.readeData());

		if (option == 1) {
			System.out.print("Insira o id do Cliente a ser alterado: ");
			int id = Integer.parseInt(Reader.readeData());

			System.out.println("1 - Nome");
			System.out.println("2 - CPF");
			System.out.print("Insira qual atributo deseja atualizar: ");
			int column = Integer.parseInt(Reader.readeData());

			System.out.print("Entre com o novo valor: ");
			String field = Reader.readeData();

			int rowsAffected = clientDB.update(id, column, field);
			if (rowsAffected > 0) {
				System.out.println("done! successfully update book");
			} else {
				throw new DbException("Unespected error! No rows affected");
			}
		} else {
			System.out.println("Insira o id do Cliente a ser alterado");
			Client client = clientDB.findById(Integer.parseInt(Reader.readeData()));

			System.out.print("Digite o titulo do livro: ");
			client.setName(Reader.readeData());

			System.out.print("Entre com o autor do livro: ");
			client.setCpf(Reader.readeData());

			int rowsAffected = clientDB.update(client);
			if (rowsAffected > 0) {
				System.out.println("done! successfully update client");
			} else {
				throw new DbException("Unespected error! No rows affected");
			}
		}
	}

	public void deleteClient() {
		System.out.print("Insira o id do cliente a ser excluido: ");
		int id = Integer.parseInt(Reader.readeData());
		clientDB.deleteById(id);
	}

	public void findCliente() {
		System.out.print("Insira o id do cliente cadastrado: ");
		int id = Integer.parseInt(Reader.readeData());
		var client = clientDB.findById(id);
		System.out.println(client);
	}

	public void findByField() {

		System.out.println("1 - Titulo");
		System.out.println("2 - Autor");
		System.out.println("3 - Genero");
		System.out.print("Insira qual atributo deseja atualizar: ");
		int column = Integer.parseInt(Reader.readeData());

		System.out.print("Entre com o valor do atributo: ");
		String field = Reader.readeData();

		List<Client> listClient = new ArrayList<>();

		listClient = clientDB.findByString(column, field);

		for (Client client : listClient) {
			System.out.println(client);
		}
	}

	public void findAllClients() {
		List<Client> allClients = new ArrayList<>();

		allClients = clientDB.findAll();

		for (Client client : allClients) {
			System.out.println(client);
		}
	}

	public Client buyer(int id) {
		var client = clientDB.findById(id);
		return client;
	}
}
