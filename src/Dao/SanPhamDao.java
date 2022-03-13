/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

/**
 *
 * @author Toàn
 */
import Model.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class SanPhamDao {

    public int loGin(String name, String pass) {
        Connection connect = Connect.getJDBCConection();
        String sql = "SELECT * FROM user WHERE name=?";
        PreparedStatement preparedStatement;
        try {
            
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                if (rs.getString("pass").equals(pass)) {
                    return 1;
                   
                } else {
                    return 0;
                }
            } else {
                return 2;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }       
    }
    
    public  List<SanPham> getAllSanPham(){
        List<SanPham> sanPhams = new ArrayList<SanPham>();
        Connection connect = Connect.getJDBCConection();
        String sql = "SELECT * FROM sanpham";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connect.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setIdSp(rs.getInt("idSp"));
                sanPham.setLoaiSp(rs.getInt("idLoaiSp"));
                sanPham.setTenSp(rs.getString("tenSp"));
                sanPham.setGiaSp(rs.getString("giaSP"));
                sanPhams.add(sanPham);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sanPhams;
    }
    
    public String getLoaiSP(int idLoai){
        Connection connect = Connect.getJDBCConection();
        String sql = "SELECT * FROM loaisp WHERE idLoai=?";
        
        PreparedStatement preparedStatement;
        try {       
            preparedStatement = connect.prepareStatement(sql);   
            preparedStatement.setString(1, String.valueOf(idLoai));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getString("loai");
            }
         
            
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public List<String> getAllLoai(){
        Connection connect = Connect.getJDBCConection();
        List<String> loais = new ArrayList<String>();
        String sql = "SELECT * FROM loaisp";
        PreparedStatement preparedStatement;
        try { 
            preparedStatement = connect.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String loai = new String();
                loai=rs.getString("loai");
                loais.add(loai);
            }
            return loais;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loais;
    }
    
    public int themSP(String tenSP,String loaiSP,String giaSP){
        Connection connect = Connect.getJDBCConection();
        String sql = " INSERT INTO sanpham( idLoaiSp, tenSp, giaSP)"
                + " VALUE(?,?,?) ";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, getIdLoaiSP(loaiSP));
            preparedStatement.setString(2, tenSP);
            preparedStatement.setString(3, giaSP);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            connect.close();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int getIdLoaiSP(String loai){
        Connection connect = Connect.getJDBCConection();
        String sql = "SELECT * FROM loaisp WHERE loai=?";
        
        PreparedStatement preparedStatement;
        try {       
            preparedStatement = connect.prepareStatement(sql);   
            preparedStatement.setString(1, String.valueOf(loai));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getInt("idLoai");
            }
                 
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
    
    public int xoa(int idSP){
        Connection connect = Connect.getJDBCConection();

        String sql = "delete from sanpham where idSp =?";
        PreparedStatement preparedStatement;
       
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, idSP);
            int rs = preparedStatement.executeUpdate();
            connect.close();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return 0;
    }
    
    public SanPham getSP(int idSP){
        Connection connect = Connect.getJDBCConection();
        String sql = "SELECT * FROM sanpham WHERE idSp=?";
        SanPham sanPham = new SanPham();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setInt(1, idSP);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                
                sanPham.setIdSp(rs.getInt("idSp"));
                sanPham.setLoaiSp(rs.getInt("idLoaiSp"));
                sanPham.setTenSp(rs.getString("tenSp"));
                sanPham.setGiaSp(rs.getString("giaSP"));
               
            }
             connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sanPham;
    }
    
    public int addSp(int idSp,String tenSp, String giaSp){
        Connection connect = Connect.getJDBCConection();
        String sql = " UPDATE sanpham SET tenSp=?, giaSP=? WHERE idSp=?";
           

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, tenSp);
            preparedStatement.setString(2, giaSp);
            preparedStatement.setInt(3, idSp);
            int rs =preparedStatement.executeUpdate();
            
            connect.close();
            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
