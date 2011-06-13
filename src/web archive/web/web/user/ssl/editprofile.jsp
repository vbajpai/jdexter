<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@taglib uri="http://struts.apache.org/tags-html"
          prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Dexter | Edit Profile</title>
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
                            <a class="block header multiwordlinks" href="/Dexter/web/user/ssl/settings.jsp">My Settings</a>
                            <span class="multiwordlinks">Edit Profile</span>
                            <c:if test="${sessionScope.role=='administrator'}">
                                <a class="block header multiwordlinks" href="/Dexter/web/admin/index.jsp">Admin Actions</a>
                                <a class="block header multiwordlinks" href="/Dexter/web/mod/index.jsp">Moderator Actions</a>
                            </c:if>
                            <c:if test="${sessionScope.role=='moderator'}">
                                <a class="block header multiwordlinks" href="/Dexter/web/mod/index.jsp">Moderator Actions</a>
                            </c:if>
                            <a id="disabledialoginvoker" class="block header multiwordlinks" href="#">Disable Account</a>
                            <a id="deletedialoginvoker" class="block header multiwordlinks" href="#">Delete Account</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">
                    <div class="rightcolumn">
                        <div class="contentheading">
                            <h3>Crop Profile Picture</h3>
                        </div>
                        <div class="dynamicdivs"><br><br>
                        </div>
                    </div>
                    <div class="leftcolumn">
                        <div class="contentheading">
                            <h3>Edit Profile</h3>
                        </div>
                        <div class="dynamicdivs" id="editprofile">
                            <html:form enctype="multipart/form-data" method="POST" action="/actions/editprofile" styleClass="centerform">
                                <h4 class="success">${sessionScope.SignupFormBean.success}</h4>
                                <h4 class="error">${sessionScope.SignupFormBean.error}</h4><br><br>
                                <ul class="nobullet nostyle">
                                    <li>
                                        <div class="fleft"><b>Username<span class="error">*</span></b></div>
                                        <div class="fright"><html:text disabled="true" property="username" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>First Name</b></div>
                                        <div class="fright"><html:text property="firstname" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Last Name</b></div>
                                        <div class="fright"><html:text property="lastname" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Profile Picture</b></div>
                                        <div class="fright"><html:file property="file"></html:file></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Email<span class="error">*</span></b></div>
                                        <div class="fright"><html:text property="email" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Country</b></div>
                                        <div class="fright">
                                            <html:select property="country" styleClass="input">
                                                <html:optionsCollection name="CountryBean" value="id" label="label"/>
                                            </html:select>
                                        </div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>City</b></div>
                                        <div class="fright"><html:text property="city" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Gender</b></div>
                                        <div class="fright">
                                            <html:select property="gender" styleClass="input">
                                                <html:option value="male">Male</html:option>
                                                <html:option value="female">Female</html:option>
                                            </html:select>
                                        </div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Date of Birth (DD-MM-YYYY)</b></div>
                                        <div class="fright"><html:text property="day" size="4" maxlength="2" styleClass="input"></html:text>
                                            <html:text property="month" size="4" maxlength="2" styleClass="input"></html:text>
                                        <html:text property="year" size="4" maxlength="4" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Website</b></div>
                                        <div class="fright"><html:text property="website" styleClass="input"></html:text></div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Activity Visible to</b></div>
                                        <div class="fright">
                                            <html:select property="activityprivacy" styleClass="input">
                                                <html:option value="everyone">everyone</html:option>
                                                <html:option value="friends">friends</html:option>
                                                <html:option value="me">me</html:option>
                                            </html:select>
                                        </div><br><br>
                                    </li>
                                    <li>
                                        <div class="fleft"><b>Default Landing Page</b></div>
                                        <div class="fright">
                                            <html:select property="landingpage" styleClass="input">
                                                <html:option value="classic">classic</html:option>
                                                <html:option value="idexter">idexter</html:option>
                                            </html:select>
                                        </div><br><br><br>
                                    </li>
                                    <li>
                                        <div class="fright">
                                            <html:submit value="Save" styleClass="input"></html:submit>
                                            <html:reset value="Clear" styleClass="input"></html:reset>
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
