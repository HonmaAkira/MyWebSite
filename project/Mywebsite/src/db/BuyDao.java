package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Buy;

public class BuyDao {
	public int InsertBuy(Buy bdb) {
		Connection con = null;
		PreparedStatement prstmt = null;
		//-1で初期値を設定している理由は初期値に-1という自動採番auto_incrementで絶対に出ることのない値を設定して
		//おくことにより(-1でも-999でもあるいは0でも良い)、-1などのあり得ない番号で設定することにより、自動採番される
		//値の仕様上あり得ない値が格納されることを防ぐ
		int autoIncKey = -1;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//PreparedStatementを発行してINSERT文を実行する
			prstmt = con.prepareStatement(
					"INSERT INTO t_buy (user_id,total_price,delivery_method_id,create_date) values (?,?,?,now())",Statement.RETURN_GENERATED_KEYS);
			prstmt.setInt(1, bdb.getUserId());
			prstmt.setInt(2, bdb.getTotalPrice());
			prstmt.setInt(3, bdb.getDeliveryMethodId());
			prstmt.executeUpdate();
			//PreparedStatementのgetGeneratedKeys()メソッドで自動採番される値を取得する
			//そして、それをrsで変数に格納する
			ResultSet rs = prstmt.getGeneratedKeys();
			if (rs.next()) {
			//自動採番されるカラムの一番新しい値を取得するには(例えばidカラムのauto_increment値)、rs.getInt()メソッドで
			//auto_incrementのカラム名ではなく(例：rs.getInt("id))、下記の通りrs.getInt(1)という表記で書くこと。
			//そうすれば一番最新の自動採番された値を取得することが出来る。(1番目のレコードなら1,5番目のレコードなら5)
			//何番目のレコードの自動採番値を取得する場合でも下記の書き方は必須である。
			//仕様なので理由は特にない。覚えること。
				autoIncKey = rs.getInt(1);
			}

			return autoIncKey;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
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


	//このメソッドは購入完了画面表示に必要な購入日時(buyconfirmから購入ボタンを押した時点の日付)、配送方法名
	//金額を表示するために必要なレコードを取りだす
	//しかしt_buyテーブルのカラムには配送方法名はなく、t_buyテーブルと他のテーブル、つまりdelivery_methodテーブル
	//とjoinして配送方法名を取得する必要がある。
	//二つのテーブルをjoinするために、t_buyテーブルのdelivery_method_idとdelivery_methodテーブルのidを結合する
	//かつ、このDaoのメソッドの引数には上記で購入情報を登録した際に自動採番された値の戻り値であるbuyIdを設置する
	//理由としてbuyIdはt_buyテーブルのレコード登録の度に発行されるものでt_buyテーブルのidと紐づくカラム値であるため
	public Buy getBDBbyBuyId(int BuyId){
		Connection con = null;

		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = " select * from t_buy join delivery_method "
					+ "on t_buy.delivery_method_id=delivery_method.id where t_buy.id =?";
			//PreparedStatementを発行し、SELECT文を実行する。
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1,BuyId);
			//ResultSetを発行し、PreparedStatementの結果を格納する
			ResultSet rs = prstmt.executeQuery();
			//rs.next()でrsの中身を他の変数に変換する
			if(!rs.next()) {
				return null;
			}
			Buy bdb = new Buy();
			bdb.setId(rs.getInt("id"));
			bdb.setUserId(rs.getInt("user_id"));
			bdb.setTotalPrice(rs.getInt("total_price"));
			bdb.setDeliveryMethodId(rs.getInt("delivery_method_id"));
			bdb.setCreateDate(rs.getDate("create_date"));
			bdb.setDeliveryMethodName(rs.getString("name"));
			bdb.setDeliveryMethodPrice(rs.getInt("price"));

			return bdb;

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
		return null;
	}

	public ArrayList<Buy> getBDBbyuserinfoId(int userinfo_id){
		Connection con = null;
		ArrayList<Buy> buylist = new ArrayList<Buy>();
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			//jspで表示する際は３つのカラム値のみの表示になり、下のsqlでデータベースを操作する時点でjspでの表示形式の形
			//まで精査することは可能だが、結局jsp側で値を表示させたいときと<属性名.プロパティ>で捕まえてこなくては
			//いけないのでテーブル結合の結果、あまりに巨大なテーブルになってしまった場合は精査する必要がある。
			//しかし、一般的には読みやすいsql文を心がけ、表示のことは分担作業で担当が変わる可能性もある。
			//締め切りまでに早く終わらせる必要があるのでDaoでのテーブル結合からのselectでの精査は省略してもよいと
			//思われる。
			String sql = "SELECT * FROM t_buy JOIN delivery_method "
					+ "on t_buy.delivery_method_id=delivery_method.id WHERE t_buy.user_id = ? ";
			//PreparedStatementを発行し、SELECT文を実行する。
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1,userinfo_id);
			//ResultSetを発行し、PreparedStatementの結果を格納する
			ResultSet rs = prstmt.executeQuery();
			//while(rs.next())でrsに格納されているレコードの値を取り出す.
			//またBuybeansに値を入れるためwhile(){}の中で毎回Buybeansをインスタンス化する.
			while(rs.next()) {
				Buy BuybyId = new Buy();
				BuybyId.setId(rs.getInt("id"));
				BuybyId.setUserId(rs.getInt("user_id"));
				BuybyId.setTotalPrice(rs.getInt("total_price"));
				BuybyId.setDeliveryMethodId(rs.getInt("delivery_method_id"));
				BuybyId.setDeliveryMethodName(rs.getString("name"));
				BuybyId.setDeliveryMethodPrice(rs.getInt("price"));
				BuybyId.setCreateDate(rs.getDate("create_date"));
				buylist.add(BuybyId);
			}
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
		return buylist;
	}
	//buyhistoryの購入詳細のbdbの表示のためにつかうメソッド。すでにbdbのレコードから紐づくものになるので複数のレコード
	//を表示させる必要はなく、ArrayListにはしない。
//	public Buy getBDBbyuserId(int userId){
//		Connection con = null;
//
//		try {
//			//データベースへ接続
//			con = DBmanager.getConnection();
//			//sql文を準備
//			String sql = " select * from t_buy join delivery_method "
//					+ "on t_buy.delivery_method_id=delivery_method.id where t_buy.id =?";
//			//PreparedStatementを発行し、SELECT文を実行する。
//			PreparedStatement prstmt = con.prepareStatement(sql);
//			prstmt.setInt(1,userId);
//			//ResultSetを発行し、PreparedStatementの結果を格納する
//			ResultSet rs = prstmt.executeQuery();
//			//rs.next()でrsの中身を他の変数に変換する
//			if(!rs.next()) {
//				return null;
//			}
//			Buy bdb = new Buy();
//			bdb.setId(rs.getInt("id"));
//			bdb.setUserId(rs.getInt("user_id"));
//			bdb.setTotalPrice(rs.getInt("total_price"));
//			bdb.setDeliveryMethodId(rs.getInt("delivery_method_id"));
//			bdb.setCreateDate(rs.getDate("create_date"));
//			bdb.setDeliveryMethodName(rs.getString("name"));
//			bdb.setDeliveryMethodPrice(rs.getInt("price"));
//
//			return bdb;
//
//		}catch (SQLException e) {
//			System.out.println(e.getMessage());
//
//		} finally {
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//
//					e.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}
}

