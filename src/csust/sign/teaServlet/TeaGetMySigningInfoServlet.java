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
import csust.sign.bean.SignInfo;
import csust.sign.bean.Dao.Impl.TeacherDaoImpl;

public class TeaGetMySigningInfoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TeacherDaoImpl tdi = new TeacherDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		// resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		if(req.getParameter("student_id").equals("")){
			pw.write("[]");
			pw.flush();
			pw.close();
			return;
		}
		// 这里仅仅是传过来的参数名不是这样而已，事实上还是teacher_id
		String teacher_id = req.getParameter("student_id").toString();
		String startCount = req.getParameter("startCount").toString();

		// 写出到页面
		List<SignInfo> list = tdi.getTeacherSignInfoByTeacherID(teacher_id,
				startCount);
		

		System.out.println(list);
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();

	}
}
