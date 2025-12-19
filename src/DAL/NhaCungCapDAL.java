
//	public static ArrayList<NhaCungCap> layDanhSachNhaCC(){
//		ArrayList<NhaCungCap> danhSachNCC = new ArrayList<NhaCungCap>();
//		try {
//			getConnection();
//			String s = "SELECT * FROM NHA_CUNG_CAP";
//			statement = conn.createStatement();
//			resultSet = statement.executeQuery(s);
//			while(resultSet.next()) {
//				String maNhaCC = resultSet.getString(1);
//				String tenNhaCC = resultSet.getString(2);
//				String diaChi = resultSet.getString(3);
//				String sdt = resultSet.getString(4);
//				NhaCungCap nhaCC = new NhaCungCap(maNhaCC,tenNhaCC, diaChi,sdt);
//				danhSachNCC.add(nhaCC);
//			}
//		}
//		catch(Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		closeConnection();
//		return danhSachNCC;
//	}
	
package DAL;
import java.util.*;//arraylist hay vector

import javax.swing.JOptionPane;

import java.sql.*;
import DTO.NhaCungCap;
import DTO.SanPham;

//DAL CÓ KẾT NỐI CSDL VÀ CÁC HÀM THAO TÁC VỚI CSDL
public class NhaCungCapDAL extends DatabaseAccess{
	
	public static String taoMaNhaCC() {
		String maNhaCC = "";
		try {
			getConnection();
			String s = "TAO_MA_NCC";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()) {
				maNhaCC = resultSet.getString(1);
				return maNhaCC;
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return maNhaCC;
	}
	
	//HÀM LẤY DANH SÁCH CÁC NHÀ CC TỪ CSDL
	public static ArrayList<NhaCungCap> layDanhSachNhaCC(){
		//Array list cũng là mảng động giống vector
		ArrayList<NhaCungCap> danhSachNCC = new ArrayList<NhaCungCap>();
		try {
			getConnection();//cài kết nối cơ sở dữ liệu, bên databaseAccess
			String s = "SELECT * FROM `NHA_CUNG_CAP` WHERE TINH_TRANG = 1";	//
			statement = conn.createStatement();//khỏi gọi biến
			resultSet = statement.executeQuery(s);
			while(resultSet.next()) {
				NhaCungCap temp=new NhaCungCap();
				temp.setMaNhaCC(resultSet.getString(1));
				temp.setTenNhaCC(resultSet.getString(2));
				temp.setDiaChi(resultSet.getString(3));
				temp.setSoDienThoai(resultSet.getString(4));
				danhSachNCC.add(temp);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			closeConnection();	//sau khi lấy dữ liệu thì đóng kết nối
		}
		return danhSachNCC;
	}
	
	//HÀM LẤY DANH SÁCH SẢN PHẨM CỦA 1 NHÀ CUNG CẤP
	public static ArrayList<SanPham> laySanPhamCuaNhaCC(String mancc){
		ArrayList<SanPham> danhsachSP=new ArrayList<SanPham>();
		try {
			getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select ma_ncc, ma_sp, ten_sp from `san_pham` where ma_ncc='"+mancc+"'");
			while(rs.next()) {
				SanPham temp = new SanPham();
				temp.setMaSanPham(rs.getString(2));
				temp.setTenSanPham(rs.getString(3));
				danhsachSP.add(temp);
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			closeConnection();
		}
		return danhsachSP;
	}
	
	//HÀM LẤY SỐ LƯỢNG SẢN PHẨM
	public static int laysoluongspcuanhacc(String mancc){
		int sl=0;
		try {
			getConnection();//cài kết nối cơ sở dữ liệu, bên databaseAccess
			Statement temp=conn.createStatement();
			ResultSet rs=temp.executeQuery("select count(ma_sp) from `san_pham` where ma_ncc= '"+mancc+"'");
			rs.next();//di chuyển tới vị trí đầu
			sl=rs.getInt(1);
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			closeConnection();
		}
		return sl;
	}
	
	//HÀM THÊM NHÀ CUNG CẤP
	public static boolean themnhacungcap(NhaCungCap ncc) {
		boolean rs = false;
		try {
			getConnection();
			String sql = "insert into `nha_cung_cap` values(?,?,?,?,1)";
			ps = conn.prepareStatement(sql);//ps là prepared statement, TRIM XÓA HẾT KHOẢNG TRẮNG	
			ps.setString(1, ncc.getMaNhaCC());
			ps.setString(2, ncc.getTenNhaCC().trim());
			ps.setString(3, ncc.getDiaChi().trim());
			ps.setString(4, ncc.getSoDienThoai().trim());
			if(ps.executeUpdate()>=1) {
				rs = true;
			}
		} catch(Exception e){
			System.out.println(e);
		} finally {
			closeConnection();
		}
		return rs;
	}
	
	//HÀM XÓA NHÀ CUNG CẤP
	public static int xoanhacungcap(String mancc) {
		int dem=0;
		try {
			getConnection();
			Statement stmt = conn.createStatement();
			String sql = "update `nha_cung_cap` set tinh_trang = 'False' where ma_ncc='"+mancc+"'";
			dem = stmt.executeUpdate(sql);
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			closeConnection();
		}
		return dem;
	}
	
	//HÀM SỬA THÔNG TIN NHÀ CUNG CẤP
	public static boolean suanhacungcap(NhaCungCap ncc) {
		boolean dem=false;
		try {
			getConnection();	//HÀM TRIM() XÓA HẾT KHOẢNG TRẮNG
			Statement stmt = conn.createStatement();
			String sql = "update `nha_cung_cap` set ten_ncc='"+ncc.getTenNhaCC()+"', dia_chi='"+ncc.getDiaChi()+"', sdt='"+ncc.getSoDienThoai()+"' where ma_ncc='"+ncc.getMaNhaCC()+"'";
			int temp = stmt.executeUpdate(sql);
			if(temp>=1)
				dem=true;
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			closeConnection();
		}
		return dem;
	}
	
	//HÀM TÌM KIẾM THÔNG TIN THEO MÃ NHÀ CUNG CẤP
	public static ArrayList<NhaCungCap> timtheomancc(String mancc, String tenncc) {
		ArrayList<NhaCungCap> arr = new ArrayList<NhaCungCap>();
		try {
			getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from `nha_cung_cap` where ma_ncc='"+mancc+"' and ten_ncc='"+tenncc+"'");
			if(mancc.equals(""))
				rs = stmt.executeQuery("select * from `nha_cung_cap` where ten_ncc='"+tenncc+"'");
			if(tenncc.equals(""))
				rs = stmt.executeQuery("select * from `nha_cung_cap` where ma_ncc='"+mancc+"'");
			
			while(rs.next()) {
				NhaCungCap temp=new NhaCungCap();
				temp.setMaNhaCC(rs.getString(1));
				temp.setTenNhaCC(rs.getString(2));
				temp.setDiaChi(rs.getString(3));
				temp.setSoDienThoai(rs.getString(4));
				arr.add(temp);
			}
		} catch(Exception e) {
			System.out.println(e);
		} finally{
			closeConnection();
		}
		return arr;
	}

}
