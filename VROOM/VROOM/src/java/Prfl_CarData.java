/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author blraj
 */
@WebServlet(urlPatterns = {"/Prfl_CarData"})
public class Prfl_CarData extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           HttpSession session = request.getSession(false);
           String cid=request.getParameter("xxx");
           session.setAttribute("cid", cid);
           String FirstName = (String) session.getAttribute("firstname");  
           String LastName = (String) session.getAttribute("lastname");
           Vroom vr = new Vroom();
           int carid = Integer.parseInt(cid);
           int locid = Integer.parseInt(vr.caravail.get(carid).get(1));

           if(session != null)
           {
            out.println("<html lang=\"en\">\n" +
                        "    <head>\n" +
                        "        <meta charset=\"UTF-8\">\n" +
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                        "        <link rel=\"stylesheet\" href=\"css/car_data.css\">\n" +
                        "         <script\n" +
                        "      src=\"https://kit.fontawesome.com/64d58efce2.js\"\n" +
                        "      crossorigin=\"anonymous\"\n" +
                        "    ></script>\n" +
                        "        <title>CRS</title>\n" +
                        "    </head>\n" +
                        "    <body>\n" +
                        "        <aside>\n" +
                        "            <figure>\n" +
                        "                <div id=\"avatar\"><img  class=\"logo2\" src=\"images/person_logo.png\"></div>\n" +
                        "                <a href=\"./Profile\">\n" +
                        "                <button style=\"background:none;border:none;font-size: 1.3em;font-weight: bold; margin-left: 15%;\">" 
                                         +FirstName+" "+ LastName+"</button></a>\n"+
                        "            </figure>\n" +
                        "            <img  class=\"img\" src=\"images/menu.svg\" alt=\"menu\">\n" +
                        "            <nav>\n" +
                        "                <ul>\n" +
                        "                    <li><a href=\"./Prfl_Data\">Profile</a></li>\n" +
                        "                \n" +
                        "                    <li><a href=\"./Prfl_CarAvailDate\">Book A Car</a></li>\n" +
                        "                    <li><a href=\"./Prfl_Booking_Hist\">Booking History</a></li>\n" +
                        "                    <li><a href=\"./Logout\">Logout</a></li>\n" +
                        "                </ul>\n" +
                        "            </nav>\n" +
                        "        </aside>\n" +
                        "        <main> \n" +
                        "<form method=\"post\" action=\"./Prfl_Bk_Confirm\" class=\"car_data\">"+
                        "<h3>Car Details</h3>"+
                        "<img src=\"images/"+vr.caravail.get(carid).get(0)+".jpg\" style=\"position:absolute; height:330px; top:100px; left:730px; bottom: 150px\">"+
                        "<p>CarID             : "+ vr.caravail.get(carid).get(0) +"</p>"+                                
                        "<p>CarCategory       : "+ vr.caravail.get(carid).get(2)  +"</p>"+
                        "<p>Model Name        : "+ vr.caravail.get(carid).get(4)  +"</p>"+
                        "<p>Model Year        : "+ vr.caravail.get(carid).get(5)  +"</p>"+
                        "<p>Person Includable : "+ vr.caravail.get(carid).get(6)  +"</p>"+
                        "<p>Cost Per Day      :  Rs. "+ vr.caravail.get(carid).get(7)  +"</p>"+ 
                        "<p> <br> </p>"+ 
                        "<h3>Location Details</h3>"+
                        "<p>LocID             : "+ vr.caravail.get(carid).get(1) +"</p>"+
                        "<p>Street            : "+ vr.carloc.get(locid).get(1) +"</p>"+
                        "<p>City              : "+ vr.carloc.get(locid).get(2) +"</p>"+
                        "<p>State             : "+ vr.carloc.get(locid).get(3) +"</p>"+
                        "<p>Zipcode           : "+ vr.carloc.get(locid).get(4) +"</br></br></p>"+ 
                        "<div>"+
                            "<input type=\"submit\" class=\"btn\" value=\"Book Now\" />"+
                        "</div>"+
                        "</form>" +
                        "        </main>\n" +
                        "        <script>\n" +
                        "        \n" +
                        "            (function() {\n" +
                        "                var menu = document.querySelector('ul'),\n" +
                        "                    menulink = document.querySelector('img');\n" +
                        "                \n" +
                        "                menulink.addEventListener('click', function(e) {\n" +
                        "                    menu.classList.toggle('active');\n" +
                        "                    e.preventDefault();\n" +
                        "                });\n" +
                        "            })();\n" +
                        "        \n" +
                        "        </script>\n" +
                        "    </body>\n" +
                        "</html>");
           }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
