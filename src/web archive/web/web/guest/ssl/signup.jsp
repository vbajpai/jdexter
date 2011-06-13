<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
	"http://www.w3.org/TR/html4/strict.dtd">

<%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>
          
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Dexter | Signup</title>
		<link href="/Dexter/_stylesheets/global.css" rel="stylesheet" type="text/css" />
		<link href="/Dexter/_stylesheets/power.css" rel="stylesheet" type="text/css" />
		<link rel="icon" type="image/png" href="/Dexter/_images/FavIcon.png" />        
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
						<img src="/Dexter/_images/Logo.png" alt="Dexter Logo" />
					</div><br><br><br><br><br><br>                        
					<div class="rightcolumn" id="rightcolumn">
						<div class="contentheading">
							<h3>Why Join Dexter?</h3>
						</div>
						<div class="dynamicdivs" id="whyjoindexter"><br><br>                        
						</div>
					</div>
					<div class="leftcolumn" id="leftcolumn">
						<div class="contentheading">
							<h3>Signup</h3>
						</div>
						<div class="dynamicdivs" id="signup">
                            <html:form enctype="multipart/form-data" method="POST" action="/actions/signup" styleClass="centerform">
                                <h4 class="error">${sessionScope.SignupFormBean.error}</h4><br><br>
                                <ul class="nobullet nostyle">
                                    <li>
                                        <div class="fleft"><b>Username<span class="error">*</span></b></div>
                                        <div class="fright"><html:text property="username" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Password<span class="error">*</span></b></div>
                                        <div class="fright"><html:password property="password" styleClass="input"></html:password></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Confirm Password<span class="error">*</span></b></div>
                                        <div class="fright"><html:password property="confirmpassword" styleClass="input"></html:password></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>First Name</b></div>
                                        <div class="fright"><html:text property="firstname" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Last Name</b></div>
                                        <div class="fright"><html:text property="lastname" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Profile Picture</b></div>
                                        <div class="fright"><html:file property="file"></html:file></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Email<span class="error">*</span></b></div>
                                        <div class="fright"><html:text property="email" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Country</b></div>
                                        <div class="fright">
                                            <html:select property="country" styleClass="input">
                                                 <html:optionsCollection name="CountryBean" value="id" label="label"/>
                                            </html:select>
                                        </div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>City</b></div>
                                        <div class="fright"><html:text property="city" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Gender</b></div>
                                        <div class="fright">
                                            <html:select property="gender" styleClass="input">
                                                <html:option value="male">Male</html:option>
                                                <html:option value="female">Female</html:option>
                                            </html:select>
                                        </div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Date of Birth* (DD-MM-YYYY)</b></div>
                                        <div class="fright"><html:text property="day" size="4" maxlength="2" styleClass="input"></html:text>
                                                            <html:text property="month" size="4" maxlength="2" styleClass="input"></html:text>
                                                            <html:text property="year" size="4" maxlength="4" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fright">
                                            <html:submit value="Signup" styleClass="input"></html:submit>
                                            <html:reset value="Clear" styleClass="input"></html:reset>
                                        </div><br><br>
                                    </li>
                                </ul>
                            </html:form>							
						</div><br><br><br>
					</div>
				</div>
			</div>
			<div class="footer" id="footer">
				© Dexter 2008 - Final Year Project for Galgotias College of Engineering and Technology<br />
				Designed By - Vaibhav Bajpai, Rahul Burman, Nupur Dixit, Yadavendra
			</div>
		</div>
	</body>
</html>
