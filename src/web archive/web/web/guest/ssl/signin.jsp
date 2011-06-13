<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>
          
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Dexter | SignIn</title>
		<link href="/Dexter/_stylesheets/global.css" rel="stylesheet" type="text/css">
		<link href="/Dexter/_stylesheets/power.css" rel="stylesheet" type="text/css">
		<link rel="icon" type="image/png" href="/Dexter/_images/FavIcon.png">
	</head>
	<body>
		<div class="container" id="container">
			<div class="header" id="header">
				<div class="toplinks" id="toplinks">
					<div class="block">
                        <a class="header toplinks" href="/Dexter/web/guest/ssl/index.jsp">Home</a>
                        <a class="header toplinks" href="/Dexter/web/guest/clear/about.jsp">About</a>
                    </div>
				</div>
			</div>
			<div class="content" id="content">
				<div class="main">
					<div class="logo" align="center">
						<img src="/Dexter/_images/Logo.png" alt="Dexter Logo">
					</div><br><br><br><br><br>
					<div class="rightcolumn" id="rightcolumn">
						<div class="contentheading">
							<h3>Lost Credentials?</h3>
						</div>
						<div class="dynamicdivs" id="shouts"><br><br>
							<blockquote>
								<form action="">
									<h3>Enter Your Email Address</h3><br>
									<input type="text" class="input"><br><br>
									<input type="submit" class="input" value="Retrieve Password"><br>
								</form>
							</blockquote>
						</div>
					</div>
					<div class="leftcolumn" id="leftcolumn">
						<div class="contentheading">
							<h3>SignIn</h3>
						</div>
						<div class="dynamicdivs"><br><br>                            
							<blockquote>
                                <h4 class="error">${sessionScope.LoginFormBean.error}</h4><br>

                                <!-- struts html form tag -->
                                <html:form action="/actions/login">
                                    <h3>Username:</h3><br><html:text styleClass="input" property="username"></html:text><br><br>
                                    <h3>Password:</h3><br><html:password styleClass="input" property="password"></html:password>
                                                          <html:hidden property="idexterpage" value="${param.forwardidexter}"></html:hidden>
                                                          <html:hidden property="searchstring" value="${param.forwardsearch}"></html:hidden>
                                                          <html:hidden property="searchpage" value="${param.forwardpage}"></html:hidden><br><br>
                                    <c:if test="${sessionScope.LoginFormBean.error=='Your Account is Disabled!'}">
                                        <html:checkbox styleClass="input" property="enable">Enable Account</html:checkbox><br><br>
                                    </c:if>
                                    <html:submit styleClass="input" value="Login"></html:submit>
                                </html:form><br>
                                    
								Don't have a Dexter account?<br><br>
								<a href="/Dexter/web/guest/ssl/signup.jsp">Create an account now</a>
							</blockquote>
						</div><br><br><br>
					</div>
				</div>
			</div>
			<div class="footer" id="footer">
				© Dexter 2008 - Final Year Project for Galgotias College of Engineering and Technology<br>
				Designed By - Vaibhav Bajpai, Rahul Burman, Nupur Dixit, Yadavendra
			</div>
		</div>
	</body>
</html>
