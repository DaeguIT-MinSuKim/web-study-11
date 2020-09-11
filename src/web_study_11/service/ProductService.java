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
}
