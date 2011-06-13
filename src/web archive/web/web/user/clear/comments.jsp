package web.user.clear;

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>
           <%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>

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
							<span class="multiwordlinks">Comments</span>
                            <a class="block header" href="/Dexter/actions/moreinfo.do?urlid=${param.urlid}">More...</a>
						</div>
					</div>
				</div>
			</div>
			<div class="content" id="content">
				<div class="main">
					<div class="rightcolumn" id="rightcolumn">
						<div class="contentheading">
							<h3>My Comment</h3>
						</div>
						<div class="dynamicdivs" id="mycomment">
							
                            <blockquote>

                                <html:form action="/actions/checkurl?urlid=${commentlist.urlRecord.id}">
                                    <span class="center">
                                        <h4 class="success">${CommentFormBean.success}</h4>
                                        <h4 class="error">${CommentFormBean.error}</h4><br>
                                    </span>
                                    <b>Comment:</b><br>
                                    <html:textarea  styleClass="input" rows="6" cols="70" property="comment"></html:textarea><br><br>
                                    <html:submit styleClass="input" value="Send"></html:submit>
                                    <html:reset styleClass="input" value="Clear"></html:reset><br><br>
                                </html:form>
                            </blockquote>
						</div><br><br><br>
					</div>
					<div class="leftcolumn" id="leftcolumn">
						<div class="contentheading">
							<h3>URL</h3>
						</div>
						<div class="dynamicdivs" id="url">
							<div class="center">
                            <ul class="nobullet">
                                <li>
                                    <div class="fright">
                                            <a href="">
                                            <div class="fright ">
                                                <h5>submitted by - <br></h5>
                                                <div>
                                                    <h4>${commentlist.urlRecord.user.userName}</h4>
                                                    
                                                </div>
                                            </div>
                                        
                                    </div>
                                    <div class="fleft">
                                        <div>
                                            
                                            <span class="padleftsmall urltitle">
                                                <b><a href="${commentlist.urlRecord.usescapedURL}">
                                                        ${commentlist.urlDetail.title}
                                                    </a>
                                                </b>
                                            </span>
                                        </div>
                                        <div class="fleft padleftsmall">
                                            <h3>${commentlist.urlRecord.voteUp - commentlist.urlRecord.voteDown}</h3>
                                        </div>
                                        <div class="fright nostyle">
                                                <div class="notop fw urlcontent">
                                                    <b>${commentlist.urlDetail.visibleURL} - </b>
                                                    ${commentlist.urlDetail.content}<br><br>
                                                    <span class="header">
                                                        <span class="multiwordlinks error">${commentlist.urlRecord.category.categoryName}</span> |
                                                        <a href=""><img height="18" weight="18" src="/Dexter/_images/favorite.png" alt="Favorite!"></a>
                                                    </span>
                                                </div>
                                            
                                        </div>
                                    </div>
                                    
                                </li>
                            
                            </ul><br>
                        </div>
                    
						</div><br>
					</div>
                    <div class="clear"></div>
					
					<div class="contentheading idexter ichannel fleft">
						<div class="channels" id="channels">
							<div class="block">
								<span class="block sublinks header multiwordlinks">Expand |</span>
                                <a class="block header" href="amazon">All</a>
                                <a class="block header" href="amazon">Me</a>
                                <a class="block header multiwordlinks" href="amazon">My Friends</a>
							</div>
						</div>
					</div>
					<div class="contentheading idexter itimeframe fright">
						<div class="timeframe sublinks" id="timeframe">
							<div class="block">
								<a class="blueblock block header" href="/Dexter/web/user/ssl/settings.jsp">Settings</a>
							</div>
						</div>
					</div>
					<div class="clear"></div>
					<div class="dynamicdivs" id="comments"><br><br>
						<blockquote>
							<ul class="nobullet demarcate">
                                <c:forEach var="commentbean" items="${commentlist.commentList}">

                                    <li>
                                        <div class="fleft" id="submitter">
                                                <a href="">
                                                <div class="fleft"><img height="32" weight="32" src="/Dexter/_images/Vaibhav.jpg"></div></a>
                                                <div class="fright"><h4>&nbsp;&nbsp;&nbsp;${commentbean.user.userName}</h4>&nbsp;&nbsp;&nbsp;&nbsp;on ${commentbean.submissionDate}</div>
                                        </div>
                                        <div class="fleft fwcomment">
                                                    ${commentbean.comment}
                                                    
                                        </div>
                                        <div class="fright">
                                            <div>
                                                <b>${commentbean.voteUp-commentbean.voteDown}</b>
                                                <a href=""><img src="/Dexter/_images/greyup.png" alt="UP" width="29" height="27"></a>
                                                <a href=""><img src="/Dexter/_images/greydown.png" alt="DOWN" width="29" height="27"></a>
                                            </div>
                                        </div>
                                        <div class="clear"></div><br><br><br>
                                    </li>
                                </c:forEach>
								
							</ul>
						</blockquote>
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
