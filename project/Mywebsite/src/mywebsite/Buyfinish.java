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
import beans.BuyDetail;
import beans.Item;
import db.BuyDao;
import db.BuydetailDao;

/**
 * Servlet implementation class Buyfinish
 */
@WebServlet("/Buyfinish")
public class Buyfinish extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buyfinish() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userinfo") == null) {
			response.sendRedirect("Login");
			return;
		}

		//sessionからcartインスタンスを取得
		ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
		//sessionからbdbインスタンスを取得
		Buy bdb = (Buy)session.getAttribute("bdb");
		//BuyDaoをインスタンス化してInsertBuy()メソッドを実行し、t_buyデータベースに値を登録する
		BuyDao buydao = new BuyDao();
		int buyId = buydao.InsertBuy(bdb);
		//自動採番された値をbuyIdとしてサイトで買い物をした回数と、もう一つt_itemテーブルのidを取得して
		//t_buydetailのitemIdにセットすることでt_buy,買い物情報とt_detail何を何回目の買い物で買ったかの買い物履歴
		//を登録する
		//取りだしたcartインスタンスからfor文で要素を取り出し、t_itemの商品を個別に識別するidカラムの値をbuydetailbeans
		//のitemIdの値にセットする。
		//そしてt_itemとt_buyのテーブルを結合するためのbuyIdの値に、先ほどの一回の買い物情報と紐づく自動採番の値を
		//セットする
		for(Item cartitem:cart) {
			BuyDetail bddb = new BuyDetail();
			bddb.setBuy_id(buyId);
			bddb.setItem_id(cartitem.getId());
			BuydetailDao buydetaildao = new  BuydetailDao();
			buydetaildao.InsertBuydetail(bddb);
		}

		//画面表示用のbeansセット
		Buy finishBDB = buydao.getBDBbyBuyId(buyId);
		request.setAttribute("finishBDB",finishBDB);

		//bdbで表示できない商品情報表示用のbeansセット(複数列のレコード表示なのでArrayList,jsp側で後で拡張for文で表示させる)
		ArrayList<Item> BuyItemList = new ArrayList<Item>();
		BuydetailDao bdDao = new BuydetailDao();
		BuyItemList=bdDao.getItemListbyBuyId(buyId);
		request.setAttribute("BuyItemList",BuyItemList);

		//買い物が終わったのでsessionのcartインスタンスを削除する
		   session.removeAttribute("cart");

		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyfinish.jsp");
		dispatcher.forward(request, response);
	}

	}
