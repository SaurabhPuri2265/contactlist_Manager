package com.contactList.manager.services;

import com.contactList.manager.repositories.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    @InjectMocks
    ContactService contactService;

    @Mock
    ContactRepository contactRepository;

    @Test
    public void updateService() {


    }

    @Test
    public void deleteService() {
        int id=1;

        doReturn(true).when(contactRepository).existsById(id);
        doReturn(false).when(contactRepository).existsById(2);

        assertDoesNotThrow(()->contactService.deleteService(1));
        assertThrows(NoSuchElementException.class,()->contactService.deleteService(2));
    }
}