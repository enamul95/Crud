/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ResponseModel;
import model.UserModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ehaqu
 */
@WebServlet(name = "UserRestApi", urlPatterns = {"/user-rest-api"})
public class UserRestApi extends HttpServlet {

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
            out.println("<title>Servlet UserRestApi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserRestApi at " + request.getContextPath() + "</h1>");
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

        UserDao dao = new UserDao();
        List<UserModel> list = dao.getAllData();
        System.out.println("list = " + list);

        try {

            JSONArray ja = new JSONArray();
            for (UserModel model : list) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("userId", model.getId());
                jSONObject.put("userName", model.getName());
                jSONObject.put("mobile", model.getMobile());
                jSONObject.put("userEmail", model.getEmail());
                ja.add(jSONObject);
            }
            System.out.println("ja = " + ja);

            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(ja.toString());
            response.getWriter().flush();
        } catch (IOException ex) {
            Logger.getLogger(UserRestApi.class.getName()).log(Level.SEVERE, null, ex);
        }

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

        String name = request.getParameter("name");
        System.out.println("name = " + name);
        String mobile = request.getParameter("mobile");
        System.out.println("mobile = " + mobile);
        String email11 = request.getParameter("email");
        System.out.println("email11 = " + email11);

        UserDao dao = new UserDao();
        UserModel model = new UserModel();
        model.setName(name);
        model.setMobile(mobile);
        model.setEmail(email11);
        ResponseModel insertData = dao.insertData(model);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("responseCode", insertData.getResponseCode());
            jSONObject.put("responseMessage", insertData.getResponseMessage());
            
            System.out.println("jSONObject = " + jSONObject);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jSONObject.toString());
            response.getWriter().flush();

        } catch (IOException e) {
            Logger.getLogger(UserRestApi.class.getName()).log(Level.SEVERE, null, e);

        }
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
