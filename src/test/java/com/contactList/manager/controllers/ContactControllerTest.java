package com.contactList.manager.controllers;

import com.contactList.manager.models.Contact;
import com.contactList.manager.repositories.ContactRepository;
import com.contactList.manager.services.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



import java.util.Arrays;
import java.util.List;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ContactRepository contactRepository;

    @MockBean
    ContactService contactService;



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
                .andExpect( jsonPath("$[0].serial", is(contact.getSerial())));;

    }

    @Test
    public void getById() {
    }

    @Test
    public void createContact() {
    }

    @Test
    public void delete() {
    }
}