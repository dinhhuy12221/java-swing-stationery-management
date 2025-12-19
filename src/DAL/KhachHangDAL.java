//package DAL;
//
//import java.util.ArrayList;
//import DTO.KhachHang;
//
//public class KhachHangDAL extends DatabaseAccess{
//	private static String taoMaKhachHang() {
//		String maKhachHang = "";
//		try {
//			getConnection();
//			String s = "TAO_MA_KHACH_HANG";
//			statement = conn.createStatement();
//			resultSet = statement.executeQuery(s);
//			while(resultSet.next()) {
//				maKhachHang = resultSet.getString(1);
//				return maKhachHang;
//			}
//		} catch(Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		return maKhachHang;
//	}
//	
//    public static ArrayList<KhachHang> layDanhSachKhachHang(){
//    	ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
//    	try {
//    		getConnection();
//    		String s = "SELECT MA_KH,HO_TEN,DIA_CHI,SDT,NGAY_SINH FROM KHACH_HANG WHERE TINH_TRANG = 'True'";
//    		statement = conn.createStatement();
//    		resultSet = statement.executeQuery(s);
//    		while(resultSet.next()) {
//    			String maKH = resultSet.getString(1);
//    			String hoTen = resultSet.getString(2);
//    			String diaChi = resultSet.getString(3);
//    			String sdt = resultSet.getString(4);
//    			String ngaySinh = resultSet.getString(5);
//    			KhachHang kh = new KhachHang(maKH, hoTen, diaChi, sdt, ngaySinh);
//    			dskh.add(kh);
//    		}
//    		closeConnection();
//    		return dskh;
//    	} catch(Exception ex) {
//    		System.out.println(ex.getMessage());
//    	}
//    	closeConnection();
//    	return null;
//    }
//    
//
//}
package DAL;

import java.util.ArrayList;

import BLL.KhachHangBLL;
import DTO.KhachHang;

public class KhachHangDAL extends DatabaseAccess{
    public static ArrayList<KhachHang> layDanhSachKhachHang(){
    	ArrayList<KhachHang> dskh = new ArrayList<KhachHang>();
    	try {
    		getConnection();
    		String s = "SELECT MA_KH,HO_TEN,DIA_CHI,SDT, Date_Format(NGAY_SINH, '%d-%m-%Y') FROM `KHACH_HANG` WHERE TINH_TRANG = 1";
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(s);
    		while(resultSet.next()) {
    			String maKH = resultSet.getString(1);
    			String hoTen = resultSet.getString(2);
    			String diaChi = resultSet.getString(3);
    			String sdt = resultSet.getString(4);
    			String ngaySinh = resultSet.getString(5);
//    			double tongChiTieu = resultSet.getDouble(6);
    			KhachHang kh = new KhachHang(maKH, hoTen, diaChi, sdt, ngaySinh);
    			dskh.add(kh);
    		}
    		closeConnection();
    		return dskh;
    	} catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	closeConnection();
    	return dskh;
    }
    
//    public static boolean capNhatChiTieu(String maKH, double tongChiTieu) {
//    	try {
//    		getConnection();
//    		double sum = tongChiTieu;
//    		String s = "SELECT TONG_CHI_TIEU FROM KHACH_HANG WHERE MA_KH='"+maKH+"'";
//    		statement = conn.createStatement();
//    		resultSet = statement.executeQuery(s);
//    		while(resultSet.next()) {
//    			sum += resultSet.getDouble(1);
//    		}
//    		String s1 = "UPDATE KHACH_HANG SET TONG_CHI_TIEU='"+sum+"' WHERE MA_KH = '" + maKH + "'";
//    		statement = conn.createStatement();
//    		int i = statement.executeUpdate(s1);
//    		closeConnection();
//    		if (i > 0) {
//    			return true;
//    		} else {
//    			return false;
//    		}
//    	} catch(Exception ex) {
//    		System.out.println(ex.getMessage());
//    	}
//    	closeConnection();
//    	return false;
//    }
    
    public static String taoMaKhachHang() {
		String maKhachHang = "";
		try {
			getConnection();
			String s = "TAO_MA_KHACH_HANG";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()) {
				maKhachHang = resultSet.getString(1);
				return maKhachHang;
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return maKhachHang;
	}
    
    public static boolean themKhachHang(KhachHang KhachHang) {
    	try {
			getConnection();
			String s = "insert into `khach_hang` values(?,?,?,?,?,1)";
			ps = conn.prepareStatement(s);
			ps.setString(1, KhachHang.getMa());
			ps.setString(2, KhachHang.getHoTen());
			ps.setString(3, KhachHang.getDiaChi());
			ps.setString(4, KhachHang.getSoDienThoai());
			ps.setString(5, KhachHang.getNgaySinh());
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
    
    public static boolean xoaKhachHang(KhachHang KhachHang){
		try {
			getConnection();
			String s;
				s = "UPDATE `KHACH_HANG` SET tinh_trang = 0 where MA_KH = '" + KhachHang.getMa() + "'";
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
    
    public static boolean SuaKhachHang(KhachHang KhachHang) {
		try {
			getConnection();
			String s = "UPDATE `KHACH_HANG` SET HO_TEN = '"+ KhachHang.getHoTen() +"', DIA_CHI = '" + KhachHang.getDiaChi() + "', SDT = '" + KhachHang.getSoDienThoai() + 
					"', NGAY_SINH = '" + KhachHang.getNgaySinh() + "' WHERE MA_KH = '" +KhachHang.getMa()+"'";
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

//	public static int laySoLuongKhachHang() {
//		int i = 0;
//		try {
//			getConnection();
//			String s = "SELECT COUNT(*) FROM KHACH_HANG";
//			statement = conn.createStatement();
//			resultSet = statement.executeQuery(s);
//			while(resultSet.next()) {
//				i = resultSet.getInt(1);
//			}
//		}
//		catch(Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		closeConnection();
//		return i;
//	}
}
    
