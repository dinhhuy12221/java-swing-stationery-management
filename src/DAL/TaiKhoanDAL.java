package DAL;

import java.util.ArrayList;

import DTO.TaiKhoan;
import DTO.ChucVu;
import DTO.NhanVien;

public class TaiKhoanDAL extends DatabaseAccess{
    public static boolean themTaiKhoan(TaiKhoan taiKhoan) {
    	try {
    		getConnection();
    		String s = "INSERT INTO `tai_khoan` VALUES (?,?,?)";
    		ps = conn.prepareStatement(s);
    		ps.setString(1, taiKhoan.getTenDangNhap());
    		ps.setString(2, taiKhoan.getMatKhau());
    		boolean tinhTrang = taiKhoan.getTinhTrang().equals("1") ? true : false;
    		ps.setBoolean(3, tinhTrang);
    		int i = ps.executeUpdate();
    		closeConnection();
    		if(i > 0) return true;
    		else return false;
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	finally {
    		closeConnection();
    	}
    	return false;
    }
    
    public static boolean xoaTaiKhoan(TaiKhoan taiKhoan) {
    	try {
    		getConnection();
    		String s = "DELETE FROM `tai_khoan` WHERE TEN_DANG_NHAP = '" + taiKhoan.getTenDangNhap() + "'";
    		statement = conn.createStatement();
    		int i = statement.executeUpdate(s);
    		closeConnection();
    		if(i > 0) return true;
    		else return false;
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	finally {
    		closeConnection();
    	}
    	return false;
    }
    
    public static boolean suaTaiKhoan(TaiKhoan taiKhoan) {
    	try {
    		getConnection();
    		String s = "UPDATE `tai_khoan` SET MAT_KHAU = ?, TINH_TRANG = ? WHERE TEN_DANG_NHAP = ?";
    		ps = conn.prepareStatement(s);
    		ps.setString(1, taiKhoan.getMatKhau());
    		boolean tinhTrang = taiKhoan.getTinhTrang().equals("1") ? true : false;
    		ps.setBoolean(2, tinhTrang);
    		ps.setString(3, taiKhoan.getTenDangNhap());
    		int i = ps.executeUpdate();
    		closeConnection();
    		if (i > 0) return true;
    		else return false;
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	finally {
    		closeConnection();
    	}
    	return false;
    }
    
    public static ArrayList<TaiKhoan> layDanhSachTaiKhoan(){
    	ArrayList<TaiKhoan> dstk = new ArrayList<TaiKhoan>();
    	try {
    		getConnection();
	    	String s = "SELECT TK.TEN_DANG_NHAP, NV.HO_TEN,CV.MA_CHUC_VU, CV.TEN_CHUC_VU ,TK.MAT_KHAU, TK.TINH_TRANG FROM `tai_khoan` TK, `NHAN_VIEN` NV, CHUC_VU CV WHERE TK.TEN_DANG_NHAP = MA_NV AND CV.MA_CHUC_VU = NV.MA_CHUC_VU";
	    	statement = conn.createStatement();
	    	resultSet = statement.executeQuery(s);
	    	while(resultSet.next()) {
	    		String tenDangNhap = resultSet.getString(1);
	    		String tenNV = resultSet.getString(2);
	    		String maChucVu = resultSet.getString(3);
	    		String tenChucVu = resultSet.getString(4);
	    		String matKhau = resultSet.getString(5);
	    		String tinhTrang;
	    		if (resultSet.getBoolean(6)) tinhTrang = "Mở khóa";
	    		else tinhTrang = "Khóa";
	    		TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap,tenNV,matKhau,new ChucVu(maChucVu,tenChucVu),tinhTrang);
	    		dstk.add(taiKhoan);
	    	}
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	finally{
    		closeConnection();
    	}
    	return dstk;
    }
    
    public static ArrayList<NhanVien> layDanhSachNVChuaCoTK(){
    	ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
    	try {
    		getConnection();
	    	String s = "SELECT NV.MA_NV, NV.HO_TEN, CV.MA_CHUC_VU, CV.TEN_CHUC_VU  FROM `NHAN_VIEN` NV, `CHUC_VU` CV WHERE NV.MA_CHUC_VU = CV.MA_CHUC_VU AND NV.TINH_TRANG = 1 AND NV.MA_NV NOT IN (SELECT TEN_DANG_NHAP FROM `tai_khoan`)";
	    	statement = conn.createStatement();
	    	resultSet = statement.executeQuery(s);
	    	while(resultSet.next()) {
	    		String maNhanVien = resultSet.getString(1);
	    		String tenNhanVien = resultSet.getString(2);
	    		String maChucVu = resultSet.getString(3);
	    		String tenChucVu = resultSet.getString(4);
	    		NhanVien nhanVien = new NhanVien();
	    		nhanVien.setMa(maNhanVien);
	    		nhanVien.setHoTen(tenNhanVien);
	    		nhanVien.setChucVu(new ChucVu(maChucVu,tenChucVu));
	    		dsnv.add(nhanVien);
	    	}
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	finally{
    		closeConnection();
    	}
    	return dsnv;
    }
}
