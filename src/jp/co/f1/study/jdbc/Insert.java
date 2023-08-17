package jp.co.f1.study.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {

	public static String RDB_DRIVE = "com.mysql.jdbc.Driver";
	public static String URL = "jdbc:mysql://localhost/mybookdb";
	public static String USER = "root";
	public static String PASSWORD = "password";

	public static void main(String[] args) {
		Connection con = null;
		Statement smt = null;

		try {
			// ドライバを読み込む
			Class.forName(RDB_DRIVE);
			// データベースへ接続する
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			// ステートメント作成する
			smt = con.createStatement();
			// 追加したいデータのSQL文
			String sql = "INSERT INTO bookinfo(isbn, title, price) VALUES ('00006', 'Shell', 1006)";
			// 追加SQL文を実行する
			int rowsCount = smt.executeUpdate(sql);
			System.out.println(rowsCount + " レコード追加しました。");

			// すべてのデータを表示する
			String listSql = "SELECT * FROM bookinfo";
			ResultSet rs = smt.executeQuery(listSql);

			while (rs.next()) {
				System.out.println("ISBN: " + rs.getString("isbn") + " Title: " + rs.getString("title") + " Price: "
						+ rs.getString("price"));
			}

		} catch (Exception e) {
			System.out.println("エラー：" + e);
		} finally {
			try {
				if (smt != null) {
					System.out.println("Statementクローズします。");
					smt.close();
				}

				if (con != null) {
					System.out.println("DB接続をクローズします。");
					con.close();
				}

			} catch (SQLException ignore) {
				// 例外処理
			}
		}
	}

}
