<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传页面</title>
<style type="text/css">
#center {
	height: 50%;
	width: 60%;
	position: relative;
	left: 20%;
	top: 80px;
}
</style>
</head>
<body>
	<div id="center">
		<div align="center">
			<h1>文件上传</h1>
		</div>
		<div align="center">
			<form action="Upload" method="post" enctype="multipart/form-data">
				<input type="file" id="fileUpload" name="fileUpload"> <input
					type="submit" value="上传">
			</form>
		<a href="download.jsp">去下传文件</a>
		</div>
	</div>
</body>
</html>