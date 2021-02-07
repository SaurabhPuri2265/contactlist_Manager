package com.contactList.manager.controllers;


import com.contactList.manager.models.Contact;
import com.contactList.manager.models.Order;
import com.contactList.manager.services.PurchaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PurchaseController.class)
public class PurchaseControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PurchaseService purchaseService;

    @Test
    public void getOrders() throws Exception {
        Contact contact=new Contact();
        contact.setSerial(1);
        contact.setContact_name("saurabh");
        contact.setContact_number("98421");

        Order order= new Order();
        order.setOrder_id(100);
        order.setOrder_item("mobile");
        order.setOrder_status("shipped");
        order.setContact(contact);

        Object[] arr=new Object[]{order.getOrder_id(),order.getOrder_item(),order.getOrder_status()};

        List<Object[]> orderlist=new ArrayList<Object[]>();

        orderlist.add(arr);

        System.out.println(orderlist.get(0));

       when(purchaseService.customerOrders(1)).thenReturn(orderlist);

        mockMvc.perform(get("/orders/{id}",contact.getSerial()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0][0]",is(order.getOrder_id())));

    }

}