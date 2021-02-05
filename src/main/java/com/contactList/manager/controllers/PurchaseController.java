package com.contactList.manager.controllers;

import com.contactList.manager.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

   // Get all order details of a customer
    @GetMapping
    @RequestMapping("{id}")
    public List<Object[]> getOrders(@PathVariable Integer id)
    {
        try{
            return purchaseService.customerOrders(id);
        }
        catch (RuntimeException e)
        {
            throw  new RuntimeException(e.getMessage());
        }
    }

    //Get status of a particular order item of a contact
    @GetMapping
    @RequestMapping("{id}/{item}")
    public String status(@PathVariable Integer id,@PathVariable String item)
    {
        try{
            return purchaseService.getStatus(id,item);
        }
        catch (RuntimeException e)
        {
            throw new NoSuchElementException(e.getMessage());
        }
    }


}
