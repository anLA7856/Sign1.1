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
import csust.sign.bean.Dao.Impl.StudentCourseDaoImpl;
import csust.sign.utils.ParameterUtil;

public class GetCourseStudentListServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StudentCourseDaoImpl scdi = new StudentCourseDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String courseId = req.getParameter("courseId");
		if(!ParameterUtil.parameterTest(courseId)){
			return;
		}
		
		List<StudentInfo> list = scdi.getAllStudentsListByCourseId(courseId);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();
	}
}
