package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import DTO.NhanVien;
import DTO.SanPham;
import DTO.TaiKhoan;
import DTO.KhachHang;
import DTO.ChiTietPhieu;
import DTO.HoaDon;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

public class HoaDonGUI extends JPanel {
	private JComboBox LHD_cbKhachHang;
	
	private DefaultTableModel LHD_tModelDSSPChon;
	private DefaultTableModel LHD_tModelDSSP;
	private DefaultTableModel XHD_tModelDSHD;
	
	private JTable LHD_tableDSSPChon;
	private JTable LHD_tableDSSP;

	private ArrayList<SanPham> LHD_DSSPChon = new ArrayList<SanPham>();
	private ArrayList<SanPham> LHD_DSSP = new ArrayList<SanPham>();
	private ArrayList<KhachHang> LHD_DSKH = new ArrayList<KhachHang>();
	private ArrayList<HoaDon> XHD_DSHD = new ArrayList<HoaDon>();
	private ArrayList<ChiTietPhieu> XHD_DSCTHD = new ArrayList<ChiTietPhieu>();
	private JTable XHD_tableDSHD;
	private JTextField XHD_tfKhachHang;
	private JTextField XHD_tfNhanVien;
	private JTextField XHD_tfTongTien;
	private JTextField XHD_tfMaHD;
	private JTextField XHD_tfNgayLap;
	private JTable XHD_tableCTHD;
	private DefaultTableModel XHD_tModelDSCTHD;
	private JTextField LHD_tfSoDienThoai;
	private JTextField LHD_tfTongSoLuong;
	private JTextField LHD_tfTongTien;
	private JTextField LHD_tfTimKiemSP;
	private JTextField LHD_tfNhapSoLuong;
	
