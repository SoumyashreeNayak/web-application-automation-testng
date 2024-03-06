package app.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	public int addUser(User user) throws ClassNotFoundException, SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "SELECT * FROM user WHERE email=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, user.getEmail());
		ResultSet rs = ps.executeQuery();
		String testEmail = "";
		while (rs.next())
			testEmail = (rs.getString("email"));
		if (!testEmail.equals(""))
			return 0;
		String sqlInsert = "INSERT INTO user VALUES(?,?,?,?)";
		PreparedStatement ps2 = conn.prepareStatement(sqlInsert);
		ps2.setString(1, user.getFirstName());
		ps2.setString(2, user.getLastName());
		ps2.setString(3, user.getEmail());
		ps2.setString(4, user.getPassword());

		return ps2.executeUpdate();

	}

	public boolean validateLogin(String email, String password) throws ClassNotFoundException, SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "SELECT * FROM user WHERE email=? AND password=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, email);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		String testUserName = "";
		while (rs.next()) {
			testUserName = (rs.getString("first_name"));

		}
		if (!testUserName.equals(""))
			return true;
		return false;

	}

	public List<Item> getItems() throws ClassNotFoundException, SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "SELECT * FROM item";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<Item> items = new ArrayList<>();
		while (rs.next()) {
			Item item = new Item();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getDouble("price"));
			item.setImageUrl(rs.getString("image_url"));
			items.add(item);
		}
		return items;
	}

	public List<Item> searchItemsFromMenu(String query) throws ClassNotFoundException, SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "SELECT * FROM item WHERE name LIKE?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "%" + query + "%");
		ResultSet rs = ps.executeQuery();

		List<Item> items = new ArrayList<>();
		while (rs.next()) {
			Item item = new Item();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getDouble("price"));
			item.setImageUrl(rs.getString("image_url"));
			items.add(item);
		}
		return items;
	}

	public Item getItemById(int id) throws ClassNotFoundException, SQLException {
		Connection conn = DBUtil.getConn();
		String sql = "SELECT * FROM item WHERE id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Item item = new Item();
		while (rs.next()) {
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setPrice(rs.getDouble("price"));
			item.setImageUrl(rs.getString("image_url"));
		}
		return item;
	}
}
