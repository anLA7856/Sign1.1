package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.SignInfo;
import csust.sign.bean.Dao.Impl.SignDaoImpl;

public class GetNotSignInfoServlet extends HttpServlet{

	private SignDaoImpl sdi = new SignDaoImpl();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getParameter("student_id") == null){
			return;
		}
		resp.setContentType("text/html;charset=utf-8");
		//resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
		
		
		if(req.getParameter("student_id").equals("")){
			pw.write("[]");
			pw.flush();
			pw.close();
			return;
		}
		
		
		String student_id = req.getParameter("student_id");
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		
		if(student_id == null || start == null || count == null){
			return;
		}
		
		//String startCount = req.getParameter("startCount");
		List<SignInfo> list = sdi.getNotSignInfoByStuId(student_id,start,count);
	
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();
	}
	
}
