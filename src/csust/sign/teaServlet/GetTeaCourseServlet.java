package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Course;
import csust.sign.bean.Dao.Impl.CourseDaoImpl;

public class GetTeaCourseServlet extends HttpServlet {

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
		resp.setContentType("text/html;charset=utf-8");
		//resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		
		if(req.getParameter("username") == null){
			return;
		}
		
		if(req.getParameter("username").equals("")){
			pw.write("[]");
			pw.flush();
			pw.close();
			return;
		}
		
		//此处虽然仍然是username，但事实上穿过过来的是teacher_id
		String student_username = req.getParameter("username");
		String startCount = null;
		List<Course> list = null;
		if(req.getParameter("start") == null){
			//在spinear中显示的。
			list = cdi.getCoursesByTeacherNum(student_username, startCount,0,0+"");
		}else{
			//在fragment中显示。
			startCount = req.getParameter("start");
			String count = req.getParameter("count");
			list = cdi.getCoursesByTeacherNum(student_username, startCount,1,count);
		}
	
		
	
		
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();
	}
	
	
}
