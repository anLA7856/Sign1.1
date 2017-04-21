package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.utils.HSSFWorkbookUtils;

/**
 * 用于让服务器生成excel报表的servlet，待生成好以后，即可下载。
 * 
 * @author U-ANLA
 *
 */
public class GetSignedReportServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		// 首先获得参数
		String course_id = req.getParameter("course_id");
		// 用于获得生成excel表格后的名字。
		HSSFWorkbookUtils myHssf = new HSSFWorkbookUtils(course_id);
		String fileName = myHssf.createFile(req);
		if (fileName == null) {
			pw.write("[0]");
		} else {
			pw.write(fileName);
		}

		pw.flush();
		pw.close();

	}
}
