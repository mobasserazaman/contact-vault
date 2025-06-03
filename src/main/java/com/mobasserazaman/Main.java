package com.mobasserazaman;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final ContactDAO dao = new ContactDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Contact Vault!");
        while (true) {
            System.out.print("\nEnter command (add, list, search <term>, delete <id>, exit): ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+", 2);
            String command = parts[0].toLowerCase();

            try {
                switch (command) {
                    case "add" -> addContact();
                    case "list" -> listContacts();
                    case "search" -> {
                        if (parts.length < 2) {
                            System.out.println("Please provide search term.");
                            break;
                        }
                        searchContacts(parts[1]);
                    }
                    case "delete" -> {
                        if (parts.length < 2) {
                            System.out.println("Please provide ID to delete.");
                            break;
                        }
                        deleteContact(parts[1]);
                    }
                    case "exit" -> {
                        System.out.println("Goodbye!");
                        return;
                    }
                    default -> System.out.println("Unknown command.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        }
    }

    private static void addContact() throws SQLException {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Phone: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Birthday (yyyy-MM-dd) or blank: ");
        String bdInput = scanner.nextLine().trim();
        LocalDate birthday = null;
        if (!bdInput.isEmpty()) {
            try {
                birthday = LocalDate.parse(bdInput);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Birthday skipped.");
            }
        }

        Contact contact = new Contact(name, email, phone, birthday);
        dao.addContact(contact);
        System.out.println("Contact added.");
    }

    private static void listContacts() throws SQLException {
        List<Contact> contacts = dao.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            contacts.forEach(System.out::println);
        }
    }

    private static void searchContacts(String term) throws SQLException {
        List<Contact> results = dao.searchContacts(term);
        if (results.isEmpty()) {
            System.out.println("No contacts matched your search.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private static void deleteContact(String idStr) throws SQLException {
        try {
            int id = Integer.parseInt(idStr);
            boolean deleted = dao.deleteContact(id);
            if (deleted) {
                System.out.println("Contact deleted.");
            } else {
                System.out.println("No contact found with that ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }
}
