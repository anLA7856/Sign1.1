package csust.sign.teaServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.StudentSignRate;
import csust.sign.bean.Dao.Impl.AllowSignDaoImpl;
import csust.sign.bean.Dao.Impl.SignDaoImpl;
/**
 * 用于获得某一门课的列表，以课程为单位，展示出每一位学生的签到几率
 * @author U-anLA
 *
 */
public class GetStudentListCourseRateServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SignDaoImpl sdi = new SignDaoImpl();
	private AllowSignDaoImpl asdi = new AllowSignDaoImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获得course_id
		String course_id = req.getParameter("course_id");
		String start = req.getParameter("start");
		String count = req.getParameter("count");
		
		if(course_id == null||start == null || count == null){
			return;
		}
		
		
		//获得所有学生签到列表
		List<StudentSignRate> list = new ArrayList<StudentSignRate>();
		list = sdi.getAllStudentSignRate(course_id,start,count);
		//获得本门课程所有签到数目。
		int allSignCount = asdi.getAllSignCountByCourseId(course_id);
		//给list添加一些必要信息。
		for(int i = 0;i < list.size();i++){
			list.get(i).setAllSignCount(allSignCount);
			float myAllSignCount = allSignCount;
			float myBase = list.get(i).getHave_sign();
			float myResult = (myBase/myAllSignCount)*100;
			list.get(i).setRate((int)myResult);
		}
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();

		System.out.println(list);
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();

		
	}
	
	
}
