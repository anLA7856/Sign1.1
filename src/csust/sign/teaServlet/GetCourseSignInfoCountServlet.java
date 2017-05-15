package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csust.sign.bean.Dao.Impl.AllowSignDaoImpl;
import csust.sign.bean.Dao.Impl.SignDaoImpl;
import csust.sign.bean.Dao.Impl.StudentCourseDaoImpl;

/**
 * 用于某一位老师获得某一门课程的签到率
 * 
 * @author anLA7856
 *
 */
public class GetCourseSignInfoCountServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AllowSignDaoImpl asdi = new AllowSignDaoImpl();
	private SignDaoImpl sdi = new SignDaoImpl();
	private StudentCourseDaoImpl scdi = new StudentCourseDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获得参数。
		String course_id = req.getParameter("course_id");

		if (course_id == null) {
			return;
		}

		int allSignCounts = sdi.getAllSignNameCountByCourseId(course_id);
		int allAllowSignCounts = asdi.getAllSignCountByCourseId(course_id);
		int allStudentsOfCourse = scdi.getAllStudentsByCourseId(course_id);

		float asc = allSignCounts;
		float aasc = allAllowSignCounts;
		float asof = allStudentsOfCourse;

		float result = 0;

		if (!(allAllowSignCounts == 0 || allStudentsOfCourse == 0)) {
			result = asc / (aasc * asof);
			result *= 100;
		}

		int myresult = (int) result;
		resp.setContentType("text/html;charset=utf-8");
		// resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();

		pw.write(myresult + "");
		pw.flush();
		pw.close();

	}

}
