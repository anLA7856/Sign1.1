package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import csust.sign.bean.Teacher;
import csust.sign.bean.Dao.Impl.TeacherDaoImpl;

/**
 * 教师端登录
 * 
 * @author anLA7856
 *
 */
public class TeaLoginServlet extends HttpServlet {

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
		// 后期必须采用加密策略
		String wifiMac = null;
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
			wifiMac = jsonObject1.get("wifiMac").toString();
			List<Teacher> list = tdi.getPasswordByTeacherNum(username);

			if (list.size() == 0) {
				// 用户不存在
				pw.print("nouser");
			} else {
				Teacher teacher = list.get(0);
				if (password.equals(teacher.getTeacher_password())) {
					// 用户存在且密码正确
					// 更新wifimac
					tdi.updateWifiMacAddress(username, wifiMac);
					teacher.setTeacher_wifimac(wifiMac);
					pw.print(JSONArray.fromObject(teacher).toString());
					// 更新当前教师的wifimac地址

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
