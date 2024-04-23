package org.example;

//We want to create a client-server application to store the telephone number of our contacts.
//
//The client must send to the server commands entered by the user.
//
//The commands to use are the following:
//
//ADD <name><telephone_number>. This command indicates that the client wants to add a new contact to the phonebook.
//FIND <name>. The client wants to know if a contact exists and, if so, to know his contact details.
//EXIT. The client informs the server that he wants to leave.
//The server responses are the following:
//
//ACCEPTED. Indicates that the contact does not exist in the phonebook and has been added.
//REJECTED. Indicates that the contact exists in the phonebook and has not been added.
//INVALID. Indicates that the command does not exist in the communication protocol.
//ERROR. Indicates that the command exists in the communication protocol but its use is incorrect.
//BYE. Indicates that the communication with the client is going to be closed.
//Create a package called client for the client and a package called server for the server.
//
//Create a ContactClient class to launch the Client.
//
//Create a ContactServer class to launch the Server.
//
//Create a ContactServerWorker class for each thread on the Server.
//
//Create a Contact class to store the name and telephone of one contact.
//
//Create a PhoneBook class to store the data of all contacts.

public class Contact {

    //Storge the name and telephone of one contact
    private String name;
    private String telephone;

    //Constructor
    public Contact(String name, String telephone) {
        this.name = name;
        this.telephone = telephone;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    //toString
    @Override

    public String toString() {
        return "Contact{" + "name=" + name + ", telephone=" + telephone + '}';
    }
}
