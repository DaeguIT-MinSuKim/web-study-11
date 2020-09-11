package web_study_11.dao;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import web_study_11.dao.impl.ProductDaoImpl;
import web_study_11.ds.JdbcUtil;
import web_study_11.dto.Product;

public class ProductDaoTest {
    private static Connection con;
    private static ProductDaoImpl dao;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        con = JdbcUtil.getConnection();
        dao = ProductDaoImpl.getInstance();
        dao.setCon(con);
    }

    @Test
    public void testSelectProductByAll() {
        System.out.println("testSelectProductByAll()");
        List<Product> list = dao.selectProductByAll();
        Assert.assertNotNull(list);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void testInsertProduct() {
        System.out.println("testInsertProduct()");
        Product pdt = new Product("java", 50000, "test", "test.jpg");
        int res = dao.insertProduct(pdt);
        Assert.assertEquals(1, res);
    }

    @Test
    public void testSelectProductBycode() {
        System.out.println("testSelectProductBycode()");
        Product pdt = dao.selectProductBycode(1);
        Assert.assertNotNull(pdt);
        System.out.println(pdt);
    }

    @Test
    public void testUpdateProduct() {
        System.out.println("testUpdateProduct()");
        Product pdt = dao.selectProductBycode(1);
        pdt.setName("정의란무엇인가?");
        pdt.setPrice(50000);
        pdt.setPictureUrl("justice.jpg");
        pdt.setDescription("정의");
        int res = dao.updateProduct(pdt);
        Assert.assertNotNull(pdt);
        System.out.println(pdt);
    }
    
    @Test
    public void testDeleteProduct() {
        System.out.println("testDeleteProduct()");
        int res = dao.deleteProduct(1);
        Assert.assertEquals(1, res);
    }

}
