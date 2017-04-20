package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Dao.Impl.SignDaoImpl;

public class VertifyIfCanSignServlet extends HttpServlet{

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
		String student_id = req.getParameter("student_id");
		
		int result = sdi.vertifyIfCanSign(allow_sign_id, student_id);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		
		System.out.println(result);
		pw.write(JSONArray.fromObject(result).toString());
		pw.flush();
		pw.close();
	}
}
