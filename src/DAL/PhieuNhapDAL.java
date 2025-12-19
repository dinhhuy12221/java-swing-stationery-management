package DAL;
import java.util.*;
import DTO.PhieuNhap;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.ChiTietPhieu;
import DTO.SanPham;
import java.sql.*;
import java.sql.Date;


public class PhieuNhapDAL extends DatabaseAccess{
	//LẤY DANH SÁCH PHIẾU NHẬP TỪ CSDL
    public static ArrayList<PhieuNhap> laydanhsachphieunhap(){
    	ArrayList<PhieuNhap> DSPN = new ArrayList<PhieuNhap>();
    	try {
    		getConnection();
    		Statement stmt = conn.createStatement();
    		String sql = "select * from `phieu_nhap`";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()) {
    			String mapn = rs.getString(1);
    			String manv = rs.getString(2);
    			String ngaylap = rs.getString(3);
    			double tongtien = rs.getInt(4);
    			
    			//do vị trí thứ 2 và 3 là 1 đối tượng nên phải tạo riêng
    			NhanVien nv = new NhanVien();
    			nv.setMa(manv);
    			PhieuNhap pn = new PhieuNhap(mapn, nv, PhieuNhapDAL.laychitietphieunhap(mapn), ngaylap, tongtien);
    			DSPN.add(pn);
    		}
    	} catch(Exception e) {
    		System.out.println(e);
    	} finally {
    		closeConnection();
    	}
    	
