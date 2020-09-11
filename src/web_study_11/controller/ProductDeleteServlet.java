package web_study_11.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_study_11.dto.Product;
import web_study_11.service.ProductService;

@WebServlet("/productDelete.do")
public class ProductDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService service;

    public void init(ServletConfig config) throws ServletException {
        service = new ProductService();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            System.out.println("GET");
            int code = Integer.parseInt(request.getParameter("code").trim());
            Product pdt = service.getProduct(code);
            request.setAttribute("product", pdt);
            request.getRequestDispatcher("product/productDelete.jsp").forward(request, response);
        } else {
            System.out.println("POST");
            int code = Integer.parseInt(request.getParameter("code").trim());
            int res = service.removeProduct(code);
            if (res == 1) response.sendRedirect("productList.do");
        }
    }
}