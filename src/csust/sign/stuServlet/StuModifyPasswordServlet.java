package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Dao.Impl.StudentDaoImpl;

public class StuModifyPasswordServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String student_id = req.getParameter("student_id");
		String myNew = req.getParameter("new");
		
		if(student_id == null || myNew == null){
			return;
		}
		
		
		
		int result = new StudentDaoImpl().modifyStudentPassword(student_id, myNew);
		
		resp.setContentType("text/html;charset=utf-8");
		//resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		pw.write(JSONArray.fromObject(new Integer(result)).toString());
		pw.flush();
		pw.close();
		
	}
}
