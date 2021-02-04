package com.contactList.manager.services;

import com.contactList.manager.models.Contact;
import com.contactList.manager.repositories.ContactRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    //UpdateService to update contact details
    public void updateService(Integer id, Contact contact) throws RuntimeException
    {
            if(contactRepository.existsById(id))
            {
                Contact existingcontact = contactRepository.getOne(id);
                System.out.println(existingcontact);
                BeanUtils.copyProperties(contact,existingcontact,"serial");
                contactRepository.saveAndFlush(existingcontact);
            }
            else
                throw new NoSuchElementException("Requested contact does not exist");
    }

    //DeleteService to delete contact
    public void deleteService(Integer id) throws RuntimeException
    {
        if(contactRepository.existsById(id))
        {
            contactRepository.deleteById(id);
        }
        else
            throw new NoSuchElementException("Contact already not present");
    }

}
