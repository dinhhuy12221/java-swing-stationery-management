package DAL;

import java.util.ArrayList;

import DTO.ChucVu;
import DTO.NhanVien;

public class NhanVienDAL extends DatabaseAccess{
//	public static ArrayList<NhanVien> layDanhSachNhanVien(){
//    	ArrayList<NhanVien> dsnv = new ArrayList<NhanVien>();
//    	try {
//    		getConnection();
//    		String s = "SELECT MA_NV,HO_TEN,DIA_CHI,SDT,NGAY_SINH,MA_CHUC_VU, LUONG FROM `NHAN_VIEN` WHERE TINH_TRANG = 'True'";
//    		statement = conn.createStatement();
//    		resultSet = statement.executeQuery(s);
//    		while(resultSet.next()) {
//    			String maKH = resultSet.getString(1);
//    			String hoTen = resultSet.getString(2);
//    			String diaChi = resultSet.getString(3);
//    			String sdt = resultSet.getString(4);
//    			String ngaySinh = resultSet.getString(5);
//    			ChucVu chucVu = new ChucVu();
//    			chucVu.setMaChucVu(resultSet.getString(6));
//    			double luong = resultSet.getDouble(7);
//    			NhanVien nv = new NhanVien(maKH, hoTen, diaChi, sdt, ngaySinh, chucVu, luong);
//    			dsnv.add(nv);
//    		}
//    		closeConnection();
//    		return dsnv;
//    	} catch(Exception ex) {
//    		System.out.println(ex.getMessage());
//    	}
//    	closeConnection();
//    	return null;
//    }
	
	private static String taoMaNhanVien() {
		String maNhanVien = "";
		try {
			getConnection();
			String s = "TAO_MA_NHAN_VIEN";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()) {
				maNhanVien = resultSet.getString(1);
				return maNhanVien;
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return maNhanVien;
	}


	public static boolean themNhanVien(NhanVien NhanVien) {
    	try {
			getConnection();
			String s = "INSERT INTO `NHAN_VIEN` VALUES(?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(s);
			ps.setString(1, taoMaNhanVien());
			ps.setString(2, NhanVien.getHoTen());
			ps.setString(3, NhanVien.getDiaChi());
			ps.setString(4, NhanVien.getSoDienThoai());
			ps.setString(5, NhanVien.getNgaySinh());
			ps.setString(6, NhanVien.getChucVu().getMaChucVu());
			ps.setDouble(7, NhanVien.getLuong());
			ps.setString(8, NhanVien.getAnhFilePath());
			ps.setBoolean(9, true);
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
	public static boolean xoaNhanVien(NhanVien NhanVien){
		try {
			getConnection();
			String s;
				s = "UPDATE `NHAN_VIEN` SET TINH_TRANG = 0 WHERE MA_NV = '" + NhanVien.getMa() + "'";
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
//	public static int laySoLuongNhanVien() {
//		int i = 0;
//		try {
//			getConnection();
//			String s = "SELECT COUNT(*) FROM `NHAN_VIEN`";
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
	public static ArrayList<NhanVien> layDanhSachNhanVien(){
		ArrayList<NhanVien> danhSachNhanVien = new ArrayList<NhanVien>();
		try{
			getConnection();
			String s = "SELECT MA_NV, HO_TEN, DIA_CHI, SDT, DATE_FORMAT(NGAY_SINH, '%d-%m-%Y'), A.MA_CHUC_VU, TEN_CHUC_VU, LUONG, ANH_NHAN_VIEN "+
						"FROM `NHAN_VIEN` AS A INNER JOIN `CHUC_VU` AS B ON A.MA_CHUC_VU=B.MA_CHUC_VU "+
					    "WHERE TINH_TRANG = 1";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()){
				String manv = resultSet.getString(1);
				String hoten = resultSet.getString(2);
				String diachi = resultSet.getString(3);
				String sdt = resultSet.getString(4);
				String ngaysinh = resultSet.getString(5);
				String machucvu = resultSet.getString(6);
				String tenchucvu = resultSet.getString(7);
				double luong = Double.valueOf(resultSet.getString(8));
				String anhnhanvien = resultSet.getString(9);
				ChucVu chucVu = new ChucVu();
				chucVu.setMaChucVu(machucvu);chucVu.setTenChucVu(tenchucvu);
				
				NhanVien nhanVien = new NhanVien(manv, hoten, diachi, sdt, ngaysinh, chucVu, luong, anhnhanvien);
				danhSachNhanVien.add(nhanVien);
				
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		closeConnection();
		return danhSachNhanVien;
	}
	public static boolean SuaNhanVien(NhanVien nhanvien) {
		try {
			getConnection();
			String s = "";
			s = "UPDATE `NHAN_VIEN` SET HO_TEN = '"+ nhanvien.getHoTen() +"', DIA_CHI = '" + nhanvien.getDiaChi() + "', SDT = '" + nhanvien.getSoDienThoai() + 
				"', NGAY_SINH = '" + nhanvien.getNgaySinh() + "', MA_CHUC_VU = '" + nhanvien.getChucVu().getMaChucVu() + "', LUONG = " + nhanvien.getLuong() +", ANH_NHAN_VIEN = '"+nhanvien.getAnhFilePath()+"'  WHERE MA_NV = '" + nhanvien.getMa() + "'";
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
}
