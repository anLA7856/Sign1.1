package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csust.sign.bean.Dao.Impl.AllowSignDaoImpl;
import csust.sign.bean.Dao.Impl.SignDaoImpl;

/**
 * 用于学生端来获得某一门课的总签到率
 * 
 * @author anLA7856
 *
 */
public class GetCourseTotalSignRateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SignDaoImpl sdi = new SignDaoImpl();
	private AllowSignDaoImpl asdi = new AllowSignDaoImpl();

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

		if (student_id == null || course_id == null) {
			return;
		}

		int totalCount = asdi.getAllSignCountByCourseId(course_id);
		int signCount = sdi.getCountOfSignNameByStudentIdCourseId(student_id,
				course_id);

		float fTotalCount = totalCount, fSignCount = signCount;

		float result = (fSignCount / fTotalCount) * 100;
		int myResult = (int) result;

		// 打印出去。
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		pw.write(myResult + "," + totalCount + "," + signCount);
		pw.flush();
		pw.close();

	}
}
