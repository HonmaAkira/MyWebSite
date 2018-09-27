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
 * Servlet implementation class Itemsearch
 */
@WebServlet("/Itemsearch")
public class Itemsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Itemsearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//HttpSessionインスタンスを取得する
		HttpSession session = request.getSession();
		//pagenation機能：まずpagenationのhrefにItemDaoのItemSearch(sarchword,pagenum,maxcount)の引数のsarchword=?とpagenum=1
		//(0以下のマイナスの値にならないように条件を付ける)
		//sqlはlimitとoffsetを使うことを忘れないようにする。
		//またmaxcountとして一ページに表示する商品の個数を入れる。変数としてmaxcountへ値を格納してもよいし
		//最大表示件数を決めた数から変更したくない場合は、定数として先にfinalでmaxcount=6のように決まてしまってもよい
		final int MAXCOUNT = 6;

		try {

		//引数のintの値を0で初期化
		int Price1 = 0;
		int Price2 = 0;

		//検索フォームに入力された値をgetParameterで取得する
		String name = request.getParameter("name");
		String price1 = request.getParameter("price1");
		String price2 = request.getParameter("price2");

		//priceフォームに値が入っていた場合にString型からint型へ変換する作業
		//文字列以外の時に()
		if (!price1.equals("")) {
			try {
				int price01 = Integer.parseInt(price1);
				Price1 = price01;
			}catch(NumberFormatException e) {
				request.setAttribute("ErrMsg","もう一度数値を入れて検索してください");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
				dispatcher.forward(request, response);
				return;
			}
				
			
		}

		if (!price2.equals("")) {
			try {
				int price02 = Integer.parseInt(price2);
				Price2 = price02;
			}catch(NumberFormatException e) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
				dispatcher.forward(request, response);
				return;
			}
				
			
		}

		//検索された空欄ではないフォームの値をセッションスコープにセットするための条件を決める
				if(!name.equals("")) {
					session.setAttribute("searchword", name);
				}else if(!price1.equals("")) {
					session.setAttribute("searchword", price1);
				}else if(!price2.equals("")) {
					session.setAttribute("searchword", price2);
				}

		//表示ページ番号が未指定の場合、1ページ目を表示
		int Pagenum = Integer.parseInt(request.getParameter("pagenum") == null ? "1" : request.getParameter("pagenum"));

		//表示リストを取得 1ページ表示分のみ
		ItemDao itemdao = new ItemDao();
		ArrayList<Item> SearchItemlist = itemdao.FindSearchforPagenation(name, Price1, Price2, Pagenum, MAXCOUNT);
		//検索し、ヒットしたt_itemのレコード数
		double Countlist = SearchItemlist.size();
		//検索アイテム数(double型からint型にキャストする)
		request.setAttribute("Countlist", (int)Countlist);
		//検索ワードに対しての総ページ数
		//(int)でdouble型からMath.celi()メソッドで数値の切り上げをした後、double型からint型へキャストしている
		int pageMax = (int)Math.ceil(Countlist/MAXCOUNT);
		request.setAttribute("pageMax", pageMax);
		//ページ表示検索アイテム
		request.setAttribute("SearchItemlist", SearchItemlist);
		//表示ページ
		request.setAttribute("Pagenum", Pagenum);

		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		dispatcher.forward(request, response);

		}catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
