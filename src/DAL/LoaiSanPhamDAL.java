package DAL;

import java.util.ArrayList;

import DTO.LoaiSanPham;

public class LoaiSanPhamDAL extends DatabaseAccess{
	
	public static boolean themLoaiSP(LoaiSanPham lsp) {
		try {
			getConnection();
			String s = "INSERT INTO `LOAI_SP` VALUES(?,?)";
			ps = conn.prepareStatement(s);
			ps.setString(1, lsp.getMaLoaiSanPham());
			ps.setString(2, lsp.getTenLoaiSanPham());
			int i = ps.executeUpdate();
			if (i > 0) {
				closeConnection();
				return true;
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		closeConnection();
		return false;
	}
	
	public static boolean xoaLoaiSP(LoaiSanPham lsp) {
		try {
			getConnection();
			String s = " UPDATE `SAN_PHAM` SET MA_LOAI_SP = NULL WHERE MA_LOAI_SP='"+lsp.getMaLoaiSanPham()+"';DELETE FROM `LOAI_SP` WHERE MA_LOAI_SP = '" +lsp.getMaLoaiSanPham()+ "'";
			statement = conn.createStatement();
			int i = statement.executeUpdate(s);
			if(i > 0) {
				closeConnection();
				return true;
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}	
		closeConnection();
		return false;
	}
	
//	public static boolean suaLoaiSP(LoaiSanPham lsp) {
//		try {
//			getConnection();
//			String s = "UPDATE LOAI_SP LSP, SAN_PHAM SP SET SP.MA_LOAI_SP = '" ++ "' AND LSP.MA_LOAI_SP = '" ++ "' WHERE "
//		} catch(Exception ex) {
//			System.out.println(ex.getMessage());
//		}	
//	}
	
	public static ArrayList<LoaiSanPham> layDanhSachLoaiSP(){
		ArrayList<LoaiSanPham> danhSachLoaiSP = new ArrayList<LoaiSanPham>();
		try {
			getConnection();
			String s = "SELECT * FROM `LOAI_SP`";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()) {
				String maLoaiSP = resultSet.getString(1);
				String tenLoaiSP = resultSet.getString(2);
				LoaiSanPham loaiSP =  new LoaiSanPham(maLoaiSP,tenLoaiSP);
				danhSachLoaiSP.add(loaiSP);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		closeConnection();
		return danhSachLoaiSP;
	}
}
