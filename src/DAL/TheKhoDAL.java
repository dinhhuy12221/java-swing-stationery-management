package DAL;

import java.util.ArrayList;

import DTO.ChiTietPhieu;
import DTO.TheKho;

public class TheKhoDAL extends DatabaseAccess{
	
	public static boolean capNhatTheKho(String maPhieu, String ngayLap,ChiTietPhieu ctp) {
		boolean checked = false;
		try {
			getConnection();
			String s = "INSERT INTO `THE_KHO` VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(s);
			ps.setString(1, ctp.getSanPham().getMaSanPham());
			ps.setString(2, maPhieu);
			ps.setString(3, ngayLap);
			ps.setDouble(4, ctp.getThanhTien());
			ps.setInt(5, ctp.getSanPham().getSoLuong());
			ps.setInt(6, SanPhamDAL.laySoLuongSanPham(ctp.getSanPham().getMaSanPham()));
			ps.executeUpdate();
			checked = true;
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		closeConnection();
		if(checked) return true;
		else return false;
	}
	
	public static ArrayList<TheKho> layTheKho(String maSP){
		ArrayList<TheKho> dstk = new ArrayList<TheKho>();
		try {
			getConnection();
			String s = "SELECT MA_SP, MA_PHIEU, DATE_FORMAT(NGAY_LAP, '%Y-%m-%d %H:%i:%s'), TONG_GIA_TRI, SO_LUONG, TON_CUOI FROM `THE_KHO` WHERE MA_SP = '" +
		    maSP + "' ORDER BY NGAY_LAP DESC";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()) {
				TheKho tk = new TheKho();
				tk.setMaSP(resultSet.getString(1));
				tk.setMaPhieu(resultSet.getString(2));
				tk.setNgayLap(resultSet.getString(3));
				tk.setTongGiaTri(resultSet.getDouble(4));
				tk.setSoLuong(resultSet.getInt(5));
				tk.setTonCuoi(resultSet.getInt(6));
				dstk.add(tk);
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		closeConnection();
		return dstk;
	}
}
