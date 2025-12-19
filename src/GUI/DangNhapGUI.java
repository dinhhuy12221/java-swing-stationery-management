package GUI;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.border.MatteBorder;

import BLL.DangNhapBLL;
import DTO.TaiKhoan;
import DTO.ChucVu;

import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Toolkit;

public class DangNhapGUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfTenDangNhap;
	private JPasswordField tfMatKhau;

	/**
	 * Create the frame.
	 */
	public DangNhapGUI() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/icon/user.png"));
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 416);
		setLocationRelativeTo(contentPane);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTenDangNhap = new JLabel("Tên đăng nhập");
		lblTenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenDangNhap.setBounds(330, 76, 101, 31);
		contentPane.add(lblTenDangNhap);
		
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMatKhau.setBounds(330, 136, 101, 31);
		contentPane.add(lblMatKhau);
		
		JLabel lblDangNhap = new JLabel("Đăng nhập");
		lblDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDangNhap.setBounds(10, 24, 588, 31);
		contentPane.add(lblDangNhap);
		
		tfTenDangNhap = new JTextField("NV00001");
		tfTenDangNhap.setBounds(330, 106, 226, 19);
		contentPane.add(tfTenDangNhap);
		tfTenDangNhap.setColumns(10);
		
		tfMatKhau = new JPasswordField("123");
		tfMatKhau.setColumns(10);
		tfMatKhau.setBounds(330, 164, 226, 19);
		contentPane.add(tfMatKhau);
		
		// Button dang nhap
		JButton btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBackground(new Color(255, 255, 255));
		btnDangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenDangNhap = tfTenDangNhap.getText().toUpperCase();
				String matKhau = String.valueOf(tfMatKhau.getPassword());
				TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap,"",matKhau,new ChucVu(),"");
				TaiKhoan tk = new TaiKhoan();
				tk = DangNhapBLL.isLogin(taiKhoan);
				if (tk != null) {
					 JOptionPane.showMessageDialog(null,"Đăng nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					 GiaoDienGUI giaoDien = new GiaoDienGUI(tk);
					 giaoDien.setVisible(true);
					 dispose();
				}
				else{
					 JOptionPane.showMessageDialog(new DangNhapGUI(),"Đăng nhập thất bại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDangNhap.setBounds(380, 233, 101, 31);
		contentPane.add(btnDangNhap);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(53, 76, 226, 226);
		lblLogo.setIcon(new ImageIcon(new ImageIcon("image/extra_img/user_logo.jpg").getImage().getScaledInstance(lblLogo.getWidth(), lblLogo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(lblLogo);
	}
}
