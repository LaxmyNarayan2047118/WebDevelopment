<%-- 
    Document   : AdminUpsertCarsJSP
    Created on : 09-May-2021, 12:26:20
    Author     : blraj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>JSP Page</title>
            <link rel="stylesheet" href="css/upsCars.css" />
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
              <form method="post" action="AdminUpdateCarFunc" class="sign-up-form">
                <div>
                    <img src="images/insCar.svg" style="position:absolute; height:400px; top:80px; left:515px; bottom:150px; transform: rotateY(180deg);">
                </div>
                <h2 class="title">Enter the Car Details</h2>
                <div class="input-field">
                    <i class="fa fa-id-card-o"></i>
                    <input type="text" placeholder="Car ID" name="carid" required />
                </div>
                <div class="input-field">
                    <i class="fa fa-map-marker"></i>
                    <select  id="cars" placeholder="Location ID" name="locid" required>
                        <option value="" disabled selected >Choose the Location ID</option>
                        <option value="2001">Forum Mall</option>
                        <option value="2002">Orion Mall</option>
                    </select>
                </div>
                <div class="input-field">
                    <i class="fas fa-car"></i>
                    <select id="cars" placeholder="Car Type" name="cartype" required>
                        <option value="" disabled selected >Choose Your Car Type</option>
                        <option value="Sedan">Sedan</option>
                        <option value="Micro">Micro</option>
                        <option value="SUV">SUV</option>
                        <option value="MUV">MUV</option>
                        <option value="Hatchback">Hatchback</option>
                    </select>
                </div>
                 <div class="input-field">
                    <i class="fa fa-registered"></i>
                    <input type="text" placeholder="Car Registeration Number" name="regno" required />
                </div>
                <div class="input-field">
                    <i class="fa fa-car"></i>
                    <input type="text" placeholder="Model Name" name="model" required />
                </div>
                <div class="input-field">
                    <i class="fa fa-calendar-o"></i>
                    <input type="text" placeholder="Model Year" name="year" required/>
                </div>
                <div class="input-field">
                    <i class="fa fa-user"></i>
                    <input type="text" placeholder="Person Includable" name="prsn" required/>
                </div>
                <div class="input-field">
                    <i class="fa fa-user"></i>
                    <input type="text" placeholder="Cost Per Day" name="cost" required/>
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