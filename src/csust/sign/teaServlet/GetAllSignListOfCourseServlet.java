package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Score;
import csust.sign.bean.Dao.Impl.AllowSignDaoImpl;
import csust.sign.bean.Dao.Impl.StudentCourseDaoImpl;

public class GetAllSignListOfCourseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AllowSignDaoImpl asdi = new AllowSignDaoImpl();
	private StudentCourseDaoImpl scdi = new StudentCourseDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 得到相应的课程id
		String course_id = req.getParameter("course_id");
		if(course_id == null){
			return;
		}

		List<Score> list = asdi.getAllSignListByCourseId(course_id);
		int count = scdi.getAllStudentsByCourseId(course_id);
		// count the rate of a time of sign
		for (int i = 0; i < list.size(); i++) {
			int rate = 0;
			if (count != 0) {

				float myScore = list.get(i).getScore();
				float myCount = count;
				float myRate = (myScore / myCount)*100;
				rate = (int) myRate;

			}

			list.get(i).setScore(rate);
		}

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		System.out.println(list);
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();

	}

}
