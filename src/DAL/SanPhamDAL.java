package DAL;

import java.util.ArrayList;

import BLL.SanPhamBLL;
import DTO.*;

public class SanPhamDAL extends DatabaseAccess{
	public static String taoMaSanPham() {
		String maSanPham = "";
		try {
			getConnection();
			String s = "TAO_MA_SAN_PHAM";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()) {
				maSanPham = resultSet.getString(1);
				return maSanPham;
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return maSanPham;
	}
	
    public static boolean themSanPham(SanPham sanPham) {
    	try {
			getConnection();
			String s = "INSERT INTO `SAN_PHAM` VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(s);
			ps.setString(1, taoMaSanPham());
			ps.setString(2, sanPham.getTenSanPham());
			ps.setString(3, sanPham.getChatLieu());
			ps.setString(4, sanPham.getDonVi());
			ps.setString(5, sanPham.getLoaiSanPham().getMaLoaiSanPham());
			ps.setString(6, sanPham.getNhaCC().getMaNhaCC());
			ps.setDouble(7, sanPham.getGiaNhap());
			ps.setDouble(8, sanPham.getGiaBan());
			ps.setInt(9, 0);
			ps.setString(10, sanPham.getFilePath());
			ps.setBoolean(11, true);
			int result = ps.executeUpdate();
			if(result > 0) {
				closeConnection();
				return true;
			}
			closeConnection();
    		return false;
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	closeConnection();
		return false;
    }

	public static boolean xoaSanPham(SanPham sanPham){
		try {
			getConnection();
			String s = "UPDATE `SAN_PHAM` SET TINH_TRANG = 0 WHERE MA_SP = '" + sanPham.getMaSanPham() + "'";
			statement = conn.createStatement();
			if(statement.executeUpdate(s) > 0) {
				closeConnection();
				return true;
			}
			else{
				closeConnection();
				return false;
			}
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
			closeConnection();
    		return false;
    	}	
	}

	public static boolean suaSanPham(SanPham sanPham){
		try {
			getConnection();
			String s = "";
//			if(!sanPham.getFilePath().equals("")) {
//				s = "UPDATE SAN_PHAM SET TEN_SP = '"+ sanPham.getTenSanPham() +"', CHAT_LIEU = '" + sanPham.getChatLieu() + "', MA_LOAI_SP = '" + sanPham.getLoaiSanPham().getMaLoaiSanPham() + 
//					"', MA_NCC = '" + sanPham.getNhaCC().getMaNhaCC() + "', GIA_NHAP = " + sanPham.getGiaNhap() + ", GIA_BAN = " + sanPham.getGiaBan() +
//					", ANH_SAN_PHAM = (SELECT  BULKCOLUMN FROM OPENROWSET(BULK  '" + sanPham.getFilePath() +"', SINGLE_BLOB) AS x)   WHERE MA_SP = '" + sanPham.getMaSanPham() + "'";
//			} 
			
				s = "UPDATE `SAN_PHAM` SET TEN_SP = '"+ sanPham.getTenSanPham() +"', CHAT_LIEU = '" + sanPham.getChatLieu() + "', DON_VI_TINH = '" + sanPham.getDonVi() + "', MA_LOAI_SP = '" + sanPham.getLoaiSanPham().getMaLoaiSanPham() + 
					"', MA_NCC = '" + sanPham.getNhaCC().getMaNhaCC() + "', GIA_NHAP = " + sanPham.getGiaNhap() + ", GIA_BAN = " + sanPham.getGiaBan() +
					", ANH_SAN_PHAM = '" + sanPham.getFilePath() +"' WHERE MA_SP = '" + sanPham.getMaSanPham() + "'";
//				else {
//				s = "UPDATE SAN_PHAM SET TEN_SP = '"+ sanPham.getTenSanPham() +"', CHAT_LIEU = '" + sanPham.getChatLieu() + "', MA_LOAI_SP = '" + sanPham.getLoaiSanPham().getMaLoaiSanPham() + 
//						"', MA_NCC = '" + sanPham.getNhaCC().getMaNhaCC() + "', GIA_NHAP = " + sanPham.getGiaNhap() + ", GIA_BAN = " + sanPham.getGiaBan() +
//						", ANH_SAN_PHAM = convert(VARBINARY(MAX),'" + sanPham.getAnhSanPham() +"') WHERE MA_SP = '" + sanPham.getMaSanPham() + "'";
//			}
			statement = conn.createStatement();
			if(statement.executeUpdate(s) > 0) {
				closeConnection();
				return true;
			}
			else{
				closeConnection();
				return false;
			}
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
		closeConnection();
		return false;
	}
	
	public static boolean capNhatSoLuongSP(String maSanPham, int soLuong) {
		boolean checked = false;
		try {
			getConnection();
			int n = 0;
    		String s = "SELECT SO_LUONG FROM `SAN_PHAM` WHERE MA_SP='" + maSanPham + "'";
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(s);
    		while(resultSet.next()) {
    			n = resultSet.getInt(1);
    		}
    		n -= soLuong;
    		String s1 = "UPDATE `SAN_PHAM` SET SO_LUONG=" + n + " WHERE MA_SP = '" + maSanPham + "'";
    		statement = conn.createStatement();
    		int i = statement.executeUpdate(s1);
    		if (i > 0) {
    			checked = true;
    		} 
    	} catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
		closeConnection();
    	if(checked) return true;
    	else return false;
	}
	
	public static int laySoLuongSanPham(String maSP) {
		int i = 0;
		try {
			getConnection();
			String s = "SELECT SO_LUONG FROM `SAN_PHAM` WHERE MA_SP = '" + maSP +"'";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()) {
				i = resultSet.getInt(1);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		closeConnection();
		return i;
	}

	public static ArrayList<SanPham> layDanhSachSanPham(){
		ArrayList<SanPham> danhSachSanPham = new ArrayList<SanPham>();
		try{
			getConnection();
			String s = "SELECT SP.MA_SP, SP.TEN_SP, SP.CHAT_LIEU, SP.DON_VI_TINH,SP.MA_LOAI_SP,LSP.TEN_LOAI_SP, NCC.MA_NCC, NCC.TEN_NCC, SP.GIA_NHAP, SP.GIA_BAN, SP.SO_LUONG, SP.ANH_SAN_PHAM "+
						"FROM `SAN_PHAM` SP INNER JOIN `NHA_CUNG_CAP` NCC " +
						"ON SP.MA_NCC = NCC.MA_NCC INNER JOIN `LOAI_SP` LSP ON SP.MA_LOAI_SP = LSP.MA_LOAI_SP WHERE SP.TINH_TRANG = 1";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()){
				String maSP = resultSet.getString(1);
				String tenSP = resultSet.getString(2);
				String chatLieu = resultSet.getString(3);
				String donVi = resultSet.getString(4);
				String maLoaiSP = resultSet.getString(5);
				String tenLoaiSP = resultSet.getString(6);
				String maNCC = resultSet.getString(7);
				String tenNCC = resultSet.getString(8);
				double giaNhap = resultSet.getDouble(9);
				double giaBan = resultSet.getDouble(10);
				int soLuong = resultSet.getInt(11);
				String fp = resultSet.getString(12);
				LoaiSanPham loaiSP = new LoaiSanPham(maLoaiSP,tenLoaiSP);
				NhaCungCap nhaCC = new NhaCungCap();
				nhaCC.setMaNhaCC(maNCC);nhaCC.setTenNhaCC(tenNCC);
				SanPham sanPham = new SanPham(maSP, tenSP, chatLieu,donVi ,loaiSP, nhaCC, giaNhap, giaBan, soLuong, fp);
				danhSachSanPham.add(sanPham);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		closeConnection();
		return danhSachSanPham;
	}


//	public static boolean xoaSanPham(SanPham sanPham) {
//		boolean checked = false;
//		try {
//			getConnection();
//			String s = "UPDATE SAN_PHAM SET TINH_TRANG = 'False' WHERE MA_SP = '" +sanPham.getMaSanPham()+ "'";
//			statement = conn.createStatement();
//			int result = statement.executeUpdate(s);
//			if(result > 0) checked = true;
//			else checked = false;
//		} catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		closeConnection();
//		return checked;
//	}
}
