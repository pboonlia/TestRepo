<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Account Information Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!--link rel="stylesheet/less" href="less/bootstrap.less" type="text/css" /-->
<!--link rel="stylesheet/less" href="less/responsive.less" type="text/css" /-->
<!--script src="js/less-1.3.3.min.js"></script-->


<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
  <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="img/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="img/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="img/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="img/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="img/favicon.png">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/scripts.js"></script>
<style type="text/css">
.navbar-default .navbar-brand {
color: #428bca
}
.navbar-default {
background-color: #F8F8BB;
border-color: #e7e7e7;
}
</style>
</head>

<body>
	<%
		String errorStr = (String) request.getAttribute("error");
		boolean error = false;
		if(errorStr.equals("true")){
			error = true;
		}
		String alertMessage = (String) request.getAttribute("alertMessage");
		//String uuid = (String) request.getAttribute("uuid");
		//String genXMLStart = (String) request.getAttribute("genXMLStart");
		//String genXMLEnd = (String) request.getAttribute("genXMLEnd");
		//String httpPostStart = (String) request.getAttribute("httpPostStart");
		//String httpPostEnd = (String) request.getAttribute("httpPostEnd");
	%>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default navbar-fixed-top"
					role="navigation">
				<div class="navbar-header">
					<a class="navbar-brand" href="index.jsp">Bill Notify XML Generator</a>
				</div>
				</nav>
				<hr>
				<hr>
				<hr>
				<% if(error){ %>
				<div class="alert alert-danger alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">x—</button>
					<h4>Alert!</h4>
					<strong>Error!</strong> <%=alertMessage %>
				</div>
				<% } %>
				<% if(!error && !alertMessage.isEmpty()){ %>
				<div class="alert alert-success alert-dismissable">
					<button type="button" class="close" data-dismiss="alert"
						aria-hidden="true">x—</button>
					<h4>Alert!</h4>
					<strong>Success!</strong> <%=alertMessage %>
				</div>
				<% } %>
			</div>
		</div>
		<div>
		<table class="table table-condensed">
					<thead>
						<tr>
							<th>XML Generation Start Time</th>
							<th>XML Generation End Time</th>
							<th>HTTP Post Start Time</th>
							<th>HTTP Post End Time</th>
							<th>UUID</th>
						</tr>
					</thead>
					<tbody>

						<tr class="danger">
							<td><%=(String) request.getAttribute("genXMLStart")%></td>
							<td><%=(String) request.getAttribute("genXMLEnd")%></td>
							<td><%=(String) request.getAttribute("httpPostStart")%>
							</td>
							<td><%=(String) request.getAttribute("httpPostEnd")%></td>
							<td><%=(String) request.getAttribute("uuid")%></td>
						</tr>

					</tbody>
				</table>
		 </div>
	</div>
</body>
</html>
