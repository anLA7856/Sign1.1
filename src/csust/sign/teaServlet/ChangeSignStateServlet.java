package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Dao.Impl.SignDaoImpl;
/**
 * 用于更改某位学生的某一次签到记录
 * @author U-anLA
 *
 */
public class ChangeSignStateServlet extends HttpServlet{

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
		String signState = req.getParameter("signState");
		String decodeSignState = new String(signState.getBytes("ISO-8859-1"), "UTF-8");
		
		
		int result = sdi.UpdateOneSignStateInfo(allow_sign_id, student_id, decodeSignState);
		
		resp.setContentType("text/html;charset=utf-8");
		// resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();

		System.out.println(result);
		pw.write(JSONArray.fromObject(result).toString());
		pw.flush();
		pw.close();
		
	}
	
	

	
}
