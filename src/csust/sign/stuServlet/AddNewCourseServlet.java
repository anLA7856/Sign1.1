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
 * 用于学生端添加新课程
 * @author U-anLA
 *
 */
public class AddNewCourseServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private StudentCourseDaoImpl scdi = new StudentCourseDaoImpl();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获得相应参数
		String course_id = req.getParameter("course_id");
		String student_id = req.getParameter("student_id");
		
		if(course_id == null || student_id == null){
			return;
		}
		
		
		
		int result = scdi.studentAddCourse(course_id, student_id);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		
		System.out.println(result);
		pw.write(JSONArray.fromObject(result).toString());
		pw.flush();
		pw.close();
	}
}
