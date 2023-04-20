package edu.joseph.model.entites;

import java.util.Date;

public class Sale {

	private Integer saleId;
	private Date saleDate;
	private double saleValor;
	private Integer buyerId;
	private Integer ItemId;

	public Sale(Integer saleId, Date saleDate, double saleValor, Integer buyerId, Integer itemId) {
		this.saleId = saleId;
		this.saleDate = saleDate;
		this.saleValor = saleValor;
		this.buyerId = buyerId;
		this.ItemId = itemId;
	}

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public double getSaleValor() {
		return saleValor;
	}

	public void setSaleValor(double saleValor) {
		this.saleValor = saleValor;
	}

	public Integer getItemId() {
		return ItemId;
	}

	public void setItemId(Integer itemId) {
		ItemId = itemId;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	@Override
	public String toString() {
		return "Sale [Id: " + saleId + ", Date: " + saleDate + ", Valor: " + saleValor + ", buyer: " + buyerId
				+ ", Item: " + ItemId + "]";
	}
}
