package web.guest.clear;

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Dexter | URL Activity</title>
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
                        <a class="header toplinks" href="/Dexter/web/guest/ssl/signin.jsp">Signin</a>
                        <a class="header toplinks" href="/Dexter/web/guest/ssl/signup.jsp">Signup</a>
                        <a class="header toplinks" href="/Dexter/web/guest/clear/about.jsp">About</a>
                    </div>
				</div>
				<div class="sublinks">
					<div class="categories">
						<div class="block">							
							<a class="block header" href="/Dexter/web/guest/clear/comments.jsp?urlid=${param.urlid}">Comments</a>
							<span class="multiwordlinks">More...</span>						
						</div>
					</div>
				</div>
			</div>
			<div class="content" id="content">
				<div class="main">
					<div class="rightcolumn" id="rightcolumn">
						<div class="contentheading">
							<h3>
								Who else Voted?
							</h3>
						</div>
						<div class="dynamicdivs" id="whoelsevoted"><br><br>
							<blockquote>
								<table summary="FriendsTable">
									<tr>
										<td>
											<img src="/Dexter/_images/Vaibhav.jpg" alt="Vaibhav">
										</td>
										<td>
											Vaibhav Bajpai
										</td>
										<td align="right" width="150">
											<img src="/Dexter/_images/Garima.jpg" alt="Garima">
										</td>
										<td>
											Garima Bajpai
										</td>
									</tr>
									<tr>
										<td>
											<img src="/Dexter/_images/Amit.jpg" alt="Amit">
										</td>
										<td>
											Amit Mishra
										</td>
										<td align="right" width="150">
											<img src="/Dexter/_images/Gaurav.jpg" alt="Gaurav">
										</td>
										<td>
											Gaurav Bajpai
										</td>
									</tr>
								</table><br>								
							</blockquote>							
						</div><br>
						<br>
						<br>
						<div class="contentheading">
							<h3>
								Report URL
							</h3>
						</div>
						<div class="shouts dynamicdivs" id="report">
							<blockquote>
								<br>
								Report the URL to the Moderator
								<form id="form2" name="form1" method="post" action="">
									<div align="left">
										<br><textarea class="tasize" name="reason" id="reason"></textarea><br><br>
										<label><input type="radio" name="radio" id="profanity" value="profanity"> Profanity</label><br><br>
										<label><input type="radio" name="radio" id="category" value="category"> Incorrect Category</label><br><br>
										<input type="submit" class="input" name="send" id="send" value="Send">
									</div>
								</form>								
							</blockquote>
						</div><br>
						<br>
						<br>
					</div>
					<div class="leftcolumn" id="leftcolumn">
						<div class="contentheading">
							<h3>
								Shout
							</h3>
						</div>
						<div class="favourite dynamicdivs" id="favourite">
							<br><br>
							<blockquote>
								<form action="">
									<b>Dexter ID:&nbsp;&nbsp;</b><input type="text" class="input" name="did" value=""><br><br>
									<b>Your Message:</b><br><br><textarea class="tasize"></textarea><br><br>
									<input type="submit" class="input" value="Send"><br><br>
								</form>
							</blockquote>
						</div><br>
						<br>
						<br>
						<div class="contentheading">
							<h3>
								Email
							</h3>
						</div>
						<div class="recentactivity dynamicdivs" id="email">
							<br><br>
							<blockquote>
								<form action="">
									<b>Email Address:</b>&nbsp;&nbsp;<input type="text" class="input" name="did" value=""><br><br>
									<b>Your Message:</b><br><br><textarea class="tasize"></textarea><br><br>
									<input type="submit" class="input" value="Send"><br><br>
								</form>
							</blockquote>
						</div>
					</div>
				</div><br><br><br>
			</div>
			<div class="footer" id="footer">
				© Dexter 2008 - Final Year Project for Galgotias College of Engineering and Technology<br>
				Designed By - Vaibhav Bajpai, Rahul Burman, Nupur Dixit, Yadavendra
			</div>
		</div>
	</body>
</html>
