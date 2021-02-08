package com.contactList.manager.controllers;

import com.contactList.manager.models.Contact;
import com.contactList.manager.repositories.ContactRepository;
import com.contactList.manager.services.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ContactRepository contactRepository;

    @MockBean
    public ContactService contactService;



    @Test
    public void list() throws Exception {
        Contact contact=new Contact();
        contact.setSerial(1);
        contact.setContact_name("saurabh");
        contact.setContact_number("98421");

        List<Contact> contacts= Arrays.asList(contact);

        given(contactRepository.findAll()).willReturn(contacts);

        mockMvc.perform(get("/contacts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect( jsonPath("$[0].serial", is(contact.getSerial())))
                .andExpect( jsonPath("$[0].contact_name", is(contact.getContact_name())));

    }

    @Test
    public void getById() throws Exception {
        int id=1;
        Contact contact=new Contact();
        contact.setSerial(1);
        contact.setContact_name("saurabh");
        contact.setContact_number("98421");

        doReturn(Optional.of(contact)).when(contactRepository).findById(id);

        mockMvc.perform(get("/contacts/findById/{id}",contact.getSerial()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect( jsonPath("$.serial", is(contact.getSerial())))
                .andExpect( jsonPath("$.contact_name", is(contact.getContact_name())));


    }


    @Test
    public void delete() throws Exception{

        Contact contact=new Contact();
        contact.setSerial(1);
        contact.setContact_name("saurabh");
        contact.setContact_number("98421");

//        doReturn(new NoSuchElementException()).when(contactRepository).deleteById(2);

        mockMvc.perform(MockMvcRequestBuilders.delete("/contacts/{id}",contact.getSerial())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}