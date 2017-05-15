package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.StudentInfo;
import csust.sign.bean.Dao.Impl.StudentDaoImpl;

/**
 * 用于获得某一次签到未签到的学生信息。
 * 
 * @author anLA7856
 *
 */
public class GetUnsignedStudentsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String allow_sign_id = req.getParameter("allow_sign_id");

		if (allow_sign_id == null) {
			return;
		}

		List<StudentInfo> list = new StudentDaoImpl()
				.getUnsignedStudentsByAllowSignId(allow_sign_id);
		resp.setContentType("text/html;charset=utf-8");
		// resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();
	}
}
