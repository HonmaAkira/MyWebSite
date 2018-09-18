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
import db.BuyDao;

/**
 * Servlet implementation class Userinfo
 */
@WebServlet("/Userinfo")
public class Userinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Userinfo() {
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

		//hrefに貼り付けたuserinfo.idをgetParameterで取得する
		String userid = request.getParameter("id");
		//String型をint型に変換する
		int userId = Integer.parseInt(userid);
		//BuyDaoクラスをインスタンス化してgetBDBbyuserinfoId()をsessionのuserinfo.idの値を引数にしてDaoを実行する
		BuyDao buydao = new BuyDao();
		//Dao内のArrayListをjspで使用できるように新しく変数に移し替える
		ArrayList<Buy> buylist = buydao.getBDBbyuserinfoId(userId);
		//リクエストスコープにセットする
		request.setAttribute("buylist", buylist);
		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userinfo.jsp");
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
