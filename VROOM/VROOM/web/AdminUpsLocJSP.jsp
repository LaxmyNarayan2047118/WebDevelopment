<%-- 
    Document   : AdminUpsLocJSP
    Created on : 09-May-2021, 13:39:47
    Author     : blraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
        <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>JSP Page</title>
            <link rel="stylesheet" href="css/upsLoc.css" />
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
            <li><a href="./AdminBkngHistDate">Booking History</a></li>
            <li><a href="./Logout">Logout</a></li>
       </ul>
       </nav>
       </aside>
       <main> 
           <div class="forms-container">
            <div class="signin-signup">
              <form method="post" action="AdminUpdateLocFunc" class="sign-up-form">
                <div>
                    <img src="images/location.svg" style="position:absolute; height:340px; top:50px; left:575px; bottom:150px; transform: rotateY(180deg);">
                </div>
                <h2 class="title">Enter the Location Details</h2>
       
                <div class="input-field">
                    <i class="fas fa-map-marker-alt"></i>
                    <input type="text" placeholder="Location ID" name="locid" required/>
                </div>
                           
                 <div class="input-field">
                    <i class="fa fa-map-pin"></i>
                    <input type="text" placeholder="Street" name="street" required />
                </div>
                
                <div class="input-field">
                    <i class="fa fa-location-arrow"></i>
                    <input type="text" placeholder="City" name="city" required />
                </div>
                
                <div class="input-field">
                    <i class="fa fa-globe"></i>
                    <input type="text" placeholder="State" name="state" required/>
                </div>
                
                <div class="input-field">
                    <i class="fas fa-map"></i>
                    <input type="text" placeholder="Zipcode" name="zipcode" required/>
                </div>
                
                <a href="#"><input type="submit" class="btn" value="Insert / update" /></a>
              </form>
            </div>
          </div>
        </main>
       <script>
            (function() {
                    var menu = document.querySelector('ul'), menulink = document.querySelector('img');
                    menulink.addEventListener('click', function(e) {
                    menu.classList.toggle('active');
                    e.preventDefault();});
                    })();
       </script>
   </body>
</html>