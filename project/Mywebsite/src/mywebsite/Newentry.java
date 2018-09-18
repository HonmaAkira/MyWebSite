package mywebsite;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.UserDao;

/**
 * Servlet implementation class Newentry
 */
@WebServlet("/Newentry")
public class Newentry extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Newentry() {
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

		

		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/newentry.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




		//リクエストパラメータに入っている値を取得
		String loginId = request.getParameter("loginId");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String copypass = request.getParameter("copypassword");
		String birth = request.getParameter("birth");

		//UserDaoをインスタンス化し、FindbyLoginId()を実行して、登録失敗の条件分岐も同時に行う

		UserDao userdao = new UserDao();
		userdao.FindbyLoginId(loginId);
		if(loginId.equals("")||name.equals("")||password.equals("")||copypass.equals("")||birth.equals("")) {
			request.setAttribute("fail1", "入力フォームに未入力の項目があります");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/newentry.jsp");
			dispatcher.forward(request, response);
			return;
		}else if(!password.equals(copypass)) {
			request.setAttribute("fail2", "パスワードとパスワード(確認)の内容が異なっています");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/newentry.jsp");
			dispatcher.forward(request, response);
			return;
		}else if(userdao.FindbyLoginId(loginId) != null) {
			request.setAttribute("fail3","既にそのログインIDは登録されています");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/newentry.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//リクエストパラメータを引数に渡し、Daoのメソッドを実行

		userdao.IncreaseUser(loginId, name, password, birth);

		//Indexサーブレットに遷移する
		response.sendRedirect("Index");
	}


}
