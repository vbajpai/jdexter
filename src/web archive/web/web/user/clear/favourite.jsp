package web.user.clear;

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Dexter | All Favourites</title>
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
                                <span class="multiwordlinks toplinks">My Profile</span>
                                <a class="header toplinks multiwordlinks" href="/Dexter/actions/myfriends.do">My Friends</a>
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
                            <a class="block header multiwordlinks" href="/Dexter/actions/myprofile.do">My Profile</a>
                            <span class="multiwordlinks">Favorites</span>
                            <a class="block header multiwordlinks" href="/Dexter/actions/recentactivity.do">Recent Activity</a>
                            <a class="block header" href="/Dexter/actions/shout.do">Shouts</a>
                        </div>						
                    </div>
				</div>
			</div>
			<div class="content" id="content">
				<div class="main">
					<div class="contentheading">
						<h3>
							All Favorites
						</h3>
					</div>
					<div class="dynamicdivs" id="allfavorites">
						<br>
						<br>
                        <!-- Commented for the moment
						<div align="center">
							<form action="">
								Filter Favourites by Tag <input type="text" class="input"><input type="submit" class="input" value="Search">
							</form>
						</div>
                        -->
                        <div class="center">
                            <ul class="nobullet demarcate">
                                <c:forEach var="resultbean" items="${FavoriteBean}">
                                    <li>
                                        <div class="fright">
                                            <blockquote>
                                                <a href="">
                                                <div class="fright"><img height="44" weight="44" src="/Dexter/_images/Rahul.jpg">
                                                    </div></a>
                                                <div class="fleft padleftalignright">
                                                    <h5>Favorited  - <br></h5>
                                                    <div class="padleftalignright">

                                                        <h6 class="error">
                                                            <c:choose>
                                                                <c:when test='${resultbean.submissiondate == "0"}'>Today...</c:when>
                                                                <c:otherwise>${resultbean.submissiondate} Days ago...</c:otherwise>
                                                            </c:choose>
                                                            <br>in <br>
                                                        </h6>
                                                        <h4>
                                                            ${resultbean.category}
                                                        </h4>
                                                    </div>
                                                </div>
                                            </blockquote>
                                        </div>
                                        <div class="fleft">
                                            <div>

                                                <span class="padleft urltitle">
                                                    <b><a href="${resultbean.unescapedUrl}">
                                                            ${resultbean.title}
                                                        </a>
                                                    </b>
                                                </span></<br><br>
                                            </div>
                                            <div class="fleft padleft">
                                                <h3>${resultbean.voteUp - resultbean.voteDown}</h3>
                                            </div>
                                            <div class="fright nostyle">
                                                <blockquote class="notop">
                                                    <blockquote class="notop fw urlcontent">
                                                        <b>${resultbean.visibleUrl} - </b>
                                                        ${resultbean.content}

                                                    </blockquote>
                                                </blockquote>
                                            </div>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                </c:forEach>
                                <li>

                                    <div class="clear"></div>
                                </li>
                            </ul>
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
