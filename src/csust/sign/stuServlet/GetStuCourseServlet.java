package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Course;
import csust.sign.bean.CourseInfo;
import csust.sign.bean.Dao.Impl.CourseDaoImpl;

public class GetStuCourseServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CourseDaoImpl cdi = new CourseDaoImpl();

	@Override
	public void init(ServletConfig config) throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		//resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		if(req.getParameter("username").equals("")){
			pw.write("[]");
			pw.flush();
			pw.close();
			return;
		}
		
		
		String student_username = req.getParameter("username");
		String startCount = req.getParameter("startCount");
		String count = req.getParameter("count");
		
		if(student_username == null || startCount == null || count == null){
			return;
		}
		
		
		
		
		List<CourseInfo> list = cdi.getCoursesByStudentNum(student_username, startCount,count);
		
	
		System.out.println(list);
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();
	}
}
