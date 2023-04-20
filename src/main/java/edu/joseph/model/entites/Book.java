package edu.joseph.model.entites;

public class Book {

	private Integer book_Id;
	private String title;
	private String author;
	private String isbn;
	private int pages;
	private String gender;
	private int quantity;
	private double valor;

	public Book() {
	}

	public Book(Integer book_Id, String title, String author, String isbn, int pages, String gender, int quantity,
			double valor) {
		this.book_Id = book_Id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.pages = pages;
		this.gender = gender;
		this.quantity = quantity;
		this.valor = valor;
	}

	public Integer getBook_Id() {
		return book_Id;
	}

	public void setBook_Id(Integer book_Id) {
		this.book_Id = book_Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Book [Book_Id: " + book_Id + ", Title: " + title + ", Author: " + author + ", ISBN: " + isbn
				+ ", Pages: " + pages + ", Gender: " + gender + ", Quantity: " + quantity + ", Valor: " + valor + "]";
	}
}
