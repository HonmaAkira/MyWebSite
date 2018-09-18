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
 * Servlet implementation class Masteritem
 */
@WebServlet("/Masteritem")
public class Masteritem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Masteritem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sessionを取得しようとして、もしセッションが存在しない場合は、ログインサーブレットへ遷移する
				HttpSession session = request.getSession();

				if(session.getAttribute("userinfo")==null) {
					response.sendRedirect("Login");
					return;
				}

		//ItemDaoをインスタンス化、同じくArrayListのbeansもインスタンス化してFindAllItem()メソッドを実行する

				ItemDao itemdao = new ItemDao();
				ArrayList<Item> itemlist = itemdao.FindAllItem();

		//リクエストスコープに格納する
				request.setAttribute("itemlist",itemlist);


				//jspをフォワードして画面を表示
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/masteritem.jsp");
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
