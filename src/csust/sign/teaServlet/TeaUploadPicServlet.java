package csust.sign.teaServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 用于上传教师的头像来服务器，仅仅与学生的就一个文件夹名字不同。
 * @author U-anLA
 *
 */
public class TeaUploadPicServlet extends HttpServlet{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		req.setCharacterEncoding("UTF-8");
		String uploadPath = req.getSession().getServletContext().getRealPath(
				"/teaPic");
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		System.out.println();
		if (isMultipart == true) {
			try {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(req);// 得到所有的文件
				Iterator<FileItem> itr = items.iterator();
				while (itr.hasNext()) {// 依次处理每个文件
					FileItem item = (FileItem) itr.next();
					String fileName = item.getName();// 获得文件名，包括路径
					if (fileName != null) {
						File fullFile = new File(item.getName());
						File savedFile = new File(uploadPath,
								fullFile.getName());
						item.write(savedFile);
					}
				}
				out.print("upload succeed");
			} catch (Exception e) {
				//e.printStackTrace();
			}
		} else {
			out.println("the enctype must be multipart/form-data");
		}
	}
}
