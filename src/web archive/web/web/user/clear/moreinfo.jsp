package web.user.clear;

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
 <%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>

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
					<table class="toptable" summary="this table contains links">
						<tr>
							<td>
								<img src="/Dexter/_images/ProfilePic.jpg" alt="ProfilePic" width="16" height="16">
							</td>
							<td>
								<a class="header toplinks" href="/Dexter/actions/logout.do">Signout</a>
                                <a class="header toplinks" href="/Dexter/web/user/clear/classic.jsp">Classic</a>
                                <a class="header toplinks multiwordlinks" href="/Dexter/actions/myprofile.do">My Profile</a>
                                <a class="multiwordlinks header toplinks" href="/Dexter/actions/myfriends.do">My Friends</a>
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
							<a class="block header" href="/Dexter/actions/checkurl.do?urlid=${param.urlid}">Comments</a>
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
								Who Voted it up?
							</h3>
						</div>
						<div class="dynamicdivs" id="whoelsevoted"><br><br>
							<blockquote>
								<table summary="FriendsTable">
                                    <c:forEach var="votedupbean" items="${whovoteditup}" varStatus="status" end="4">                                        
                                        <c:choose>
                                            <c:when test="${status.count % 2!=0}">
                                                <tr>
                                                    <td>
                                                        <img src="/Dexter/_images/Vaibhav.jpg" alt="Vaibhav">
                                                    </td>
                                                    <td>
                                                        ${votedupbean.user.userName}
                                                    </td>
                                             </c:when>
                                             <c:otherwise>
                                                    <td align="right" width="150">
                                                        <img src="/Dexter/_images/Garima.jpg" alt="Garima">
                                                    </td>
                                                    <td>
                                                        ${votedupbean.user.userName}
                                                    </td>
                                                </tr>
                                              </c:otherwise>
                                       </c:choose>

                                    </c:forEach>
                                </table>
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
									<div align="left">
                                        
										<html:form action="/actions/moreinfo?urlid=${param.urlid}">
                                            <span class="center">
                                                <h4 class="success">${MoreInfoFormBean.success}</h4>
                                                <h4 class="error">${MoreInfoFormBean.error}</h4><br>
                                            </span>
                                            <b>Report:</b><br>
                                            <html:textarea  styleClass="input" rows="6" cols="70" property="report"></html:textarea><br><br>
                                            <html:submit styleClass="input" value="Report"></html:submit>
                                            <html:reset styleClass="input" value="Clear"></html:reset><br><br>
                                        </html:form>
                                    </div>
								
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
							
							<blockquote>
								<html:form action="/actions/moreinfo.do?urlid=${param.urlid}">
                                    <span class="center">
                                        <h4 class="success">${MoreInfoFormBean.shoutSuccess}</h4>
                                        <h4 class="error">${MoreInfoFormBean.shoutError}</h4><br>
                                    </span>
                                    <b>Friend:</b><br>
                                    <html:text styleClass="input" property="userName"></html:text><br><br>
                                    <b>Message:</b><br>
                                    <html:textarea rows="6" cols="50" styleClass="input" property="message"></html:textarea><br><br>
                                    <html:submit styleClass="input" value="Send"></html:submit>
                                    <html:reset styleClass="input" value="Clear"></html:reset><br><br>
                                </html:form>
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
