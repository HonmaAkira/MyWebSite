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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログインセッションを取得しようとし、もしセッションが存在する場合はIndexサーブレットへ遷移する
		HttpSession session = request.getSession();

		if(session.getAttribute("userinfo")!= null) {
			response.sendRedirect("Index");
			return;
		}

		//jspをフォワードして画面を表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータのフォームに入っている値を取得
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");

		//リクエストパラメータを引数に渡し、Daoのメソッドを実行
		UserDao userdao = new UserDao();
		User user=userdao.Findbylogininfo(loginId, password);

		/** テーブルに該当のデータが見つからなかった場合 **/
		if (user == null) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "ログインに失敗しました。ログインIDまたはパスワードが異なります。");
			// ログインjspにもう一度フォワード
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
						dispatcher.forward(request, response);
						return;
					}

		/** テーブルに該当のデータが見つかった場合 **/
		// セッションにユーザの情報をセット
		HttpSession session = request.getSession();
		session.setAttribute("userinfo",user);

		// メイン画面のサーブレットにリダイレクト
		response.sendRedirect("Index");
	}

}
