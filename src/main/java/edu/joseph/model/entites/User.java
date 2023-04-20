package edu.joseph.model.entites;

import edu.joseph.controler.actions.Reader;

public class User {

	private String adm = "joseph";
	private String password = "123456";

	public User() {
	}

	public void verificarCredenciais() {
		int tentativas = 3;

		while (tentativas > 0) {
			System.out.print("Digite o nome de usuário: ");
			String adm = Reader.readeData();
			System.out.print("Digite a senha: ");
			String password = Reader.readeData();

			if (adm.equals(this.adm) && password.equals(this.password)) {
				System.out.println("Bem-vindo ao sistema!");
				return;
			}

			tentativas--;
			System.out.println("Credenciais inválidas. Tentativas restantes: " + tentativas);
		}

		System.out.println("Você excedeu o limite de tentativas. O sistema será fechado.");
		System.exit(0);
	}
}
