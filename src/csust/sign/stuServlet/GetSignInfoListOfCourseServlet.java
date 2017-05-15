package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.StudentSignDetail;
import csust.sign.bean.Dao.Impl.SignDaoImpl;

/**
 * 用于获得某一位同学某一门课程的签到记录
 * 
 * @author anLA7856
 *
 */
public class GetSignInfoListOfCourseServlet extends HttpServlet {

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
		// 获得相应参数。
		String student_id = req.getParameter("student_id");
		String course_id = req.getParameter("course_id");
		String start = req.getParameter("start");
		String count = req.getParameter("count");

		if (student_id == null || course_id == null || start == null
				|| count == null) {
			return;
		}

		List<StudentSignDetail> list = sdi.getSignListByCourseIdAndTeacherId(
				student_id, course_id, start, count);
		resp.setContentType("text/html;charset=utf-8");
		// resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();

		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();
	}
}
