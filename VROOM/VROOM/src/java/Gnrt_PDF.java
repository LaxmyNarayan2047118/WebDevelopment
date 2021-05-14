/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author blraj
 */
@WebServlet(urlPatterns = {"/Gnrt_PDF"})
public class Gnrt_PDF extends HttpServlet {

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
           String uid= (String) session.getAttribute("userid");
           
           String FirstName = (String) session.getAttribute("firstname");  
           String LastName = (String) session.getAttribute("lastname");
           
           Vroom vr = new Vroom();
           int userid = Integer.parseInt(uid);
           vr.pop_usr_bkng(userid);
           int no_of_bkngs = vr.user_bookings.get(userid).size();
           
           if(session == null)
           {
               RequestDispatcher rd = request.getRequestDispatcher("Login");
               rd.forward(request,response);
           }
           else
           {   
               try
               {
                   Document bk_hist = new Document();
                   PdfWriter.getInstance(bk_hist, new FileOutputStream("D://Booking_History.pdf"));
                  
                   bk_hist.open();
                   
                   Font blue = new Font(FontFamily.COURIER, 26, Font.BOLD, BaseColor.BLUE);
                   Chunk heading = new Chunk("VROOM CAR SHARE - BOOKING HISTORY", blue);
                   Paragraph p = new Paragraph(30);
                   p.add(heading);
                   //p.setAlignment(Element.ALIGN_CENTER);
                   bk_hist.add(p);
                   
                   Font nm = new Font(FontFamily.COURIER, 20, Font.BOLD, BaseColor.BLACK);
                   String nmhead = "\nName: " + FirstName + LastName + "   User-ID: " + uid;
                   Chunk nmid = new Chunk(nmhead, nm);
                   Paragraph nmidp = new Paragraph(30);
                   nmidp.add(nmid);
                   //p.setAlignment(Element.ALIGN_CENTER);
                   bk_hist.add(nmidp);
                  
                   float img_y=500f;
                   float yRed = 200f;
                   
                   if(no_of_bkngs>0)
                   {
                       for(int i=0; i < no_of_bkngs; i++)
                       {
                           String carid = vr.car_details.get(Integer.parseInt(vr.user_bookings.get(userid).get(i).get(2))).get(0);
                           
                           String filename = "C:\\Users\\blraj\\Documents\\NetBeansProjects\\VROOM\\web\\images\\"+carid+".jpg";
                           String text = "\nBooking : " + (i+1) + "\n"
                                           +"Booking ID : " + vr.user_bookings.get(userid).get(i).get(0) + "\n"            
                                           +"Billing ID : " + vr.user_bookings.get(userid).get(i).get(8) + "\n"
                                           +"Car Model : "+ vr.car_details.get(Integer.parseInt(vr.user_bookings.get(userid).get(i).get(2))).get(4) + "\n"
                                           +"Total Amount : "+ vr.user_bookings.get(userid).get(i).get(6) + "\n"
                                           +"Pick Up Date : "+ vr.user_bookings.get(userid).get(i).get(3) + "\n"
                                           +"Return Date : "+ vr.user_bookings.get(userid).get(i).get(4) + "\n"
                                           +"Location : "+ vr.carloc.get(Integer.parseInt(vr.user_bookings.get(userid).get(i).get(5))).get(1);
                           
                           PdfPTable table = new PdfPTable(2);
                           table.setWidthPercentage(100);
                           table.setWidths(new int[]{3, 2});
                           table.addCell(createTextCell(text));
                           table.addCell(createImageCell(filename));
                           bk_hist.add(table);
                       }
                   }
                   
                   bk_hist.close();
                   
                   String altmsg = "Pdf Generated successfully!";
                   response.setContentType("text/html");  
                   out.println("<script type=\"text/javascript\">");  
                   out.println("alert('" + altmsg +"');"); 
                   out.println("</script>");
                   RequestDispatcher rd = request.getRequestDispatcher("./Prfl_Booking_Hist");
                   rd.forward(request,response);
                           
               } catch (Exception ex) {
                   Logger.getLogger(Gnrt_PDF.class.getName()).log(Level.SEVERE, null, ex);
               }
           }

        }
    }
    
    public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
    Image img = Image.getInstance(path);
    //img.scalePercent(40);
    img.setAlignment(Image.MIDDLE);
    PdfPCell cell = new PdfPCell();
    cell.addElement(new Paragraph(" "));
    cell.addElement(new Paragraph(" "));
    cell.addElement(img);
    cell.setBorder(Rectangle.NO_BORDER);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);//cell.setVerticalAlignment(Element.ALIGN_CENTER);
    return cell;
    }
    
    public static PdfPCell createTextCell(String text) throws DocumentException, IOException {
    PdfPCell cell = new PdfPCell();
    Paragraph p = new Paragraph(23);
    Font blk = new Font(FontFamily.HELVETICA, 18, Font.NORMAL, BaseColor.BLACK);
    Chunk ech_bkn = new Chunk(text, blk);
    p.add(ech_bkn);
    cell.addElement(p);
    //cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
    cell.setBorder(Rectangle.NO_BORDER);
    return cell;
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
