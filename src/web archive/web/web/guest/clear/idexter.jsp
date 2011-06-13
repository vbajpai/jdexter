<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN"
"http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"
           prefix="c"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Dexter | iDexter</title>
        <link href="/Dexter/_stylesheets/global.css" rel="stylesheet" type="text/css">
        <link href="/Dexter/_stylesheets/power.css" rel="stylesheet" type="text/css">
        <link rel="icon" type="image/png" href="/Dexter/_images/FavIcon.png">
    </head>
    <body>
        <div class="container" id="container">
            <div class="header" id="header">
                <div class="toplinks" id="toplinks">
                    <div class="block">
                        <a class="header toplinks" href="/Dexter/web/guest/ssl/index.jsp">Home</a>
                        <a class="header toplinks" href="/Dexter/web/guest/ssl/signin.jsp">Signin</a>
                        <a class="header toplinks" href="/Dexter/web/guest/ssl/signup.jsp">Signup</a>
                        <a class="header toplinks" href="/Dexter/web/guest/clear/about.jsp">About</a>
                    </div>
                </div>
                <div id="sublinks">
                    <div class="categories icategories sublinks" id="categories">
                        <div class="menu">
                            <ul>
                                <c:forEach var="categorybean" items="${category}">
                                    <li>
                                        <a class="hide" href="/Dexter/actions/idexter.do?page=${param.page}&category=${categorybean.categoryname}&channel=${param.channel}&timeframe=${param.timeframe}"><b>${categorybean.categoryname}</b></a>
                                        <ul>
                                            <c:forEach var="childbean" items="${categorybean.child}">
                                                <li>
                                                    <a class="multiwordlinks" href="/Dexter/actions/idexter.do?page=${param.page}&category=${childbean.categoryname}&channel=${param.channel}&timeframe=${param.timeframe}">${childbean.categoryname}</a>
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
                                            <a class="block header" href="/Dexter/actions/idexter.do?page=${param.page}&category=${param.category}&channel=${channelbean.visibleURL}&timeframe=${param.timeframe}">${channelbean.channelName}</a>
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
                                    <a class="blueblock block header" href="/Dexter/actions/idexter.do?page=${param.page}&category=${param.category}&channel=${param.channel}&timeframe=today">Today</a>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test='${param.timeframe == "week"}'>
                                    <span class="block header">Week</span>
                                </c:when>
                                <c:otherwise>
                                    <a class="blueblock block header" href="/Dexter/actions/idexter.do?page=${param.page}&category=${param.category}&channel=${param.channel}&timeframe=week">Week</a>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test='${param.timeframe == "month"}'>
                                    <span class="block header">Month</span>
                                </c:when>
                                <c:otherwise>
                                    <a class="blueblock block header" href="/Dexter/actions/idexter.do?page=${param.page}&category=${param.category}&channel=${param.channel}&timeframe=month">Month</a>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test='${param.timeframe == "year"}'>
                                    <span class="block header">Year</span>
                                </c:when>
                                <c:otherwise>
                                    <a class="blueblock block header" href="/Dexter/actions/idexter.do?page=${param.page}&category=${param.category}&channel=${param.channel}&timeframe=year">Year</a>
                                </c:otherwise>
                            </c:choose>
                            </div>
                        </div>
                    </div>
                    <div class="clear"></div>
                    <div class="dynamicdivs"><br><br><br>
                        <div class="center">
                            <ul class="nobullet demarcate">
                                <c:forEach var="resultbean" items="${idexter}">
                                    <li>
                                        <div class="fleft">
                                            <div>
                                                <a href="/Dexter/web/guest/ssl/signin.jsp?forwardidexter=${param.page}">
                                                    <img src="/Dexter/_images/greyup.png" alt="UP" width="29" height="27">
                                                </a>
                                                <a href="/Dexter/web/guest/ssl/signin.jsp?forwardidexter=${param.page}">
                                                    <img src="/Dexter/_images/greydown.png" alt="DOWN" width="29" height="27">
                                                </a>
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
                                                            <a href="/Dexter/actions/checkurl.do?urlid=123"><b>Comment</b></a> |
                                                            <span class="multiwordlinks error">${resultbean.category}</span> |
                                                            <a href=""><img height="18" weight="18" src="/Dexter/_images/favorite.png" alt="Favorite!"></a>
                                                        </span><br><br>
                                                    </blockquote>
                                                </blockquote>
                                            </div>
                                        </div>
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
                                        <div class="clear"></div><br>
                                    </li>
                                </c:forEach>
                                <li>
                                    <div class="fleft">
                                        <c:if test="${param.page!=1}">
                                            <a href="/Dexter/actions/idexter.do?page=${param.page-1}">
                                            </c:if>
                                            <button class="input">Previous</button>
                                        </a>
                                    </div>
                                    <div class="fright">
                                        <a href="/Dexter/actions/idexter.do?page=${param.page+1}">
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
                © Dexter 2008 - Final Year Project for Galgotias College of Engineering and Technology<br>
                Designed By - Vaibhav Bajpai, Rahul Burman, Nupur Dixit, Yadavendra
            </div>
        </div>
    </body>
</html>
