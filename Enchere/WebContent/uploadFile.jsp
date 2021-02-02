<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1><a href="/Enchere/ListeEncheresConnecteServlet">ENI-ENCHERE</a></h1>
<h2 style="color:red;">${message}</h2>
<h2>  Upload Photo</h2>
 <form action="UploadFileServlet" method="post" enctype="multipart/form-data" >
					<label>Photo de l'article : </label>
					<input type="hidden" name="description">
					<input type="file"  name="photo" size="60" /> <br />
					<input type="submit" value="Upload"   />
				</form>

</body>
</html>