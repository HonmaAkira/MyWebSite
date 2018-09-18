package mywebsite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class Userlist
 */
@WebServlet("/Userlist")
public class Userlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Userlist() {
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
		//UserDaoをインスタンス化し、同じくArrayListのbeansもインスタンス化して、引数無しのAllFindUser()を実行する
		UserDao userdao = new UserDao();
		ArrayList<User> userlist = userdao.AllFindUser();

		//リクエストスコープに格納する
		 request.setAttribute("userlist",userlist);

		//jspをフォワードして画面を表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userlist.jsp");
			dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォームに入力された値をgetParameterで取りだす
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String birth1 = request.getParameter("birth1");
		String birth2 = request.getParameter("birth2");

		//UserDaoをインスタンス化し、同じくArrayListのbeansもインスタンス化して、フォーム値の変数をFindSeach()の
		//引数に入れて実行する
		UserDao userdao = new UserDao();
		List<User> userlist = userdao.FindSearch(loginId, name, birth1, birth2);
		//リクエストスコープにセットする
		request.setAttribute("userlist",userlist);
		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userlist.jsp");
		dispatcher.forward(request, response);


	}

}
