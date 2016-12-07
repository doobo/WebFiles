<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件下载</title>
</head>
<body>
	<table align="center">
		<%
			File file = new File(request.getServletContext().getRealPath("/upload"));
			if (!file.exists()) {
				out.print(request.getServletContext().getRealPath("/upload"));
			} else {
				File[] list = file.listFiles();
				if (list.length == 0) {
					out.print("<tr><td colspan='2'>暂无文件可下载</td></tr>");
				} else {
					for (int i = 0; i < list.length; i++) {
						out.print("<tr><td>" + list[i].getName() + "</td><td><a href='Download?fileName="
								+ list[i].getName() + "'>下载</a></td>" + "</tr>");
					}
				}
			}
		%>
		<tr>
			<td colspan="2"><a href="upload.jsp">去上传文件</a></td>
		</tr>
	</table>
</body>
</html>