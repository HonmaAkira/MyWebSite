package mywebsite;

import java.io.IOException;

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
 * Servlet implementation class Masterdelete
 */
@WebServlet("/Masterdelete")
public class Masterdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Masterdelete() {
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
//ボタンに貼り付けたid情報をgetParameterで取ってくる。
		String id = request.getParameter("id");
//String型をint型に変換する
		int Id = Integer.parseInt(id);
//ItemDaoをインスタンス化し、FindbyId()メソッドを実行して、結果をリクエストスコープに格納する
		ItemDao itemdao = new ItemDao();
		Item item = itemdao.FindbyId(Id);
		request.setAttribute("item",item);
//jspにフォワードする
		RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/masterdelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//hiddenで渡したリクエストパラメータをgetParameterで取得する
		String id = request.getParameter("id");
		//String型をint型にする
		int Id = Integer.parseInt(id);
		//ItemDaoをインスタンス化し、DeleteId()メソッドを実行する
		ItemDao itemdao = new ItemDao();
		itemdao.DeleteId(Id);
		//Masteritemサーブレットへ遷移する。
		response.sendRedirect("Masteritem");
	}

}
