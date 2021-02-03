package com.contactList.manager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="contacts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serial;


    private String contact_name;
    private String contact_number;

    public Contact() {
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "serial=" + serial +
                ", contact_name='" + contact_name + '\'' +
                ", contact_number='" + contact_number + '\'' +
                '}';
    }
}
