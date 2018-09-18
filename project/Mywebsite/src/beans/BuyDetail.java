package beans;

import java.io.Serializable;

public class BuyDetail implements Serializable{

	private int id;
	private int buy_id;
	private int item_id;

	public BuyDetail() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBuy_id() {
		return buy_id;
	}

	public void setBuy_id(int buy_id) {
		this.buy_id = buy_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}



}
