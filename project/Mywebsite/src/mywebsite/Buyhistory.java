package mywebsite;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Buy;
import beans.Item;
import db.BuyDao;
import db.BuydetailDao;

/**
 * Servlet implementation class Buyhistory
 */
@WebServlet("/Buyhistory")
public class Buyhistory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buyhistory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sessionを取得しようとして、もしセッションが存在しない場合は、ログインサーブレットへ遷移する
				HttpSession session = request.getSession();

				if (session.getAttribute("userinfo") == null) {
					response.sendRedirect("Login");
					return;
				}
		//hrefに配置したt_buy.idをgetParameterで取得する
		String buyId = request.getParameter("id");
		//String型をint型に変換する
		int BuyId = Integer.parseInt(buyId);
		//BuyDaoクラスをインスタンス化して、購入完了画面と同じ画面を表示させるため同じ内容のメソッドを使用するが
		//区別のために引数の名前を使う変数の名前にしておくのを忘れない。
		//bdb値使用画面表示用
		BuyDao buydao = new BuyDao();
		Buy BuyresultBDB = buydao.getBDBbyBuyId(BuyId);
		request.setAttribute("BuyresultBDB",BuyresultBDB);

		//bdbで表示できない商品情報表示用のbeansセット(複数列のレコード表示なのでArrayList,jsp側で後で拡張for文で表示させる)
		ArrayList<Item> BuyItemList = new ArrayList<Item>();
		BuydetailDao bdDao = new BuydetailDao();
		BuyItemList = bdDao.getItemListbyBuyId(BuyId);
		request.setAttribute("BuyItemList",BuyItemList );

		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyhistory.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
