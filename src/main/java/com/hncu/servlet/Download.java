package com.hncu.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Download extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从页面获取需要下载的文件名
		String fileName = request.getParameter("fileName");
		// 设置文件类型对应的MIME类型
		response.setContentType(getServletContext().getMimeType(fileName));
		//设置头信息，文件下载默认名：attachment;fileName
//		response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
		response.setHeader("Content-disposition","attachment; filename="+new String(fileName.getBytes("gb2312"),"iso8859-1"));
		// 获取输入流
		String path = getServletContext().getRealPath("/upload/" + fileName);
		InputStream is = new FileInputStream(path);
		OutputStream os = response.getOutputStream();
		int b;
		while ((b = is.read()) != -1) {
			os.write(b);
		}
		is.close();
		os.close();
	}
}
