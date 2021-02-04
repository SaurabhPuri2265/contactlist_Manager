package com.contactList.manager.repositories;

import com.contactList.manager.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query(value = "SELECT order_id,order_item,order_status FROM orders WHERE contact_id=?1")
    List<Object[]> findByContact(Integer id);

    @Query("SELECT order_status FROM orders WHERE contact_id=?1 AND order_item=?2")
    String getOrderStatus(Integer id,String item);
}
