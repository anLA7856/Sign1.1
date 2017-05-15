package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csust.sign.bean.Dao.Impl.SignDaoImpl;
import csust.sign.bean.Dao.Impl.StudentCourseDaoImpl;

/**
 * 得到总签到次数
 * 
 * @author anLA7856
 *
 */
public class GetSignCountServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SignDaoImpl sdi = new SignDaoImpl();
	private StudentCourseDaoImpl scdi = new StudentCourseDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获得allow_sign_id
		String allow_sign_id = req.getParameter("allow_sign_id");

		if (allow_sign_id == null) {
			return;
		}
		// 已经签到的数目
		int haveSign = sdi.getRealCountInOneSign(allow_sign_id);
		// 本门课应该签到的总人数
		int allCount = scdi.getOneCourseCount(allow_sign_id);

		String str = haveSign + "," + allCount;
		resp.setContentType("text/html;charset=utf-8");
		// resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();

		pw.write(str);
		pw.flush();
		pw.close();
	}
}
