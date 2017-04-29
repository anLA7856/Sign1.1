package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import csust.sign.bean.ChatMessage;
import csust.sign.bean.Dao.Impl.ChatMessageDaoImpl;
import csust.sign.utils.ParameterUtil;

public class StuGetAllChatMessageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ChatMessageDaoImpl cmdi = new ChatMessageDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//获得相应参数。
		String studentId = req.getParameter("studentId");
		
		if(!ParameterUtil.parameterTest(studentId)){
			return;
		}

		List<ChatMessage> list = cmdi.getAllMessages(Integer.parseInt(studentId));
		resp.setContentType("text/html;charset=utf-8");
		//resp.setCharacterEncoding("utf-8");
		PrintWriter pw = resp.getWriter();
	
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();
	}
}
