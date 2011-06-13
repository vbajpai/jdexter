package web.user.clear;

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>
			Dexter | Add Friends
		</title>
		<link href="/Dexter/_stylesheets/global.css" rel="stylesheet" type="text/css">
		<link href="/Dexter/_stylesheets/power.css" rel="stylesheet" type="text/css">
		<link rel="icon" type="image/png" href="/Dexter/_images/FavIcon.png">
	</head>
	<body>
		<div class="container" id="container">
			<div class="header" id="header">
                <div class="toplinks" id="toplinks">
                    <div class="topleft">
                        <a href="/Dexter/web/user/clear/classic.jsp">
                            <img src="/Dexter/_images/home.png" width="20" height="20" alt="Home">
                        </a>
                    </div>
                    <table class="toptable" summary="this table contains links">
                        <tr>
                            <td>
                                <img src="/Dexter/_images/ProfilePic.jpg" alt="ProfilePic" width="16" height="16">
                            </td>
                            <td>
                                <a class="header toplinks" href="/Dexter/actions/logout.do">Signout</a>
                                <a class="header toplinks" href="/Dexter/actions/idexter.do?page=1">iDexter</a>
                                <a class="header toplinks multiwordlinks" href="/Dexter/actions/myprofile.do">My Profile</a>
                                <span class="toplinks multiwordlinks">My Friends</span>
                                <a class="header toplinks" href="/Dexter/web/user/clear/submit.jsp">Submit</a>
                                <a class="header toplinks" href="/Dexter/web/user/ssl/settings.jsp">Settings</a>
                                <a class="header toplinks" href="/Dexter/web/user/clear/about.jsp">About</a>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="sublinks">
                    <div class="categories">
                        <div class="block">
                            <a class="block header multiwordlinks" href="/Dexter/actions/myfriends.do">My Friends</a>
                            <a class="block header multiwordlinks" href="/Dexter/web/user/clear/friendsactivity.jsp">Friend's Activity</a>
                            <a class="block header multiwordlinks" href="/Dexter/actions/friendsrequest.do">Friend's Request</a>
                            <a class="block header multiwordlinks" href="/Dexter/actions/allfriends.do">All Friends</a>
                            <span class="multiwordlinks">Add Friends</span>
                        </div>
                    </div>
                </div>
            </div>
			<div class="content" id="content">
				<div class="main">
					<div class="contentheading">
						<h3>
							Add Friends
						</h3>
					</div>
					<div class="dynamicdivs" id="allfriends">
						<br>
						<br>
						<div align="center">
							<form action="">
								<b>Search Friends in Dexter:</b> <input type="text" class="input"> <input type="submit" value="Search" class="input">
							</form>
						</div>
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
