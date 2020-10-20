<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	System.out.println("hello_sysout");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	function init(){
		var str = document.getElementById("mydiv").innerHTML;
	}
</script>
</head>
<body onload="init()">
	<div id = "mydiv"><a>ttt</a></div>
	<table>
		<tr>
			<td>박주영</td>
			<td>010-2222-3333</td>
		</tr>
	</table>
	<ul class = "rank_top1000_list"">
		<li>박주영</li>
		<li>손흥민</li>
		<li>차범근</li>
		<li>기성용</li>
		<li>안정환</li>
	</ul>
</body>
</html>