    	return DSPN;
    }
    
    //LẤY CHI TIẾT PHIẾU NHẬP
    public static ArrayList<ChiTietPhieu> laychitietphieunhap(String mapn){
    	ArrayList<ChiTietPhieu> CTPN = new ArrayList<ChiTietPhieu>();
    	try {
    		getConnection();
    		Statement stmt = conn.createStatement();
    		String sql = "select * from ct_`phieu_nhap` where ma_pn='"+mapn+"'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()) {
    			ChiTietPhieu ctpn = new ChiTietPhieu();
    			
    			SanPham sp = new SanPham();
    			sp.setMaSanPham(rs.getString(2));
    			sp.setGiaNhap(rs.getDouble(3));
    			sp.setSoLuong(rs.getInt(4));
    			ctpn.setSanPham(sp);
    			ctpn.setThanhTien(rs.getDouble(5));
    			CTPN.add(ctpn);
    		}
    	} catch(Exception e) {
    		System.out.println(e);
    	} finally {
    		closeConnection();
    	}
    	return CTPN;
    }
    
    		//CHO KHUNG THÊM SP
    //LẤY TOÀN BỘ SẢN PHẨM HIỆN CÓ
    public static ArrayList<SanPham> laysanphamhienco(String mancc){
    	ArrayList<SanPham> sphc = new ArrayList<SanPham>();
    	try {
    		getConnection();
    		Statement stmt = conn.createStatement();
    		String sql = "select ma_sp, ten_sp, gia_nhap, so_luong from `san_pham` where ma_ncc='"+mancc+"'";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		while(rs.next()) {
    			SanPham sp = new SanPham();
    			
    			sp.setMaSanPham(rs.getString(1));
    			sp.setTenSanPham(rs.getString(2));
    			sp.setGiaNhap(rs.getDouble(3));
    			sp.setSoLuong(rs.getInt(4));
    			sphc.add(sp);
    		}
    	} catch(Exception e) {
    		System.out.println(e);
    	} finally {
    		closeConnection();
    	}
    	return sphc;
    }
    
    //////////////////////////
    
    public static String taomaphieunhap() {
		String maPhieuNhap = "";
		try {
			getConnection();
			String s = "TAO_MA_PHIEU_NHAP";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()) {
				maPhieuNhap = resultSet.getString(1);
				return maPhieuNhap;
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return maPhieuNhap;
	}
    
    //THÊM PHIẾU NHẬP
    public static boolean themphieunhap(PhieuNhap pn) {
    	boolean rs = false;
    	try {
    		getConnection();
    		Statement stmt = conn.createStatement();
    		
    		String sql = "insert into `phieu_nhap` values(?,?,?,?)";//tạo phiếu nhập
    		ps = conn.prepareStatement(sql);//ps là prepared statement, TRIM XÓA HẾT KHOẢNG TRẮNG
    		ps.setString(1, pn.getMaPhieu());
//    		ps.setString(2, pn.getNhaCC().getMaNhaCC().trim());
    		ps.setString(2, pn.getNhanVien().getMa().trim());
    		ps.setString(3, pn.getNgayLap());
    		ps.setDouble(4, pn.getTongTien());
    		if(ps.executeUpdate()>=1) {
				rs = true;
			}
    	} catch(Exception e) {
    		System.out.println(e);
    	} finally {
    		closeConnection();
    	}
    	return rs;
    }
    
    //XÓA PHIẾU NHẬP
    public static int xoaphieunhap(String mapn) {
		int dem=0;
		try {
			getConnection();
			Statement stmt = conn.createStatement();
			String xoact = "delete from `ct_phieu_nhap where ma_pn='"+mapn+"'";
			int temp = stmt.executeUpdate(xoact);
			String sql = "delete from `phieu_nhap` where ma_pn='"+mapn+"'";
			dem = stmt.executeUpdate(sql);
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			closeConnection();
		}
		return dem;
	}
    
    //SỬA PHIẾU NHẬP
    public static boolean suaphieunhap(PhieuNhap pn) {
    	boolean dem =false;
    	try {
    		getConnection();
    		Statement stmt = conn.createStatement();
    		String sql ="update `phieu_nhap` set ma_nv='"
    					+ pn.getNhanVien().getMa() +"', ngay_lap='"
    					+ pn.getNgayLap()+"', tong_tien='"
    					+ pn.getTongTien()+"' where ma_pn='"+pn.getMaPhieu()+"'";
    		int temp = stmt.executeUpdate(sql);
    		if(temp>=1)
    			dem = true;
    	} catch(Exception e) {
    		System.out.println(e);
    	} finally {
    		closeConnection();
    	}
    	return dem;
    }
    
    //TÌM KIẾM PHIẾU NHẬP
    public static ArrayList<PhieuNhap> timkiemphieunhap(String mapn, String ngaytao){
    	ArrayList<PhieuNhap> arr = new ArrayList<PhieuNhap>();
    	try {
    		getConnection();
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("select * from `phieu_nhap` where ma_pn='"+mapn+"' and ngay_lap='"+ngaytao+"'");
			if(mapn.equals(""))
				rs = stmt.executeQuery("select * from `phieu_nhap` where ngay_lap='"+ngaytao+"'");
			if(ngaytao.equals(""))
				rs = stmt.executeQuery("select * from `phieu_nhap` where ma_pn='"+mapn+"'");
			
			while(rs.next()) {
				String ma_pn = rs.getString(1);
//				String ma_ncc = rs.getString(2);
				String ma_nv = rs.getString(2);
				String ngay_lap = rs.getString(3);
				Double tong_tien = rs.getDouble(4);
				//do vị trí thứ 2 và 3 là 1 đối tượng nên phải tạo riêng
    			NhaCungCap ncc = new NhaCungCap();
    			NhanVien nv = new NhanVien();
				PhieuNhap temp = new PhieuNhap(ma_pn,nv,PhieuNhapDAL.laychitietphieunhap(mapn),ngay_lap,tong_tien);
				arr.add(temp);
			}
    	} catch(Exception e) {
    		System.out.println(e);
    	} finally {
    		closeConnection();
    	}
    	return arr;
    }
    
    		//PHẦN CHI TIẾT PHIẾU NHẬP
    //THÊM CHI TIẾT PHIẾU NHẬP
    public static boolean themchitietphieunhap(ChiTietPhieu pn, String mapn) {
    	boolean rs = false;
    	try {
    		getConnection();
    		String sql = "insert into `ct_phieu_nhap values(?,?,?,?,?)";
    		ps = conn.prepareStatement(sql);//ps là prepared statement, TRIM XÓA HẾT KHOẢNG TRẮNG
    		
    		ps.setString(1, mapn);
    		ps.setString(2, pn.getSanPham().getMaSanPham().trim());
    		ps.setDouble(3, pn.getSanPham().getGiaNhap());
    		ps.setInt(4, pn.getSanPham().getSoLuong());
    		ps.setDouble(5, pn.getThanhTien());
    		if(ps.executeUpdate()>=1) {
				rs = true;
			}
    	} catch(Exception e) {
    		System.out.println(e);
    	} finally {
    		closeConnection();
    	}
    	return rs;
    }
    //CẬP NHẬP THÀNH TIỀN THÀNH TIỀN CỦA PHIẾU NHẬP
    public static boolean capnhapthanhtien(String mapn) {
    	boolean rs = false;
    	try {
    		getConnection();
    		//sau khi đã thêm vào ct phiếu nhập
    		String sql="select sum(thanh_tien) from `ct_phieu_nhap` where ma_pn='"+mapn+"'";
    		Statement stmt = conn.createStatement();
    		ResultSet result = stmt.executeQuery(sql);
    		result.next();
    		double tongtien = result.getDouble(1);
    		sql="update `phieu_nhap` set tong_tien="+tongtien+" where ma_pn='"+mapn+"'";
    		int kq=stmt.executeUpdate(sql);
    		if(kq==1)
    			rs=true;
    	} catch(Exception e) {
    		System.out.println(e);
    	} finally {
    		closeConnection();
    	}
    	return rs;
    }
    
    //CẬP NHẬP SỐ LƯỢNG CỦA SẢN PHẨM
    public static boolean capnhapsoluongsanpham(String mapn, String masp) {
    	boolean rs = false;
    	try {
    		getConnection();
    		String sql="select so_luong from `ct_phieu_nhap` where ma_sp='"+masp+"' and ma_pn = '"+mapn+"'";
    		Statement stmt = conn.createStatement();
    		ResultSet result = stmt.executeQuery(sql);
    		result.next();
    		int sltrongpn = result.getInt(1);
    		
    		sql = "select so_luong from `san_pham` where ma_sp='"+masp+"'";
    		result = stmt.executeQuery(sql);
    		result.next();
    		int sltrongsp = result.getInt(1);
    		
    		int newsl = sltrongpn+sltrongsp;
    		sql = "update `san_pham` set so_luong="+newsl+" where ma_sp='"+masp+"'";
    		int kq=stmt.executeUpdate(sql);
    		if(kq==1)
    			rs=true;
    	} catch(Exception e) {
    		System.out.println();
    	} finally {
    		closeConnection();
    	}
    	return rs;
    }
    
    //SỬA CHI TIẾT PHIẾU NHẬP
    public static boolean suachitietphieunhap(ChiTietPhieu ncc) {
		boolean dem=false;
		try {
			getConnection();	//HÀM TRIM() XÓA HẾT KHOẢNG TRẮNG
			Statement stmt = conn.createStatement();
			String sql = "update `ct_phieu_nhap` set don_gia='"+ncc.getSanPham().getGiaNhap()
					+"', so_luong='"+ncc.getSanPham().getSoLuong()
					+"', thanh_tien='"+ncc.getThanhTien()
					+"' where ma_sp='"+ncc.getSanPham().getMaSanPham()+"'";
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
    //XÓA CHI TIẾT PHIẾU NHẬP
    public static int xoachitietphieunhap(String masp) {
		int dem=0;
		try {
			getConnection();
			Statement stmt = conn.createStatement();
			String sql = "delete from `ct_phieu_nhap` where ma_sp='"+masp+"'";
			dem = stmt.executeUpdate(sql);
		} catch(Exception e) {
			System.out.println(e);
		} finally {
			closeConnection();
		}
		return dem;
	}
}
