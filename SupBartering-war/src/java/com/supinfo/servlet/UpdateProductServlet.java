/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supinfo.beans.ProductService;
import com.supinfo.entity.Product;
import com.supinfo.entity.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.UUID;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Oudomsieng
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/admin/UpdateProductServlet"})
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {
    @EJB
    private ProductService productService;
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
        HttpSession session = request.getSession();
        ObjectMapper mapper = new ObjectMapper();
        User user = (User) session.getAttribute("user");
        
        Product product = productService.getProductById(
                Long.parseLong(request.getParameter("id")));
        
        if(Objects.equals(user.getId(), product.getUser().getId())){
            getServletContext().getRealPath("/");
            
            if(request.getParameter("name") != null){
                product.setName(request.getParameter("name"));
            }
            
            if(request.getParameter("description") != null){
                product.setDescription(request.getParameter("description"));
            }
            
            if(request.getParameter("price") != null){
                product.setPrice(Float.parseFloat(request.getParameter("price")));
            }
            
            Part filePart = request.getPart("file");
            if(filePart != null && filePart.getSize() != 0 && !"".equals(filePart.getSubmittedFileName())){
                InputStream fileContent = filePart.getInputStream();
                String fileName = UUID.randomUUID() + filePart.getSubmittedFileName();
                OutputStream outputStream = new FileOutputStream(new File(
                    getServletContext().getRealPath("")
                    .replace("dist\\gfdeploy\\SupBartering\\SupBartering-war_war",
                            "SupBartering-war\\web\\UploadedImages\\") +
                    fileName
                ));

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = fileContent.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                outputStream.flush();
                outputStream.close();
                product.setPicture(fileName);
            }
            
            product = productService.updateProduct(product);
        }
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(mapper.writeValueAsString(product));
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
