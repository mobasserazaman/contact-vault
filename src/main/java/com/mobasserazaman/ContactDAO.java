package com.mobasserazaman;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ContactDAO {

    public void addContact(Contact contact) throws SQLException{
        String sql = "INSERT INTO contacts (name, email, phone, birthday) VALUES (?, ?, ?, ?)";
        try(Connection conn = DBUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);){

                stmt.setString(1, contact.getName());
                stmt.setString(2, contact.getEmail());
                stmt.setString(3, contact.getPhone());
                if(contact.getBirthday() != null){
                    stmt.setDate(4, Date.valueOf(contact.getBirthday()));
                }else{
                    stmt.setNull(4, Types.DATE);
                }

                stmt.executeUpdate(); 
        }
    }

    public List<Contact> getAllContacts() throws SQLException {
        String sql = "SELECT * FROM contacts ORDER BY id";
        List<Contact> contacts = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                contacts.add(mapRowToContact(rs));
            }
        }
        return contacts;
    }

    public List<Contact> searchContacts(String term) throws SQLException {
        String sql = "SELECT * FROM contacts WHERE LOWER(name) LIKE ? OR LOWER(email) LIKE ? ORDER BY id";
        List<Contact> results = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchTerm = "%" + term.toLowerCase() + "%";
            stmt.setString(1, searchTerm);
            stmt.setString(2, searchTerm);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    results.add(mapRowToContact(rs));
                }
            }
        }
        return results;
    }

    public boolean deleteContact(int id) throws SQLException {
        String sql = "DELETE FROM contacts WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affected = stmt.executeUpdate();
            return affected > 0;
        }
    }

    private Contact mapRowToContact(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        Date bd = rs.getDate("birthday");
        LocalDate birthday = bd != null ? bd.toLocalDate() : null;
        return new Contact(id, name, email, phone, birthday);
    } 
}
