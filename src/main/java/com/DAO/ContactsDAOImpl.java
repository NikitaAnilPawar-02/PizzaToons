package com.DAO;

import java.util.*;
import java.sql.*;
import com.entity.Contacts;

public class ContactsDAOImpl implements ContactsDAO {

	private Connection conn;

	public ContactsDAOImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Contacts> getAllContacts() {
		List<Contacts> contacts = new ArrayList<>();
		try {
			String sql = "SELECT * FROM contact";
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Contacts c = new Contacts();
				c.setContactId(rs.getInt(1));
				c.setName(rs.getString(2));
				c.setEmail(rs.getString(3));
				c.setMessage(rs.getString(4));
				contacts.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}
}
