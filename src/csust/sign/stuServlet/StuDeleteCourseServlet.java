package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Dao.Impl.StudentCourseDaoImpl;

/**
 * 学生删除一门课程
 * 
 * @author anLA7856
 *
 */
public class StuDeleteCourseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String student_id = req.getParameter("student_id");
		String course_id = req.getParameter("course_id");

		if (student_id == null || course_id == null) {
			return;
		}

		int result = new StudentCourseDaoImpl().studentDeleteOneCourse(
				student_id, course_id);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		pw.write(JSONArray.fromObject(result).toString());
		pw.flush();
		pw.close();

	}
}
