package csust.sign.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import csust.sign.bean.AllowSignInfo;
import csust.sign.bean.SignReportInfo;
import csust.sign.bean.StudentReportInfo;
import csust.sign.bean.Dao.Impl.AllowSignDaoImpl;
import csust.sign.bean.Dao.Impl.SignDaoImpl;
import csust.sign.bean.Dao.Impl.StudentCourseDaoImpl;

/**
 * 用于生成报表的excel文件
 * 
 * @author anLA7856
 *
 */
public class HSSFWorkbookUtils {

	private String course_id;

	private String fileName;

	// 创建excel工作簿
	private HSSFWorkbook workBook = new HSSFWorkbook();
	// 建一张表
	private HSSFSheet sheet = workBook.createSheet("基本签到详情");

	// 头
	private List<AllowSignInfo> listHead;

	// 每一个学生，每一列
	private List<StudentReportInfo> listStudent;

	// 用于存储签到数据
	private List<SignReportInfo> listContent;

	// 定义一个数组，模拟表格
	private String[][] report;

	public HSSFWorkbookUtils(String course_id) {
		super();
		this.course_id = course_id;
		fileName = "" + System.currentTimeMillis() + ".xls";
		listHead = new AllowSignDaoImpl()
				.getCourseSignInfoByCourseId(course_id);
		listStudent = new StudentCourseDaoImpl()
				.getStudentListForReport(course_id);
		report = new String[listStudent.size()][listHead.size()];
		listContent = new SignDaoImpl().getSignReportInfos(course_id);
		initArray();
		createZeroRow();
		createOtherRow();
	}

	/**
	 * 用于生成除第一行外的其他行。
	 */
	private void createOtherRow() {
		for (int i = 0; i < listStudent.size(); i++) {
			// 制造其他行
			HSSFRow row = sheet.createRow(i + 1);
			// 制造一行中的其他列
			// 先加个学生信息
			HSSFCell cell = row.createCell(0);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(listStudent.get(i).getStudent_name() + "("
					+ listStudent.get(i).getStudent_username() + ")");
			for (int j = 0; j < listHead.size(); j++) {
				HSSFCell cellTemp = row.createCell(j + 1);
				cellTemp.setCellType(HSSFCell.CELL_TYPE_STRING);
				cellTemp.setCellValue(report[i][j]);
			}
		}
	}

	/**
	 * 此函数，专门用来插入在excel中的表头 也就是第一行
	 */
	public void createZeroRow() {
		/*
		 * 此处为打印总表的 写入excel中的语句。
		 */
		// 建立到第n行的行
		HSSFRow row = sheet.createRow(0);

		// 创建单元格

		if (listHead.size() == 0) {
			return;
		}
		HSSFCell cell = row.createCell(0);
		sheet.setColumnWidth(0, 6000);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cell.setCellValue("学生姓名\\签到时间");
		for (int i = 0; i < listHead.size(); i++) {
			sheet.setColumnWidth(i + 1, 6000);
			HSSFCell cellTemp = row.createCell(i + 1);
			cellTemp.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellTemp.setCellValue(listHead.get(i).getSign_time());
		}

	}

	/**
	 * 用于初始化数组。将有值的填进去。
	 */
	private void initArray() {
		// 便利大的二维数组
		for (int i = 0; i < listStudent.size(); i++) {
			for (int j = 0; j < listHead.size(); j++) {
				// 遍历小的一维数组
				report[i][j] = "缺课";
				for (int k = 0; k < listContent.size(); k++) {
					if (listContent.get(k).getAllow_sign_id() == listHead
							.get(j).getAllow_sign_id()
							&& listContent.get(k).getStudent_id() == listStudent
									.get(i).getStudent_id()) {
						report[i][j] = listContent.get(k).getSign_state();
					}
				}

			}
		}
	}

	/**
	 * 用于在本地服务器中生成文件。
	 * 
	 * @return
	 */
	public String createFile(HttpServletRequest req) {

		String filePath = req.getSession().getServletContext()
				.getRealPath("/xls");
		// File file = new File(filePath);
		java.io.File file = new java.io.File(filePath, fileName);
		try {
			// if(!file.exists()){
			// file.createNewFile();
			// }
			FileOutputStream fos = new FileOutputStream(file);
			workBook.write(fos);
			fos.flush();
			fos.close();

		} catch (IOException e1) {
			e1.printStackTrace();
			return null;
		}
		return fileName;
	}

}
