package com.contactList.manager.services;

import com.contactList.manager.models.Contact;
import com.contactList.manager.repositories.ContactRepository;
import com.contactList.manager.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            throw new RuntimeException("Nothing Found");
    }

    //Return order status of an item of a customer
    public String getStatus(Integer id,String item)
    {
        Optional<Contact> requestedCustomer = contactRepository.findById(id);

        String status = orderRepository.getOrderStatus(id,item);

        if(status==null)
            throw new RuntimeException("Item not ordered by customer");

        return status;
    }
}
