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
@WebServlet(urlPatterns = {"/Prfl_Bk_Confirm"})
public class Prfl_Bk_Confirm_Pay extends HttpServlet {

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
           String cid= (String) session.getAttribute("cid");
           Vroom vr = new Vroom();
           int carid = Integer.parseInt(cid);
           int locid = Integer.parseInt(vr.caravail.get(carid).get(1));
           
           String FirstName = (String) session.getAttribute("firstname");  
           String LastName = (String) session.getAttribute("lastname");
           
           String fromdate = (String) session.getAttribute("fromdate");  
           String todate = (String) session.getAttribute("todate");
           
           int todt = Integer.parseInt(todate.substring(8,10));
           int frmdt = Integer.parseInt(fromdate.substring(8,10));
           int total =(1+todt)-frmdt;
           total = total * Integer.parseInt(vr.caravail.get(carid).get(7));
           

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
                        "<h3>Booking Details</h3>"+
                        "<img src=\"images/CreditCard.svg\" style=\"position:absolute; height:420px; top:50px; left:720px; bottom: 150px\">"+                               
                        "<p>Model Name        : "+ vr.caravail.get(carid).get(4)  +"</p>"+
                        "<p>Model Year        : "+ vr.caravail.get(carid).get(5)  +"</p>"+
                        "<p>CarCategory       : "+ vr.caravail.get(carid).get(2)  +"</p>"+
                        "<p>Person Includable : "+ vr.caravail.get(carid).get(6)  +"</p>"+
                        "<p>Cost Per Day      :  Rs. "+ vr.caravail.get(carid).get(7)  +"</p>"+ 
                        "<p>PickUp Date       : "+ fromdate  +"</p>"+ 
                        "<p>Return Date       : "+ todate  +"</p>"+ 
                        "<p style=\" line-height: 2.2;\">Pick-Up Location  :- <br>"+ vr.carloc.get(locid).get(1) + "<br>" +
                                                   vr.carloc.get(locid).get(2) + ", " +
                                                   vr.carloc.get(locid).get(3) + "<br> India - " +
                                                   vr.carloc.get(locid).get(4) + "</p>"+ 
                        "<h4>Total Amount      :  Rs. "+  total +"</h4>"+ 
                        "<p>\n</p>"+ 
                        "<button id=\"rzp-button1\" onclick=\"payProcess()\"  style=\"height:50px; border: none; border-radius: 10px; background-color:#072654; outline: none;\"><img src=\"images/rz_pay.png\" style=\"height:40px;\"></button>"+
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
                        "       <script src=\"https://checkout.razorpay.com/v1/checkout.js\"></script>\n" +
                        "       <script>\n" +
                        "       var image = document.getElementById(\"payimage\");"+
                        "       image.addEventListener('click', function(){\n" +
                        "           payProcess();\n" +
                        "           });"+
                        "         function  payProcess()\n" +
                        "         {\n" +
                        "             var options = {\n" +
                        "                \"key\": \"rzp_test_tDCcn6OIndC4xK\", // Enter the Key ID generated from the Dashboard\n" +
                        "                \"amount\":"+Integer.toString(total*100)+", // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise\n" +
                        "                \"currency\": \"INR\",\n" +
                        "                \"name\": \"Vroom Car Share\",\n" +
                        "                \"description\": \"Car Rental Transaction\",\n" +
                        "                \"image\": \"images/"+vr.caravail.get(carid).get(0)+".jpg\",\n" +
                        "                 //This is a sample Order ID. Pass the `id` obtained in the response of Step 1\n" +
                        "                \"handler\": function (response){\n" +
                       // "                    alert(response.razorpay_payment_id);\n" +
                       // "                    alert(response.razorpay_order_id);\n" +
                       // "                    alert(response.razorpay_signature)\n" +
                        "                    alert(\"Payment has been received successfully\")\n" +
                        "                document.location.href = \"./Prfl_Payment\";"+
                        "                },\n" +
                        "                \"prefill\": {\n" +
                        "                    \"name\": \"Raj Kumar\",\n" +
                        "                    \"email\": \"vroom.crs@gmail.com\",\n" +
                        "                    \"contact\": \"936104881\"\n" +
                        "                },\n" +
                        "                \"notes\": {\n" +
                        "                    \"address\": \"Razorpay Corporate Office\"\n" +
                        "                },\n" +
                        "                \"theme\": {\n" +
                        "                    \"color\": \"#072654\"\n" +
                        "                }\n" +
                        "            };\n" +
                        "            var rzp1 = new Razorpay(options);\n" +
                        "            rzp1.on('payment.failed', function (response){\n" +
                        "                    alert(response.error.code);\n" +
                        "                    alert(response.error.description);\n" +
                        "                    alert(response.error.source);\n" +
                        "                    alert(response.error.step);\n" +
                        "                    alert(response.error.reason);\n" +
                        "                    alert(response.error.metadata.order_id);\n" +
                        "                    alert(response.error.metadata.payment_id);\n" +
                        "            });\n" +
                        "            document.getElementById('rzp-button1').onclick = function(e){\n" +
                        "                rzp1.open();\n" +
                        "                e.preventDefault();\n" +
                        "            }\n" +
                        "         }\n" +
                        "       </script>"+
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
