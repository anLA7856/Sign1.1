package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.TeacherListInfo;
import csust.sign.bean.Dao.Impl.TeacherDaoImpl;

public class GetTeacherListServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获得相应参数
		String student_id = req.getParameter("student_id");
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		
		if(student_id == null || start == null || count == null){
			return;
		}
		
		
		List<TeacherListInfo> list = new TeacherDaoImpl().getTeaListByStudentId(student_id, start, count);

	
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();
	}
}
