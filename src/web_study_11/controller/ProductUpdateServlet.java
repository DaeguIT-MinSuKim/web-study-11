package web_study_11.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import web_study_11.dto.Product;
import web_study_11.service.ProductService;

@WebServlet("/productUpdate.do")
public class ProductUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService service;

    public void init() throws ServletException {
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
            int code = Integer.parseInt(request.getParameter("code").trim());
            Product pdt = service.getProduct(code);
            request.setAttribute("product", pdt);
            request.getRequestDispatcher("product/productUpdate.jsp").forward(request, response);
        } else {
            System.out.println("POST");
            Product pdt = getProduct(request);
            int res = service.modifyProduct(pdt);
            if (res == 1) response.sendRedirect("productList.do");
        }
    }

    private Product getProduct(HttpServletRequest request) throws IOException {
        ServletContext context = getServletContext();
        String path = context.getRealPath("upload");
        String encType = "UTF-8";
        int sizeLimit = 20 * 1024 * 1024;
        
        MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,
                encType, new DefaultFileRenamePolicy());
        String code = multi.getParameter("code");
        String name = multi.getParameter("name");
        int price = Integer.parseInt(multi.getParameter("price"));
        String description = multi.getParameter("description");
        String pictureUrl = multi.getFilesystemName("pictureUrl");
        if (pictureUrl == null) {
            pictureUrl = multi.getParameter("nonmakeImg");
        }
        
        Product pdt = new Product();
        pdt.setCode(Integer.parseInt(code));
        pdt.setName(name);
        pdt.setPrice(price);
        pdt.setDescription(description);
        pdt.setPictureUrl(pictureUrl);
        return pdt;
    }
}