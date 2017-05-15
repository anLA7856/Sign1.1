package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import csust.sign.bean.StudentInfo;
import csust.sign.bean.Dao.Impl.StudentDaoImpl;

/**
 * 增加学生
 * 
 * @author anLA7856
 *
 */
public class StuAddServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StudentDaoImpl sdi = new StudentDaoImpl();

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

		if (!value.equalsIgnoreCase("")) {

			JSONObject jsonObject1 = JSONObject.fromObject(value);
			String username = jsonObject1.get("username").toString().trim();
			String password = jsonObject1.get("password").toString().trim();
			String name1 = jsonObject1.get("name").toString().trim();
			String name = new String(name1.getBytes("ISO-8859-1"), "UTF-8");
			String sex1 = jsonObject1.get("sex").toString().trim();
			String sex = new String(sex1.getBytes("ISO-8859-1"), "UTF-8");
			String age = jsonObject1.get("age").toString().trim();
			String stuNum = jsonObject1.get("stuNum").toString().trim();
			StudentInfo student = new StudentInfo(0, name, sex, stuNum,
					username, password);
			int validate = sdi.validataUsername(username);
			if (validate == 1) {
				// 表示该用户名已经被注册了，不能注册了
				pw.print("no");
			} else {
				// 可以注册
				int result = sdi.addStudent(student);
				if (result == 1) {
					// 注册成功！
					pw.print("ok");
				} else {
					// 不明原因注册失败
					pw.print("fail");
				}
			}

			pw.flush();
			pw.close();

		}
	}
}
