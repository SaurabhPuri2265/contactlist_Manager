package com.contactList.manager.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity(name="contacts")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serial;


    private String contact_name;
    private String contact_number;

    @OneToMany(mappedBy = "contact")
    @JsonIgnore
    private List<Order> orders;

    public Contact() {
    }

}
