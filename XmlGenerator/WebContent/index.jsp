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
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<nav class="navbar navbar-default navbar-fixed-top"
					role="navigation">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Bill Notify XML Generator</a>
				</div>
				</nav>
				<hr>
				<hr>
				<hr>
			</div>
		</div>
		<div class="row clearfix">
			<div class="col-md-8 column">
				<form class="form-horizontal" role="form"
					action="BnrServiceServlet" method="post">
					<div class="form-group">
						<label for="inputXmlTemplatePath" class="col-sm-3 control-label">XML Template Path</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputXmlTemplatePath"
								name="inputXmlTemplatePath" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputDestPath" class="col-sm-3 control-label">XML Generation Path</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputDestPath"
								name="inputDestPath" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputCommUrl" class="col-sm-3 control-label">Communication URL</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="inputCommUrl"
								name="inputCommUrl" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputXmlCount" class="col-sm-3 control-label">Total XML to generate</label>
						<div class="col-sm-2">
							<input type="number" class="form-control" id="inputXmlCount"
								name="inputXmlCount" />
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">Generate and Post XML!!</button>
						</div>
					</div>
				</form>
			</div>
			
		</div>
	</div>
</body>
</html>
