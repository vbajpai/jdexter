<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Dexter | Friends</title>
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
                            <span class="sublinks multiwordlinks">My Friends</span>
                            <a class="block header multiwordlinks" href="/Dexter/actions/friendsactivity.do">Friend's Activity</a>
                            <a class="block header multiwordlinks" href="/Dexter/actions/friendsrequest.do">Friend's Requests</a>
                            <a class="block header multiwordlinks" href="/Dexter/actions/allfriends.do">All Friends</a>
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">
                    <div class="rightcolumn" id="rightcolumn">
                        <div class="contentheading">
                            <h3>Friends</h3>
                        </div>
                        <div class="friends dynamicdivs" id="friends"><br><br>

                            <blockquote>
                                <table summary="FriendsTable">
                                    <c:forEach var="friendbean" items="${friends}" varStatus="status" end="4">
                                        <c:choose>
                                            <c:when test="${status.count % 2!=0}">
                                                <tr>
                                                    <td>
                                                        <img src="/Dexter/_images/Vaibhav.jpg" alt="Vaibhav">
                                                    </td>
                                                    <td>
                                                        <c:if test="${userRecord.userName==friendbean.userNameFrom.userName}">
                                                                ${friendbean.userNameTo.userName}
                                                                
                                                        </c:if>
                                                        <c:if test="${userRecord.userName==friendbean.userNameTo.userName}">

                                                            ${friendbean.userNameFrom.userName}
                                                            
                                                        </c:if>
                                                    </td>
                                             </c:when>
                                             <c:otherwise>
                                                    <td align="right" width="150">
                                                        <img src="/Dexter/_images/Garima.jpg" alt="Garima">
                                                    </td>
                                                    <td>
                                                        <c:if test="${userRecord.userName==friendbean.userNameFrom.userName}">
                                                                ${friendbean.userNameTo.userName}
                                                                
                                                        </c:if>
                                                        <c:if test="${userRecord.userName==friendbean.userNameTo.userName}">

                                                            ${friendbean.userNameFrom.userName}
                                                            
                                                        </c:if>
                                                    </td>
                                                </tr>
                                              </c:otherwise>
                                       </c:choose>
                                        
                                    </c:forEach>
                                </table><br>
                                <a href="/Dexter/web/user/clear/allfriends.jsp">more...</a><br>
                                &nbsp;
                            </blockquote>
                        </div><br><br><br>
                        <div class="contentheading">
                            <h3>
                                Add Friends
                            </h3>
                        </div>
                        <div class="addfriends dynamicdivs" id="addfriends">
                            <html:form action="/actions/addfriend" styleClass="centerform"><br>
                                <h4 class="success">${FriendsFormBean.success}</h4>
                                <h4 class="error">${FriendsFormBean.error}</h4><br><br>
                                <ul class="nobullet nostyle">
                                    <li>
                                        <div class="fleft"><b>Enter Dexter ID<span class="error"></span></b></div>
                                        <div class="fright"><html:text property="username" styleClass="input"></html:text>
                                            <html:submit value="Add" styleClass="input"></html:submit>
                                        </div><br><br>
                                    </li>
                                </ul><br><br>
                                <div class="fleft">Click <a href="/Dexter/web/user/clear/addfriends.jsp">here</a> to search for Dexter Friends...</div>
                            </html:form>
                        </div>
                    </div>
                    <div class="leftcolumn" id="leftcolumn">
                        <div class="contentheading">
                            <h3>
                                Friend Requests
                            </h3>
                        </div>
                        <div class="friendrequest dynamicdivs" id="friendrequest"><br><br>
                            <ul class="nobullet">
                                <c:forEach end="2" var="friendRequestBean" items="${friendRequest}">
                                    <li>
                                        <table summary="FriendRequests">
                                            <tr>
                                                <td>
                                                    <img src="/Dexter/_images/Rahul.jpg" alt="Rahul">
                                                </td>
                                                <td>
                                                    <b>${friendRequestBean.userNameFrom.firstName} ${friendRequestBean.userNameFrom.lastName}</b> has sent you a friend request.
                                                </td>
                                            </tr>
                                        </table>
                                        <form action="/Dexter/actions/addfriend.do" method="post">
                                            Do you want to approve?
                                            <input type="hidden" name="id" value="${friendRequestBean.id}">
                                            <input type="hidden" name="page" value="myfriends">
                                            <input type="submit" class="input" value="yes" name="approve">
                                            <input type="submit" class="input" value="no" name="approve">
                                        </form>
                                    </li><br><br>
                                </c:forEach>
                            </ul>
                            <blockquote>
                                <a href="/Dexter/actions/friendsrequest.do">more...</a>
                            </blockquote><br>
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
