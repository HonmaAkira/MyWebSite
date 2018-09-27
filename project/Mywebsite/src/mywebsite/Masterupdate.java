package mywebsite;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.Item;
import db.ItemDao;

/**
 * Servlet implementation class Masterupdate
 */
@WebServlet("/Masterupdate")
//fileのアップロードをするためには以下のMultipartConfig(location=uploadフォルダの場所,maxFileSizeの指定)と下記メソッドが必要である
@MultipartConfig(location="C:\\pleiades\\workspace\\Mywebsite\\WebContent\\upload", maxFileSize=1048576)
public class Masterupdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Masterupdate() {
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
		//ItemDaoをインスタンス化し、FindbyId()メソッドを実行して、結果を繰り返し使用したいため
		//セッションスコープに格納する
				ItemDao itemdao = new ItemDao();
				Item item = itemdao.FindbyId(Id);
				session.setAttribute("item",item);

		//jspにフォワードする
				RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/masterupdate.jsp");
				dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sessionscopeのインスタンスを使用するためにHttpSessionインスタンスを取得
		HttpSession session = request.getSession();
		//doGetでセッションスコープに送ったインスタンスを属性名を使って取り出す
		Item item =(Item)session.getAttribute("item");


		//リクエストパラメータの値をgetParameter()で取得する
				String id = request.getParameter("id");
				String category = request.getParameter("category");
				String name = request.getParameter("name");
				String detail = request.getParameter("detail");
				String price = request.getParameter("price");

		//getParameterでなくfileはgetPartでリクエストスコープの値を取ってくる
		//this.getFileNameの変数名をitemdaoのIncreaseItemの引数に入れてデータベース内にfile名を入れられる
		//またファイルをweb上で表示させたいときはWebContent直下の/WebContent/upload/logo.pngのようにして
		//<img src="WebContent/upload/logo.png"のように呼び出すことが出来る。
		Part part = request.getPart("image");

		if(part.getSize()==0) {
			request.setAttribute("ErrMsg1", "画像ファイルが選択されていません");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/masterupdate.jsp");
			dispatcher.forward(request, response);
			return;
		}
        String image = this.getFileName(part);
        part.write(image);



		//フォームに画像以外の未入力の項目が一つでもある場合"未入力の項目があります。戻るボタンを押して、
		//もう一度やり直して下さい"のエラーメッセージを
		//ErrMsg2の属性名でリクエストスコープにセットし、JSPへフォワードする
		if(id.equals("")||category.equals("")||name.equals("")||detail.equals("")||price.equals("")) {
			request.setAttribute("ErrMsg2","未入力の項目があります");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/masterupdate.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//String型をint型に変換する
		//スコープの中でしか変数は機能しないので、プログラム中で使用するときは、先に変数を宣言し値を初期化しておく
		int Id = -1;
		int Category = -1;
		int Price = -1;
		try {
			Id = Integer.parseInt(id);
			Category = Integer.parseInt(category);
			Price = Integer.parseInt(price);
		}catch(NumberFormatException e) {
			request.setAttribute("ErrMsg3","価格に数値でなく文字列が入っています");
			RequestDispatcher dispatcher= request.getRequestDispatcher("/WEB-INF/jsp/masterupdate.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//ItemDaoをインスタンス化し、UpdateItem()メソッドを実行する
		ItemDao itemdao = new ItemDao();
		itemdao.UpdateItem(Id, Category, name, detail, Price, image);
		session.removeAttribute("item");
		//Masteritemサーブレットへ遷移する
		response.sendRedirect("Masteritem");
	}
	private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }


}
