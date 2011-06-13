<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Dexter | Settings</title>
        <link href="/Dexter/_stylesheets/global.css" rel="stylesheet" type="text/css">
        <link href="/Dexter/_stylesheets/power.css" rel="stylesheet" type="text/css">
        <link href="/Dexter/_stylesheets/jquery/themes/base/ui.all.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript" src="/Dexter/_javascripts/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="/Dexter/_javascripts/ui/ui.core.js"></script>
        <script type="text/javascript" src="/Dexter/_javascripts/ui/ui.draggable.js"></script>
        <script type="text/javascript" src="/Dexter/_javascripts/ui/ui.resizable.js"></script>
        <script type="text/javascript" src="/Dexter/_javascripts/ui/ui.dialog.js"></script>
        <script type="text/javascript" src="/Dexter/_javascripts/modaldialog.js"></script>
        <link rel="icon" type="image/png" href="/Dexter/_images/FavIcon.png">
    </head>
    <body class="dialog">
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
                            <span class="multiwordlinks">My Settings</span>
                            <a class="block header multiwordlinks" href="/Dexter/web/user/ssl/editprofile.jsp">Edit Profile</a>
                            <c:if test="${sessionScope.role=='administrator'}">
                                <a class="block header multiwordlinks" href="/Dexter/web/admin/index.jsp">Admin Actions</a>
                                <a class="block header multiwordlinks" href="/Dexter/web/mod/index.jsp">Moderator Actions</a>
                            </c:if>
                            <c:if test="${sessionScope.role=='moderator'}">
                                <a class="block header multiwordlinks" href="/Dexter/web/mod/index.jsp">Moderator Actions</a>
                            </c:if>
                            <a id="disabledialoginvoker" class="block header multiwordlinks" href="/Dexter/actions/disableuser.do">Disable Account</a>
                            <a id="deletedialoginvoker" class="block header multiwordlinks" href="/Dexter/actions/deleteuser.do">Delete Account</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">
                    <div class="rightcolumn">
                        <div class="contentheading">
                            <h3>Privacy Settings</h3>
                        </div>
                        <div id="privacy" class="dynamicdivs">
                            <html:form action="actions/privacy" styleClass="centerdiv">
                                <h4 class="success">${PrivacyFormBean.success}</h4><br><br>
                                <table border="0" width="100%" summary="PrivacySettings">
                                    <thead class="tablehead">
                                        <tr height="30">
                                            <td><h4>Privacy</h4></td>
                                            <td><h4>Anyone</h4></td>
                                            <td><h4>Friends</h4></td>
                                            <td><h4>Only Me</h4></td>
                                        </tr>
                                    </thead>
                                    <tbody class="dynamicdivs">
                                        <tr align="center">
                                            <td>Age</td>
                                            <td><html:radio value="1" property="age"></html:radio></td>
                                            <td><html:radio value="2" property="age"></html:radio></td>
                                            <td><html:radio value="3" property="age"></html:radio></td>
                                        </tr>
                                        <tr align="center" class="designrow">
                                            <td>Email</td>
                                            <td><html:radio value="1" property="email"></html:radio></td>
                                            <td><html:radio value="2" property="email"></html:radio></td>
                                            <td><html:radio value="3" property="email"></html:radio></td>
                                        </tr>
                                        <tr align="center">
                                            <td>Gender</td>
                                            <td><html:radio value="1" property="gender"></html:radio></td>
                                            <td><html:radio value="2" property="gender"></html:radio></td>
                                            <td><html:radio value="3" property="gender"></html:radio></td>
                                        </tr>
                                        <tr align="center" class="designrow">
                                            <td>Location</td>
                                            <td><html:radio value="1" property="location"></html:radio></td>
                                            <td><html:radio value="2" property="location"></html:radio></td>
                                            <td><html:radio value="3" property="location"></html:radio></td>
                                        </tr>
                                        <tr align="center">
                                            <td>Name</td>
                                            <td><html:radio value="1" property="name"></html:radio></td>
                                            <td><html:radio value="2" property="name"></html:radio></td>
                                            <td><html:radio value="3" property="name"></html:radio></td>
                                        </tr>
                                        <tr align="center" class="designrow">
                                            <td>Profile Links</td>
                                            <td><html:radio value="1" property="profilelinks"></html:radio></td>
                                            <td><html:radio value="2" property="profilelinks"></html:radio></td>
                                            <td><html:radio value="3" property="profilelinks"></html:radio></td>
                                        </tr>
                                        <tr align="center">
                                            <td>Shouts</td>
                                            <td><html:radio value="1" property="shouts"></html:radio></td>
                                            <td><html:radio value="2" property="shouts"></html:radio></td>
                                            <td><html:radio value="3" property="shouts"></html:radio></td>
                                        </tr>                                        
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                            <td align="center">
                                                <html:submit value="Apply" styleClass="input"></html:submit>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </html:form>
                        </div>
                    </div>
                    <div class="leftcolumn">
                        <div class="contentheading">
                            <h3>Preferences</h3>
                        </div>
                        <div id="preferences" class="dynamicdivs">
                            <html:form action="actions/preferences" styleClass="centerform">
                                <h4 class="success">${PreferenceFormBean.success}</h4><br><br>

                                <ul class="nostyle nobullet">
                                    <li>
                                        <div class="fleft"><b>Comments Sort</b></div>
                                        <div class="fright">
                                            <html:select property="commentSort" styleClass="input">
                                                <html:option value="0">Oldest First</html:option>
                                                <html:option value="1">Newest First</html:option>
                                            </html:select>
                                        </div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Expanded Comments</b></div>
                                        <div class="fright">
                                            <html:select property="commentThreshold" styleClass="input">
                                                <html:option value="20">+20 or Higher</html:option>
                                                <html:option value="10">+10 or Higher</html:option>
                                                <html:option value="5">+5 or Higher</html:option>
                                                <html:option value="0">+0 or Higher</html:option>
                                            </html:select>
                                        </div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Comments per Page</b></div>
                                        <div class="fright">
                                            <html:select property="commentPageSize" styleClass="input">
                                                <html:option value="25">25</html:option>
                                                <html:option value="50">50</html:option>
                                                <html:option value="75">75</html:option>
                                                <html:option value="100">100</html:option>
                                            </html:select>
                                        </div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Comments Initially Collapsed</b></div>
                                        <div class="fright">
                                            <html:select property="commentReplyCollapsed" styleClass="input">
                                                <html:option value="1">True</html:option>
                                                <html:option value="0">False</html:option>
                                            </html:select>
                                        </div><br><br>
                                    </li>
                                    <li>
                                        <div class="fright">
                                            <html:submit value="Apply" styleClass="input"></html:submit>
                                        </div><br><br>
                                    </li>
                                </ul>
                            </html:form>
                        </div><br><br><br>
                        <div class="contentheading">
                            <h3>Change Password</h3>
                        </div>
                        <div class="dynamicdivs" id="changepassword">
                            <html:form action="actions/changepassword" styleClass="centerform">
                                <h4 class="success">${requestScope.PasswordFormBean.success}</h4>
                                <h4 class="error">${requestScope.PasswordFormBean.error}</h4><br><br>

                                <ul class="nostyle nobullet">
                                    <li>
                                        <div class="fleft"><b>Current Password</b></div>
                                        <div class="fright"><html:password property="currentpwd" styleClass="input"></html:password></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>New Password</b></div>
                                        <div class="fright"><html:password property="newpwd" styleClass="input"></html:password></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Confirm New Password</b></div>
                                        <div class="fright"><html:password property="confirmnewpwd" styleClass="input"></html:password></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fright">
                                            <html:submit value="Apply" styleClass="input"></html:submit>
                                        </div><br><br>
                                    </li>
                                </ul>
                            </html:form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer" id="footer">
                <div id="deletedialog" class="hidden" title="Delete Account?">
                    <p>Your account will be deleted permanently. Are you sure?</p>
                </div>
                <div id="disabledialog" class="hidden" title="Disable Account?">
                    <p>Your account will be disabled. Are you sure?</p>
                </div>
                © Dexter 2008 - Final Year Project for Galgotias College of Engineering and Technology<br>
                Designed By - Vaibhav Bajpai, Rahul Burman, Nupur Dixit, Yadavendra
            </div>
        </div>
    </body>
</html>
