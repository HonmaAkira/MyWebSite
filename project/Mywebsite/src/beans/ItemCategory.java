package beans;

import java.io.Serializable;

public class ItemCategory implements Serializable {

	private int id;
	private String name;

	public ItemCategory() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
