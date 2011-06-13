<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Dexter | My Profile</title>
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
                                <span class="toplinks multiwordlinks">My Profile</span>
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
                            <span class="multiwordlinks">My Profile</span>
                            <a class="block header" href="/Dexter/actions/displayfavorite.do">Favorites</a>
                            <a class="block header multiwordlinks" href="/Dexter/actions/recentactivity.do">Recent Activity</a>
                            <a class="block header" href="/Dexter/actions/shout.do">Shouts</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">
                    <div class="rightcolumn" id="rightcolumn">
                        <div class="contentheading">
                            <h3>About Me</h3>
                        </div>
                        <div class="dynamicdivs" id="about">
                            <div class="centerdiv">
                                <div class="fright">
                                    <img src="/Dexter/_images/ProfilePic.jpg" alt="ProfilePic">
                                </div>
                                <span>
                                    <h1 class="headinginline">${SignupFormBean.firstname}&nbsp;${SignupFormBean.lastname}</h1>
                                    <h4 class="headinginline">(${SignupFormBean.username})</h4><br><br>
                                    Joined Dexter on ${SignupFormBean.monthjoined}&nbsp;${SignupFormBean.dayjoined},&nbsp;${SignupFormBean.yearjoined}
                                </span><br><br><br><br>
                                <h4>Instant Message</h4><br>
                                <table summary="InstantMessageLinks">
                                    <tr>
                                        <td align="right" width="70">
                                            <img src="/Dexter/_images/buddyicons/GoogleTalk.png" alt="GoogleTalk">
                                        </td>
                                        <td>Google Talk</td>
                                        <td align="right" width="140">
                                            <img src="/Dexter/_images/buddyicons/YahooMessenger.jpg" alt="YahooMessenger">
                                        </td>
                                        <td>Yahoo</td>
                                    </tr>
                                    <tr>
                                        <td align="right" width="70">
                                            <img src="/Dexter/_images/buddyicons/MSNMessenger.png" alt="MSNMessenger">
                                        </td>
                                        <td>MSN</td>
                                        <td align="right" width="140">
                                            <img src="/Dexter/_images/buddyicons/AIM.png" alt="AIM">
                                        </td>
                                        <td>AIM</td>
                                    </tr>
                                </table><br><br>
                                <h4>Social Networking Profiles</h4><br>
                                <table summary="BuddyIcon">
                                    <tr>
                                        <td align="right" width="70">
                                            <img src="/Dexter/_images/buddyicons/Digg.png" alt="Digg">
                                        </td>
                                        <td>Digg</td>
                                        <td align="right" width="140">
                                            <img src="/Dexter/_images/buddyicons/Orkut.png" alt="Orkut">
                                        </td>
                                        <td>Orkut</td>
                                    </tr>
                                    <tr>
                                        <td align="right" width="70">
                                            <img src="/Dexter/_images/buddyicons/Facebook.png" alt="Facebook">
                                        </td>
                                        <td>Facebook</td>
                                        <td align="right" width="140">
                                            <img src="/Dexter/_images/buddyicons/Twitter.png" alt="Twitter">
                                        </td>
                                        <td>Twitter</td>
                                    </tr>
                                    <tr>
                                        <td align="right" width="70">
                                            <img src="/Dexter/_images/buddyicons/Lastfm.jpg" alt="Lastfm">
                                        </td>
                                        <td>Last.fm</td>
                                        <td align="right" width="140">
                                            <img src="/Dexter/_images/buddyicons/LinkedIn.png" alt="LinkedIn">
                                        </td>
                                        <td>LinkedIn</td>
                                    </tr>
                                    <tr>
                                        <td align="right" width="70">
                                            <img src="/Dexter/_images/buddyicons/YouTube.png" alt="YouTube">
                                        </td>
                                        <td>YouTube</td>
                                        <td align="right" width="140">
                                            <img src="/Dexter/_images/buddyicons/Stumbleupon.png" alt="StumbleUpon">
                                        </td>
                                        <td>StumbleUpon</td>
                                    </tr>
                                </table>
                            </div>
                        </div><br><br><br>
                        <div class="contentheading">
                            <h3>
                                Shouts
                            </h3>
                        </div>
                        <div class="shouts dynamicdivs" id="shouts">
                            <div class="centersmall"><br><br>
                                <ul class="demarcate nobullet">
                                    <c:forEach end="2" var="shoutBean" items="${shouts}">
                                        <li>
                                            <div class="fright">
                                                <a href="">
                                                <div class="fleft"><img height="26" weight="26" src="/Dexter/_images/Vaibhav.jpg"></div></a>
                                                <div class="fright padleftsmall">
                                                    <h5>sent by - <br></h5>
                                                    <div class="padleftsmall">
                                                        <!-- Changed by Rahul on 11 march -->
                                                        <h4>${shoutBean.userFrom.userName}</h4>
                                                        <h6 class="error">
                                                            <c:choose>
                                                                <c:when test='${shoutBean.shoutTimeString == "0"}'>Today...</c:when>
                                                                <c:otherwise>${shoutBean.shoutTimeString} Days ago...</c:otherwise>
                                                            </c:choose>
                                                        </h6>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="fleft">
                                                <b>"${shoutBean.shout}"</b>
                                            </div><br>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <div class="fright"><a href="/Dexter/actions/shout.do"><input type="submit" class="input" value="more..."></a></div>
                                    </li>
                                </ul>
                            </div>
                        </div><br><br><br>
                    </div>
                    <div class="leftcolumn" id="leftcolumn">
                        <div class="contentheading">
                            <h3>
                                Favorites
                            </h3>
                        </div>
                        <div class="dynamicdivs" id="favorite">
                            <div class="center"><br><br>
                                <ul class="nobullet demarcate">
                                    <c:forEach var="resultbean" items="${FavoriteBean}" end="2">
                                        <li>
                                            <div class="fleft">
                                                <b><a href="${resultbean.unescapedUrl}">${resultbean.title}</a></b><br><br>
                                                <div class="padleftsmall">
                                                    Favorited with tags - <h4 class="headinginline">${resultbean.tags}</h4>
                                                    <h6 class="error headinginline">
                                                        <c:choose>
                                                            <c:when test='${resultbean.submissiondate == "0"}'>Today...</c:when>
                                                            <c:otherwise>${resultbean.submissiondate} Days ago...</c:otherwise>
                                                        </c:choose>
                                                    </h6>
                                                </div>
                                            </div><br><br><br>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <div class="fright"><a href="/Dexter/actions/displayfavorite.do"><input type="submit" class="input" value="more..."></a></div>
                                    </li>
                                </ul>
                            </div>
                        </div><br><br><br>
                        <div class="contentheading">
                            <h3>
                                My Recent Activity
                            </h3>
                        </div>
                        <div class="recentactivity dynamicdivs" id="recentactivity">
                            <div class="center"><br><br>
                                <ul class="nobullet demarcate">
                                    <c:forEach var="resultbean" items="${RecentActivityBean}" end="2">
                                        <li>
                                            <div class="fleft">
                                                <b><a href="${resultbean.unescapedUrl}">${resultbean.title}</a></b><br><br>
                                                <div class="padleftsmall">
                                                    Voted in - <h4 class="headinginline">${resultbean.category}</h4>
                                                    <h6 class="error headinginline">
                                                        <c:choose>
                                                            <c:when test='${resultbean.submissiondate == "0"}'>Today...</c:when>
                                                            <c:otherwise>${resultbean.submissiondate} Days ago...</c:otherwise>
                                                        </c:choose>
                                                    </h6>
                                                </div>
                                            </div><br><br><br>
                                        </li>
                                    </c:forEach>
                                    <li>
                                        <div class="fright"><a href="/Dexter/actions/recentactivity.do"><input type="submit" class="input" value="more..."></a></div>
                                    </li>
                                </ul>
                            </div>
                        </div><br><br>
                    </div>
                </div>
            </div>
            <div class="footer" id="footer">
                Dexter 2008 - Final Year Project for Galgotias College of Engineering and Technology<br>
                Designed By - Vaibhav Bajpai, Rahul Burman, Nupur Dixit, Yadavendra
            </div>
        </div>
    </body>
</html>
