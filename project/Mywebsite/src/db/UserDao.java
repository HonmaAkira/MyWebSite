package db;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import beans.User;

public class UserDao {



	public User Findbylogininfo(String loginId,String password) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT * FROM t_user WHERE login_id = ? and password = ?";
			//SELECTを実行し、結果票を取得
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setString(1,loginId);
			prstmt.setString(2,changePass(password));
			ResultSet rs = prstmt.executeQuery();
			//結果を取り出し、beansのインスタンスに保存
			if(!rs.next()) {
				return null;
			}
				int Id = rs.getInt("id");
				String LoginId = rs.getString("login_id");
				String Name = rs.getString("name");
				String Password = rs.getString("password");
				String Birth = rs.getString("birth");
				String CreateData = rs.getString("create_data");
				String UpdataData = rs.getString("update_data");
				User user = new User(Id,loginId,Name,password , Birth, CreateData, UpdataData);
				//メソッドを実行した時に返す値をreturnする。
				return user;

			} catch (SQLException e) {
				System.out.println(e.getMessage());

			}
		return null;

	}

	public void IncreaseUser(String loginId,String name,String password,String birth) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql ="INSERT INTO t_user (login_id,name,password,birth,create_data,update_data)"
					+ " VALUES (?,?,?,?,now(),now())";
			//SELECTを実行し、結果票を取得
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setString(1,loginId);
			prstmt.setString(2,name);
			prstmt.setString(3,changePass(password));
			prstmt.setString(4,birth);
			//executeUpdate()を実行し、resultsetでデータベース内へ値を格納する
			int rs = prstmt.executeUpdate();

		}catch(SQLException e) {
			e.printStackTrace();

		}finally {
			// データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}
	//t_userテーブルのidを引数にidに紐づくレコードを削除するメソッド
	public void DeleteId(int id) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "DELETE FROM t_user WHERE id= ?";
			//DELETE文を実行する。PreparedStatementにセットする
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1,id);
			//executeUpdate()を実行し、データベース内の値をDELETEする
			int rs = prstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

	//t_userのテーブルのレコードをレコードごとにArrayListにいれて取得するメソッド
	public ArrayList<User> AllFindUser(){
		Connection con = null;
		ArrayList<User> userlist = new ArrayList<User>();
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT * FROM t_user WHERE id>1";
			//値をバインドしないのでStatementでSELECTを実行する
			Statement stmt = con.createStatement();
			ResultSet rs =stmt.executeQuery(sql);
			//rs.next()でrsに値を格納していく。すべてのレコード格納まで繰り返すのでWhileで終わるまで繰り返す。
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String birth = rs.getString("birth");
				String createdata = rs.getString("create_data");
				String updatedata = rs.getString("update_data");
				User user = new User(id,loginId,name,password,birth,createdata,updatedata);
				userlist.add(user);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			// データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userlist;
	}

	//idを引数にそのidに紐づくレコード情報を取得するメソッド
	public User FindbyId(int id) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT * FROM t_user WHERE id = ?";
			//SELECTを実行し、結果票を取得
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1,id);
			ResultSet rs = prstmt.executeQuery();
			//nextメソッドを回し、rsからUser型のインスタンスに値を格納していく。
			if(!rs.next()) {
				return null;
			}
			int Id = rs.getInt("id");
			String loginId = rs.getString("login_id");
			String name = rs.getString("name");
			String pass = rs.getString("password");
			String birth = rs.getString("birth");
			String CreateData = rs.getString("create_data");
			String UpdateData = rs.getString("update_data");
			User user = new User(id,loginId,name,pass,birth,CreateData,UpdateData);

			return user;
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				// データベース切断
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				}

			}
		return null;
		}
	//idを引数にidに紐づくレコードを消去するメソッド
	public void DeletebyId(int id) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "DELETE FROM t_user WHERE id = ?";
			//DELETE文を実行する。PreparedStatementにセットする
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1,id);
			//executeUpdate()を実行し、データベース内の値をDELETEする
			int rs = prstmt.executeUpdate();
			System.out.println(rs);

			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				// データベース切断
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
//	masterupdateのフォームに割り当てられているものを引数にして内容を更新するメソッド
	public void UpdatebyId(int id,String name,String password,String birth){
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "UPDATE t_user SET name = ?,password = ?,birth = ?,update_data = now() WHERE id = ? ";
			//UPDATE文を実行する。PreparedStatementにセットする
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setString(1,name);
			prstmt.setString(2,changePass(password));
			prstmt.setString(3,birth);

			prstmt.setInt(4,id);
			//executeUpdate()を実行し、データベース内の値をUPDATEする
			int rs = prstmt.executeUpdate();
			System.out.println(rs);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			// データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}
	}
	//ログインIDを引数に渡して、レコード情報を取得するメソッド
	public User FindbyLoginId(String loginId) {
		Connection con = null;
		try {
			con = DBmanager.getConnection();

			String sql = "SELECT * FROM t_user WHERE login_id =?";
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setString(1,loginId);
			ResultSet rs = prstmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			int id = rs.getInt("id");
			String LoginId = rs.getString("login_id");
			String name = rs.getString("name");
			String password = rs.getString("password");
			String birth = rs.getString("birth");
			String CreateData = rs.getString("create_data");
			String UpdateData = rs.getString("update_data");
			User user = new User(id, loginId, name, password, birth, CreateData, UpdateData);

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void  NonPassUpdate(String name,String birth,int id) {
		Connection con = null;
		try {
			con = DBmanager.getConnection();
			String sql = "UPDATE t_user SET name=?, birth=?, update_data=now() WHERE id=?";
			PreparedStatement prstmt = con.prepareStatement(sql);

			prstmt.setString(1,name);
			prstmt.setString(2,birth);
			prstmt.setInt(3,id);


			int rs = prstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<User> FindSearch(String loginId, String name,String birth1,String birth2) {
		Connection con = null;
		List<User> userlist = new ArrayList<User>();

		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			//同時に検索条件分岐設定を行う。
			//1,idが1以上のユーザ情報の表示(管理者はユーザ一覧に表示させない)
			//2,ログインIDが入力されている場合、完全一致で検索をかける仕様
			//3,ユーザ名が入力されている場合、LIKE演算子とワイルドカードによる部分一致検索をかける仕様
			//4,生年月日の一番目が入力されている場合、入力された値よりも大きい(後の)データに検索をかける仕様
			//5,生年月日の二番目が入力されている場合、入力された値よりも小さい(前の)データに検索をかける仕様
			String sql = "SELECT * FROM t_user WHERE id>1";

			if(!loginId.equals("")) {
				sql += " AND login_id ='" + loginId + "'";
			}else if(!name.equals("")) {
				sql += " AND name LIKE '%" + name + "%'";
			}else if(!birth1.equals("")) {
				sql += " AND birth >='" + birth1 + "'";
			}else if(!birth2.equals("")) {
				sql += " AND birth <='" + birth2 + "'";
			}
			//あらかじめわかっている値の入力なので、PreparedStatementでバインドするのではなく、Statementクラスで
			//SELECT文を実行する
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()) {
				int id = rs.getInt("id");
				String LoginId = rs.getString("login_id");
				String Name = rs.getString("name");
				String pass = rs.getString("password");
				String birth = rs.getString("birth");
				String createdata = rs.getString("create_data");
				String updatedata = rs.getString("update_data");

				User user = new User(id,LoginId,Name,pass,birth,createdata,updatedata);
				userlist.add(user);
			}
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}finally {
				// データベース切断
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		return userlist;
	}

	private String changePass(String password) {
		//ハッシュ生成前にバイト配列に置き換える際のCharset
		Charset charset = StandardCharsets.UTF_8;
		//ハッシュアルゴリズム
		String algorithm = "MD5";

		//ハッシュ生成処理
		byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(password.getBytes(charset));
		} catch (NoSuchAlgorithmException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String result = DatatypeConverter.printHexBinary(bytes);
		//標準出力
		System.out.println(result);
		return result;
	}
}


