package com.contactList.manager.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "orders")
@Getter @Setter @NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    private String order_item;
    private String order_status;

    @ManyToOne
    @JoinColumn(name="contact_id")
    private Contact contact;

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", order_item='" + order_item + '\'' +
                ", order_status='" + order_status + '\'' +
                ", contact=" + contact +
                '}';
    }
}
