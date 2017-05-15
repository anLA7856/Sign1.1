package csust.sign.stuServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import csust.sign.bean.ChatMessage;
import csust.sign.bean.Dao.Impl.ChatMessageDaoImpl;
import csust.sign.utils.ParameterUtil;

/**
 * 增加学生信息
 * 
 * @author anLA7856
 *
 */
public class StuChatMessageAddServlet extends HttpServlet {

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
		// 后期必须采用加密策略
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		String studentId = req.getParameter("studentId");
		String teacherId = req.getParameter("teacherId");
		String message = req.getParameter("message");

		if (!ParameterUtil.parameterTest(studentId, teacherId, message)) {
			return;
		}
		ChatMessage cm = new ChatMessage();
		cm.setSenderId(Integer.parseInt(studentId.toString()));
		cm.setReceiveId(Integer.parseInt(teacherId.toString()));
		cm.setMessage(message.toString());
		// 保存。

		int validate = cmdi.addNewMessage(cm);
		pw.print(validate);
		pw.flush();
		pw.close();

	}
}
