package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import csust.sign.bean.StudentInfo;
import csust.sign.bean.Dao.Impl.TeacherDaoImpl;
/**
 * 用于教师端的用户名密码注册
 * @author U-anLA
 *
 */
public class TeaAddServlet extends HttpServlet{

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
		//获得相应的参数。

		
		
		// 后期必须采用加密策略
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		String value = req.getParameter("value");

		if(value == null){
			return;
		}
		
		
		if (!value.equalsIgnoreCase("")) {

			JSONObject jsonObject1 = JSONObject.fromObject(value);
			String myName = jsonObject1.get("name").toString().trim();
			String name = new String(myName.getBytes("ISO-8859-1"), "utf-8");
			String password = jsonObject1.get("password").toString().trim();
			String username = jsonObject1.get("username").toString().trim();
			String wifiMac = jsonObject1.get("wifiMac").toString().trim();
			

			int validate = tdi.validateNewUserInfo(username);
			if (validate == 1) {
				// 表示该用户名已经被注册了，不能注册了
				pw.print("no");
			} else {
				// 可以注册
				int result = tdi.addTeacherUserInfo(name, username, password, wifiMac);
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
