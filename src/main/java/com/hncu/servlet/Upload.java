package com.hncu.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class Upload extends HttpServlet {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String savePath = this.getServletContext().getRealPath("/upload"); // 获取保存文件的绝对路径
		File file = new File(savePath);
		// 该路径是否有效，无效则创建该文件夹
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
		// 消息提示
		String message = "";
		try {
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 3.解决上传中文乱码问题
			upload.setHeaderEncoding("utf-8");
			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			FileItemIterator list = upload.getItemIterator(request);
			while (list.hasNext()) {
				FileItemStream item = list.next();
				String tempname = item.getName() ;
				if(tempname.indexOf("\\") != -1)  
				{  
					String fse = File.separator;
				     tempname = tempname.substring(tempname.lastIndexOf(fse)+1);
				}  
				String path = savePath + File.separator + tempname;// 文件绝对路径
				InputStream in = item.openStream();
				FileOutputStream out = new FileOutputStream(path);//创建文件输出流
				byte buffer[] = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}

				in.close();
				out.close();
				message = "上传文件成功";
			}
		} catch (Exception e) {
			message = "上传文件失败";
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("uploadMessage.jsp").forward(request, response);
	}

}
