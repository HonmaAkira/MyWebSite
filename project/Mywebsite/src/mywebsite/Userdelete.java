package mywebsite;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import db.UserDao;

/**
 * Servlet implementation class Userdelete
 */
@WebServlet("/Userdelete")
public class Userdelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Userdelete() {
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
		//UserDaoクラスをインスタンス化し、Userbeansに値を格納して、リクエストスコープにUserbeansをセットして
		//JSPで使用できるようにする
		UserDao userdao = new UserDao();
		User user = userdao.FindbyId(Id);
		//リクエストスコープへセットする
		request.setAttribute("user", user);
		//jspへフォワードする
		RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/userdelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//hiddenでinputしたid情報をgetParameterで取ってくる
		//ボタンに貼り付けたid情報をgetParameterで取ってくる。
				String id = request.getParameter("id");
		//String型をint型に変換する
				int Id = Integer.parseInt(id);
		//UserDaoクラスをインスタンス化し、DeletebyIdメソッドを実行する。
				UserDao userdao = new UserDao();
				userdao.DeletebyId(Id);
		//Userlistサーブレットへ遷移する
				response.sendRedirect("Userlist");

	}

}
