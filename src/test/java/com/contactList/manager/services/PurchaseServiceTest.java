package com.contactList.manager.services;

import com.contactList.manager.models.Contact;
import com.contactList.manager.models.Order;
import com.contactList.manager.repositories.ContactRepository;
import com.contactList.manager.repositories.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;



@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceTest {

    @InjectMocks
    PurchaseService purchaseService;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ContactRepository contactRepository;


    @Test
    public void customerOrders() throws NullPointerException{

        Contact contact=new Contact();
        contact.setSerial(1);
        contact.setContact_name("Saurabh");
        contact.setContact_number("123456");


        Order order= new Order();
        order.setOrder_id(100);
        order.setOrder_item("mobile");
        order.setOrder_status("shipped");
        order.setContact(contact);

        System.out.println(order);

        Object[] arr=new Object[]{100,"mobile","shipped"};

        List<Object[]> orderList = new ArrayList<>();

        orderList.add(arr);

        doReturn(orderList).when(orderRepository).findByContact(contact.getSerial());

        System.out.println(orderList);

        //Execute service call
        assertEquals(orderList.size(), purchaseService.customerOrders(contact.getSerial()));
        assertThrows(RuntimeException.class,() -> purchaseService.customerOrders(10),"Nothing found");


    }

    @Test
    public void getStatus() {
        Contact contact=new Contact();
        contact.setSerial(1);
        contact.setContact_name("Saurabh");
        contact.setContact_number("123456");


        Order order= new Order();
        order.setOrder_id(100);
        order.setOrder_item("mobile");
        order.setOrder_status("shipped");
        order.setContact(contact);

        doReturn(order.getOrder_status()).when(orderRepository).getOrderStatus(1,"mobile");

        assertEquals("shipped",purchaseService.getStatus(contact.getSerial(),"mobile"));
    }
}