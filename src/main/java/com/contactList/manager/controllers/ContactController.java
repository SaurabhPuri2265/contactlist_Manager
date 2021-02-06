package com.contactList.manager.controllers;

import com.contactList.manager.models.Contact;
import com.contactList.manager.repositories.ContactRepository;
import com.contactList.manager.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping({"/contacts","/contacts/"})
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactService contactService;

    //Get all the contacts in the contact list
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Contact> list() {
        return contactRepository.findAll();
    }

    //Get a contact by id
    @GetMapping
    @RequestMapping("/findById/{id}")
    public Contact getById(@PathVariable Integer id) {

        try {
            Contact contact = contactRepository.findById(id).get();
            return contact;
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "ID entered in invalid");
        }
    }

    //Get a contact by name
    @GetMapping
    @RequestMapping("/findByName/{name}")
    public Contact getByName(@PathVariable String name)
    {
        List<Contact> existingContacts = contactRepository.findAll();

        for(Contact contact : existingContacts)
        {
            if(name.equals(contact.getContact_name()))
                return contact;
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"No contact with given name found");
    }

    //Create a new contact inside the contactlist repo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody Contact contact){
        contactRepository.saveAndFlush(contact);
    }

    //Update a contact inside the contactlist repo
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public void update(@PathVariable Integer id,@RequestBody Contact contact)
    {
        try {
            contactService.updateService(id,contact);
        }
        catch (RuntimeException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }

    //Delete a contact inside the contactlist repo

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Integer id)
    {
        try{
            contactService.deleteService(id);
        }
        catch (RuntimeException e)
        {
            throw new NoSuchElementException(e.getMessage());
        }
    }
}

