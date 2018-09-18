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

import beans.Item;
import db.ItemDao;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
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

		//カテゴリーリンクを押したときに貼り付けられているcategory_idをgetParameter()で取得する
		String category = request.getParameter("category_id");

		//カテゴリーがnullだったときはFindAllItemで一覧表示させ、nullでなかった時はリンクに設定したカテゴリーIDに
		//紐づいたt_itemのレコードを呼び出す。
		if(category==null) {
			ItemDao itemdao = new ItemDao();
			ArrayList<Item> itemlist =itemdao.FindAllItem();
			request.setAttribute("itemlist",itemlist);

			int count = itemdao.CountRecord();
			request.setAttribute("count",count);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			dispatcher.forward(request, response);
			return;
		}else if(category!=null) {
			//String型をint型に変換する
			int Category = Integer.parseInt(category);

			ItemDao itemdao = new ItemDao();
			ArrayList<Item> itemlist = itemdao.FindbyCategoryId(Category);
			request.setAttribute("Categorylist", itemlist);

			int count = itemdao.CountbyCategoryId(Category);
			request.setAttribute("CategoryNumber",count);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//ItemDaoをインスタンス化し、 FindAllItem()メソッドでt_itemのレコードを順番に取り出し、それをリクエストスコープに
		//セットしていく

		ItemDao itemdao = new ItemDao();
		ArrayList<Item> itemlist =itemdao.FindAllItem();
		request.setAttribute("itemlist",itemlist);

		//t_itemのすべてのレコードの数も表示するのでCountRecord()メソッドを実行する。
		//結果をリクエストスコープに保存する
		int count = itemdao.CountRecord();
		request.setAttribute("count",count);
		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//引数のintの値を0で初期化
		int Price1 = 0;
		int Price2 = 0;

		//フォームに入力された値をgetParameterで取得する
		String name = request.getParameter("name");
		String price1 = request.getParameter("price1");
		String price2 = request.getParameter("price2");


		//itemlist.size()
		//String型をint型に変換する
		//("")の文字列が入力されている時とそうでないときで分岐させる
//		if(!price1.equals("")&&!price2.equals("")) {
//			int price01 = Integer.parseInt(price1);
//			int price02 = Integer.parseInt(price2);
//
//			Price1 = price01;
//			Price2 = price02;
//
//		}else if(price1.equals("")) {
//			int price02 = Integer.parseInt(price2);
//
//			Price2 = price02;
//		}else if(price2.equals("")) {
//			int price01 = Integer.parseInt(price1);
//
//			Price1 = price01;
//		}else if(price1.equals("")&&price2.equals("")){
//			//ItemDaoをインスタンス化し、FindSearchメソッドを実行して、その結果をリクエストスコープに格納する
//			ItemDao itemdao = new ItemDao();
//			List<Item> itemlist = itemdao.FindSearch(name, Price1, Price2);
//
//			request.setAttribute("itemlist", itemlist);
//
//			//jspへフォワードする
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
//			dispatcher.forward(request, response);
//			return;
//		}


//		条件分岐の際に自分はすべて一度にelseで行おうとする癖がある
//		上記の構文はまさにその悪い癖が出ている部分で、プログラムはなるべく短く簡潔に書くことが理想だが
//		日本語で考えた構図をそのまま書き出してしまってはうまくいかない部分が多々出てくることが予想される
//		英語のように、プログラム言語も日本語の文法とは違う考え方で思考し書いたほうがうまくいくので
//		制御構文を使用する際にはフローチャートを書き、考えた方が良い

//		分岐の順番により、上手く狙った結果にたどり着くかその前の条件に捕まってしまうか
//		表示結果に大きな差が出るので、多分岐の際はよく考え多様なケースを想定して色々試してみる。
		if(!price1.equals("")) {
			int price01 = Integer.parseInt(price1);
			Price1 = price01;
		}

		if(!price2.equals("")) {
			int price02 = Integer.parseInt(price2);
			Price2 = price02;
		}


		//ItemDaoをインスタンス化し、FindSearchメソッドを実行して、その結果をリクエストスコープに格納する
		ItemDao itemdao = new ItemDao();
		List<Item> itemlist = itemdao.FindSearch(name, Price1, Price2);
		int Countlist =itemlist.size();
		request.setAttribute("Countlist", Countlist);
		request.setAttribute("itemlist", itemlist);

		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);
	}

}
