package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import csust.sign.bean.StudentInfo;
import csust.sign.bean.Dao.Impl.StudentDaoImpl;

/**
 * 学生端登录
 * 
 * @author anLA7856
 *
 */
public class StuLoginServlet extends HttpServlet {
	StudentDaoImpl sdi = new StudentDaoImpl();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 后期必须采用加密策略
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		String value = req.getParameter("value");
		if (value == null) {
			return;
		}
		// String value2 = req.getParameter("username");
		// String value3 = req.getParameter("password");
		// Enumeration value1 = req.getParameterNames();
		if (!value.equalsIgnoreCase("")) {

			JSONObject jsonObject1 = JSONObject.fromObject(value);
			String username = jsonObject1.get("uname").toString().trim();
			String password = jsonObject1.get("upassword").toString().trim();

			List<StudentInfo> list = sdi.getStuPassWordByStuUsername(username);

			if (list.size() == 0) {
				// 用户不存在
				pw.print("nouser");
			} else {
				StudentInfo student = list.get(0);
				if (password.equals(student.getStudent_password())) {
					// 用户存在切密码正确
					pw.print(JSONArray.fromObject(student).toString());
				} else {
					// 用户存在但密码不正确
					pw.print("nopass");
				}
			}

			pw.flush();
			pw.close();

		}
	}

}
