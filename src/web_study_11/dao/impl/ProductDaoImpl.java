package web_study_11.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web_study_11.dao.ProductDao;
import web_study_11.dto.Product;

public class ProductDaoImpl implements ProductDao {
    private static final ProductDaoImpl instance = new ProductDaoImpl();

    private Connection con;

    public void setCon(Connection con) {
        this.con = con;
    }

    public static ProductDaoImpl getInstance() {
        return instance;
    }

    private ProductDaoImpl() {}

    @Override
    public List<Product> selectProductByAll() {
        String sql = "SELECT CODE, NAME, PRICE, PICTUREURL, DESCRIPTION FROM PRODUCT";
        try (PreparedStatement pstmt = con.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                ArrayList<Product> list = new ArrayList<>();
                do {
                    list.add(getProduct(rs));
                }while(rs.next());
                return list;
            }
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    @Override
    public int insertProduct(Product pdt) {
        String sql = "insert into product(NAME, PRICE, PICTUREURL, DESCRIPTION) " + "values(?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, pdt.getName());
            pstmt.setInt(2, pdt.getPrice());
            pstmt.setString(3, pdt.getPictureUrl());
            pstmt.setString(4, pdt.getDescription());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

    @Override
    public Product selectProductBycode(int code) {
        String sql = "SELECT CODE, NAME, PRICE, PICTUREURL, DESCRIPTION FROM PRODUCT WHERE CODE = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, code);
            try(ResultSet rs = pstmt.executeQuery()){
                if (rs.next()) {
                    return getProduct(rs);
                }
            }
        }catch (SQLException e) {
            throw new CustomSQLException(e);
        }
        return null;
    }

    private Product getProduct(ResultSet rs) throws SQLException {
        Product pdt =new Product(rs.getInt("CODE"));
        pdt.setName(rs.getString("NAME"));
        pdt.setPrice(rs.getInt("PRICE"));
        pdt.setPictureUrl(rs.getString("PICTUREURL"));
        pdt.setDescription(rs.getString("DESCRIPTION"));
        return pdt;
    }

    @Override
    public int updateProduct(Product pdt) {
        String sql = "UPDATE PRODUCT SET NAME=?, PRICE=?, PICTUREURL=?, DESCRIPTION=? WHERE CODE = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, pdt.getName());
            pstmt.setInt(2, pdt.getPrice());
            pstmt.setString(3, pdt.getPictureUrl());
            pstmt.setString(4, pdt.getDescription());
            pstmt.setInt(5, pdt.getCode());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

    @Override
    public int deleteProduct(int code) {
        String sql = "DELETE FROM PRODUCT WHERE CODE = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, code);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }
    }

}
