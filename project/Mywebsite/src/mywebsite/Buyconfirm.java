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
import beans.DeliveryMethod;
import beans.Item;
import beans.User;
import db.DeliveryDao;

/**
 * Servlet implementation class Buyconfirm
 */
@WebServlet("/Buyconfirm")
public class Buyconfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buyconfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userinfo") == null) {
			response.sendRedirect("Login");
			return;
		}
		//sessionからcartインスタンスを取り出す
		//for文を使ってリストの中の各要素の金額を足す
		//足し終わった値を変数に入れてリクエストスコープにセットする。
		DeliveryMethod deliverymethod = (DeliveryMethod)request.getAttribute("deliverymethod");

		ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
		int sum = 0;
		for(Item item : cart) {

			sum = sum + item.getPrice();


			System.out.println(item.getPrice());
		}
		int total = sum;
		//合計金額をリクエストスコープにセットする
		request.setAttribute("total",total);

		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyconfirm.jsp");
		dispatcher.forward(request, response);
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

		//sessionからcartインスタンスを取り出す
				//for文を使ってリストの中の各要素の金額を足す



				ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
				int sum = 0;
				for(Item item : cart) {

					sum = sum + item.getPrice();


					System.out.println(item.getPrice());
				}
				int total = sum;


		String delivery =request.getParameter("delivery");
		//もし取得したgetParameterがnullだった場合エラーメッセージをリクエストスコープにセットして
		//jspへフォワードする
		if(delivery == null) {
			String ErrMsg = "配送方法が選択されていません";

			request.setAttribute("ErrMsg",ErrMsg);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buy.jsp");
			dispatcher.forward(request, response);
		}
		//セッションのuserinfoを取り出す
		User userinfo = (User)session.getAttribute("userinfo");

		//String型をint型に変換する
		int id = Integer.parseInt(delivery);
		//DeliveryDaoクラスをインスタンス化して、FindbyIdDeliveryMethod()を実行する
		DeliveryDao deliverydao = new DeliveryDao();
		DeliveryMethod deliverymethod = deliverydao.FindbyIdDeliveryMethod(id);
		//足し終わった合計金額の値とその他の値をBuyDataBeansのフィールドに格納する
		Buy bdb = new Buy();
		bdb.setUserId(userinfo.getId());
		bdb.setTotalPrice(total);
		bdb.setDeliveryMethodId(deliverymethod.getId());
		bdb.setDeliveryMethodName(deliverymethod.getName());
		bdb.setDeliveryMethodPrice(deliverymethod.getPrice());

		//購入情報のbdbインスタンスをセッションスコープに格納する
		session.setAttribute("bdb",bdb);

		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buyconfirm.jsp");
		dispatcher.forward(request, response);
	}

}
