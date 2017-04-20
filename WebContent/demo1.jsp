<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GB18030">
    <title>File upload</title>
</head>
<body>
    <form name="myform" action="stuUploadPic" method="post"
       enctype="multipart/form-data">
       File1:<br>
       <input type="file" name="myfile"><br>
       File2:<br>
       <input type="file" name="myfile"><br>
       <br>
       <input type="submit" name="submit" value="Commit">
    </form>
</body>
</html>