package GUI;

import javax.swing.border.EmptyBorder;

import DTO.TaiKhoan;
import javafx.scene.shape.Box;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Component;

public class GiaoDienGUI extends JFrame {

	private JPanel contentPane;
	JPanel panelGiaoDien = new JPanel();
	JMenu mnKhoHang = new JMenu("Kho hàng");
	JMenuItem mntmSanPham = new JMenuItem("Sản phẩm");
	JMenuItem mntmNhapHang = new JMenuItem("Nhập hàng");
	JMenu mnBanHang = new JMenu("Bán hàng");
	JMenu mnDoiTac = new JMenu("Đối tác");
	JMenuItem mntmNhaCC = new JMenuItem("Nhà cung cấp");
	JMenuItem mntmKhachHang = new JMenuItem("Khách hàng");
	JMenu mnNhanSu = new JMenu("Nhân sự");
	JMenu mnTaiKhoan = new JMenu("Tài khoản");
	JMenu mnThongKe = new JMenu("Thống kê");
	/**
	 * Create the frame.
	 */
	public GiaoDienGUI(TaiKhoan taiKhoan) {
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/extra_img/pencil_icon.png"));
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		panelGiaoDien.setSize(new Dimension(1082,689));
		panelGiaoDien.setBounds(0, 0, 1269,679);
		contentPane.add(panelGiaoDien);
		panelGiaoDien.setLayout(null);
		
		panelGiaoDien.setLayout(null);
//		panelGiaoDien.add(new JPanel());
//		panelGiaoDien.add(new SanPhamGUI(), "SanPham");
//		panelGiaoDien.add(new NhanVienGUI(), "NhanVien");
//		panelGiaoDien.add(new KhachHangGUI(), "KhachHang");
//		panelGiaoDien.add(new NhaCungCapGUI(), "NhaCungCap");
//		panelGiaoDien.add(new HoaDonGUI(), "HoaDon");
//		panelGiaoDien.add(new PhieuNhapGUI(), "PhieuNhap");
//		panelGiaoDien.add(new PhieuXuatGUI(), "PhieuXuat");
//		panelGiaoDien.add(new TaiKhoanGUI(), "TaiKhoan");
//		panelGiaoDien.add(new ThongKeGUI(), "ThongKe");
		setResizable(false);
		setTitle("Giao diện quản lý");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1276, 740);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 0, 0));
		menuBar.setBackground(new Color(240, 240, 240));
		menuBar.setFont(new Font("Sylfaen", Font.PLAIN, 14));
		setJMenuBar(menuBar);
		
		mnKhoHang.setIcon(new ImageIcon("image/icon/box.png"));
		mnKhoHang.setForeground(new Color(0, 51, 255));
		mnKhoHang.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		mnKhoHang.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnKhoHang);
		
		mntmSanPham.setForeground(new Color(0, 51, 255));
		mntmSanPham.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		mntmSanPham.setHorizontalAlignment(SwingConstants.LEFT);
		mntmSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				cl.show(panelGiaoDien, "SanPham");
				panelGiaoDien.removeAll();
				panelGiaoDien.revalidate();
				panelGiaoDien.repaint();
				panelGiaoDien.add(new SanPhamGUI());
			}
		});
		mnKhoHang.add(mntmSanPham);
		
		mntmNhapHang.setForeground(new Color(0, 51, 255));
		mntmNhapHang.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		mntmNhapHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				cl.show(panelGiaoDien, "PhieuNhap");
				panelGiaoDien.removeAll();
				panelGiaoDien.revalidate();
				panelGiaoDien.repaint();
				panelGiaoDien.add(new PhieuNhapGUI(taiKhoan));
			}
		});
		mnKhoHang.add(mntmNhapHang);
		
		mnBanHang.setIcon(new ImageIcon("image/icon/cross.png"));
		mnBanHang.setForeground(new Color(0, 51, 255));
		mnBanHang.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		mnBanHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				cl.show(panelGiaoDien,"HoaDon");
				panelGiaoDien.removeAll();
				panelGiaoDien.revalidate();
				panelGiaoDien.repaint();
				panelGiaoDien.add(new HoaDonGUI(taiKhoan));
			}
		});
		menuBar.add(mnBanHang);
		
		mnDoiTac.setIcon(new ImageIcon("image/icon/management.png"));
		mnDoiTac.setForeground(new Color(0, 51, 255));
		mnDoiTac.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		menuBar.add(mnDoiTac);

		mntmNhaCC.setForeground(new Color(0, 51, 255));
		mntmNhaCC.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		mntmNhaCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				cl.show(panelGiaoDien,"NhaCungCap");
				panelGiaoDien.removeAll();
				panelGiaoDien.revalidate();
				panelGiaoDien.repaint();
				panelGiaoDien.add(new NhaCungCapGUI());
			}
		});
		mnDoiTac.add(mntmNhaCC);
		
		mntmKhachHang.setForeground(new Color(0, 51, 255));
		mntmKhachHang.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		mntmKhachHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				cl.show(panelGiaoDien,"KhachHang");
				panelGiaoDien.removeAll();
				panelGiaoDien.revalidate();
				panelGiaoDien.repaint();
				panelGiaoDien.add(new KhachHangGUI());
			}
		});
		mnDoiTac.add(mntmKhachHang);
		
		mnNhanSu.setIcon(new ImageIcon("image/icon/employees.png"));
		mnNhanSu.setForeground(new Color(0, 51, 255));
		mnNhanSu.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		mnNhanSu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				cl.show(panelGiaoDien,"NhanVien");
				panelGiaoDien.removeAll();
				panelGiaoDien.revalidate();
				panelGiaoDien.repaint();
				panelGiaoDien.add(new NhanVienGUI());
			}
		});
		menuBar.add(mnNhanSu);
		
		mnTaiKhoan.setIcon(new ImageIcon("image/icon/user.png"));
		mnTaiKhoan.setForeground(new Color(0, 51, 255));
		mnTaiKhoan.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		mnTaiKhoan.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				cl.show(panelGiaoDien,"TaiKhoan");
				panelGiaoDien.removeAll();
				panelGiaoDien.revalidate();
				panelGiaoDien.repaint();
				panelGiaoDien.add(new TaiKhoanGUI());
			}
		});
		menuBar.add(mnTaiKhoan);
		
		mnThongKe.setIcon(new ImageIcon("image/icon/dashboard.png"));
		mnThongKe.setForeground(new Color(0, 51, 255));
		mnThongKe.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		mnThongKe.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				cl.show(panelGiaoDien,"ThongKe");
				panelGiaoDien.removeAll();
				panelGiaoDien.revalidate();
				panelGiaoDien.repaint();
				panelGiaoDien.add(new ThongKeGUI());
			}
		});
		menuBar.add(mnThongKe);
		
