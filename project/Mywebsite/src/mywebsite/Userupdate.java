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
 * Servlet implementation class Userupdate
 */
@WebServlet("/Userupdate")
public class Userupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Userupdate() {
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
		//ボタンに貼り付けたid情報をgetParameterで取ってくる。
		String id = request.getParameter("id");
		//String型をint型に変換する
		int Id = Integer.parseInt(id);
		//UserDaoクラスをインスタンス化し、Userbeansに値を格納して、リクエストスコープにUserbeansをセットして
		//JSPで使用できるようにする
		UserDao userdao = new UserDao();
		User user = userdao.FindbyId(Id);
		//結果を繰り返し使用したいためセッションスコープへセットする
		session.setAttribute("user", user);
		//jspをフォワードして画面を表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/userupdate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//sessionscopeのインスタンスを使用するためにHttpSessionインスタンスの取得
		HttpSession session = request.getSession();
		//deGetでセッションスコープに格納したインスタンスを属性名を使って取り出す
		User user = (User)session.getAttribute("user");
		//フォームに入れた値をgetParameterで取ってくる。
		String id =request.getParameter("id");
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		String copypass = request.getParameter("copypass");
		String birth=request.getParameter("birth");
		//String型をint型に変換する
		int Id = Integer.parseInt(id);
		//UserDaoをインスタンス化する
		UserDao userdao = new UserDao();
		//ユーザ更新の際の更新失敗条件を条件分岐させて失敗のメッセージをリクエストスコープに
		//セットしてuserupdate.jspへフォワードする。更新失敗条件に引っかかった場合、その後のUpdatebyIdの処理は行っては
		//いけないのでreturn;でその時点でのプログラムで終了するようにする
		//passとcopypassが共に空欄だった場合にNonPassUpdate()を実行する
		if(name.equals("")||birth.equals("")) {
			request.setAttribute("fail1","フォームに未入力の項目があります");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/userupdate.jsp");
			dispatcher.forward(request, response);
			return;
		}else if(!pass.equals(copypass)) {
			request.setAttribute("fail2","パスワードとパスワード(確認用)の内容が異なっています");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/userupdate.jsp");
			dispatcher.forward(request, response);
			return;
		}else if(pass.equals("")&&copypass.equals("")) {
			userdao.NonPassUpdate(name, birth, Id);
			response.sendRedirect("Userlist");
			return;
		}
		//UpdatebyIdメソッドを実行する
		userdao.UpdatebyId(Id, name, pass, birth);
		//Userlistサーブレットへ遷移する。
		response.sendRedirect("Userlist");
	}

}
