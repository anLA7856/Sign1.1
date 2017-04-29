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

public class StuGetNewChatMessageServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ChatMessageDaoImpl cmdi = new ChatMessageDaoImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String studentId = req.getParameter("studentId");
		if(!ParameterUtil.parameterTest(studentId)){
			return;
		}
		List<ChatMessage> list = cmdi.getNotReadMessages(Integer.parseInt(studentId.toString()));
		
		//将所有的已读的，都修改为已读。而不能一概而论，吧not_read为1的都改为了，可能有并发，然后新消息来了，这次轮询没查到。
		int[] ids = new int[list.size()];
		for(int i = 0;i < list.size();i++){
			ids[i] = list.get(i).getId();
		}
		if(ids.length != 0){
			cmdi.modifyMessageState(ids);
		}
		
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		//System.out.println("访问了一次");
		pw.write(JSONArray.fromObject(list).toString());
		pw.flush();
		pw.close();
	}
}
