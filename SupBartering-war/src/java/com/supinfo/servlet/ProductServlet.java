/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supinfo.beans.ProductService;
import com.supinfo.entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oudomsieng
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {
    @EJB
    private ProductService productService;

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
        
        if(request.getParameter("productid") != null){
            Long productid = Long.parseLong(request.getParameter("productid"));
            Product product = productService.getProductById(productid);
            
            out.print(mapper.writeValueAsString(product));
        }else if(request.getParameter("userid") != null){
            Long userId = Long.parseLong(request.getParameter("userid"));
            List<Product> products = productService.getProductsByUserId(userId);
            out.print(mapper.writeValueAsString(products));
        }else if(request.getParameter("stats") != null){
            Long productsCounted = productService.countProducts();
            out.print("{\"productsCounted\": " + productsCounted + "}");
        }else{
            String description = "";
            Float price = null;
            if(isNumeric(request.getParameter("price"))){
                price = Float.parseFloat(request.getParameter("price"));
            }
            if(request.getParameter("description") != null){
                description = request.getParameter("description");
            }
            List<Product> products = productService.getProducts(description, price);
            
            out.print(mapper.writeValueAsString(products));
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
    
    public boolean isNumeric(String s) {
        if(s != null){
            return s.matches("[-+]?\\d*\\.?\\d+");
        }
        return false;
    }  
    
}
