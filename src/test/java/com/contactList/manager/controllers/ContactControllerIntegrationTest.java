package com.contactList.manager.controllers;

import com.contactList.manager.models.Contact;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ContactControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void list() throws Exception {
        ResponseEntity<List> responseEntity = this.restTemplate.getForEntity("/contacts",List.class);
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.ACCEPTED));

    }

    @Test
    public void getById() throws Exception{
        Contact contact=new Contact();


        ResponseEntity<Contact> responseEntity = this.restTemplate.getForEntity("/contacts/findById/{id}",Contact.class,1);

        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.ACCEPTED));
        assertThat(responseEntity.getBody().getContact_name(), equalTo("Sanjeev"));
    }

    @Test
    public void createContact() {

        Contact contact=new Contact();
        contact.setContact_name("saurabh");
        contact.setContact_number("98421");


        ResponseEntity<Contact> response = this.restTemplate.postForEntity("/contacts", contact, Contact.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));


    }
}