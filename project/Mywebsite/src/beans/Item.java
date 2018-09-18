package beans;

import java.io.Serializable;

public class Item implements Serializable{

	private int id;
	private int category_id;
	private String name;
	private String detail;
	private int price;
	private String image;

	public Item(int id,int categoryId,String name,String detail,int price,String image) {
		this.id = id;
		this.category_id = categoryId;
		this.name = name;
		this.detail = detail;
		this.price = price;
		this.image = image;
	}

	public Item() {
		this.id=id;
		this.name=name;
		this.price=price;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}



}
