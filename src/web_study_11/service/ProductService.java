package web_study_11.service;

import java.util.List;

import web_study_11.dao.impl.ProductDaoImpl;
import web_study_11.ds.JndiDS;
import web_study_11.dto.Product;

public class ProductService {
    private ProductDaoImpl dao = ProductDaoImpl.getInstance();

    public ProductService() {
        dao.setCon(JndiDS.getConnection());
    }
    
    public List<Product> productLists(){
        return dao.selectProductByAll();
    }
    
    public int addProduct(Product pdt) {
        return dao.insertProduct(pdt);
    }
    
    public Product getProduct(int code) {
        return dao.selectProductBycode(code);
    }
    
    public int modifyProduct(Product pdt) {
        return dao.updateProduct(pdt);
    }
    
    public int removeProduct(int code) {
        return dao.deleteProduct(code);
    }
}
