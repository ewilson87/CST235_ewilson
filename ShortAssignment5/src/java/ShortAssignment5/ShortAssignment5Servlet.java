/**
 * Program: CST-235 Short Assignment 5
 * File: ShortAssignment5Servlet.java
 * Summary: This servlet will read all init params of the associated web.xml file and display to user
 * Author: Evan Wilson
 * Date: September 11th, 2018
 * */
package ShortAssignment5;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShortAssignment5Servlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShortAssignment5Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShortAssignment5Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        //Looks in web.xml and creates an enumeration of all the parameter names
        Enumeration<String> initParams = getInitParameterNames();

        //sets response to html and writes all the basic html tags
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ShortAssignment5Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet ShortAssignment5Servlet at " + request.getContextPath() + "</h1><br/>");
        out.println("<p>Below are all the init parameters of the web.xml file:</p><br/>");

        //loop through the entire enumeration of init params, printing out the param name and value
        while (initParams.hasMoreElements()) {
            String paramName = initParams.nextElement();
            out.println("<p>Init Param: " + paramName + ", value: ");
            out.println(getServletConfig().getInitParameter(paramName) + "</p>");
        }

        //closing html tags
        out.println("</body>");
        out.println("</html>");
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
