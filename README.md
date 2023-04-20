<p align="center">
  <img src="https://user-images.githubusercontent.com/60453269/220384874-f136b1f9-a852-4774-a600-7fab9d77e8a2.png" alt="Logo" width="300" height="200" />
</p>

<h1 align="center"> ⭐ Readme </h1>

<p align="center">
  <b> ⭐ Readme Legal </b></br>
  <sub> ⭐ Uma readme bonita, pratica e eficiente para usar em todos projeto e padronizar as readmes.
  <sub>
</p>

[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#table-of-contents)

<p align="center">
  <a href="#Introdução"> 🧩 Introdução </a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#Resultados"> 🚀 Resultados</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#Dependências"> 🧪 Dependências</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#Creditos"> 🏆 Créditos </a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</p>

<br/>

<a id="Introdução"></a>
## 🧩 Introdução 

  ***⠀Projeto realizado com o objetivo de demonstrar os conhecimentos adquitidos a respieto de JDBC juntamente com a criação de um schema de banco de dados utilizando o MySQL,
  onde foi gerado 3 tabelas de nomes books, client, sale responsaveis fazer a persistencias dos dados inseridos durante execução do programa.***

<br/>

<a id="Resultados"></a>
## 🚀 Resultados 

<br/>

## Banco de dados

<br/>

![modelo relacional](https://user-images.githubusercontent.com/122057368/233491560-6263a5d9-7647-4244-a3eb-b48f18568d34.png)

[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#table-of-contents)

<br/>

## Back-end

<br/>

### 🎯 CONECTAR AO BANCO DE DADOS 

### ```GET``` 
```
  public static Connection getConnection() {

		try {
			if (connection == null) {
				Properties properties = loadProperties();
				String url = properties.getProperty("dburl");
				connection = DriverManager.getConnection(url, properties);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		return connection;
	}
```

### 🎯 VERIFICA AS CREDENCIAIS DO USUARIO PARA ENTRADA NO SISTEMA E ACESSO A METODOS COM INFORMAÇÕES SENSIVEIS
```URL 
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
```
[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#table-of-contents)

## `📖 Dependencies` 
``` MySQL Connector J
 <dependency>
	<groupId>com.mysql</groupId>
	<artifactId>mysql-connector-j</artifactId>
	<scope>runtime</scope>
</dependency>
```

[![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/colored.png)](#table-of-contents)

<a id="Creditos"></a>
## 🏆 Créditos


<br />
⠀⠀

<br /> 

<div > 

| [<img src="https://user-images.githubusercontent.com/122057368/229660568-a0628ed7-4e7c-4cd3-98c2-7c70107f9e42.jpg" width=300><br><sub> José Antonio </sub>](https://www.linkedin.com/in/josé-antônio-chaves-junior/) | ***Hello 😃 Se você chegou até aqui, acredito que gostou do meu projeto, nesse caso temos algo em comum, sendo assim que tal conversamos um pouco? Meu chama no linkedin 😁*** | 
|---|---|


</div> 
