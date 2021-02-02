package com.contactList.manager.controllers;

import com.contactList.manager.models.Contact;
import com.contactList.manager.repositories.ContactListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping({"/contacts","/contacts/"})
public class FetchController {

    @Autowired
    private ContactListRepository contactListRepository;

    //Get all the contacts in the contact list
    @GetMapping
    public List<Contact> list()
    {
        return contactListRepository.findAll();
    }


    //Get a contact by id
    @GetMapping
    @RequestMapping("{id}")
    public Contact get(@PathVariable Integer id)
    {
        return contactListRepository.findById(id).get();
    }

}
