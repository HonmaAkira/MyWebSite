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

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
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

				//sessionscopeからインスタンスを取得
				ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");

				//もし、cartコレクションが存在しない場合にcartコレクションを作成してsessionscopeに格納しておく
				if(cart == null) {
					cart = new ArrayList<Item>();
					session.setAttribute("cart", cart);
				}

				//エラーメッセージの宣言を行う
					String CartErrMsg = "";
				//もしcartコレクションの中身が空だったとき(コレクション内の要素数が0だったとき)はエラーメッセージ
				//をリクエストスコープに格納する
				if(cart.size()==0) {
				   CartErrMsg = "何も選択されていません";
				}
				//エラーメッセージをリクエストスコープに(空の文字列かエラーメッセージのどちら)
				//cartコレクションをセッションスコープに入れ、jspで使えるようにする。
				session.setAttribute("cart",cart);
				request.setAttribute("CartErrMsg",CartErrMsg);
				//jspへフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
				dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
