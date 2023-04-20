package edu.joseph.model.entites;

public class Client {

	private Integer client_Id;
	private String name;
	private String cpf;

	public Client() {
	}

	public Client(Integer client_Id, String name, String cpf) {
		this.client_Id = client_Id;
		this.name = name;
		this.cpf = cpf;
	}

	public Integer getClient_Id() {
		return client_Id;
	}

	public void setClient_Id(Integer client_Id) {
		this.client_Id = client_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "Client [Id: " + client_Id + ", Name: " + name + ", CPF: " + cpf + "]";
	}
}
