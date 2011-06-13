<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Dexter | Search</title>

        <link href="/Dexter/_stylesheets/global.css" rel="stylesheet" type="text/css">
        <link href="/Dexter/_stylesheets/power.css" rel="stylesheet" type="text/css">
        <link type="text/css" rel="stylesheet" href="/Dexter/_stylesheets/jquery/themes/base/ui.all.css">

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
                    <table class="toptable" summary="this table contains links">
                        <tr>
                            <td>
                                <img src="/Dexter/_images/ProfilePic.jpg" alt="ProfilePic" width="16" height="16">
                            </td>
                            <td>
                                <a class="header toplinks" href="/Dexter/actions/logout.do">Signout</a>
                                <a class="header toplinks" href="/Dexter/actions/idexter.do?page=1">iDexter</a>
                                <a class="header toplinks multiwordlinks" href="/Dexter/actions/myprofile.do">My Profile</a>
                                <a class="multiwordlinks header toplinks" href="/Dexter/actions/myfriends.do">My Friends</a>
                                <a class="header toplinks" href="/Dexter/web/user/clear/submit.jsp">Submit</a>
                                <a class="header toplinks" href="/Dexter/web/user/ssl/settings.jsp">Settings</a>
                                <a class="header toplinks" href="/Dexter/web/user/clear/about.jsp">About</a>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="sublinks">
                    <div class="categories icategories sublinks" id="categories">
                        <div class="menu">
                            <ul>
                                <c:forEach var="categorybean" items="${category}">
                                    <li>
                                        <a class="hide" href="/Dexter/actions/search.do?search=${param.search}&page=${param.page}&category=${categorybean.categoryname}&channel=${param.channel}&timeframe=${param.timeframe}"><b>${categorybean.categoryname}</b></a>
                                        <ul>
                                            <c:forEach var="childbean" items="${categorybean.child}">
                                                <li>
                                                    <a class="multiwordlinks" href="">${childbean.categoryname}</a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="content" id="content">
                <div class="main">
                    <div class="contentheading idexter ichannel fleft">
                        <div class="channels" id="channels">
                            <div class="block">
                                <c:forEach var="channelbean" items="${channel}">
                                    <c:choose>
                                        <c:when test='${channelbean.visibleURL == param.channel}'>
                                            <span class="block header">${channelbean.channelName}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="block header" href="/Dexter/actions/search.do?search=${param.search}&page=${param.page}&category=${param.category}&channel=${channelbean.visibleURL}&timeframe=${param.timeframe}">${channelbean.channelName}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <div class="contentheading idexter itimeframe fright">
                        <div class="timeframe sublinks" id="timeframe">
                            <div class="block">
                                <c:choose>
                                    <c:when test='${param.timeframe == "today"}'>
                                        <span class="block header">Today</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="blueblock block header" href="/Dexter/actions/search.do?search=${param.search}&page=${param.page}&category=${param.category}&channel=${param.channel}&timeframe=today">Today</a>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test='${param.timeframe == "week"}'>
                                        <span class="block header">Week</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="blueblock block header" href="/Dexter/actions/search.do?search=${param.search}&page=${param.page}&category=${param.category}&channel=${param.channel}&timeframe=week">Week</a>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test='${param.timeframe == "month"}'>
                                        <span class="block header">Month</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="blueblock block header" href="/Dexter/actions/search.do?search=${param.search}&page=${param.page}&category=${param.category}&channel=${param.channel}&timeframe=month">Month</a>
                                    </c:otherwise>
                                </c:choose>
                                <c:choose>
                                    <c:when test='${param.timeframe == "year"}'>
                                        <span class="block header">Year</span>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="blueblock block header" href="/Dexter/actions/search.do?search=${param.search}&page=${param.page}&category=${param.category}&channel=${param.channel}&timeframe=year">Year</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <div class="dynamicdivs"><br><br><br>
                        <div class="center">
                            <ul class="nobullet demarcate">

                                <c:forEach var="resultbean" items="${searchdexter}">
                                    <li>
                                        <div class="fright">
                                            <blockquote>
                                                <a href="">
                                                <div class="fleft"><img height="44" weight="44" src="/Dexter/_images/Vaibhav.jpg"></div></a>
                                                <div class="fright padleftsmall">
                                                    <h5>submitted by - <br></h5>
                                                    <div class="padleftsmall">
                                                        <h4>${resultbean.userFullName}</h4>
                                                        <h6 class="error">
                                                            <c:choose>
                                                                <c:when test='${resultbean.submissiondate == "0"}'>Today...</c:when>
                                                                <c:otherwise>${resultbean.submissiondate} Days ago...</c:otherwise>
                                                            </c:choose>
                                                        </h6>
                                                    </div>
                                                </div>
                                            </blockquote>
                                        </div>
                                        <div class="fleft">
                                            <div>
                                                <c:choose>
                                                    <c:when test="${resultbean.ifmodded == true}">
                                                        <c:choose>
                                                            <c:when test="${resultbean.ifvotedup == true}">
                                                                <a href="/Dexter/actions/vote.do?search=${param.search}&page=${param.page}&mod=up&ID=${resultbean.ID}&category=${param.category}&channel=${param.channel}&timeframe=${param.timeframe}&keywordID=${resultbean.keywordurlid}">
                                                                    <img src="/Dexter/_images/standardup.png" alt="UP" width="29" height="27">
                                                                </a>
                                                                <a href="/Dexter/actions/vote.do?search=${param.search}&page=${param.page}&mod=down&ID=${resultbean.ID}&category=${param.category}&channel=${param.channel}&timeframe=${param.timeframe}&keywordID=${resultbean.keywordurlid}">
                                                                    <img src="/Dexter/_images/greydown.png" alt="DOWN" width="29" height="27">
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="/Dexter/actions/vote.do?search=${param.search}&page=${param.page}&mod=up&ID=${resultbean.ID}&category=${param.category}&channel=${param.channel}&timeframe=${param.timeframe}&keywordID=${resultbean.keywordurlid}">
                                                                    <img src="/Dexter/_images/greyup.png" alt="UP" width="29" height="27">
                                                                </a>
                                                                <a href="/Dexter/actions/vote.do?search=${param.search}&page=${param.page}&mod=down&ID=${resultbean.ID}&category=${param.category}&channel=${param.channel}&timeframe=${param.timeframe}&keywordID=${resultbean.keywordurlid}">
                                                                    <img src="/Dexter/_images/standarddown.png" alt="DOWN" width="29" height="27">
                                                                </a>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a class="dextervoteupdialoginvoker" href="">
                                                            <img src="/Dexter/_images/greyup.png" alt="UP" width="29" height="27">
                                                            <input type="hidden" value="${resultbean.ID}">
                                                        </a>
                                                        <a class="dextervotedowndialoginvoker" href="">
                                                            <img src="/Dexter/_images/greydown.png" alt="DOWN" width="29" height="27">
                                                            <input type="hidden" value="${resultbean.ID}">
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
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
                                                        ${resultbean.content}<br><br>
                                                        <span class="header">
                                                            <a class="multiwordlinks" href="${resultbean.cacheUrl}"><b>Cached Webpage</b></a> |
                                                            <a href="#"><b>Comment</b></a> |
                                                            <span class="multiwordlinks error">${resultbean.category}</span> |
                                                            <a href=""><img height="18" weight="18" src="/Dexter/_images/favorite.png" alt="Favorite!"></a>
                                                        </span><br><br>
                                                    </blockquote>
                                                </blockquote>
                                            </div>
                                        </div>
                                        <div class="clear"></div><br>
                                    </li>
                                </c:forEach>

                                <c:forEach var="resultbean" items="${search}">
                                    <li>
                                        <div class="fleft">
                                            <div>
                                                <a class="voteupdialoginvoker" href="">
                                                    <img src="/Dexter/_images/greyup.png" alt="UP" width="29" height="27">
                                                    <input type="hidden" value="${resultbean.ID}">
                                                </a>
                                                <a class="votedowndialoginvoker" href="">
                                                    <img src="/Dexter/_images/greydown.png" alt="DOWN" width="29" height="27">
                                                    <input type="hidden" value="${resultbean.ID}">
                                                </a>
                                                <span class="padleft urltitle">
                                                    <b><a href="${resultbean.unescapedUrl}">
                                                            ${resultbean.title}
                                                        </a>
                                                    </b>
                                                </span></<br><br>
                                            </div>
                                            <div class="fleft padleft">
                                                <h3>0</h3>
                                            </div>
                                            <div class="fright nostyle">
                                                <blockquote class="notop">
                                                    <blockquote class="notop fw urlcontent">
                                                        <b>${resultbean.visibleUrl} - </b>
                                                        ${resultbean.content}<br><br>
                                                        <span class="header">
                                                            <a class="multiwordlinks" href="${resultbean.cacheUrl}"><b>Cached Webpage</b></a> |
                                                            <a href="#"><b>Comment</b></a> |
                                                            <a href=""><img height="18" weight="18" src="/Dexter/_images/favorite.png" alt="Favorite!"></a>
                                                        </span>
                                                    </blockquote>
                                                </blockquote>
                                            </div>
                                        </div>
                                        <div class="fright">
                                            <blockquote>
                                                <a href="">
                                                <div class="fleft"><img height="44" weight="44" src="/Dexter/_images/google.jpg"></div></a>
                                            </blockquote>
                                        </div>
                                        <div class="clear"></div><br>
                                    </li>
                                </c:forEach>
                                <li>
                                    <div class="fleft">
                                        <c:if test="${param.page!=1}">
                                            <a href="/Dexter/actions/search.do?search=${param.search}&page=${param.page-1}">
                                            </c:if>
                                            <button class="input">Previous</button>
                                        </a>
                                    </div>
                                    <div class="fright">
                                        <a href="/Dexter/actions/search.do?search=${param.search}&page=${param.page+1}">
                                            <button class="input">Next</button>
                                        </a>
                                    </div>
                                    <div class="clear"></div>
                                </li>
                            </ul><br><br>
                        </div>
                    </div>
                </div><br><br>
            </div>
            <div class="footer" id="footer">

                <div id="voteupdialog" class="hidden" title="Submission">
                    <p>Choose Category to make submission in...</p>
                    <form id="voteupform" action="/Dexter/actions/vote.do" method="POST">
                        <fieldset>
                            <ul class="nostyle nobullet">
                                <li><div class="fleft"> <b>Choose Category</b></div>
                                    <div class="fright"><select name="category" class="input">
                                            <c:forEach var="categorybean" items="${category}">
                                                <optgroup label="${categorybean.categoryname}">
                                                    <c:forEach var="childbean" items="${categorybean.child}">
                                                        <option value="${childbean.categoryname}">${childbean.categoryname}
                                                    </c:forEach>
                                                </optgroup>
                                            </c:forEach>
                                    </select></div><br><br>
                                </li>
                                <li><div class="fleft"><b>Keywords:</b></div>
                                    <div class="fright"><input type="text" class="input" name="keywords" value=""></input></div>
                                </li>
                            </ul>
                            <input type="hidden" value="" name="ID" id="upbeanUUID"></input>
                            <input type="hidden" value="${param.search}" name="search"></input>
                            <input type="hidden" value="${param.page}" name="page"></input>
                            <input type="hidden" value="up" name="mod"></input>
                            <input type="hidden" value="${param.category}" name="category"></input>
                            <input type="hidden" value="${param.channel}" name="channel"></input>
                            <input type="hidden" value="${param.timeframe}" name="timeframe"></input>
                        </fieldset>
                    </form>
                </div>

                <div id="votedowndialog" class="hidden" title="Submission">
                    <p>Choose Category to make submission in...</p>
                    <form id="votedownform" action="/Dexter/actions/vote.do" method="POST">
                        <fieldset>
                            <ul class="nostyle nobullet">
                                <li><div class="fleft"> <b>Choose Category</b></div>
                                    <div class="fright"><select name="category" class="input">
                                            <c:forEach var="categorybean" items="${category}">
                                                <optgroup label="${categorybean.categoryname}">
                                                    <c:forEach var="childbean" items="${categorybean.child}">
                                                        <option value="${childbean.categoryname}">${childbean.categoryname}
                                                    </c:forEach>
                                                </optgroup>
                                            </c:forEach>
                                    </select></div><br><br>
                                </li>
                                <li><div class="fleft"><b>Keywords:</b></div>
                                    <div class="fright"><input type="text" class="input" name="keywords" value=""></input></div>
                                </li>
                            </ul>
                            <input type="hidden" value="" name="ID" id="downbeanUUID"></input>
                            <input type="hidden" value="${param.search}" name="search"></input>
                            <input type="hidden" value="${param.page}" name="page"></input>
                            <input type="hidden" value="down" name="mod"></input>
                            <input type="hidden" value="${param.category}" name="category"></input>
                            <input type="hidden" value="${param.channel}" name="channel"></input>
                            <input type="hidden" value="${param.timeframe}" name="timeframe"></input>
                        </fieldset>
                    </form>
                </div>

                <div id="dextervoteupdialog" class="hidden" title="Vote">
                    <p>Choose Keyword to Vote against...</p>
                    <form id="dextervoteupform" action="/Dexter/actions/vote.do" method="POST">
                        <fieldset>
                            <ul class="nostyle nobullet">
                                <li><div class="fleft"><b>Keywords:</b></div>
                                    <div class="fright"><input type="text" class="input" name="keywords" value=""></input></div>
                                </li>
                            </ul>
                            <input type="hidden" value="" name="ID" id="dexterupbeanUUID"></input>
                            <input type="hidden" value="${param.search}" name="search"></input>
                            <input type="hidden" value="${param.page}" name="page"></input>
                            <input type="hidden" value="up" name="mod"></input>
                            <input type="hidden" value="${param.category}" name="category"></input>
                            <input type="hidden" value="${param.channel}" name="channel"></input>
                            <input type="hidden" value="${param.timeframe}" name="timeframe"></input>
                        </fieldset>
                    </form>
                </div>

                <div id="dextervotedowndialog" class="hidden" title="Submission">
                    <p>Choose Keyword to Vote against...</p>
                    <form id="dextervotedownform" action="/Dexter/actions/vote.do" method="POST">
                        <fieldset>
                            <ul class="nostyle nobullet">
                                <li><div class="fleft"><b>Keywords:</b></div>
                                    <div class="fright"><input type="text" class="input" name="keywords" value=""></input></div>
                                </li>
                            </ul>
                            <input type="hidden" value="" name="ID" id="dexterdownbeanUUID"></input>
                            <input type="hidden" value="${param.search}" name="search"></input>
                            <input type="hidden" value="${param.page}" name="page"></input>
                            <input type="hidden" value="down" name="mod"></input>
                            <input type="hidden" value="${param.category}" name="category"></input>
                            <input type="hidden" value="${param.channel}" name="channel"></input>
                            <input type="hidden" value="${param.timeframe}" name="timeframe"></input>
                        </fieldset>
                    </form>
                </div>


                © Dexter 2008 - Final Year Project for Galgotias College of Engineering and Technology<br>
                Designed By - Vaibhav Bajpai, Rahul Burman, Nupur Dixit, Yadavendra
            </div>
        </div>
    </body>
</html>