	JDateChooser LHD_dcNgayLap = new JDateChooser();
	private JTextField XHD_tfTimKiemHD;
	JComboBox XHD_cbTimKiemHD = new JComboBox();
	
	
	/**
	 * Create the panel.
	 */
	public HoaDonGUI(TaiKhoan taiKhoan) {
		setBackground(new Color(240, 240, 240));
		setSize(1269,679);
		setLayout(null);
		
		LHD_tModelDSSPChon = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã sản phẩm", "Tên sản phẩm", "Đơn vị","Đơn giá", "Số lượng", "Thành tiền"
				}
			);
		LHD_tModelDSSP = new DefaultTableModel(new Object [][] {},new String [] {"Ảnh sản phẩm","Mã sản phẩm","Tên sản phẩm","Đơn vị", "Đơn giá" ,"Số lượng"});
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1249, 667);
		add(tabbedPane);
		
		JPanel panelLapHoaDon = new JPanel();
		tabbedPane.addTab("Lập hóa đơn", null, panelLapHoaDon, null);
		panelLapHoaDon.setLayout(null);
		
		JPanel LHD_panelDSSP = new JPanel();
		LHD_panelDSSP.setBounds(23, 11, 865, 269);
		panelLapHoaDon.add(LHD_panelDSSP);
		LHD_panelDSSP.setBorder(new TitledBorder(null, "Danh s\u00E1ch s\u1EA3n ph\u1EA9m", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		LHD_panelDSSP.setLayout(null);
		
		JScrollPane LHD_spDSSP = new JScrollPane();
		LHD_spDSSP.setBounds(10, 58, 845, 200);
		LHD_panelDSSP.add(LHD_spDSSP);
		
		LHD_tableDSSP = new JTable(){
			@Override 
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		LHD_tableDSSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LHD_tableDSSP.getTableHeader().setReorderingAllowed(false);
		LHD_spDSSP.setViewportView(LHD_tableDSSP);
		LHD_tableDSSP.setRowHeight(50);
		LHD_tableDSSP.setModel(LHD_tModelDSSP);
		LHD_spDSSP.setViewportView(LHD_tableDSSP);
		
		JButton LHD_btnChonSanPham = new JButton("Chọn");
		LHD_btnChonSanPham.setIcon(new ImageIcon("image/icon/check-mark.png"));
		LHD_btnChonSanPham.setBounds(724, 27, 95, 27);
		LHD_panelDSSP.add(LHD_btnChonSanPham);
		LHD_btnChonSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LHD_btnChonSanPham_ChonSanPham();
			}
		});
		LHD_btnChonSanPham.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel LHD_lblTimKiemSP = new JLabel("Tìm kiếm");
		LHD_lblTimKiemSP.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LHD_lblTimKiemSP.setBounds(10, 27, 69, 19);
		LHD_panelDSSP.add(LHD_lblTimKiemSP);
		
		LHD_tfTimKiemSP = new JTextField();
		LHD_tfTimKiemSP.setColumns(10);
		LHD_tfTimKiemSP.setBounds(89, 27, 164, 20);
		LHD_panelDSSP.add(LHD_tfTimKiemSP);
		
		JLabel LHD_lblNhapSoLuong = new JLabel("Nhập số lượng");
		LHD_lblNhapSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LHD_lblNhapSoLuong.setBounds(499, 27, 98, 19);
		LHD_panelDSSP.add(LHD_lblNhapSoLuong);
		
		LHD_tfNhapSoLuong = new JTextField();
		LHD_tfNhapSoLuong.setColumns(10);
		LHD_tfNhapSoLuong.setBounds(607, 27, 111, 20);
		LHD_panelDSSP.add(LHD_tfNhapSoLuong);
		
		JButton LHD_btnTimKiemSP = new JButton("Tìm ");
		LHD_btnTimKiemSP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LHD_btnTimKiemSP_TimKiemSP();
			}
		});
		LHD_btnTimKiemSP.setIcon(new ImageIcon("image/icon/search.png"));
		LHD_btnTimKiemSP.setFont(new Font("Tahoma", Font.PLAIN, 11));
		LHD_btnTimKiemSP.setBounds(269, 24, 98, 26);
		LHD_panelDSSP.add(LHD_btnTimKiemSP);
		
		JButton LHD_btnHuyTimKiem = new JButton("Hủy");
		LHD_btnHuyTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LHD_btnHuyTimKiem_HuyTimKiem();
			}
		});
		LHD_btnHuyTimKiem.setIcon(new ImageIcon("image/icon/close.png"));
		LHD_btnHuyTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 11));
		LHD_btnHuyTimKiem.setBounds(369, 24, 98, 26);
		LHD_panelDSSP.add(LHD_btnHuyTimKiem);
		
		
		JPanel LHD_panelThongTinHD = new JPanel();
		LHD_panelThongTinHD.setBounds(898, 11, 336, 390);
		panelLapHoaDon.add(LHD_panelThongTinHD);
		LHD_panelThongTinHD.setLayout(null);
		LHD_panelThongTinHD.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin l\u1EADp h\u00F3a \u0111\u01A1n", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel LHD_lblKhachHang = new JLabel("Khách hàng");
		LHD_lblKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LHD_lblKhachHang.setBounds(10, 53, 98, 19);
		LHD_panelThongTinHD.add(LHD_lblKhachHang);
		
		LHD_cbKhachHang = new JComboBox();
		LHD_cbKhachHang.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				LHD_cbKhachHang_LayThongTinKhachHang();
			}
		});
		AutoCompleteDecorator.decorate(LHD_cbKhachHang);
		LHD_cbKhachHang.setBounds(115, 52, 197, 22);
		LHD_panelThongTinHD.add(LHD_cbKhachHang);
		
		JLabel LHD_lblSoDienThoai = new JLabel("Số điện thoại");
		LHD_lblSoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LHD_lblSoDienThoai.setBounds(10, 100, 98, 19);
		LHD_panelThongTinHD.add(LHD_lblSoDienThoai);
		
		LHD_tfSoDienThoai = new JTextField();
		LHD_tfSoDienThoai.setEditable(false);
		LHD_tfSoDienThoai.setBounds(115, 100, 197, 20);
		LHD_panelThongTinHD.add(LHD_tfSoDienThoai);
		LHD_tfSoDienThoai.setColumns(10);
		
		JLabel LHD_lblNgayLap = new JLabel("Ngày lập");
		LHD_lblNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LHD_lblNgayLap.setBounds(10, 179, 98, 19);
		LHD_panelThongTinHD.add(LHD_lblNgayLap);
		
		LHD_dcNgayLap.setDateFormatString("dd-MM-yyyy HH:mm");
