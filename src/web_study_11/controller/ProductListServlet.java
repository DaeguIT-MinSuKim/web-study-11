package web_study_11.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web_study_11.dto.Product;
import web_study_11.service.ProductService;

@WebServlet("/productList.do")
public class ProductListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService service;

    public void init(ServletConfig config) throws ServletException {
        service = new ProductService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            System.out.println("GET");
            List<Product> list = service.productLists();
            list.stream().forEach(System.out::println);
            request.setAttribute("list", list);
            request.getRequestDispatcher("product/productList.jsp").forward(request, response);
        } else {
            System.out.println("POST");
        }
    }
}
