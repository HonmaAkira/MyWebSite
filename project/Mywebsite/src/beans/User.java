package beans;

import java.io.Serializable;


public class User implements Serializable{

	private int id;
	private String login_id;
	private String name;
	private String password;
	private String birth;
	private String create_data;
	private String update_data;

	public User() {

	}

	public User(int id, String loginId, String name, String password, String birth, String create_data,
			String update_data) {
		this.id = id;
		this.login_id = loginId;
		this.name = name;
		this.password = password;
		this.birth = birth;
		this.create_data = create_data;
		this.update_data = update_data;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getCreate_data() {
		return create_data;
	}

	public void setCreate_data(String create_data) {
		this.create_data = create_data;
	}

	public String getUpdate_data() {
		return update_data;
	}

	public void setUpdate_data(String update_data) {
		this.update_data = update_data;
	}


}
