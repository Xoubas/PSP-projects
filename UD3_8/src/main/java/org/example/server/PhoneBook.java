package org.example.server;

import org.example.Contact;

import java.util.ArrayList;

public class PhoneBook {
    private static ArrayList<Contact> phonebook = new ArrayList<Contact>();
    public static boolean addContact(Contact contact) {
        phonebook.add(contact);
        return false;
    }

    public static String findContact(String name) {
        return null;
    }

}
