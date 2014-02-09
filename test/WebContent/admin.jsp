<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckfinder"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<script type="text/javascript" src="/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
    var editor = null;
    window.onload = function() {
        CKFinder.setupCKEditor( editor, '/ckfinder/' );
    };
</script>
<html>
	<body>
		<form action="index.jsp" method="get"  id="myform">
			<p>
				<label for="editor1">Editor 1:</label>
				<textarea cols="80" id="editor" name="editor" rows="10"></textarea>
			</p>
			<p>
				<input type="submit" value="保存" />
			</p>
		</form>
	 <ckfinder:setupCKEditor basePath="ckfinder" editor="editor" />
	<ckeditor:replace replace="editor" basePath="ckeditor" />
	</body>
</html>