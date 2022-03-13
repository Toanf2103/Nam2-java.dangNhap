/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Dao.SanPhamDao;
import Model.SanPham;
import java.util.List;

/**
 *
 * @author Toàn
 */
public class SanPhamService {
    private SanPhamDao sanPhamDao;
     public SanPhamService() {
        sanPhamDao= new SanPhamDao();
    }
    public int loGin(String name,String pass){
        return sanPhamDao.loGin(name,pass);
    }
    public List<SanPham> getAllSanPham(){
        return sanPhamDao.getAllSanPham();
    }
    public String getLoaiSP(int idLoai){
        return sanPhamDao.getLoaiSP(idLoai);
    }
    public List<String> getAllLoai(){
        return sanPhamDao.getAllLoai();
    }
    public int themSP(String tenSP,String loaiSP,String giaSP){
         return sanPhamDao.themSP(tenSP,loaiSP,giaSP);
    }
    public int xoa(int idsp){
        return sanPhamDao.xoa(idsp);
    }
    public SanPham getSP(int idsp){
        return sanPhamDao.getSP(idsp);
    }
    public int addSp(int idSp,String tenSp,String giaSp){
        return sanPhamDao.addSp(idSp,tenSp,giaSp);
    }
}
