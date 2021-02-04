package com.contactList.manager.services;

import com.contactList.manager.models.Contact;
import com.contactList.manager.models.Order;
import com.contactList.manager.repositories.ContactRepository;
import com.contactList.manager.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import javax.xml.ws.Response;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private OrderRepository orderRepository;

    //Return list of all orders placed by a customer
    public List<Object[]> customerOrders(Integer id)
    {
        Optional<Contact> requestedCustomer = contactRepository.findById(id);

        if(requestedCustomer.isPresent())
        {
            Contact contact = requestedCustomer.get();
            return orderRepository.findByContact(contact.getSerial());
        }
            throw new NoSuchElementException("Nothing Found");
    }

    //Return order status of an item of a customer
    public String getStatus(Integer id,String item)
    {
        Optional<Contact> requestedCustomer = contactRepository.findById(id);
        if(!requestedCustomer.isPresent())
            throw new RuntimeException("No such order of a contact");

        String status = orderRepository.getOrderStatus(id,item);

        if(status==null)
            throw new RuntimeException("Item not ordered by customer");

        return status;
    }
}
