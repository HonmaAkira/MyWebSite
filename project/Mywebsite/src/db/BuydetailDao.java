package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.BuyDetail;
import beans.Item;

public class BuydetailDao {
	//購入確認画面から購入完了画面に遷移する際にbdbとして登録する購入情報とともにgeneratedkey()で発行される
	//自動採番値(これが何番目に登録された購入情報のレコードか)のBuyIdと商品の固有番号として割り振られているt_itemの
	//(このメソッド使用タイミングではsessionに保存されているcartitem.id)idを登録して、何回目の買い物で商品として何を買ったか
	//を登録するメソッド。つまりbdbが購入情報だったらbddbは購入情報レコードに紐づく購入商品情報である
	public void InsertBuydetail(BuyDetail bddb) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "INSERT INTO t_buy_detail (buy_id,item_id) values (?,?)";
			//INSERTを実行するPreparedStatementを発行する
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1,bddb.getBuy_id());
			prstmt.setInt(2,bddb.getItem_id());
			//executeUpdateメソッドを実行してデータベースに登録する
			int rs = prstmt.executeUpdate();
		}catch (SQLException e) {
			System.out.println(e.getMessage());

		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//上記で登録した購入商品情報を意味のある使い方をするためのメソッドである。t_buy_detailのitem_idで購入した個別の商品を
	//識別可能になったので、t_buy_detailテーブルを元にt_itemテーブルと結合することで購入商品情報のitem_idに紐づく
	//購入商品情報の名前と値段を取ることが出来る。
	//また元となるfromのテーブルが逆になった時は正しい結果が得られないので気を付ける事。
	public ArrayList<Item> getItemListbyBuyId(int BuyId){
		Connection con = null;
		ArrayList<Item> itemlist = new ArrayList<Item>();
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = " select t_item.id,t_item.name,t_item.price from t_buy_detail "
					+ "join t_item on t_buy_detail.item_id=t_item.id where t_buy_detail.buy_id =?";
			//PreparedStatementを発行してSELECTを実行する
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1,BuyId);
			//executeQueryを実行して結果をResultSetに格納する
			ResultSet rs = prstmt.executeQuery();
			//一回の買い物で買ったすべての商品のレコードをwhile(rs.next())を使用し、rsからItembeansへと移し替えて使用する
			while(rs.next()) {
			//beansの各フィールドに値を格納するのでItemクラスをインスタンス化する。
				Item item = new Item();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getInt("price"));
				itemlist.add(item);
			}

			}catch (SQLException e) {
				System.out.println(e.getMessage());
				return null;
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		return itemlist;
		}
//	//上記の
//	public ArrayList<Item> getItemListbyUserId(int userId){
//		Connection con = null;
//		ArrayList<Item> itemlist = new ArrayList<Item>();
//		try {
//			//データベースへ接続
//			con = DBmanager.getConnection();
//			//sql文を準備
//			String sql = " select t_item.id,t_item.name,t_item.price from t_buy_detail "
//					+ "join t_item on t_buy_detail.item_id=t_item.id where t_buy_detail.buy_id =?";
//			//PreparedStatementを発行してSELECTを実行する
//			PreparedStatement prstmt = con.prepareStatement(sql);
//			prstmt.setInt(1,userId);
//			//executeQueryを実行して結果をResultSetに格納する
//			ResultSet rs = prstmt.executeQuery();
//			//一回の買い物で買ったすべての商品のレコードをwhile(rs.next())を使用し、rsからItembeansへと移し替えて使用する
//			while(rs.next()) {
//			//beansの各フィールドに値を格納するのでItemクラスをインスタンス化する。
//				Item item = new Item(userId);
//				item.setId(userId);
//				String name = rs.getString("name");
//				item.setName(name);
//				int price = rs.getInt("price");
//				item.setPrice(price);
//				itemlist.add(item);
//			}
//			return itemlist;
//			}catch (SQLException e) {
//				System.out.println(e.getMessage());
//				return null;
//			} finally {
//				if (con != null) {
//					try {
//						con.close();
//					} catch (SQLException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}

}
