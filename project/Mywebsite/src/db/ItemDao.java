package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Item;

public class ItemDao {
	public void IncreaseItem(int categoryId, String name, String detail, int price, String image) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "INSERT INTO t_item (category_id,name,detail,price,image)"
					+ "VALUES (?,?,?,?,?)";
			//SELECTを実行し、結果票を取得
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1, categoryId);
			prstmt.setString(2, name);
			prstmt.setString(3, detail);
			prstmt.setInt(4, price);
			prstmt.setString(5, image);
			//executeUptate()を実行し、resultsetでデータベース内へ値を格納する
			int rs = prstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断
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

	//t_itemテーブルに格納されているすべてのレコードを取り出すメソッド
	public ArrayList<Item> FindAllItem() {
		Connection con = null;
		ArrayList<Item> itemlist = new ArrayList<Item>();
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT * FROM t_item ";
			//SELECTを実行し、結果票を取得
			PreparedStatement prstmt = con.prepareStatement(sql);
			ResultSet rs = prstmt.executeQuery();
			//rs.next()で取得し、データをArrayListに格納していく
			while (rs.next()) {
				int id = rs.getInt("id");
				int category = rs.getInt("category_id");
				String name = rs.getString("name");
				String detail = rs.getString("detail");
				int price = rs.getInt("price");
				String image = rs.getString("image");
				Item item = new Item(id, category, name, detail, price, image);

				itemlist.add(item);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return itemlist;
	}

	//t_itemテーブルのidを引数にレコードを取りだすメソッド
	public Item FindbyId(int id) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT * FROM t_item WHERE id = ?";
			//PreparedStatementでSELECTを実行し、結果票を取得する
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1, id);
			ResultSet rs = prstmt.executeQuery();
			//rs.next()メソッドで取得した結果をインスタンス化したItemクラスに格納しreturnで結果を返す
			if (!rs.next()) {
				return null;
			}
			int Id = rs.getInt("id");
			int category = rs.getInt("category_id");
			String name = rs.getString("name");
			String detail = rs.getString("detail");
			int price = rs.getInt("price");
			String image = rs.getString("image");
			Item item = new Item(id, category, name, detail, price, image);

			return item;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}
		return null;
	}
	//t_itemテーブルのnameを引数にnameに紐づくレコードを検索するメソッド
	public Item FindbyItemName(String name) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT * FROM t_item WHERE name = ?";
			//PreparedStatementでSELECTを実行し、結果票を取得する
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setString(1,name);
			ResultSet rs = prstmt.executeQuery();
			//if(rs.next())で結果票の中身を取り出して変数に格納する
			if(!rs.next()) {
				return null;
			}
			int id = rs.getInt("id");
			int category = rs.getInt("category_id");
			String Name = rs.getString("name");
			String detail = rs.getString("detail");
			int price = rs.getInt("price");
			String image = rs.getString("image");
			Item item = new Item(id,category,Name,detail,price,image);
			return item;
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				//connection切断
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		return null;
	}

	//t_itemテーブルのidを引数にidに紐づくレコードを削除するメソッド
	public void DeleteId(int id) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "DELETE FROM t_item WHERE id= ?";
			//DELETE文を実行する。PreparedStatementにセットする
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1, id);
			//executeUpdate()を実行し、データベース内の値をDELETEする
			int rs = prstmt.executeUpdate();
			System.out.println(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//masterupdateのフォームに割り当てられているものを引数にして内容を更新するメソッド
	public void UpdateItem(int id, int categoryId, String name, String detail, int price, String image) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "UPDATE t_item SET category_id = ?,name = ?,detail = ?,price = ?, image = ? WHERE id = ? ";
			//UPDATE文を実行する。PreparedStatementにセットする。
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1, categoryId);
			prstmt.setString(2, name);
			prstmt.setString(3, detail);
			prstmt.setInt(4, price);
			prstmt.setString(5, image);
			prstmt.setInt(6, id);
			//executeUpdate()を実行し、データベース内の値をUPDATEする
			int rs = prstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

	//t_itemテーブルのすべてのレコードの数を数えるメソッド
	public int CountRecord() {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT COUNT(*) FROM t_item";
			//SELECTを実行し、結果票を取得
			PreparedStatement prstmt = con.prepareStatement(sql);
			ResultSet rs = prstmt.executeQuery();
			//rsの中身をnext()で取得する
			rs.next();
			int count = rs.getInt("count(*)");
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
		return 0;
	}

	public ArrayList<Item> FindbyCategoryId(int categoryId) {
		Connection con = null;
		ArrayList<Item> categorylist = new ArrayList<Item>();
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT * FROM t_item WHERE category_id = ?";
			//SELECTを実行し、PreparedStatementを発行する
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1, categoryId);
			ResultSet rs = prstmt.executeQuery();
			//if(next())でrsから結果票を取得する
			while (rs.next()) {

				int id = rs.getInt("id");
				int CategoryId = rs.getInt("category_id");
				String name = rs.getString("name");
				String detail = rs.getString("detail");
				int price = rs.getInt("price");
				String image = rs.getString("image");

				Item item = new Item(id, categoryId, name, detail, price, image);
				categorylist.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return categorylist;
	}

	//t_itemの指定されたカテゴリーIDを含むレコードを数えるメソッド
	public int CountbyCategoryId(int categoryId) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT COUNT(*) FROM t_item WHERE category_id = ?";
			//SELECTを実行するPreparedStatementを発行する
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1, categoryId);
			//ResultSetにprstmtの実行結果を格納する
			ResultSet rs = prstmt.executeQuery();
			//rsの中身をrs.next()で取りだす
			rs.next();
			//rs.getString又はrs.getInt()で各カラムの値を取得する
			int count = rs.getInt("count(*)");
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
		return 0;
	}

	//Index画面の検索フォームに入力された値に対応するレコードを取得するレコード
	public List<Item> FindSearch(String name, int price1, int price2) {
		Connection con = null;
		List<Item> itemlist = new ArrayList<Item>();
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT * from t_item WHERE id > 0";

			if (!name.equals("")) {
				sql += " AND name LIKE '%" + name + "%'";
			}

			if (price1>0) {
				sql += " AND price >=" + price1 + "";
			}

			if(price2>0){
				sql += " AND price <=" + price2 + "";
			}
			//Statementを発行し、sqlを実行する
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			//while(rs.next())でレコードごとのカラムの値を変数に格納し、取得する
			while (rs.next()) {
				int id = rs.getInt("id");
				int categoryId = rs.getInt("category_id");
				String Name = rs.getString("name");
				String detail = rs.getString("detail");
				int price = rs.getInt("price");
				String image = rs.getString("image");

				Item item = new Item(id, categoryId, Name, detail, price, image);

				itemlist.add(item);
//				int Countlist = itemlist.size();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return itemlist;

	}



	//検索してpagenationで制限して表示できるもの
	public ArrayList<Item> FindSearchforPagenation(String name, int price1, int price2,int Pagenum,int MaxCount) {
		Connection con = null;
		ArrayList<Item> itemlist = new ArrayList<Item>();
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//1ページにおける表示件数を準備する
			int startItemCount = (Pagenum-1) * MaxCount;
			//sql文を準備
			String sql = "SELECT * from t_item WHERE id > 0";

			if (!name.equals("")) {
				sql += " AND name LIKE '%" + name + "%' ORDER BY id asc LIMIT ?,?";
			}

			if (price1>0) {
				sql += " AND price >=" + price1 + "ORDER BY id asc LIMIT ?,?";
			}

			if(price2>0){
				sql += " AND price <=" + price2 + "ORDER BY id asc LIMIT ?,?";
			}
			//PreparedStatementを発行し、sqlを実行する
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1, startItemCount);
			prstmt.setInt(2, MaxCount);
			ResultSet rs = prstmt.executeQuery();
			//while(rs.next())でレコードごとのカラムの値を変数に格納し、取得する
			while (rs.next()) {
				int id = rs.getInt("id");
				int categoryId = rs.getInt("category_id");
				String Name = rs.getString("name");
				String detail = rs.getString("detail");
				int price = rs.getInt("price");
				String image = rs.getString("image");

				Item item = new Item(id, categoryId, Name, detail, price, image);

				itemlist.add(item);
//				int Countlist = itemlist.size();

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//データベース切断
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return itemlist;

	}
	//検索したときの商品総数を取得

}