//		Component horizontalGlue = Box.createHorizontalGlue();
//		menuBar.add(horizontalGlue);
		
//		menuBar.add(JMenuBar.);
		
		JMenu mnDangXuat = new JMenu("Đăng xuất");
		mnDangXuat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dangXuat();
			}
		});
		mnDangXuat.setIcon(new ImageIcon("image/icon/switch.png"));
		mnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
		mnDangXuat.setForeground(new Color(0, 51, 255));
		mnDangXuat.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		menuBar.add(mnDangXuat);
		
		quyenTaiKhoan(taiKhoan);
	}
	
	private void quyenTaiKhoan(TaiKhoan tk) {
		switch(tk.getChucVu().getMaChucVu()) {
			case "QUANLY":
				break;
			case "THUKHO":
				mntmKhachHang.setVisible(false);
				mnBanHang.setVisible(false);
				mnThongKe.setVisible(false);
				mnTaiKhoan.setVisible(false);
				mnNhanSu.setVisible(false);
				break;
			case "BANHANG":
				mnThongKe.setVisible(false);
				mnTaiKhoan.setVisible(false);
				mntmNhaCC.setVisible(false);
				mnKhoHang.setVisible(false);
				mnNhanSu.setVisible(false);
				break;
		}
	}
	
	private void dangXuat() {
		int c = JOptionPane.showConfirmDialog(null, "Xác nhận đăng xuất tài khoản ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
		if(c==0) {
			
			this.dispose();
			new DangNhapGUI().setVisible(true);
		}
	}

	public static void main(String[] args) {
		GiaoDienGUI gd = new GiaoDienGUI(new TaiKhoan());
		gd.setVisible(true);
	}
}
