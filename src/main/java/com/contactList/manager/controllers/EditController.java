package com.contactList.manager.controllers;

import com.contactList.manager.models.Contact;
import com.contactList.manager.repositories.ContactListRepository;
import com.contactList.manager.services.ManipulationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@RestController
@RequestMapping({"/contacts","/contacts/"})
public class EditController {

    @Autowired
    private ContactListRepository contactListRepository;

    @Autowired
    private ManipulationService manipulationService;

    //Create a new contact inside the contactlist repo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createContact(@RequestBody Contact contact){
        contactListRepository.saveAndFlush(contact);
    }

    //Update a contact inside the contactlist repo
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public void update(@PathVariable Integer id,@RequestBody Contact contact)
    {
        try {
            manipulationService.updateService(id,contact);
        }
        catch (RuntimeException e)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }

    //Delete a contact inside the contactlist repo
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id)
    {
        try{
            manipulationService.deleteService(id);
        }
        catch (RuntimeException e)
        {
            throw new NoSuchElementException(e.getMessage());
        }
    }

}

