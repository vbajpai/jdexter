<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>
            Dexter | Friend Requests
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
                            <a class="block header multiwordlinks" href="/Dexter/actions/friendsactivity.do">Friend's Activity</a>
                            <span class="multiwordlinks">Friend's Request</span>
                            <a class="block header multiwordlinks" href="/Dexter/web/user/clear/allfriends.jsp">All Friends</a>
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">
                    <div class="contentheading">
                        <h3>
                            Friend's Requests
                        </h3>
                    </div>
                    <div class="dynamicdivs" id="friendsrequest">
                        <div class="center">
                            <div class="fright">
                                <ul class="nobullet">
                                    <c:forEach begin="1" step="2" var="friendRequestBean" items="${friendRequest}" >
                                        <li>
                                            <table summary="FriendRequests">
                                                <tr>
                                                    <td>
                                                        <img src="/Dexter/_images/Rahul.jpg" alt="Rahul">
                                                    </td>
                                                    <td>
                                                        <b>${friendRequestBean.userNameFrom.firstName} ${friendRequestBean.userNameFrom.lastName}</b> has sent you a friend request.
                                                    </td>
                                                    <td></td>
                                                </tr>
                                            </table>
                                            <form action="/Dexter/actions/addfriend.do">
                                                Do you want to approve? <input type="submit" class="input" value="yes" name="approve"><input class="input" type="submit" value="no" name="approve">
                                            </form>
                                        </li><br><br>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="fleft">
                                <ul class="nobullet">
                                    <c:forEach begin="0" step="2" var="friendRequestBean" items="${friendRequest}">
                                        <li>
                                            <table summary="Linus">
                                                <tr>
                                                    <td>
                                                        <img src="/Dexter/_images/Linus.jpg" alt="Linus">
                                                    </td>
                                                    <td>
                                                        <b>${friendRequestBean.userNameFrom.firstName} ${friendRequestBean.userNameFrom.lastName}</b> has sent you a friend request.
                                                    </td>
                                                </tr>
                                            </table>
                                            <form action="/Dexter/actions/addfriend.do">
                                                Do you want to approve?
                                                <input type="hidden" name="id" value="${friendRequestBean.id}">
                                                <input type="hidden" name="page" value="friendsrequest">
                                                <input type="submit" class="input" value="yes" name="approve">
                                                <input class="input" type="submit" value="no" name="approve">
                                            </form>
                                        </li><br><br>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="clear"></div>
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
