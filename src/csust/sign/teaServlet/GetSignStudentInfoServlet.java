package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.SignNameInfo;
import csust.sign.bean.Dao.Impl.SignDaoImpl;

public class GetSignStudentInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SignDaoImpl sdi = new SignDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String allow_sign_id = req.getParameter("allow_sign_id");

		List<SignNameInfo> list = sdi.getRealTimeSignNameInfo(allow_sign_id);

		resp.setContentType("text/html;charset=utf-8");
		// resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();

		System.out.println(list);
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();
	}

}