//		LHD_dcNgayLap.setMaxSelectableDate(Calendar.getInstance().getTime());
		LHD_dcNgayLap.setDate(Calendar.getInstance().getTime());
		LHD_dcNgayLap.setBounds(115, 179, 197, 20);
		LHD_panelThongTinHD.add(LHD_dcNgayLap);
		
		JLabel LHD_lblTongSoLuong = new JLabel("Tổng số lượng");
		LHD_lblTongSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LHD_lblTongSoLuong.setBounds(10, 223, 98, 19);
		LHD_panelThongTinHD.add(LHD_lblTongSoLuong);
		
		LHD_tfTongSoLuong = new JTextField();
		LHD_tfTongSoLuong.setText("0");
		LHD_tfTongSoLuong.setEditable(false);
		LHD_tfTongSoLuong.setColumns(10);
		LHD_tfTongSoLuong.setBounds(115, 223, 197, 20);
		LHD_panelThongTinHD.add(LHD_tfTongSoLuong);
		
		JLabel LHD_lblTongTien = new JLabel("Tổng tiền");
		LHD_lblTongTien.setFont(new Font("Tahoma", Font.PLAIN, 12));
		LHD_lblTongTien.setBounds(10, 272, 98, 19);
		LHD_panelThongTinHD.add(LHD_lblTongTien);
		
		LHD_tfTongTien = new JTextField();
		LHD_tfTongTien.setText("0.0");
		LHD_tfTongTien.setEditable(false);
		LHD_tfTongTien.setColumns(10);
		LHD_tfTongTien.setBounds(115, 272, 197, 20);
		LHD_panelThongTinHD.add(LHD_tfTongTien);
		
		JButton LHD_btnLapHoaDon = new JButton("Lập hóa đơn");
		LHD_btnLapHoaDon.setIcon(new ImageIcon("image/icon/check-mark.png"));
		LHD_btnLapHoaDon.setBounds(10, 314, 302, 65);
		LHD_panelThongTinHD.add(LHD_btnLapHoaDon);
		LHD_btnLapHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LHD_LapHoaDon(taiKhoan);
			}
		});
		LHD_btnLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JPanel LHD_panelDSSPChon = new JPanel();
		LHD_panelDSSPChon.setBounds(23, 301, 865, 327);
		panelLapHoaDon.add(LHD_panelDSSPChon);
		LHD_panelDSSPChon.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "S\u1EA3n ph\u1EA9m ch\u1ECDn", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		LHD_panelDSSPChon.setLayout(null);
		
		JScrollPane LHD_spDSSPChon = new JScrollPane();
		LHD_spDSSPChon.setBackground(new Color(255, 255, 255));
		LHD_spDSSPChon.setBounds(6, 15, 849, 268);
		LHD_panelDSSPChon.add(LHD_spDSSPChon);
		LHD_tableDSSPChon = new JTable() {
			@Override 
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		LHD_tableDSSPChon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		LHD_tableDSSPChon.getTableHeader().setReorderingAllowed(false);
		LHD_tableDSSPChon.setBackground(new Color(255, 255, 255));
		LHD_tableDSSPChon.setRowHeight(30);
		LHD_tableDSSPChon.setModel(LHD_tModelDSSPChon);
		LHD_spDSSPChon.setViewportView(LHD_tableDSSPChon);
		
		JButton LHD_btnXoaSanPham = new JButton("Xóa sản phẩm");
		LHD_btnXoaSanPham.setIcon(new ImageIcon("image/icon/bin.png"));
		LHD_btnXoaSanPham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LHD_btnXoaSanPham_XoaSanPhamChon();
		}});
		LHD_btnXoaSanPham.setBounds(308, 294, 130, 26);
		LHD_panelDSSPChon.add(LHD_btnXoaSanPham);
		LHD_btnXoaSanPham.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JPanel panelXemHoaDon = 
				new JPanel();
		tabbedPane.addTab("Xem hóa đơn", null, panelXemHoaDon, null);
		panelXemHoaDon.setLayout(null);
		
		JPanel XHD_panelDSHD = new JPanel();
		XHD_panelDSHD.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		XHD_panelDSHD.setBounds(0, 11, 557, 617);
		panelXemHoaDon.add(XHD_panelDSHD);
		XHD_panelDSHD.setLayout(null);
		
		JScrollPane XHD_spDSHD = new JScrollPane();
		XHD_spDSHD.setBounds(6, 83, 545, 528);
		XHD_spDSHD.setEnabled(false);
		XHD_panelDSHD.add(XHD_spDSHD);
		
		XHD_tableDSHD = new JTable();
		XHD_tableDSHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		XHD_tableDSHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				XHD_XemHoaDon();
			}
		});
		XHD_spDSHD.setViewportView(XHD_tableDSHD);
		XHD_tModelDSHD = new DefaultTableModel(
				new Object[][] {}, new String[] {"Mã hóa đơn", "Người bán","Khách hàng","Ngày lập", "Tổng tiền"}
				) {
			@Override
			public boolean isCellEditable(int row, int height) {
				return false;
			}
		};
		XHD_tableDSHD.getTableHeader().setReorderingAllowed(false);;
		XHD_tableDSHD.setRowHeight(20);
		XHD_tableDSHD.setModel(XHD_tModelDSHD);
		XHD_cbTimKiemHD.setModel(new DefaultComboBoxModel(new String[] {"", "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng"}));
		
		XHD_cbTimKiemHD.setBackground(Color.WHITE);
		XHD_cbTimKiemHD.setBounds(78, 41, 113, 21);
		XHD_panelDSHD.add(XHD_cbTimKiemHD);
		
		XHD_tfTimKiemHD = new JTextField();
		XHD_tfTimKiemHD.setColumns(10);
		XHD_tfTimKiemHD.setBackground(Color.WHITE);
		XHD_tfTimKiemHD.setBounds(201, 41, 215, 19);
		XHD_panelDSHD.add(XHD_tfTimKiemHD);
		
		JLabel lblTimKiem = new JLabel("Tìm kiếm");
		lblTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTimKiem.setBounds(10, 41, 69, 19);
		XHD_panelDSHD.add(lblTimKiem);
		
		JButton XHD_btnTimKiemHD = new JButton("Tìm");
		XHD_btnTimKiemHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XHD_btnTimKiemHD_TimKiemHD();
			}
		});
		XHD_btnTimKiemHD.setIcon(new ImageIcon("D:\\Study Folder\\SGU\\2022-2023 HK2\\Java\\Project\\image\\icon\\search.png"));
		XHD_btnTimKiemHD.setBounds(425, 11, 122, 29);
		XHD_panelDSHD.add(XHD_btnTimKiemHD);
		
		JButton XHD_btnHuyTimKiemHD = new JButton("Hủy");
		XHD_btnHuyTimKiemHD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XHD_btnHuyTimKiemHD_HuyTimKiemHD();
			}
		});
		XHD_btnHuyTimKiemHD.setIcon(new ImageIcon("D:\\Study Folder\\SGU\\2022-2023 HK2\\Java\\Project\\image\\icon\\close.png"));
		XHD_btnHuyTimKiemHD.setBounds(426, 43, 122, 29);
		XHD_panelDSHD.add(XHD_btnHuyTimKiemHD);
		
		JPanel XHD_panelTTHD = new JPanel();
		XHD_panelTTHD.setBorder(new TitledBorder(null, "Th\u00F4ng tin h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		XHD_panelTTHD.setBounds(567, 11, 667, 617);
		panelXemHoaDon.add(XHD_panelTTHD);
		XHD_panelTTHD.setLayout(null);
		
		XHD_tfKhachHang = new JTextField((String) null);
		XHD_tfKhachHang.setEditable(false);
		XHD_tfKhachHang.setColumns(10);
		XHD_tfKhachHang.setBounds(148, 75, 150, 19);
		XHD_panelTTHD.add(XHD_tfKhachHang);
		
		XHD_tfNhanVien = new JTextField((String) null);
		XHD_tfNhanVien.setEditable(false);
		XHD_tfNhanVien.setColumns(10);
		XHD_tfNhanVien.setBounds(148, 116, 150, 19);
		XHD_panelTTHD.add(XHD_tfNhanVien);
		
		JLabel XHD_lblTongTien = new JLabel("Tổng tiền");
		XHD_lblTongTien.setBounds(36, 206, 87, 13);
		XHD_panelTTHD.add(XHD_lblTongTien);
		
		JLabel XHD_lblMaKH = new JLabel();
		XHD_lblMaKH.setText("Mã khách hàng");
		XHD_lblMaKH.setBounds(36, 74, 87, 20);
		XHD_panelTTHD.add(XHD_lblMaKH);
		
		JLabel XHD_lblMaNV = new JLabel();
		XHD_lblMaNV.setText("Mã nhân viên");
		XHD_lblMaNV.setBounds(36, 116, 87, 20);
		XHD_panelTTHD.add(XHD_lblMaNV);
		
		JLabel XHD_lblNgayLap = new JLabel();
		XHD_lblNgayLap.setText("Ngày lập");
		XHD_lblNgayLap.setBounds(36, 159, 87, 20);
		XHD_panelTTHD.add(XHD_lblNgayLap);
		
		XHD_tfTongTien = new JTextField("0.0");
		XHD_tfTongTien.setEditable(false);
		XHD_tfTongTien.setColumns(10);
		XHD_tfTongTien.setBounds(148, 203, 150, 19);
		XHD_panelTTHD.add(XHD_tfTongTien);
		
		JPanel XHD_panelSanPham = new JPanel();
		XHD_panelSanPham.setBorder(new TitledBorder(null, "S\u1EA3n ph\u1EA9m mua", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		XHD_panelSanPham.setBounds(10, 260, 643, 346);
		XHD_panelTTHD.add(XHD_panelSanPham);
		XHD_panelSanPham.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane XHD_spCTHD = new JScrollPane();
		XHD_panelSanPham.add(XHD_spCTHD);
		
		XHD_tableCTHD = new JTable();
		XHD_tableCTHD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		XHD_tModelDSCTHD = new DefaultTableModel(new Object[][] {}, new String[] {"Mã sản phẩm", "Tên sản phẩm","Đơn giá", "Số lượng", "Thành tiền"}) {
			@Override 
			public boolean isCellEditable(int row, int height) {
				return false;
			}
		};;
		XHD_tableCTHD.getTableHeader().setReorderingAllowed(false);
		XHD_tableCTHD.setRowHeight(20);
		XHD_tableCTHD.setModel(XHD_tModelDSCTHD);
		XHD_spCTHD.setViewportView(XHD_tableCTHD);
		
		XHD_tfMaHD = new JTextField((String) null);
		XHD_tfMaHD.setEditable(false);
		XHD_tfMaHD.setColumns(10);
		XHD_tfMaHD.setBounds(148, 30, 150, 19);
		XHD_panelTTHD.add(XHD_tfMaHD);
		
		JLabel XHD_lblMaHD = new JLabel();
		XHD_lblMaHD.setText("Mã hóa đơn");
		XHD_lblMaHD.setBounds(36, 29, 87, 20);
		XHD_panelTTHD.add(XHD_lblMaHD);
		
		XHD_tfNgayLap = new JTextField((String) null);
		XHD_tfNgayLap.setEditable(false);
		XHD_tfNgayLap.setColumns(10);
		XHD_tfNgayLap.setBounds(148, 160, 150, 19);
		XHD_panelTTHD.add(XHD_tfNgayLap);
		
		LHD_hienThiDSSP(BLL.SanPhamBLL.layDanhSachSP());
		LHD_LayThongTinKhachHang(BLL.KhachHangBLL.layDanhSachKhachHang());
		XHD_HienThiDSHD(BLL.HoaDonBLL.layDanhSachHD());
	}
	
	//--------------------------------CHUC NANG---------------------------------------
	
//	private void themSanPham() {
//		try {
//				int i = LHD_tableDSSP.getSelectedRow();
//				if (soLuongSP <= LHD_DSSP.get(i).getSoLuong() && soLuongSP > 0 && i >= 0) {
//					boolean checked = true;
//					for (SanPham item: LHD_DSSPChon) 
//						if (item.getMaSanPham().equals(LHD_DSSP.get(i).getMaSanPham())){
//							checked = false;
//							break;
//						}
//					if(checked) {
//						SanPham sp = new SanPham();
//						sp.setMaSanPham(LHD_DSSP.get(i).getMaSanPham());
//						sp.setTenSanPham(LHD_DSSP.get(i).getTenSanPham());
//						sp.setSoLuong(soLuongSP);
//						sp.setGiaBan(LHD_DSSP.get(i).getGiaBan());
//						LHD_DSSPChon.add(sp);
//						LHD_hienThiDSSPChon(LHD_DSSPChon);
//						
//						// Xoa di sp da chon 
//						LHD_DSSP.get(i).setSoLuong(LHD_DSSP.get(i).getSoLuong() - sp.getSoLuong());
//						LHD_hienThiDSSP(LHD_DSSP);
//						
//						tfSoLuong.setText("");
//						
//					} else {
//						JOptionPane.showMessageDialog(null, "Sản phẩm đã được chọn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//					}
//				}
//			} else {
//				JOptionPane.showMessageDialog(null, "Số lượng sản phẩm không hợp lệ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//			}
//		} catch(Exception ex) {
//			System.out.println(ex.getMessage());
//		}
//		
//	}
	
	private void LHD_btnChonSanPham_ChonSanPham() {
		int i = LHD_tableDSSP.getSelectedRow();
		if(i >= 0) {
			try {
				int soLuong  = Integer.parseInt(LHD_tfNhapSoLuong.getText().toString().trim().equals("") ? "0" : LHD_tfNhapSoLuong.getText().toString().trim());
				if(soLuong <= LHD_DSSP.get(i).getSoLuong() && soLuong > 0) {
					for(SanPham item : LHD_DSSPChon) {
						if(item.getMaSanPham().equals(LHD_DSSP.get(i).getMaSanPham())) {
							JOptionPane.showMessageDialog(null, "Sản phẩm đã được chọn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}
					SanPham sp = new SanPham();
					sp.setSoLuong(soLuong);
					sp.setMaSanPham(LHD_DSSP.get(i).getMaSanPham());
					sp.setTenSanPham(LHD_DSSP.get(i).getTenSanPham());
					sp.setGiaBan(LHD_DSSP.get(i).getGiaBan());
					sp.setDonVi(LHD_DSSP.get(i).getDonVi());
					LHD_DSSP.get(i).setSoLuong(LHD_DSSP.get(i).getSoLuong()- soLuong);
					LHD_DSSPChon.add(sp);
					ArrayList<SanPham> LHD_DSSP1 = (ArrayList<SanPham>) LHD_DSSP.clone();
					LHD_hienThiDSSP(LHD_DSSP1);
					LHD_hienThiDSSPChon(LHD_DSSPChon);
					LHD_tfNhapSoLuong.setText("");
					int tongSoLuong = Integer.parseInt(LHD_tfTongSoLuong.getText()) + soLuong;
					double thanhTien = soLuong * sp.getGiaBan();
					double tongTien = Double.parseDouble(LHD_tfTongTien.getText()) + thanhTien;
					LHD_tfTongSoLuong.setText(tongSoLuong + "");
					LHD_tfTongTien.setText(tongTien + "");
				} else {
					JOptionPane.showMessageDialog(null, "Sô lượng sản phẩm không hợp lệ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Sô lượng sản phẩm không hợp lệ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private void LHD_btnXoaSanPham_XoaSanPhamChon(){
		int i = LHD_tableDSSPChon.getSelectedRow();
		if(i >=0 ) { 
			SanPham sp = LHD_DSSPChon.remove(i);
			LHD_hienThiDSSPChon(LHD_DSSPChon);
			for (SanPham sanPham : LHD_DSSP)
				if(sanPham.getMaSanPham().equals(sp.getMaSanPham())) {
					sanPham.setSoLuong(sanPham.getSoLuong() + sp.getSoLuong());
					LHD_tfTongSoLuong.setText((Integer.parseInt(LHD_tfTongSoLuong.getText()) - sp.getSoLuong())+"");
					LHD_tfTongTien.setText((Double.parseDouble(LHD_tfTongTien.getText()) - sp.getSoLuong() * sp.getGiaBan()) + "");
				}
			LHD_hienThiDSSP(LHD_DSSP);
		}
	}
	
	private void LHD_hienThiDSSPChon(ArrayList<SanPham> dssp) {
		LHD_DSSPChon = dssp;
		LHD_tModelDSSPChon.setRowCount(0);
		for(SanPham sp: LHD_DSSPChon) {
			LHD_tModelDSSPChon.addRow(new Object[] {sp.getMaSanPham(),sp.getTenSanPham(),sp.getDonVi(),sp.getGiaBan(),sp.getSoLuong(),sp.getGiaBan()*sp.getSoLuong()});
		}
	}
	
	private void LHD_hienThiDSSP(ArrayList<SanPham> dssp) {
		LHD_tModelDSSP.setRowCount(0);
		LHD_DSSP = new ArrayList<SanPham>();
		for(SanPham sp: dssp) {
			LHD_tModelDSSP.addRow(new Object[] {"image/san_pham/"+sp.getFilePath(),sp.getMaSanPham(),sp.getTenSanPham(),sp.getDonVi(),sp.getGiaBan(),sp.getSoLuong()});
			LHD_tableDSSP.getColumnModel().getColumn(0).setCellRenderer(new ImageRender());
			LHD_DSSP.add(sp);
		}
//		LHD_DSSP = dssp1;
	}
	

	private void LHD_cbKhachHang_LayThongTinKhachHang(){
		int i = LHD_cbKhachHang.getSelectedIndex();
		if (i > 0 )
			LHD_tfSoDienThoai.setText(LHD_DSKH.get(i - 1).getSoDienThoai());
	}
	
	private void LHD_btnTimKiemSP_TimKiemSP() {
		String timKiem = LHD_tfTimKiemSP.getText().toUpperCase();
		ArrayList<SanPham> dssp = new ArrayList<SanPham>();
		if(!timKiem.trim().equals("")) {
			LHD_hienThiDSSP(BLL.SanPhamBLL.layDanhSachSP());
			for(SanPham sp : LHD_DSSP) {
				if(sp.getMaSanPham().toUpperCase().contains(timKiem) || sp.getTenSanPham().toUpperCase().contains(timKiem)) {
					dssp.add(sp);
				}
			}
			LHD_hienThiDSSP(dssp);
		}
	}
	
	private void LHD_btnHuyTimKiem_HuyTimKiem() {
		LHD_hienThiDSSP(BLL.SanPhamBLL.layDanhSachSP());
	}
	
	private boolean kiemTraThongTinHD() {
		if(!LHD_cbKhachHang.getSelectedItem().equals("")) {
			if(LHD_DSSPChon.size() > 0) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
		} else {
			JOptionPane.showMessageDialog(null, "Chưa chọn khách hàng", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}
	
	private void LHD_LapHoaDon(TaiKhoan taiKhoan) {
		if(kiemTraThongTinHD()) {
			KhachHang khachHang = new KhachHang();
			khachHang.setMa(LHD_DSKH.get(LHD_cbKhachHang.getSelectedIndex()-1).getMa());
			NhanVien nhanVien = new NhanVien();
			nhanVien.setMa(taiKhoan.getTenDangNhap());
			
//			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm");  
//			LocalDateTime now = LocalDateTime.now();  
//			String ngayLap = dtf.format(now).toString();
			String ngayLap = "";
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			if(LHD_dcNgayLap.getDate() != null) {
				try {
					ngayLap = sdf.format(LHD_dcNgayLap.getDate());
				} catch (Exception e) {
					System.out.print(e.getMessage());
				}
			}
	
			double tongTien = 0;
			ArrayList<ChiTietPhieu> dsct = new ArrayList<ChiTietPhieu>();
			for(SanPham sp: LHD_DSSPChon) {
				double thanhTien = sp.getGiaBan() * sp.getSoLuong();
				ChiTietPhieu ctp = new ChiTietPhieu(sp, thanhTien);
				dsct.add(ctp);
				tongTien += thanhTien;
			}
			
			HoaDon hoaDon = new HoaDon(BLL.HoaDonBLL.taoMaHoaDon(),khachHang,nhanVien,dsct,ngayLap,tongTien);
			int c = JOptionPane.showConfirmDialog(null, "Xác nhận lập hóa đơn ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
			boolean checked = true;
			if(c == 0 && BLL.HoaDonBLL.lapHoaDon(hoaDon)) {
				for(ChiTietPhieu ctp1: hoaDon.getDSCT()) {
					if(!BLL.TheKhoBLL.capNhatTheKho(hoaDon.getMaPhieu(),hoaDon.getNgayLap(),ctp1)) {
						checked = false;
						break;
					}
				}
				if(checked) {
					JOptionPane.showMessageDialog(null, "Lập hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					LHD_tModelDSSPChon.setRowCount(0);
					LHD_DSSPChon.clear();
					XHD_HienThiDSHD(BLL.HoaDonBLL.layDanhSachHD());
					
					
				} else JOptionPane.showMessageDialog(null, "Lập hóa đơn không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
			else JOptionPane.showMessageDialog(null, "Lập hóa đơn không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
		LHD_tfTongTien.setText("0.0");
		LHD_tfTongSoLuong.setText("0");
	}
	
	private void LHD_LayThongTinKhachHang(ArrayList<KhachHang> dskh) {
		LHD_DSKH = dskh;
		LHD_cbKhachHang.removeAllItems();
		LHD_cbKhachHang.addItem("");
		for(KhachHang i : LHD_DSKH)
			LHD_cbKhachHang.addItem(i.getHoTen() +"-"+ i.getMa());
	}
	
	private void XHD_HienThiDSHD(ArrayList<HoaDon> dshd) {
		XHD_tModelDSHD.setRowCount(0);
		XHD_DSHD = dshd;
		for (HoaDon hoaDon: XHD_DSHD) {
			XHD_tModelDSHD.addRow(new Object[] {hoaDon.getMaPhieu(),hoaDon.getNhanVien().getMa(),hoaDon.getKhachHang().getMa(), 
					hoaDon.getNgayLap(), hoaDon.getTongTien()});
		}
	}
	
//	private void timKiemHoaDon(String loaiTimKiem, String tenTimKiem) {
//		ArrayList<HoaDon> XHD_DSHD = new ArrayList<HoaDon>();
//		switch(loaiTimKiem){
//		case "Mã hóa đơn":
//			for (HoaDon hd: XHD_DSHD) {
//				if (hd.getMaPhieu().equals(tenTimKiem.toUpperCase())) {
//					XHD_DSHD.add(hd);
//				}
//			}
//			break;
//		case "Mã khách hàng":
//			for (HoaDon hd: XHD_DSHD) {
//				if (hd.getKhachHang().getMa().equals(tenTimKiem.toUpperCase())) {
//					XHD_DSHD.add(hd);
//				}
//			}
//			break;
//		case "Mã nhân viên":
//			for (HoaDon hd: XHD_DSHD) {
//				if (hd.getNhanVien().getMa().equals(tenTimKiem.toUpperCase())) {
//					XHD_DSHD.add(hd);
//				}
//			}
//			break;
//		case "Tên khách hàng":
//			for (HoaDon hd: XHD_DSHD) {
//				if (hd.getKhachHang().getHoTen().equals(tenTimKiem.toUpperCase())) {
//					XHD_DSHD.add(hd);
//				}
//			}
//			break;
//		case "Tên nhân viên":for (HoaDon hd: XHD_DSHD) {
//			if (hd.getNhanVien().getHoTen().equals(tenTimKiem.toUpperCase())) {
//				XHD_DSHD.add(hd);
//			}
//		}
//			break;
//		}
//		XHD_HienThiDSHD(XHD_DSHD);
//	}
	
	private void XHD_btnTimKiemHD_TimKiemHD() {
		int i = XHD_cbTimKiemHD.getSelectedIndex();
		String timKiem = XHD_tfTimKiemHD.getText();
		ArrayList<HoaDon> DSHDTimKiem = new ArrayList<HoaDon>();
		if(i > 0 && !timKiem.equals("")) {
			XHD_HienThiDSHD(BLL.HoaDonBLL.layDanhSachHD());
			switch(i) {
				case 1:
					for(HoaDon hd : XHD_DSHD) {
						if(hd.getMaPhieu().contains(timKiem)) {
							DSHDTimKiem.add(hd);
						}
					}
					break;
				case 2:
					for(HoaDon hd : XHD_DSHD) {
						if(hd.getNhanVien().getMa().contains(timKiem)) {
							DSHDTimKiem.add(hd);
						}
					}
					break;
				case 3:
					for(HoaDon hd : XHD_DSHD) {
						if(hd.getKhachHang().getMa().contains(timKiem)) {
							DSHDTimKiem.add(hd);
						}
					}
					break;
				default:
					break;
			}
			XHD_HienThiDSHD(DSHDTimKiem);
		} else {
			XHD_HienThiDSHD(BLL.HoaDonBLL.layDanhSachHD());
		}
	}
	
	private void XHD_btnHuyTimKiemHD_HuyTimKiemHD(){
		XHD_HienThiDSHD(BLL.HoaDonBLL.layDanhSachHD());
	}

	private void XHD_HienThiDSCTHD(int i) {
		XHD_tModelDSCTHD.setRowCount(0);
		XHD_DSCTHD = XHD_DSHD.get(i).getDSCT();
		for(ChiTietPhieu cthd : XHD_DSCTHD) {
			String [] ct = {cthd.getSanPham().getMaSanPham(),cthd.getSanPham().getTenSanPham(),cthd.getSanPham().getGiaBan()+"",cthd.getSanPham().getSoLuong() + "",cthd.getThanhTien() + ""};
			XHD_tModelDSCTHD.addRow(ct);
		}
	}
	
	private void XHD_XemHoaDon() {
		int i = XHD_tableDSHD.getSelectedRow();
		if (i >= 0) {
			XHD_tfMaHD.setText(XHD_DSHD.get(i).getMaPhieu());
			XHD_tfKhachHang.setText(XHD_DSHD.get(i).getKhachHang().getMa());
			XHD_tfNhanVien.setText(XHD_DSHD.get(i).getNhanVien().getMa());
			XHD_tfNgayLap.setText(XHD_DSHD.get(i).getNgayLap());
			XHD_tfTongTien.setText(XHD_DSHD.get(i).getTongTien() + "");
			XHD_HienThiDSCTHD(i);
		}
	}
}
