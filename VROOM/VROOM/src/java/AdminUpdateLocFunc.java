/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(urlPatterns = {"/AdminUpdateLocFunc"})
public class AdminUpdateLocFunc extends HttpServlet {

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
         Vroom vr = new Vroom();
         if(session != null)
         {
             Connection con=null;
             Statement stm=null;
             ResultSet rs=null;
             String FirstName = (String) session.getAttribute("firstname"); 
             String LastName = (String) session.getAttribute("lastname");
             
             String carlocid = request.getParameter("locid");
             String street = request.getParameter("street");
             String city = request.getParameter("city");
             String state = request.getParameter("state");
             String zipcode = request.getParameter("zipcode");
             
             
             try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
                PreparedStatement carreg;
                carreg=con.prepareStatement("insert into location values(?, ?, ?, ?, ?)");
              
                carreg.setInt(1, Integer.parseInt(carlocid));
                carreg.setString(2, street);
                carreg.setString(3, city);
                carreg.setString(4, state);
                carreg.setInt(5, Integer.parseInt(zipcode));
                carreg.executeUpdate();  
                
                String altmsg = "Location Insertion Successful!";

                out.println("<script type=\"text/javascript\">");  
                out.println("alert('" + altmsg +"');");  
                out.println("window.location.href = \"./AdminUpsLoc\";");
                out.println("</script>"); 
            } catch (ClassNotFoundException ex) {out.println("Invalid1");
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
