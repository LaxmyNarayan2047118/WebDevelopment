<%-- 
    Document   : AdminBkngHistJSP
    Created on : 09-May-2021, 17:11:40
    Author     : blraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>JSP Page</title>
            <link rel="stylesheet" href="css/adminca.css" />
        <script src="https://kit.fontawesome.com/64d58efce2.js"
                crossorigin="anonymous"></script>
    </head>
    <body>
       <aside>
       <figure>
       <div id="avatar"><img  class="logo2" src="images/person_logo.png"></div>
       <a href="./AdminProfile">
       <button style="background:none;border:none;font-size: 1.3em;font-weight: bold; margin-left: 15%;"> 
       ${FirstName} ${LastName}</button></a>
       </figure>
       <img  class="img" src="images/menu.svg" alt="menu">
       <nav>
       <ul>
            <li><a href="./AdminPrflData">Profile</a></li>
            <li><a href="./AdminUpsertCars">Upsert Cars</a></li>
            <li><a href="./AdminUpsLoc">Upsert Location</a></li>
            <li><a href="#">Booking History</a></li>
            <li><a href="./Logout">Logout</a></li>
       </ul>
       </nav>
       </aside>
       <main>
        <form method="post" action="AdminBkgHistFunc" class="date-form">
        <div class="headline">
            <h1 class="title1">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSELECT THE RANGE </h1>
        </div>
            <div class="container">
            <div>
                <img src="images/Date.svg" style="position:absolute; height:370px; top:180px; left:680px; bottom:150px;">
            </div>
            <div class="input-field" style="top:30px; right:320px; bottom:150px;">
            <i class="fas fa-calendar-alt"></i>
            <input type="text" id="fromdate" placeholder="From date" name="fromdate" required onfocus="focusFunction()" onblur="(this.type='placeholder')">
            </div>
            <div class="input-field" style="top:140px; right:605px; bottom:150px;">
            <i class="fas fa-calendar-alt"></i>
            <input type="text" id="todate" placeholder="To date" name="todate" required onfocus="focusFunction()" onblur="(this.type='placeholder')">
            </div>
            <input style="position:absolute; height:55px; width:165px; top:410px; left:360px; bottom:150px;" type="submit" class="btn" value="Submit"/>
            </div>
        </form>
        </main>
        <script>
            (function() {
                    var menu = document.querySelector('ul'), menulink = document.querySelector('img');
                    menulink.addEventListener('click', function(e) {
                    menu.classList.toggle('active');
                    e.preventDefault();});
                    })();
       </script>
       <script>
            function focusFunction() {
                    document.getElementById("fromdate").type="date";
                    document.getElementById("fromdate").min = "2021-03-04";
                    document.getElementById("fromdate").max = "2021-03-30";
                           
                    document.getElementById("todate").type="date";
                    document.getElementById("todate").min = "2021-03-04";
                    document.getElementById("todate").max = "2021-03-30";
                    }
       </script>
    </body>
</html>