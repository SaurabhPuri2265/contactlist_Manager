package com.contactList.manager.repositories;

import com.contactList.manager.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

public interface ContactListRepository extends JpaRepository<Contact,Integer> {

}
