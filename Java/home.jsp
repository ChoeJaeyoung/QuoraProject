<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Quora Insincere Questions Classification</title>
<style>
	.container{
		position:relative;
	}
	.center{
		position:absolute;
		left:450px;
		top:530px;
		text-align:center;
		font-size:18px;
	}
	#back{
		width:1500;
		height:auto;
	}
	input[type=text]{
		width:600px;
		height:35px;
		
	background-color: #A6250F;
    background-image: url('resources/img/search.png');
    background-position: 10px 10px; 
    background-repeat: no-repeat;
    padding-left: 40px;
    border: 2px solid white;
    color:white;
	}
	input[type=submit]{
	background-color :#C97714;
	color:white;
	border:none;
	padding:5px 5px;
	border-radius:4px;
	cursor:pointer;
	float:right;
	margin:auto;
	width:100px;
	height:35px;
	}

</style>
<title>Home</title>
</head>
<body>
<div class="container">
<img id="back"src="resources/img/quora_main.jpg"/>
<div class="center">
	<form class="box" action="result" method="POST">
		<input type="text" name="question" value="Write your Question!">
		<input class="row" type="submit" value="HELP">
	</form>
</div>
	
</div>
</body>
</html>