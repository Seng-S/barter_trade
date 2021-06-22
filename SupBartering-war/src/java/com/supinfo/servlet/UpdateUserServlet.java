/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supinfo.beans.UserService;
import com.supinfo.entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Oudomsieng
 */
@WebServlet(name = "Update User", urlPatterns = {"/admin/editUser"})
public class UpdateUserServlet extends HttpServlet {
    @EJB
    private UserService userService;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ObjectMapper mapper = new ObjectMapper();
        
        User user = (User) session.getAttribute("user");
        if(request.getParameter("password") != null && !"".equals(request.getParameter("password"))){
            user.setPassword(user.hashPassword(request.getParameter("password")));
        }
        if(request.getParameter("firstname") != null){
            user.setFirstName(request.getParameter("firstname"));
        }
        if(request.getParameter("lastname") != null){
            user.setLastName(request.getParameter("lastname"));
        }
        if(request.getParameter("email") != null){
            user.setEmail(request.getParameter("email"));
        }
        userService.updateUser(user);
        
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
