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

@WebServlet("/productWrite.do")
public class ProductWriteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService service= new ProductService();
    
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
            
            request.getRequestDispatcher("product/productWrite.jsp").forward(request, response);
        } else {
            System.out.println("POST");
            ServletContext context = getServletContext();
            String path = context.getRealPath("upload");
            String encType = "UTF-8";
            int sizeLimit = 20 * 1024 * 1024;
            MultipartRequest multi = new MultipartRequest(request, path, sizeLimit,
                    encType, new DefaultFileRenamePolicy());
            String name = multi.getParameter("name");
            int price = Integer.parseInt(multi.getParameter("price"));
            String description = multi.getParameter("description");
            String pictureUrl = multi.getFilesystemName("pictureUrl");
            Product pdt = new Product();
            pdt.setName(name);
            pdt.setPrice(price);
            pdt.setDescription(description);
            pdt.setPictureUrl(pictureUrl);
            
            int res = service.addProduct(pdt);
            if (res == 1) response.sendRedirect("productList.do");
        }
    }
}