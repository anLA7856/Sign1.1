package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.Dao.Impl.SignDaoImpl;

public class UploadSignInfoServlet extends HttpServlet{

	private SignDaoImpl sdi = new SignDaoImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String student_id = req.getParameter("student_id");
		String course_id = req.getParameter("course_id");
		//用于处理上传信息的处理逻辑。
		//分别获取年月和时间
		String date = req.getParameter("date");
		String time = req.getParameter("time");
		
		String allow_id = req.getParameter("allow_id");
		
		if(student_id == null || course_id == null || allow_id == null){
			return;
		}
		
		int result = sdi.addStuentSignInfo(date+" "+time, "已签到", student_id, course_id,allow_id);
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
	
		System.out.println(result);
		pw.write(JSONArray.fromObject(result).toString());
		pw.flush();
		pw.close();
		
	}
}



















