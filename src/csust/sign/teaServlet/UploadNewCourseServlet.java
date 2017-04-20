package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Dao.Impl.CourseDaoImpl;

public class UploadNewCourseServlet extends HttpServlet{

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
		String teacher_id = req.getParameter("teacher_id");
		String course_name = req.getParameter("course_name");
		String description = req.getParameter("description");
		String name = new String(course_name.getBytes("ISO-8859-1"), "utf-8");
		String des = new String(description.getBytes("ISO-8859-1"), "utf-8"); 
		
		
		int result = cdi.addCourse(teacher_id, name, des);
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		
		System.out.println(result);
		pw.write(JSONArray.fromObject(result).toString());
		pw.flush();
		pw.close();
		
		
	}
}
