<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>
            Admin | Moderator
        </title>
        <link href="/Dexter/_stylesheets/global.css" rel="stylesheet" type="text/css">
        <link href="/Dexter/_stylesheets/power.css" rel="stylesheet" type="text/css">
        <link href="/Dexter/_stylesheets/admin.css" rel="stylesheet" type="text/css">
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
                                <a class="header toplinks multiwordlinks" href="/Dexter/actions/myfriends.do">My Friends</a>
                                <a class="header toplinks" href="/Dexter/web/user/clear/submit.jsp">Submit</a>
                                <span class="toplinks">Settings</span>
                                <a class="header toplinks" href="/Dexter/web/user/clear/about.jsp">About</a>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="sublinks">
                    <div class="categories">
                        <div class="block">
                            <a class="block header multiwordlinks" href="/Dexter/web/user/ssl/settings.jsp">My Settings</a>
                            <a class="block header multiwordlinks" href="/Dexter/web/user/ssl/editprofile.jsp">Edit Profile</a>
                            <span class="multiwordlinks">Admin Actions</span>
                            <a class="block header multiwordlinks" href="/Dexter/web/mod/index.jsp">Moderator Actions</a>
                            <a id="disabledialoginvoker" class="block header multiwordlinks" href="#">Disable Account</a>
                            <a id="deletedialoginvoker" class="block header multiwordlinks" href="#">Delete Account</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">
                    <div class="rightpanel" id="rightpanel">
                        <div class="contentheading">
                            <h3>Dashboard</h3>
                        </div>
                        <div class="dynamicdivs" id="dashboard">
                            <ul class="side-menu" id="adminlinks">
                                <li>
                                    <a href="/Dexter/web/admin/index.jsp" >Admin Home</a>
                                </li>
                                <li>
                                    <a href="/Dexter/actions/admin/channel.do">Channel</a>
                                </li>
                                <li>
                                    <a href="/Dexter/actions/admin/category.do">Category</a>
                                </li>
                                <li>
                                    <a href="/Dexter/actions/admin/user.do">User</a>
                                </li>
                                <li>
                                    <a href="/Dexter/actions/admin/moderator.do" class="current">Moderator</a>
                                </li>
                                <li>
                                    <a href="/Dexter/actions/admin/im.do">Instant Messaging</a>
                                </li>
                                <li>
                                    <a href="/Dexter/actions/admin/visibilityvalidation.do">Visibility Control</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="leftpanel" id="leftpanel">
                        <div class="rightcolumn">
                            <div class="contentheading">
                                <h3>Remove Moderator</h3>
                            </div>
                            <div class="dynamicdivs">
                                <div class="centeradmin">
                                    <html:form action="/actions/admin/moderator">
                                        <div class="fleft">
                                            <h3 class="headinginline">Select Moderator:</h3>
                                        </div>
                                        <div class="fright">
                                            <html:select property="moderator" styleClass="input">
                                                <html:optionsCollection name="ModeratorBean" value="id" label="label"/>
                                            </html:select>
                                            <html:submit value="Remove" styleClass="input"></html:submit>
                                        </div>
                                        <div class="clear"></div>
                                    </html:form>
                                </div>
                            </div>
                        </div>
                        <div class="leftcolumn">
                            <div class="contentheading">
                                <h3>Add Moderator</h3>
                            </div>
                            <div class="dynamicdivs" id="addmoderator">
                                <div class="centeradmin">
                                    <html:form action="/actions/admin/moderator">
                                        <div class="fleft">
                                            <h3 class="headinginline">Select User:</h3>
                                        </div>
                                        <div class="fright">
                                            <html:select property="user" styleClass="input">
                                                <html:optionsCollection name="UserBean" value="id" label="label"/>
                                            </html:select>
                                             <html:submit value="Add" styleClass="input"></html:submit>
                                        </div>                                                                            
                                    </html:form>
                                </div>
                            </div>
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
