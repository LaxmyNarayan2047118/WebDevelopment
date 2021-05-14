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
@WebServlet(urlPatterns = {"/Prfl_Data"})
public class Prfl_Data extends HttpServlet {

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
           response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
           response.setHeader("Pragma", "no-cache");
           response.setHeader("Expires", "0");
           if(session != null)
           {
               String Userid = (String) session.getAttribute("userid");
               String FirstName = (String) session.getAttribute("firstname");  
               String LastName = (String) session.getAttribute("lastname");
               String Email = (String) session.getAttribute("email");
               String Phnum = (String) session.getAttribute("phnum");
               String DOB = (String) session.getAttribute("dob");
               String DLnum = (String) session.getAttribute("dlnum");
               String ADRnum = (String) session.getAttribute("adrnum");
               String Street = (String) session.getAttribute("street");
               String City = (String) session.getAttribute("city");
               String State = (String) session.getAttribute("state");
               String Zipcode = (String) session.getAttribute("zip");
               
               
                out.println("<html lang=\"en\">\n" +
                        "    <head>\n" +
                        "        <meta charset=\"UTF-8\">\n" +
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                        "        <link rel=\"stylesheet\" href=\"css/prfl_data.css\">\n" +
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
                        "<div>"+ 
                        "<img src=\"images/prfldata.svg\" style=\"position:absolute; top:50px; left:665px; bottom: 150px\">"+
                        "<h3>User Details</h3>"+
                        "<p>UserID           : "+ Userid +"</p>"+                                
                        "<p>Name             : "+ FirstName +" "+ LastName +"</p>"+
                        "<p>Email            : "+ Email +"</p>"+
                        "<p>Phone Number     : "+ Phnum +"</p>"+
                        "<p>Date Of Birth    : "+ DOB +"</p>"+
                        "<p>Driver's License : "+ DLnum +"</p>"+
                        "<p>Aaadhaar Number  : "+ ADRnum +"</p>"+
                        "<p>Street           : "+ Street +"</p>"+
                        "<p>City             : "+ City +"</p>"+
                        "<p>State            : "+ State +"</p>"+
                        "<p>Zipcode          : "+ Zipcode +"</p>"+  
                        "</div>"+ 
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
           else
           {
               response.sendRedirect("signin-signup.html");
               response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
               response.setHeader("Pragma", "no-cache");
               response.setHeader("Expires", "0");

               
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
