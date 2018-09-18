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

import beans.Item;
import db.ItemDao;

/**
 * Servlet implementation class Itemdetail
 */
@WebServlet("/Itemdetail")
public class Itemdetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Itemdetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//sessionを取得しようとして、もしセッションが存在しない場合は、ログインサーブレットへ遷移する
		HttpSession session = request.getSession();

		if (session.getAttribute("userinfo") == null) {
			response.sendRedirect("Login");
			return;
		}
		//リンクに貼り付けたidをgetParameterで取得する
		String id = request.getParameter("id");
		//String型をint型に変換する
		int Id = Integer.parseInt(id);
		//ItemDaoクラスをインスタンス化し、リンクに貼り付けたidをFindbyId()メソッドの引数に渡し、その結果をインスタンス化したItemインスタンスに格納して
		//リクエストスコープにセットする
		ItemDao itemdao = new ItemDao();
		Item item = itemdao.FindbyId(Id);
		request.setAttribute("item", item);
		//jspにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/itemdetail.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Sessionを取得する
		HttpSession session = request.getSession();

		//リンクに貼り付けたidをgetParameterで取得する
		String id = request.getParameter("id");
		//String型をint型に変換する
		int Id = Integer.parseInt(id);
		//ItemDaoクラスをインスタンス化し、リンクに貼り付けたidをFindbyId()メソッドの引数に渡し、その結果をインスタンス化したItemインスタンスに格納して
		//リクエストスコープにセットする
		ItemDao itemdao = new ItemDao();
		Item item = itemdao.FindbyId(Id);
		request.setAttribute("item", item);


		//SessionからCart情報を取得する
		ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");

		//もしArrayListのコレクションcartが存在しなかった場合、作成する
		if(cart==null) {
		   cart = new ArrayList<Item>();
		}

		//作成したコレクションに商品情報インスタンスを格納する
		cart.add(item);

		//追加したコレクションをsessionscopeへ格納する
		session.setAttribute("cart",cart);

		//Cartサーブレットへ遷移する
		response.sendRedirect("Cart");
	}

}
