package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.DeliveryMethod;

public class DeliveryDao {

	public DeliveryMethod FindbyIdDeliveryMethod(int id) {
		Connection con = null;
		try {
			//データベースへ接続
			con = DBmanager.getConnection();
			//sql文を準備
			String sql = "SELECT * FROM delivery_method where id = ?";
			//SELECTを実行するPreparedStatementを発行する
			PreparedStatement prstmt = con.prepareStatement(sql);
			prstmt.setInt(1, id);
			//ResultSetにPreparedStatementの結果を格納する
			ResultSet rs = prstmt.executeQuery();
			//rs.next()でrsから値を取得し、変数に入れ直す
			if (!rs.next()) {
				return null;
			}

			int Id = rs.getInt("id");
			String name = rs.getString("name");
			int price = rs.getInt("price");
			DeliveryMethod delivery = new DeliveryMethod(Id, name, price);

			return delivery;

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
}
