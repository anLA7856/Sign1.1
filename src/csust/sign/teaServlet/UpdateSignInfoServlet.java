package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Dao.Impl.AllowSignDaoImpl;

public class UpdateSignInfoServlet extends HttpServlet{

	private AllowSignDaoImpl asdi = new AllowSignDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//注：传过来date值，
		String courseId = req.getParameter("courseId");
		String signDate = req.getParameter("date");
		String signTime = req.getParameter("date2");
		
		int result = asdi.addAllowSignInfo(courseId, signDate+" "+signTime);
		resp.setContentType("text/html;charset=utf-8");
		//resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		pw.write(JSONArray.fromObject(new Integer(result)).toString());
		pw.flush();
		pw.close();
		
	}
}
