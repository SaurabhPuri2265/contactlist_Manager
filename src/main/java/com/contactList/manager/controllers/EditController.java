package com.contactList.manager.controllers;

import com.contactList.manager.models.Contact;
import com.contactList.manager.repositories.ContactListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/contacts","/contacts/"})
public class EditController {

    @Autowired
    private ContactListRepository contactListRepository;

    //Create a new contact inside the contactlist repo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody Contact contact){
        contactListRepository.saveAndFlush(contact);
    }

    //Update a contact inside the contactlist repo
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public void updateContact(@PathVariable Integer id,@RequestBody Contact contact)
    {
        Contact existingcontact = contactListRepository.getOne(id);
        BeanUtils.copyProperties(contact,existingcontact,"id");
        contactListRepository.saveAndFlush(existingcontact);
    }

    //Delete a contact inside the contactlist repo
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id)
    {
        contactListRepository.deleteById(id);
    }

}
