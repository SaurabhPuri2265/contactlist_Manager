package com.contactList.manager.services;

import com.contactList.manager.models.Contact;
import com.contactList.manager.repositories.ContactListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sound.midi.Soundbank;
import java.util.NoSuchElementException;

@Service
public class ContactService {

    @Autowired
    ContactListRepository contactListRepository;

    //UpdateService to update contact details
    public void updateService(Integer id, Contact contact) throws RuntimeException
    {
            if(contactListRepository.existsById(id))
            {
                Contact existingcontact = contactListRepository.getOne(id);
                System.out.println(existingcontact);
                BeanUtils.copyProperties(contact,existingcontact,"serial");
                contactListRepository.saveAndFlush(existingcontact);
            }
            else
                throw new NoSuchElementException("Requested contact does not exist");
    }

    //DeleteService to delete contact
    public void deleteService(Integer id) throws RuntimeException
    {
        if(contactListRepository.existsById(id))
        {
            contactListRepository.deleteById(id);
        }
        else
            throw new NoSuchElementException("Contact already not present");
    }

}
