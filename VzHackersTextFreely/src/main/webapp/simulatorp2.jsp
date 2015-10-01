<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.vzw.vzhackers.textfreely.core.TextFreelyCore"
	import="com.vzw.vzhackers.textfreely.dto.ProcessTextResponse"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>VzHackers Text Freely Application</title>
</head>
<body bgcolor="#BDBDBD">
	<!--#E6E6FA-->

	<%-- <jsp:useBean id="core" class="com.vzw.vzhackers.textfreely.core.TextFreelyCore" scope="request"> --%>
	<!-- core.process() -->
	<%-- </jsp:useBean> --%>
	<%
		String text1 = request.getParameter("text");
		String mtn = request.getParameter("cust_mtn_num");
		TextFreelyCore core = new TextFreelyCore();
		ProcessTextResponse resp = core.process(text1, mtn);
	%>
	<center>
		<h1><font color= "#DF0101">Welcome to VzHackers Text Freely Application</font></h1>
	
	<form method="get" action="">
		<table>
			<tr>
				<td>MTN</td>
				<td><input type="text" name="cust_mtn_num" value=<%=mtn %>><br></td>
			</tr>
			<tr>
				<td>SMS</td>
				<td><textarea rows="2" cols="50" name="text" "overflow:hidden;"><%=text1 %></textarea></td>
			</tr>
			<tr>
			<td></td>
				<td><input type="submit" value="SEND"></td>
			</tr>
			<tr>
				<td>RESPONSE SMS</td>
				<td><textarea rows="3" cols="50"><%=resp.getResponseMessage()%></textarea></td>
			</tr>
		</table>
	</form>
	</center>

<!-- 	<form method="get" action=""> -->
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<td>Your response was generated as below:</td> -->
<%-- 				<td><textarea rows="5" cols="30"><%=resp.getResponseMessage()%> --%>
<!-- 					</textarea></td> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 	</form>	 -->


</body>
</html>