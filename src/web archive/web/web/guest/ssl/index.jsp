<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>

<html>
	<head>        
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Dexter | Home</title>
		<link href="/Dexter/_stylesheets/global.css" rel="stylesheet" type="text/css">
		<link href="/Dexter/_stylesheets/power.css" rel="stylesheet" type="text/css">
		<link href="/Dexter/_images/FavIcon.png" rel="icon" type="image/png">
		<script type="text/javascript" src="/Dexter/_javascripts/jquery-1.3.2.js"></script>
		<script type="text/javascript">
                $(document).ready(function() {
				$('#Login').hide();
				$("#clickMe").click(function() {    
					$("#Login").toggle();
				});
			});
		</script>
	</head>
	<body>
		<div class="container" id="container">
			<div class="header" id="header">
				<div class="jtoplinks toplinks">
					<div class="topleft"  id="Login">
                        <!-- struts html form tag -->
                        <html:form action="/actions/login">
                            Username:<html:text styleClass="input" size="10" property="username"></html:text>
                            Password:<html:password styleClass="input" size="10" property="password"></html:password>
                            <html:hidden property="idexterpage" value="${param.forwardidexter}"></html:hidden>
                            <html:hidden property="searchstring" value="${param.forwardsearch}"></html:hidden>
                            <html:submit styleClass="input" value="Login"></html:submit>
                        </html:form>
					</div>
					<div class="topright">
						<a class="header toplinks" id="clickMe" href="#" name="clickMe">SignIn</a>
                        <a class="header toplinks" href="/Dexter/web/guest/ssl/signup.jsp">Signup</a>
                        <a class="header toplinks" href="/Dexter/actions/idexter.do?page=1">iDexter</a>
                        <a class="header toplinks" href="/Dexter/web/guest/clear/about.jsp">About</a>
					</div>
					<div class="clear"></div>
				</div>
			</div>
			<div class="content" id="content" align="center">
				<div align="center" class="logo">
					<img src="/Dexter/_images/Logo.png" alt="Dexter Logo">
				</div><br><br><br>
                <form action="/Dexter/actions/search.do">
					<input type="text" size="70" class="input" name="search"><br>
					<input type="submit" class="input" value="Search"><br>
                    <input type="hidden" name="page" value="1">
				</form>
			</div>
			<div class="footer" id="footer">
				© Dexter 2008 - Final Year Project for Galgotias College of Engineering and Technology<br>
				Designed By - Vaibhav Bajpai, Rahul Burman, Nupur Dixit, Yadavendra
			</div>
		</div>
	</body>
</html>
