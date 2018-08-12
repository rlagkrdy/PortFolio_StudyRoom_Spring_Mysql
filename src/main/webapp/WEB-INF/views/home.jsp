<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="kr.co.hakyo.utils.Constants"%>

<!doctype html>
<html lang="ko">

<head>
<meta charset="utf-8">
<title>Yo Study</title>
<base href="/">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/x-icon" href="favicon.ico">
<!-- <script src="assets/js/ckeditor/ckeditor.js" defer></script> -->
<script type="text/javascript">
	var CD01 =
<%=Constants.CD01%>
	;
	var CD02 =
<%=Constants.CD02%>
	;
	var CD03 =
<%=Constants.CD03%>
	;
	console.log(CD01);
	console.log(CD02);
	console.log(CD03);
</script>
</head>

	
<body>
	<%@include  file="../../resources/index.html" %>
	<!-- <app-root>
	<div class="spinner">
		<div class="bounce1"></div>
		<div class="bounce2"></div>
		<div class="bounce3"></div>
	</div>
	</app-root>
	<script type="text/javascript" src="runtime.4a85d8d970858c4c4146.js"></script>
	<script type="text/javascript" src="polyfills.c91c047ac3a1842c3b8b.js"></script>
	<script type="text/javascript" src="main.0d9041554c2b016f3487.js"></script> -->

	<!-- Global site tag (gtag.js) - Google Analytics -->
	<script async
		src="https://www.googletagmanager.com/gtag/js?id=UA-123719466-1"></script>
	<script>
		window.dataLayer = window.dataLayer || [];
		function gtag() {
			dataLayer.push(arguments);
		}
		gtag('js', new Date());

		gtag('config', 'UA-123719466-1');
	</script>

</body>

</html>