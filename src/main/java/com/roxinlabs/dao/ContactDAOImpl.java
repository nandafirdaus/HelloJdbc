package com.roxinlabs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.roxinlabs.model.Contact;

public class ContactDAOImpl implements ContactDAO {

	private JdbcTemplate jdbcTemplate;
	
	public ContactDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void saveOrUpdate(Contact contact) {
		if (contact.getId() > 0) {
			// Update because it already has an id
			String sql = "UPDATE contact SET name=?, email=?, address=?, "
					+ "telephone=? WHERE contact_id=?";
			jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(),
					contact.getTelephone(), contact.getId());
		} else {
			// Insert new data
			String sql = "INSERT INTO contact (name, email, address, telephone)"
					+ " VALUES (?,?,?,?)";
			jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(), 
					contact.getTelephone());
		}
		
	}

	@Override
	public void delete(int contactId) {
		String sql = "DELETE FROM contact WHERE contact_id=?";
		jdbcTemplate.update(sql, contactId);		
	}

	@Override
	public Contact get(int contactId) {
		String sql = "SELECT * FROM contact WHERE contact_id=" + contactId;
		
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Contact contact = new Contact();
					contact.setId(rs.getInt("contact_id"));
					contact.setName(rs.getString("name"));
					contact.setEmail(rs.getString("email"));
					contact.setAddress(rs.getString("address"));
					contact.setTelephone(rs.getString("telephone"));
					
					return contact;
				}
				
				return null;
			}
			
		});
	}

	@Override
	public List<Contact> list() {
		String sql = "SELECT * FROM contact";
		List<Contact> listContact = jdbcTemplate.query(sql, new RowMapper<Contact>() {

			@Override
			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Contact contact = new Contact();
				contact.setId(rs.getInt("contact_id"));
				contact.setName(rs.getString("name"));
				contact.setEmail(rs.getString("email"));
				contact.setAddress(rs.getString("address"));
				contact.setTelephone(rs.getString("telephone"));
				
				return contact;
			}
			
		});
		
		return listContact;
	}
	
}
