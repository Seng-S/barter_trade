/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.entity.User;
import com.supinfo.beans.UserService;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Oudomsieng
 */
@WebServlet(name = "User", urlPatterns = {"/User"})
public class UserServlet extends HttpServlet {

    @EJB
    private UserService userService;

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
        ObjectMapper mapper = new ObjectMapper();
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        if(request.getParameter("userid") != null){
            Long userid = Long.parseLong(request.getParameter("userid"));
            User user = userService.getUserById(userid);
            out.print(mapper.writeValueAsString(user));
        }else{
            Long usersCounted = userService.countUsers();
            out.print("{\"usersCounted\": " + usersCounted + "}");
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
        ObjectMapper mapper = new ObjectMapper();
        
        User user = new User();
        user.setUsername(request.getParameter("username"));
        if(request.getParameter("password") != null && !"".equals(request.getParameter("password"))){
            user.setPassword(user.hashPassword(request.getParameter("password")));
        }
        user.setFirstName(request.getParameter("firstname"));
        user.setLastName(request.getParameter("lastname"));
        user.setEmail(request.getParameter("email"));
        user = userService.addUser(user);
        
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();        
        out.print(mapper.writeValueAsString(user));
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
