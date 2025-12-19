//package GUI;
//
//import javax.swing.JPanel;
//import java.awt.Color;
//
//public class KhachHangGUI extends JPanel {
//
//	/**
//	 * Create the panel.
//	 */
//	public KhachHangGUI() {
//		setBackground(new Color(255, 255, 255));
//		setSize(1269,679);
//		setLayout(null);
//	}
//}
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BLL.KhachHangBLL;
import DTO.KhachHang;
import javax.swing.ImageIcon;
public class KhachHangGUI extends JPanel {
	private JTextField textFieldSDT;
	private JTextField textFieldMAKH;
	private JTextField textFieldTenKH;
	private JTextField textFieldDIACHI;
	private JTextField tfTimKiem;
	private JComboBox cbTimKiem;
	private JButton btnXcNhn;
	private JButton btnHy;
	String confirmMode = "";
	private DefaultTableModel tableModel;
	private JTable table = new JTable(){
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};;
	private ArrayList<KhachHang> danhSachKhachHang = new ArrayList<KhachHang>();
	private JDateChooser dateChooserNgaySinh;
	/**
	 * Create the panel.
	 */
	public KhachHangGUI() {
		setSize(1269,679);
		setLayout(null);
		
		JPanel panelThongTinKH = new JPanel();
		panelThongTinKH.setLayout(null);
		panelThongTinKH.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Thông Tin Khách Hàng", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelThongTinKH.setBounds(0, 31, 494, 279);
		add(panelThongTinKH);
		
		JLabel lbltenKH = new JLabel("Tên Khách Hàng");
		lbltenKH.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lbltenKH.setBounds(10, 60, 98, 19);
		panelThongTinKH.add(lbltenKH);
		
		JLabel lblMaKH = new JLabel("Mã Khách Hàng");
		lblMaKH.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMaKH.setBounds(10, 29, 105, 19);
		panelThongTinKH.add(lblMaKH);
		
		textFieldTenKH = new JTextField();
		textFieldTenKH.setEditable(false);
		textFieldTenKH.setColumns(10);
		textFieldTenKH.setBounds(124, 60, 230, 19);
		panelThongTinKH.add(textFieldTenKH);
		
		textFieldMAKH = new JTextField();
		textFieldMAKH.setEditable(false);
		textFieldMAKH.setColumns(10);
		textFieldMAKH.setBounds(124, 29, 230, 19);
		panelThongTinKH.add(textFieldMAKH);
		
		JLabel lblDiaChi = new JLabel("Địa Chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDiaChi.setBounds(10, 90, 98, 19);
		panelThongTinKH.add(lblDiaChi);
		
		JLabel lblSDT = new JLabel("Số Điện Thoại");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSDT.setBounds(10, 119, 104, 19);
		panelThongTinKH.add(lblSDT);
		
		textFieldSDT = new JTextField();
		textFieldSDT.setEditable(false);
		textFieldSDT.setColumns(10);
		textFieldSDT.setBounds(124, 119, 230, 19);
		panelThongTinKH.add(textFieldSDT);
		
		textFieldDIACHI = new JTextField();
		textFieldDIACHI.setEditable(false);
		textFieldDIACHI.setColumns(10);
		textFieldDIACHI.setBounds(124, 90, 230, 19);
		panelThongTinKH.add(textFieldDIACHI);
		
		JLabel lblNgaySinh = new JLabel("Ngày Sinh");
		lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNgaySinh.setBounds(10, 148, 104, 19);
		panelThongTinKH.add(lblNgaySinh);
		
		dateChooserNgaySinh = new JDateChooser();
		dateChooserNgaySinh.setDateFormatString("dd-MM-yyyy");
		dateChooserNgaySinh.setBackground(new Color(255, 255, 255));
		dateChooserNgaySinh.setEnabled(false);
		dateChooserNgaySinh.setBounds(124, 148, 230, 20);
		panelThongTinKH.add(dateChooserNgaySinh);
		
		JPanel panelTimKiem = new JPanel();
		panelTimKiem.setLayout(null);
		panelTimKiem.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTimKiem.setBounds(516, 51, 743, 45);
		add(panelTimKiem);
		
		JLabel lblTmKim = new JLabel("Tìm Kiếm");
		lblTmKim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTmKim.setBounds(10, 10, 69, 19);
		panelTimKiem.add(lblTmKim);
		
		tfTimKiem = new JTextField();
		tfTimKiem.setColumns(10);
		tfTimKiem.setBounds(199, 10, 518, 19);
		panelTimKiem.add(tfTimKiem);
		tfTimKiem.getDocument().addDocumentListener((new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				timKiemKH();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				timKiemKH();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				timKiemKH();	
			}
		}));
		cbTimKiem = new JComboBox();
		cbTimKiem.setBounds(76, 10, 113, 21);
		panelTimKiem.add(cbTimKiem);
		cbTimKiem.addItem("");
		cbTimKiem.addItem("Tên Khách Hàng");
		cbTimKiem.addItem("Mã Khách Hàng");
		cbTimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timKiemKH();
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(372, 345, 134, 88);
		add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnXcNhn = new JButton("Xác Nhận");
		btnXcNhn.setIcon(new ImageIcon("image/icon/check-mark.png"));
		btnXcNhn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnXcNhn.setEnabled(false);
		panel_1.add(btnXcNhn);
		btnXcNhn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xacNhan();
			}
		});
		
		btnHy = new JButton("Hủy");
		btnHy.setIcon(new ImageIcon("image/icon/close.png"));
		btnHy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnHy.setEnabled(false);
		panel_1.add(btnHy);
		btnHy.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				refresh();
				hienThiDanhSachKH(BLL.KhachHangBLL.layDanhSachKhachHang());
				table.setEnabled(true);
				isEnable(false);
				btnXcNhn.setEnabled(false);
				btnHy.setEnabled(false);
				confirmMode = "";
			}

		});
		
		
		
		JPanel panelChucNang = new JPanel();
		panelChucNang.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ch\u1EE9c  n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelChucNang.setBounds(10, 337, 352, 96);
		add(panelChucNang);
		panelChucNang.setLayout(new GridLayout(1, 1, 0, 0));
		
		JButton btnThm = new JButton("Thêm");
		btnThm.setIcon(new ImageIcon("image/icon/plus.png"));
		btnThm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelChucNang.add(btnThm);
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				themKH();
			}
		});
		
		JButton btnXa = new JButton("Xóa");
		btnXa.setIcon(new ImageIcon("image/icon/bin.png"));
		btnXa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelChucNang.add(btnXa);
		btnXa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xoaKH();
			}
		});
		
		JButton btnSa = new JButton("Sửa");
		btnSa.setIcon(new ImageIcon("image/icon/edit.png"));
		btnSa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelChucNang.add(btnSa);
		btnSa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaKH();
			}
		});
		JPanel panelDanhSachSP = new JPanel();
		panelDanhSachSP.setBorder(new TitledBorder(null, "Danh Sách Khách Hàng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDanhSachSP.setBounds(516, 118, 743, 550);
		add(panelDanhSachSP);
		panelDanhSachSP.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		panelDanhSachSP.add(scrollPane);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowHeight(30);
		scrollPane.setColumnHeaderView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chonKhachHang();
			}
		});
		
		tableModel = new DefaultTableModel(
				new Object[][] {},
				new String[] {
					"Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ" ,"Số Điện Thoại", "Ngày Sinh"
				} 
			);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		
		hienThiDanhSachKH(danhSachKhachHang);
	}
	


	private void hienThiDanhSachKH(ArrayList<KhachHang> dskh) {
		tableModel.setRowCount(0);
		danhSachKhachHang = KhachHangBLL.layDanhSachKhachHang();
		for(KhachHang kh : danhSachKhachHang){
			String[] KhachHang = {kh.getMa(),kh.getHoTen(),kh.getDiaChi(),kh.getSoDienThoai(),kh.getNgaySinh()};
			tableModel.addRow(KhachHang);
		}
	}


