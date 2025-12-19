package GUI;

import DTO.LoaiSanPham;
import DTO.SanPham;
import DTO.TheKho;
import DTO.NhaCungCap;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFileChooser;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.List;
import javax.swing.ListSelectionModel;
import java.awt.Choice;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SanPhamGUI extends JPanel {
	private JComboBox cbNCC = new JComboBox();
//	private JComboBox cbLoaiSP = new JComboBox();
	private JComboBox cbGia = new JComboBox();
	private JComboBox cbLoaiSP = new JComboBox();
	private JComboBox cbLoaiSPTK;
	private JComboBox cbTimKiem = new JComboBox();
	
	private JButton btnXacNhan;
	private JButton btnHuy;
	private JButton btnThemAnh;
	
	private JLabel lblAnhSP = new JLabel("");
	private String filePath = "";
	
	private ArrayList<SanPham> danhSachSP = new ArrayList<SanPham>();
	private ArrayList<NhaCungCap> danhSachNCC = new ArrayList<NhaCungCap>();
	private ArrayList<LoaiSanPham> danhSachLSP = new ArrayList<LoaiSanPham>();
	private ArrayList<TheKho> TK_DSTK = new ArrayList<TheKho>();
	
	private JTextField tfMaSP;
	private JTextField tfTenSP;
	private JTextField tfGiaNhap;
	private JTextField tfGiaBan;
	private JTextField tfSoLuong;
	private JTable tableDSSP;
	private JTextField tfTimKiem;
	private JTextField tfGiaMin;
	private JTextField tfGiaMax;
//	private String anhFile;
	private JTextField tfChatLieu;
	private DefaultTableModel tModelDSSP;
	
//	private SanPham_LoaiSanPhamGUI lspgui = new SanPham_LoaiSanPhamGUI();
	
	String confirmMode = "";
	private JTable TK_tableDSTK;
	private DefaultTableModel TK_tModelTheKho;
	private JTextField tfDonViTinh;
	private JTextField TK_tfTimKiemSP;

	/**
	 * Create the panel.
	 */
	public SanPhamGUI() {
		setBackground(new Color(240, 240, 240));
		danhSachSP = BLL.SanPhamBLL.layDanhSachSP();
		danhSachNCC = BLL.NhaCungCapBLL.layDanhSachNhaCC();
		danhSachLSP = BLL.LoaiSanPhamBLL.layDanhSachLoaiSP();
		
		setSize(new Dimension(1269,679));
		setLayout(null);
		Vector<String> sMaNCC = new Vector<String>();
		sMaNCC.add("");
		for(int i = 0; i < danhSachNCC.size(); i++) {
			sMaNCC.add(danhSachNCC.get(i).getTenNhaCC());
		}
		Vector<String> sLoaiSP = new Vector<String>();
		sLoaiSP.add("");
		for(int i = 0; i < danhSachLSP.size(); i++) {
			sLoaiSP.add(danhSachLSP.get(i).getTenLoaiSanPham());
		}
		tModelDSSP = new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"Ảnh","Mã sản phẩm","Tên sản phẩm"
			} 
		) {
			@Override
			public boolean isCellEditable(int row, int height) {
				return false;
			}
		};
						
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1249, 667);
		add(tabbedPane);
		
		JPanel panelSanPham = new JPanel();
		tabbedPane.addTab("Sản phẩm", null, panelSanPham, null);
		panelSanPham.setLayout(null);
		
		JPanel panelThucHien = new JPanel();
		panelThucHien.setBounds(324, 518, 123, 75);
		panelSanPham.add(panelThucHien);
		panelThucHien.setBackground(new Color(240, 240, 240));
		panelThucHien.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelThucHien.setLayout(null);
		
		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setBackground(new Color(255, 255, 255));
		btnXacNhan.setEnabled(false);
		btnXacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		btnXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXacNhan.setBounds(10, 11, 103, 23);
		panelThucHien.add(btnXacNhan);
		
		// Button huy
		btnHuy = new JButton("Hủy");
		btnHuy.setBackground(new Color(255, 255, 255));
		btnHuy.setEnabled(false);
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
				hienThiDanhSachSP(BLL.SanPhamBLL.layDanhSachSP());
				tableDSSP.setEnabled(true);
				isEnable(false);
				btnXacNhan.setEnabled(false);
				btnHuy.setEnabled(false);
				confirmMode = "";
			}
		});
		btnHuy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnHuy.setBounds(10, 43, 103, 23);
		panelThucHien.add(btnHuy);
		
		// Panel chuc nang
		JPanel panelChucNang = new JPanel();
		panelChucNang.setBounds(8, 507, 306, 103);
		panelSanPham.add(panelChucNang);
		panelChucNang.setBackground(new Color(240, 240, 240));
		panelChucNang.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ch\u1EE9c  n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelChucNang.setLayout(new GridLayout(1, 1, 0, 0));
		
		// Button them san pham
		JButton btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("image/icon/plus.png"));
		panelChucNang.add(btnThem);
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themSanPham();
			}
		});
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		// Button xoa san pham
		JButton btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("image/icon/bin.png"));
		panelChucNang.add(btnXoa);
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaSanPham();
			}
		});
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		// Button sua san pham
		JButton btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("image/icon/edit.png"));
		panelChucNang.add(btnSua);
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaSanPham();
			}
		});
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JPanel panelDanhSachSP = new JPanel();
		panelDanhSachSP.setBounds(611, 183, 623, 313);
		panelSanPham.add(panelDanhSachSP);
		panelDanhSachSP.setBackground(new Color(240, 240, 240));
		panelDanhSachSP.setBorder(new TitledBorder(null, "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDanhSachSP.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.enableInputMethods(false);
		scrollPane.setEnabled(false);
		panelDanhSachSP.add(scrollPane);
		
		tableDSSP = new JTable();
		tableDSSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableDSSP.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDSSP.setBackground(new Color(255, 255, 255));
		tableDSSP.setOpaque(false);
		tableDSSP.setFillsViewportHeight(true);
		tableDSSP.setRowHeight(70);
		tableDSSP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chonSanPham();
			}
		});
		tableDSSP.getTableHeader().setReorderingAllowed(false);
		tableDSSP.setModel(tModelDSSP);
		scrollPane.setViewportView(tableDSSP);
		
		JPanel panelThongTinSP = new JPanel();
		panelThongTinSP.setBounds(0, 11, 601, 485);
		panelSanPham.add(panelThongTinSP);
		panelThongTinSP.setBackground(new Color(240, 240, 240));
		panelThongTinSP.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin s\u1EA3n ph\u1EA9m", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelThongTinSP.setLayout(null);
		
		JLabel lblMaSanPham = new JLabel("Mã sản phẩm");
		lblMaSanPham.setBounds(247, 43, 82, 19);
		panelThongTinSP.add(lblMaSanPham);
		lblMaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblTenSanPham = new JLabel("Tên sản phẩm ");
		lblTenSanPham.setBounds(247, 88, 89, 19);
		panelThongTinSP.add(lblTenSanPham);
		lblTenSanPham.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblGiaNhap = new JLabel("Giá nhập ");
		lblGiaNhap.setBounds(247, 293, 57, 19);
		panelThongTinSP.add(lblGiaNhap);
		lblGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblGiaBan = new JLabel("Giá bán ");
		lblGiaBan.setBounds(247, 335, 49, 19);
		panelThongTinSP.add(lblGiaBan);
		lblGiaBan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblSoLuongTon = new JLabel("Số lượng tồn");
		lblSoLuongTon.setBounds(247, 376, 79, 19);
		panelThongTinSP.add(lblSoLuongTon);
		lblSoLuongTon.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblMaNhaCungCap = new JLabel("Nhà cung cấp ");
		lblMaNhaCungCap.setBounds(247, 256, 79, 19);
		panelThongTinSP.add(lblMaNhaCungCap);
		
		
		lblMaNhaCungCap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cbNCC.setEnabled(false);
		cbNCC.setBackground(new Color(255, 255, 255));
		cbNCC.setBounds(339, 256, 238, 21);
		cbNCC.setModel(new DefaultComboBoxModel(sMaNCC));
		AutoCompleteDecorator.decorate(cbNCC);
		panelThongTinSP.add(cbNCC);
		
		tfMaSP = new JTextField();
		tfMaSP.setBackground(new Color(255, 255, 255));
		tfMaSP.setEditable(false);
		tfMaSP.setBounds(339, 43, 238, 19);
		panelThongTinSP.add(tfMaSP);
		tfMaSP.setColumns(10);
		
		tfTenSP = new JTextField();
		tfTenSP.setBackground(new Color(255, 255, 255));
		tfTenSP.setEditable(false);
		tfTenSP.setBounds(339, 88, 238, 19);
		panelThongTinSP.add(tfTenSP);
		tfTenSP.setColumns(10);
		
		tfGiaNhap = new JTextField();
		tfGiaNhap.setBackground(new Color(255, 255, 255));
		tfGiaNhap.setEditable(false);
		tfGiaNhap.setBounds(339, 293, 238, 19);
		panelThongTinSP.add(tfGiaNhap);
		tfGiaNhap.setColumns(10);
		
		tfGiaBan = new JTextField();
		tfGiaBan.setBackground(new Color(255, 255, 255));
		tfGiaBan.setEditable(false);
		tfGiaBan.setBounds(339, 335, 238, 19);
		panelThongTinSP.add(tfGiaBan);
		tfGiaBan.setColumns(10);
		
		tfSoLuong = new JTextField();
		tfSoLuong.setBackground(new Color(255, 255, 255));
		tfSoLuong.setEditable(false);
		tfSoLuong.setBounds(339, 376, 238, 19);
		panelThongTinSP.add(tfSoLuong);
		tfSoLuong.setColumns(10);
		
		JLabel lblMaLoaiSP = new JLabel("Loại sản phẩm");
		lblMaLoaiSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaLoaiSP.setBounds(247, 134, 82, 19);
		panelThongTinSP.add(lblMaLoaiSP);
		cbLoaiSP.setEnabled(false);
		cbLoaiSP.setBackground(new Color(255, 255, 255));
		
		//CB ma loai sp
		cbLoaiSP.setBounds(339, 134, 238, 21);
		cbLoaiSP.setModel(new DefaultComboBoxModel(sLoaiSP));
		//		cbLoaiSP.addActionListener(new ActionListener() {
		//			public void actionPerformed(ActionEvent e) {
		//				for(LoaiSanPham lsp : danhSachLSP) {
		//					if(lsp.getMaLoaiSanPham().equals(cbLoaiSP.getSelectedItem())) {
		//						tfTenLoaiSP.setText(lsp.getTenLoaiSanPham());
		//						break;
		//					}
		//				}
		//			}
		//		});
				AutoCompleteDecorator.decorate(cbLoaiSP);
				panelThongTinSP.add(cbLoaiSP);
				
				// Button chon anh
				btnThemAnh = new JButton("Chọn ảnh");
				btnThemAnh.setBackground(new Color(255, 255, 255));
				btnThemAnh.setEnabled(false);
				btnThemAnh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						chonAnh();
					}
				});
				btnThemAnh.setBounds(56, 258, 104, 21);
				panelThongTinSP.add(btnThemAnh);
				
				JLabel lblChatLieu = new JLabel("Chất liệu");
				lblChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblChatLieu.setBounds(247, 214, 57, 19);
				panelThongTinSP.add(lblChatLieu);
				
				tfChatLieu = new JTextField();
				tfChatLieu.setBackground(new Color(255, 255, 255));
				tfChatLieu.setEditable(false);
				tfChatLieu.setColumns(10);
				tfChatLieu.setBounds(339, 214, 238, 19);
				panelThongTinSP.add(tfChatLieu);
				lblAnhSP.setBounds(10, 25, 227, 223);
				panelThongTinSP.add(lblAnhSP);
				lblAnhSP.setBorder(new LineBorder(new Color(0, 0, 0)));
				
				JLabel lblDonViTinh = new JLabel("Đơn vị tính");
				lblDonViTinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblDonViTinh.setBounds(247, 175, 82, 19);
				panelThongTinSP.add(lblDonViTinh);
				
				tfDonViTinh = new JTextField();
				tfDonViTinh.setEditable(false);
				tfDonViTinh.setColumns(10);
				tfDonViTinh.setBackground(Color.WHITE);
				tfDonViTinh.setBounds(339, 175, 238, 19);
				panelThongTinSP.add(tfDonViTinh);
				
				
				// Panel tim kiem
				JPanel panelTimKiem = new JPanel();
				panelTimKiem.setBounds(611, 11, 607, 142);
				panelSanPham.add(panelTimKiem);
				panelTimKiem.setBackground(new Color(240, 240, 240));
				panelTimKiem.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panelTimKiem.setLayout(null);
				
				//Label tim kiem
				JLabel lblTimKiem = new JLabel("Tìm kiếm");
				lblTimKiem.setBounds(14, 29, 69, 19);
				panelTimKiem.add(lblTimKiem);
				lblTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 12));
				
				//Textfield tim kiem tu chon
				tfTimKiem = new JTextField();
				tfTimKiem.setBackground(new Color(255, 255, 255));
				tfTimKiem.setBounds(321, 32, 246, 19);
				panelTimKiem.add(tfTimKiem);
				tfTimKiem.setColumns(10);
				
				JLabel lblLoc = new JLabel("Lọc");
				lblLoc.setBounds(10, 80, 27, 19);
				panelTimKiem.add(lblLoc);
				lblLoc.setHorizontalAlignment(SwingConstants.LEFT);
				lblLoc.setFont(new Font("Tahoma", Font.PLAIN, 12));
				
				cbGia.setBounds(47, 78, 108, 21);
				panelTimKiem.add(cbGia);
				cbGia.setModel(new DefaultComboBoxModel(new String[] {"", "Giá nhập", "Giá bán"}));
				
				tfGiaMin = new JTextField();
				tfGiaMin.setBackground(new Color(255, 255, 255));
				tfGiaMin.setBounds(165, 80, 84, 19);
				panelTimKiem.add(tfGiaMin);
				tfGiaMin.setColumns(10);
				
				tfGiaMax = new JTextField();
				tfGiaMax.setBackground(new Color(255, 255, 255));
				tfGiaMax.setBounds(278, 80, 84, 19);
				panelTimKiem.add(tfGiaMax);
				tfGiaMax.setColumns(10);
				
				JLabel lblDauNgang = new JLabel("-");
				lblDauNgang.setBackground(new Color(255, 255, 255));
				lblDauNgang.setEnabled(false);
				lblDauNgang.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblDauNgang.setBounds(259, 83, 9, 13);
				panelTimKiem.add(lblDauNgang);
				
				// Combobox tim kiem theo loai san pham
				cbLoaiSPTK = new JComboBox();
				cbLoaiSPTK.setBackground(new Color(255, 255, 255));
				cbLoaiSPTK.setModel(new DefaultComboBoxModel(sLoaiSP));
				cbLoaiSPTK.setBounds(80, 30, 108, 21);
				panelTimKiem.add(cbLoaiSPTK);
				
				// Combobox tim kiem theo ma sp, ten sp, ma ncc, ten ncc
				cbTimKiem = new JComboBox();
				cbTimKiem.setBackground(new Color(255, 255, 255));
				cbTimKiem.setBounds(198, 29, 113, 21);
				cbTimKiem.setModel(new DefaultComboBoxModel(new String[] {"", "Mã sản phẩm", "Tên sản phẩm", "Mã nhà cung cấp", "Tên nhà cung cấp"}));
				panelTimKiem.add(cbTimKiem);
				
				JButton btnTimKiem = new JButton("Tìm kiếm");
				btnTimKiem.setBackground(new Color(255, 255, 255));
				btnTimKiem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						timKiemSP();
					}
				});
				btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnTimKiem.setBounds(373, 70, 92, 38);
				panelTimKiem.add(btnTimKiem);
				
				JButton btnHuyTimKiem = new JButton("Hủy");
				btnHuyTimKiem.setBackground(new Color(255, 255, 255));
				btnHuyTimKiem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						hienThiDanhSachSP(BLL.SanPhamBLL.layDanhSachSP());
					}
				});
				btnHuyTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnHuyTimKiem.setBounds(475, 70, 92, 38);
				panelTimKiem.add(btnHuyTimKiem);
				
				JPanel panelTheKho = new JPanel();
				
				tabbedPane.addTab("Thẻ kho", null, panelTheKho, null);
				panelTheKho.setLayout(null);
				
				JLabel TK_lblTimKiemSP = new JLabel("Nhập sản phẩm");
				TK_lblTimKiemSP.setBounds(10, 11, 88, 24);
				panelTheKho.add(TK_lblTimKiemSP);
				
				JPanel TK_panelDSTK = new JPanel();
				TK_panelDSTK.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				TK_panelDSTK.setBounds(10, 50, 1084, 578);
				panelTheKho.add(TK_panelDSTK);
				TK_panelDSTK.setLayout(new GridLayout(0, 1, 0, 0));
				
				JScrollPane TK_spDSTK = new JScrollPane();
				TK_panelDSTK.add(TK_spDSTK);
				
				TK_tableDSTK = new JTable();
				TK_tableDSTK.setFont(new Font("Tahoma", Font.PLAIN, 13));
				TK_tModelTheKho = new DefaultTableModel(new Object [][] {},new String [] {"Mã phiếu","Hình thức", "Ngày lập" ,"Tổng giá trị","Sô lượng", "Tồn cuối"}) {
					@Override
					public boolean isCellEditable(int row, int height) {
						return false;
					}
				};
				TK_tableDSTK.getTableHeader().setReorderingAllowed(false);
				TK_tableDSTK.setRowHeight(30);
				TK_tableDSTK.setModel(TK_tModelTheKho);
				TK_tableDSTK.getTableHeader().setReorderingAllowed(false);
				TK_spDSTK.setViewportView(TK_tableDSTK);
				
				TK_tfTimKiemSP = new JTextField();
				TK_tfTimKiemSP.setBounds(108, 13, 168, 20);
				panelTheKho.add(TK_tfTimKiemSP);
				TK_tfTimKiemSP.setColumns(10);
				
				JButton TK_btnTimKiemSP = new JButton("Tìm");
				TK_btnTimKiemSP.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						TK_btnTimKiemSP_HienThiTheKhoSP();
					}
				});
				TK_btnTimKiemSP.setIcon(new ImageIcon("image/icon/search.png"));
				TK_btnTimKiemSP.setBounds(296, 12, 99, 23);
				panelTheKho.add(TK_btnTimKiemSP);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		hienThiDanhSachSP(BLL.SanPhamBLL.layDanhSachSP());
	}
	
	//------------------------------CHUC NANG----------------------------//
	
	//Hien thi danh sach san pham
	private void hienThiDanhSachSP(ArrayList<SanPham> dssp) {
		tModelDSSP.setRowCount(0);
		danhSachSP = dssp;
		for(SanPham sp : danhSachSP){
//			Object[] sanPham = {sp.getAnhSanPham(),sp.getMaSanPham(),sp.getTenSanPham(),sp.getChatLieu(),sp.getLoaiSanPham().getMaLoaiSanPham(),sp.getLoaiSanPham().getTenLoaiSanPham(),sp.getNhaCC().getMaNhaCC(),sp.getNhaCC().getTenNhaCC(),sp.getGiaNhap()+"",sp.getGiaBan()+"",sp.getSoLuong()+""};
			Object[] sanPham = {"image/san_pham/"+sp.getFilePath(), sp.getMaSanPham(), sp.getTenSanPham()};
			tModelDSSP.addRow(sanPham);
//			System.out.println(sp.getFilePath());
			tableDSSP.getColumnModel().getColumn(0).setCellRenderer(new ImageRender());
		}
	}
	
	//Tim kiem san pham
	private void timKiemSP() {
		ArrayList<SanPham> dssp = new ArrayList<SanPham>();
		String loaiSP = "";
		int loaiTimKiem = 0;
		int loaiGia = 0;
		String timKiem = "";
		double giaMin = 0;
		double giaMax = 0;
		
		ArrayList<Integer> itemIndexes = new ArrayList<Integer>();
		
		try {
			if(!tfTimKiem.getText().trim().equals("")) {
				timKiem = tfTimKiem.getText().toString();
			}
			
			if(cbLoaiSPTK.getSelectedIndex() > 0) {
				loaiSP = danhSachLSP.get(cbLoaiSPTK.getSelectedIndex() - 1).getMaLoaiSanPham();
				itemIndexes.add(1); 
			}
			if(! cbTimKiem.getSelectedItem().toString().equals("")) {
				loaiTimKiem = cbTimKiem.getSelectedIndex();
				itemIndexes.add(2);
			}
			if (cbGia.getSelectedIndex() > 0) {
				loaiGia = cbGia.getSelectedIndex();
				if(! (tfGiaMin.getText().trim().equals("") && tfGiaMax.getText().trim().equals(""))) {
					try {
						giaMin = Double.parseDouble(tfGiaMin.getText().trim());
						giaMax = Double.parseDouble(tfGiaMax.getText().trim());
						itemIndexes.add(3);
					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Giá nhập hoặc giá bán không hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			danhSachSP = BLL.SanPhamBLL.layDanhSachSP();
			
			tModelDSSP.setRowCount(0);
			
			for (SanPham sp : danhSachSP) {
				boolean check = true;
				for(int i : itemIndexes) {
					switch(i) {
					case 1:
						if(!loaiSP.equals(sp.getLoaiSanPham().getMaLoaiSanPham())) check = false;
						break;
					case 2:
						switch(loaiTimKiem) {
							case 1:
//								System.out.println(sp.getMaSanPham() + timKiem.toUpperCase());
								if(! sp.getMaSanPham().toUpperCase().contains(timKiem.toUpperCase())) check = false;
								break;
							case 2:
								if(! sp.getTenSanPham().toUpperCase().contains(timKiem.toUpperCase())) check = false;
								break;
							case 3:
								if(! sp.getNhaCC().getMaNhaCC().toUpperCase().contains(timKiem.toUpperCase())) check = false;
								break;
							case 4:
								if(! sp.getNhaCC().getTenNhaCC().toUpperCase().contains(timKiem.toUpperCase())) check = false;
								break;
							default:
								break;
						}
						break;
					case 3:
						if(loaiGia == 1) {
							if(!(giaMin <= sp.getGiaNhap() && sp.getGiaNhap() <= giaMax)) check = false;
						} else if(loaiGia == 2) {
							if(!(giaMin <= sp.getGiaBan() && sp.getGiaBan() <= giaMax)) check = false;
						} 
						break;
					default:
						break;
					}
				}
				if (check) {
					dssp.add(sp);
				}
			}
			hienThiDanhSachSP(dssp);
		}
		catch(Exception ex) {
			System.out.print(ex.getMessage());
		}
	}
	
	
	//Dieu chinh de them san pham
	private void themSanPham() {
		isEnable(true);
		tableDSSP.setEnabled(false);
		refresh();
		tfMaSP.setText(BLL.SanPhamBLL.taoMaSanPham());
		confirmMode = "Them";
	}
	
	// Dieu chinh de xoa san pham
	private void xoaSanPham() {
		isEnable(false);
		tableDSSP.setEnabled(true);
		confirmMode = "Xoa";
	}
	
	// Dieu chinh de sua san pham
	private void suaSanPham() {
		isEnable(true);
		tableDSSP.setEnabled(true);
		confirmMode = "Sua";
	}	
	
	// Them va sua san pham code tuong tu
	private SanPham themVaSua() {
		String maSP = tfMaSP.getText();
		String tenSP = tfTenSP.getText();
		String chatLieu = tfChatLieu.getText();
		String donVi = tfDonViTinh.getText();
		String maLoaiSP = danhSachLSP.get(cbLoaiSP.getSelectedIndex()-1).getMaLoaiSanPham();
		String maNCC = danhSachNCC.get(cbNCC.getSelectedIndex()-1).getMaNhaCC();
		double giaNhap = Double.parseDouble(tfGiaNhap.getText().toString());
		double giaBan = Double.parseDouble(tfGiaBan.getText().toString());
		int soLuong = 0;
		String fp = filePath; 
		
		LoaiSanPham loaiSP = new LoaiSanPham();
		loaiSP.setMaLoaiSanPham(maLoaiSP);
		NhaCungCap nhaCC = new NhaCungCap();
		nhaCC.setMaNhaCC(maNCC);
		SanPham sanPham = new SanPham(maSP, tenSP, chatLieu, donVi,loaiSP ,nhaCC,giaNhap,giaBan,soLuong,fp);
		
		return sanPham;
	}
	
	// Xac nhan
	private void xacNhan() {
		if(kiemTraThongTinSP()) {
			if(confirmMode == "Them" ) {
				try {
					SanPham sanPham = themVaSua();
					int c = JOptionPane.showConfirmDialog(null, "Xác nhận thêm sản phẩm ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
					if(c == 0 && BLL.SanPhamBLL.themSanPham(sanPham)) {
						JOptionPane.showMessageDialog(null, "Thêm thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
					else JOptionPane.showMessageDialog(null, "Thêm không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
			else if(confirmMode == "Xoa") {
				try {
					int i = tableDSSP.getSelectedRow();
					if(i>=0) {
						int c = JOptionPane.showConfirmDialog(null, "Xác nhận xóa sản phẩm ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
						if(c == 0 && BLL.SanPhamBLL.xoaSanPham(danhSachSP.get(i))) {
							JOptionPane.showMessageDialog(new SanPhamGUI(), "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
						}
						else JOptionPane.showMessageDialog(new SanPhamGUI(), "Xóa không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				catch(Exception ex){
					System.out.println(ex.getMessage());
				}
			}
			else if(confirmMode == "Sua") {
				try {
					SanPham sanPham = themVaSua();
					int c = JOptionPane.showConfirmDialog(null, "Xác nhận sửa sản phẩm ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
					if(c == 0 && BLL.SanPhamBLL.suaSanPham(sanPham)) {
						JOptionPane.showMessageDialog(null, "Sửa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					}
					else JOptionPane.showMessageDialog(null, "Sửa không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
			refresh();
			hienThiDanhSachSP(BLL.SanPhamBLL.layDanhSachSP());
		} 
	}
	
	private boolean kiemTraThongTinSP() {
		if(tfTenSP.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "Tên sản phẩm không được để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if(cbLoaiSP.getSelectedIndex() == 0 || cbLoaiSP.getSelectedItem().toString().equals("")){
			JOptionPane.showMessageDialog(null, "Mã loại sản phẩm chưa chọn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if(cbNCC.getSelectedIndex() == 0 || cbNCC.getSelectedItem().toString().equals("")) {
			JOptionPane.showMessageDialog(null, "Mã nhà cung cấp chưa chọn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else if(!tfGiaNhap.getText().equals("") && !tfGiaBan.getText().equals("")) {
			if(!(tfGiaNhap.getText().matches("[0-9].+") && tfGiaBan.getText().matches("[0-9].+"))) {
				JOptionPane.showMessageDialog(null, "Giá tiền không hợp lệ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				return false;
			} else {
				return true;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Giá tiền không được để trống", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}
	
	// Xoa thong tin trong textfield
	private void refresh() {
		tfMaSP.setText("");
		tfTenSP.setText("");
		tfChatLieu.setText("");
		tfGiaNhap.setText("");
		tfGiaBan.setText("");
		tfSoLuong.setText("");
		tfDonViTinh.setText("");;
		cbNCC.setSelectedIndex(0);
		cbLoaiSP.setSelectedIndex(0);
		lblAnhSP.setIcon(null);
		filePath = "";
	}
	
	// enable cho textfield 
	private void isEnable(boolean i) {
		btnXacNhan.setEnabled(true);
		btnHuy.setEnabled(true);
		btnThemAnh.setEnabled(i);
		tfTenSP.setEditable(i);
		cbLoaiSP.setEnabled(i);
		tfChatLieu.setEditable(i);
		tfDonViTinh.setEditable(i);
		cbNCC.setEnabled(i);
		tfGiaNhap.setEditable(i);
		tfGiaBan.setEditable(i);
	}
	
	
	private void chonAnh() {
		try {
			JFileChooser fc = new JFileChooser("image/san_pham");
			fc.setDialogTitle("Chọn ảnh");
			fc.showOpenDialog(new SanPhamGUI());
			File file = fc.getSelectedFile();
			filePath = file.getName();
			if(filePath != null) {
				ImageIcon imageIcon = new ImageIcon(new ImageIcon("image/san_pham/"+filePath).getImage().getScaledInstance(lblAnhSP.getWidth(), lblAnhSP.getHeight(), Image.SCALE_SMOOTH));
				lblAnhSP.setIcon(imageIcon);
//				FileInputStream fis = new FileInputStream(file);
//				ByteArrayOutputStream bos = new ByteArrayOutputStream();
//				byte[] buf = new byte[1024];
//				int readNum;
//				while((readNum = fis.read(buf)) != -1) {
//					bos.write(buf, 0, readNum);
//				}
//				anhFile = bos.toByteArray();
			}
		}
		catch(Exception ex) {
//			System.out.println(ex.getMessage());
		}
	}
	
	private void chonSanPham() {
		int i = tableDSSP.getSelectedRow();
		if(i >= 0) {
			try {
				tfMaSP.setText(danhSachSP.get(i).getMaSanPham());
				tfTenSP.setText(danhSachSP.get(i).getTenSanPham());
				tfChatLieu.setText(danhSachSP.get(i).getChatLieu());
				tfDonViTinh.setText(danhSachSP.get(i).getDonVi());
				cbLoaiSP.setSelectedItem(danhSachSP.get(i).getLoaiSanPham().getTenLoaiSanPham());
				cbNCC.setSelectedItem(danhSachSP.get(i).getNhaCC().getTenNhaCC());
				tfGiaNhap.setText(danhSachSP.get(i).getGiaNhap()+ "");
				tfGiaBan.setText(danhSachSP.get(i).getGiaBan()+ "");
				tfSoLuong.setText(danhSachSP.get(i).getSoLuong() + "");
				if(danhSachSP.get(i).getFilePath()!=null) {
					filePath = danhSachSP.get(i).getFilePath();
					ImageIcon imageIcon = new ImageIcon(new ImageIcon("image/san_pham/"+filePath).getImage().getScaledInstance(lblAnhSP.getWidth(), lblAnhSP.getHeight(), Image.SCALE_SMOOTH));
					lblAnhSP.setIcon(imageIcon);
				}
				else lblAnhSP.setIcon(null);
			}
			catch(Exception ex){
				System.out.println(ex.getMessage());
			}
		}
	}
	
	// Khóa textfield để tránh sửa đổi
	private void enableTextField(boolean i) {
		tfTenSP.setEnabled(i);
		tfChatLieu.setEnabled(i);
		cbLoaiSP.setEnabled(i);
		cbNCC.setEnabled(i);
		tfGiaNhap.setEnabled(i);
		tfGiaBan.setEnabled(i);
	}
	
	private void TK_HienThiDSTK(ArrayList<TheKho> dstk) {
		TK_tModelTheKho.setRowCount(0);
		TK_DSTK = dstk;
		for (TheKho tk : TK_DSTK) {
			String [] s = {tk.getMaPhieu(),tk.getMaPhieu().contains("HD") ? "Bán hàng" : "Nhập hàng" ,tk.getNgayLap(), tk.getTongGiaTri()+"",tk.getMaPhieu().contains("HD") ? "-" + tk.getSoLuong(): "+" + tk.getSoLuong(),tk.getTonCuoi()+""};
			TK_tModelTheKho.addRow(s);
		}
	}
	
	private void TK_btnTimKiemSP_HienThiTheKhoSP() {
		String s = TK_tfTimKiemSP.getText().trim();
		if(!s.equals("")) {
			TK_HienThiDSTK(BLL.TheKhoBLL.layTheKho(s.toUpperCase()));
		}
	}
}
