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

import db.ItemDao;
/**
 * Servlet implementation class Masterentry
 */
@WebServlet("/Masterentry")
//fileのアップロードをするためには以下のMultipartConfig(location=uploadフォルダの場所,maxFileSizeの指定)と下記メソッドが必要である
@MultipartConfig(location="C:\\pleiades\\workspace\\Mywebsite\\WebContent\\upload", maxFileSize=1048576)
public class Masterentry extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Masterentry() {
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

				//jspをフォワードして画面を表示
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/masterentry.jsp");
				dispatcher.forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //getParameterでなくfileはgetPartでリクエストスコープの値を取ってくる
		//this.getFileNameの変数名をitemdaoのIncreaseItemの引数に入れてデータベース内にfile名を入れられる
		//またファイルをweb上で表示させたいときはWebContent直下の/WebContent/upload/logo.pngのようにして
		//<img src="WebContent/upload/logo.png"のように呼び出すことが出来る。
		Part part = request.getPart("image");

		if(part.getSize() == 0) {
			request.setAttribute("ErrMsg1", "画像ファイルが選択されていません");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/masterentry.jsp");
			dispatcher.forward(request, response);
			return;
		}

        String image = this.getFileName(part);
        part.write(image);


		//リクエストパラメータの値を取得する
		String category = request.getParameter("category");
		String name = request.getParameter("name");
		String detail = request.getParameter("detail");
		String price = request.getParameter("price");

		//フォームに画像以外の未入力の項目がある場合"未入力の項目があります"の内容をErrMsgとして
		//リクエストスコープにセットしjspへフォワードする
		if(category.equals("")||name.equals("")||detail.equals("")||price.equals("")) {
			request.setAttribute("ErrMsg2","未入力の項目があります" );
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/masterentry.jsp");
			dispatcher.forward(request, response);
			return;
				}

		//categoryとpriceの型をStringからintに変換する
		int Category =Integer.parseInt(category);
		int Price =Integer.parseInt(price);

		//ItemDaoクラスをインスタンス化してIncreaseItemメソッドを実行する
		ItemDao itemdao = new ItemDao();
		itemdao.IncreaseItem(Category, name, detail, Price,image);


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