private void chonKhachHang() {
	int i = table.getSelectedRow();
	if(i >= 0) {
		try {
			textFieldTenKH.setText(table.getValueAt(i, 1)+"");
			textFieldMAKH.setText(table.getValueAt(i, 0)+"");
			textFieldDIACHI.setText(table.getValueAt(i, 2)+"");
			textFieldSDT.setText(table.getValueAt(i, 3)+"");
			Date d = new SimpleDateFormat("dd-MM-yyyy").parse(table.getValueAt(i, 4)+"");
			dateChooserNgaySinh.setDate(d);
	
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}	
private void themKH() {
	isEnable(true);
	table.setEnabled(false);
	refresh();
	textFieldMAKH.setText(BLL.KhachHangBLL.taoMaKhachHang());
	confirmMode = "Them";
}

// Dieu chinh de xoa san pham
private void xoaKH() {
	isEnable(false);
	table.setEnabled(true);
	confirmMode = "Xoa";
}

// Dieu chinh de sua san pham
private void suaKH() {
	isEnable(true);
	table.setEnabled(true);
	confirmMode = "Sua";
}	
private KhachHang themVaSua() {
	String maKH = textFieldMAKH.getText();
	String tenKH = textFieldTenKH.getText();
	String diachi = textFieldDIACHI.getText();
	String SDT = textFieldSDT.getText();
	String ngaysinh = "";
	try {
		ngaysinh = new SimpleDateFormat("dd-MM-yyyy").format(dateChooserNgaySinh.getDate());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	KhachHang KhachHang = new KhachHang(maKH, tenKH, diachi, SDT ,ngaysinh);
	return KhachHang;
		
		
	
	
	}
private void refresh() {
	textFieldMAKH.setText("");
	textFieldTenKH.setText("");
	textFieldDIACHI.setText("");
	textFieldSDT.setText("");
	dateChooserNgaySinh.setDate(null);;
}
private void isEnable(boolean i) {
	textFieldTenKH.setEditable(i);
	textFieldDIACHI.setEditable(i);
	textFieldSDT.setEditable(i);
	dateChooserNgaySinh.setEnabled(i);
	btnHy.setEnabled(true);
	btnXcNhn.setEnabled(true);
}
//private String taoMaKhachHang() {
//	int i = BLL.KhachHangBLL.laySoLuongKhachHang();
//	return "KH" + (i+1);
//}

private void xacNhan() {
	if(confirmMode == "Them") {
		try {
			KhachHang KhachHang = themVaSua();
			int c = JOptionPane.showConfirmDialog(null, "Xác Nhận Thêm Khách Hàng", "Xác Nhận", JOptionPane.YES_NO_OPTION);
			if(c == 0 && BLL.KhachHangBLL.themKhachHang(KhachHang)) {
				JOptionPane.showMessageDialog(null, "Thêm Thành Công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
			}
			else JOptionPane.showMessageDialog(null, "Thêm Không Thành Công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	else if(confirmMode == "Xoa") {
		try {
			int i = table.getSelectedRow();
			if(i>=0) {
				int c = JOptionPane.showConfirmDialog(null, "Xác Nhận Xóa Khách Hàng ?", "Xác Nhận", JOptionPane.YES_NO_OPTION);
				if(c == 0 && BLL.KhachHangBLL.xoaKhachHang(danhSachKhachHang.get(i))) {
					JOptionPane.showMessageDialog(null,"Xóa Thành Công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
				}
				else JOptionPane.showMessageDialog( null, "Xóa không thành công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	else if(confirmMode == "Sua") {
		try {
			KhachHang KhachHang = themVaSua();
			int c = JOptionPane.showConfirmDialog(null, "Xác Nhận Sửa Khách Hàng", "Xác Nhận", JOptionPane.YES_NO_OPTION);
			if(c == 0 && BLL.KhachHangBLL.SuaKhachHang(KhachHang)) {
				JOptionPane.showMessageDialog(null, "Sửa thành Công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
			}
			else JOptionPane.showMessageDialog(null, "Sửa không thành Công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	refresh();
	hienThiDanhSachKH(BLL.KhachHangBLL.layDanhSachKhachHang());
}
private void timKiemKH() {
	try {
		ArrayList<KhachHang> danhSachKhachHang2 = new ArrayList<KhachHang>();

		int loaiTimKiem = cbTimKiem.getSelectedIndex();
		String timKiem = tfTimKiem.getText().toLowerCase();
		danhSachKhachHang2 = danhSachKhachHang;
		tableModel.setRowCount(0);
		if(loaiTimKiem > 0) {
			for(KhachHang kh : danhSachKhachHang2) {
				boolean check = false;
				if(loaiTimKiem == 1 && kh.getHoTen().toLowerCase().contains(timKiem)) check = true;
				else if(loaiTimKiem == 2 && kh.getMa().toLowerCase().contains(timKiem)) check = true;
				if(check == true) {
					String[] KhachHang = {kh.getMa(),kh.getHoTen(),kh.getDiaChi(),kh.getSoDienThoai(),kh.getNgaySinh()};
					tableModel.addRow(KhachHang);
				}
			}
			if(tfTimKiem.getText().equals("")) {
				hienThiDanhSachKH(danhSachKhachHang2);
			}
		} else {
			hienThiDanhSachKH(BLL.KhachHangBLL.layDanhSachKhachHang());
		}
	}
	catch(Exception ex) {
		System.out.print(ex.getMessage());
	}
	
	
	

}
}