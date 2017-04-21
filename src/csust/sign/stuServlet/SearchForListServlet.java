package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.SearchCourseInfo;
import csust.sign.bean.Dao.Impl.CourseDaoImpl;

/**
 * 用于在学生端获取搜索的搜索课程结果。
 * 
 * @author U-anLA
 *
 */
public class SearchForListServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CourseDaoImpl cdi = new CourseDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String courseNum = null, teacherNum = null,student_id=null;
		
		student_id = req.getParameter("student_id");
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		
		if(student_id == null || start == null || count == null){
			return;
		}
		
		List<SearchCourseInfo> list = new ArrayList<SearchCourseInfo>();

		// 获得相应参数。
		if (req.getParameter("teacherNum") == null
				&& req.getParameter("courseNum") != null) {
			// 通过courseNum来进行模糊搜索
			courseNum = req.getParameter("courseNum");
			list = cdi.getSearchCourseInfoByCourseId(courseNum,student_id,start,count);
		} else if (req.getParameter("teacherNum") != null
				&& req.getParameter("courseNum") == null) {
			// 通过teacherNum来进行精确搜索
			teacherNum = req.getParameter("teacherNum");
			list = cdi.getSearchCourseInfoByTeacherId(teacherNum,student_id,start,count);
		}

		resp.setContentType("text/html;charset=utf-8");
		// resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();

		System.out.println(list);
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();

	}
}
