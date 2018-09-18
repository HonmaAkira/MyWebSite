package mywebsite;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Item;

/**
 * Servlet implementation class Itemdelete
 */
@WebServlet("/Itemdelete")
public class Itemdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Itemdelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//sessionscopeからArrayListを取り出す
		ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
		//元々のsessionscopeに入っていたcartコレクションはremoveする
		session.removeAttribute("cart");
		//hrefに設定したvarStatusのindexをgetParameterで取得する
		String index =request.getParameter("index");
		//String型をint型に変換する
		int Index = Integer.parseInt(index);
		//ArrayList cartのhrefに設定されたindex番号に紐づく要素をremoveする
		cart.remove(Index);
		//狙った要素をremoveしたArrayList cartをもう一度sessionscopeにセットする
		session.setAttribute("cart",cart);
		//Cartサーブレットへ遷移する
		response.sendRedirect("Cart");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
