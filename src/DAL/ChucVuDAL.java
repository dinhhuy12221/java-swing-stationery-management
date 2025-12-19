package DAL;

import java.util.ArrayList;

import DTO.ChucVu;

public class ChucVuDAL extends DatabaseAccess {
	public static ArrayList<ChucVu> layDanhSachLoaiCV(){
		ArrayList<ChucVu> danhSachLoaiCV = new ArrayList<ChucVu>();
		try {
			getConnection();
			String s = "SELECT * FROM `CHUC_VU`";
			statement = conn.createStatement();
			resultSet = statement.executeQuery(s);
			while(resultSet.next()) {
				String maLoaiCV = resultSet.getString(1);
				String tenLoaiCV = resultSet.getString(2);
				ChucVu loaiCV =  new ChucVu(maLoaiCV,tenLoaiCV);
				danhSachLoaiCV.add(loaiCV);
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		closeConnection();
		return danhSachLoaiCV;
	}
}
