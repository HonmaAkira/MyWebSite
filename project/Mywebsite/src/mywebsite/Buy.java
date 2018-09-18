package mywebsite;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DeliveryMethod;
import db.DeliveryDao;

/**
 * Servlet implementation class Buy
 */
@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buy() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("userinfo") == null) {
			response.sendRedirect("Login");
			return;
		}
		//jspへフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buy.jsp");
		dispatcher.forward(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//radioボタンの値をgetParameterで取得する
				String delivery =request.getParameter("delivery");
				//もし取得したgetParameterがnullだった場合エラーメッセージをリクエストスコープにセットして
				//jspへフォワードする
				if(delivery == null) {
					String ErrMsg = "配送方法が選択されていません";

					request.setAttribute("ErrMsg",ErrMsg);

					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/buy.jsp");
					dispatcher.forward(request, response);
				}
				//String型をint型に変換する
				int id = Integer.parseInt(delivery);
				//DeliveryDaoクラスをインスタンス化して、FindbyIdDeliveryMethod()を実行する
				DeliveryDao deliverydao = new DeliveryDao();
				DeliveryMethod deliverymethod = deliverydao.FindbyIdDeliveryMethod(id);
				//リクエストスコープにセットする
				request.setAttribute("deliverymethod", deliverymethod);
				//Buyconfirmサーブレットへ遷移する
				response.sendRedirect("Buyconfirm");
	}

}
