package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csust.sign.bean.Dao.Impl.CourseDaoImpl;
import net.sf.json.JSONArray;

public class TeaDeleteCourse extends HttpServlet{

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
		String course_id = req.getParameter("course_id").toString().trim();
		
		if(course_id == null){
			return;
		}
		
		
		int result = cdi.teaDeleteCourse(course_id);
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		
		System.out.println(result);
		pw.write(JSONArray.fromObject(result).toString());
		pw.flush();
		pw.close();
		
	}
}
