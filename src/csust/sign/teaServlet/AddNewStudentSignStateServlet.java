package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Student;
import csust.sign.bean.Dao.Impl.SignDaoImpl;
import csust.sign.bean.Dao.Impl.StudentDaoImpl;

/**
 * 用于添加学生签到信息
 * @author U-ANLA
 *
 */
public class AddNewStudentSignStateServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String student_username = req.getParameter("student_username");
		String my_sign_state = req.getParameter("sign_state");
		
		String course_id = req.getParameter("course_id");
		String allow_sign_id = req.getParameter("allow_sign_id");
		
		if(student_username == null || my_sign_state == null || course_id == null || allow_sign_id == null){
			return;
		}
		
		String sign_state = new String(my_sign_state.getBytes("ISO-8859-1"), "UTF-8");

		if(student_username == null || sign_state == null || course_id == null ||allow_sign_id == null){
			return;
		}
		
		
		//获得student_id
		String student_id = new StudentDaoImpl().getStudentIdByStudentUsername(student_username);
		//插入数据
		int result = new SignDaoImpl().addNewSignInfo(sign_state, course_id, student_id, allow_sign_id);
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.write(JSONArray.fromObject(result).toString());
		pw.flush();
		pw.close();
		
		
		
		
	}
}
