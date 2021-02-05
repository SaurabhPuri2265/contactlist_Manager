package com.contactList.manager.services;

import com.contactList.manager.models.Contact;
import com.contactList.manager.models.Order;
import com.contactList.manager.repositories.ContactRepository;
import com.contactList.manager.repositories.OrderRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class PurchaseServiceTest {

    @InjectMocks
    PurchaseService purchaseService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ContactRepository contactRepository;


    @BeforeClass
    void setUp() {
        System.out.println("Service layer testing started ->");
    }

    @AfterClass
    void tearDown() {
        System.out.println("Service layer testing ended ->");
    }

    @Test
    void customerOrders() throws NullPointerException{

        Contact contact=new Contact();
        contact.setSerial(1);
        contact.setContact_name("Saurabh");
        contact.setContact_number("123456");


        Order order= new Order();
        order.setOrder_id(100);
        order.setOrder_item("mobile");
        order.setOrder_status("shipped");
        order.setContact(contact);

        //contactRepository.save(contact);
        orderRepository.save(order);

        Object[] arr=new Object[]{100,"mobile","shipped"};

        List<Object[]> orderList = new ArrayList<>();

        orderList.add(arr);

        System.out.println(orderList);

        when(orderRepository.findByContact(contact.getSerial())).thenReturn(orderList);

        assertEquals(orderList,purchaseService.customerOrders(contact.getSerial()));



    }

    @Test
    void getStatus() {
    }
}