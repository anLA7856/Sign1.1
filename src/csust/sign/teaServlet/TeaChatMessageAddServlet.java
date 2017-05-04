package csust.sign.teaServlet;

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
 * 这个类重复了，开始没设计好。
 * @author U-ANLA
 *
 */
public class TeaChatMessageAddServlet extends HttpServlet{

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
		
		if(!ParameterUtil.parameterTest(studentId,teacherId,message)){
			return;
		}
		ChatMessage cm = new ChatMessage();
		cm.setSenderId(Integer.parseInt(teacherId.toString()));
		cm.setReceiveId(Integer.parseInt(studentId.toString()));
		cm.setMessage(message.toString());
		//保存。
		
		int validate = cmdi.addNewMessage(cm);
		pw.print(validate);
		pw.flush();
		pw.close();

	}
}
