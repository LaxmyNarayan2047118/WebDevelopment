/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            Connection con=null;
            Statement stm=null;
            ResultSet rs=null;
            ResultSet rs2=null;
            HttpSession session = request.getSession();
            int userid=Integer.parseInt(request.getParameter("userid"));
            String pass=request.getParameter("password");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
                String query="select *from logincredentials where CustomerUserID='"+userid+"' and Password='"+pass+"'";
                stm=con.createStatement();
                rs=stm.executeQuery(query);
                if(rs.next())
                {
                    String query2="select *from customer where UserID='"+userid+"'";
                    stm=con.createStatement();
                    rs2=stm.executeQuery(query2);
                    if(rs2.next())
                    {
                        Character intl = new Character(request.getParameter("userid").charAt(0));
                        session.setAttribute("userid", rs2.getString("UserID"));
                        session.setAttribute("firstname", rs2.getString("FirstName"));
                        session.setAttribute("lastname", rs2.getString("LastName"));
                        session.setAttribute("email", rs2.getString("Email"));
                        session.setAttribute("phnum", rs2.getString("PhoneNumber"));
                        session.setAttribute("dob", rs2.getString("DOB"));
                        session.setAttribute("dlnum", rs2.getString("DLnumber"));
                        session.setAttribute("adrnum", rs2.getString("AadhaarNumber"));
                        session.setAttribute("street", rs2.getString("Street"));
                        session.setAttribute("city", rs2.getString("City"));
                        session.setAttribute("state", rs2.getString("State"));
                        session.setAttribute("zip", rs2.getString("Zipcode"));
                        if(intl.equals('2'))
                        {
                            response.sendRedirect("./Profile");
                        }
                        else if(intl.equals('1'))
                        {
                            response.sendRedirect("./AdminProfile");        
                        }
                        else
                        {
                                
                        }
                        
                    }
                }
                else
                {
                    String altmsg = "Invalid UserId or Password \\nPlease Try Again!";
                    response.setContentType("text/html");  
                    out.println("<script type=\"text/javascript\">");  
                    out.println("alert('" + altmsg +"');"); 
                    out.println("window.location.href = \"signin-signup.html\";");
                    out.println("</script>");
                }
            } catch (ClassNotFoundException ex) {out.println("Invalid1");
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
