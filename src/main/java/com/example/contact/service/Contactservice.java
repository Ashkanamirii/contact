package com.example.contact.service;

import com.example.contact.model.Contact;
import com.example.contact.repository.ContactRepo;
import com.example.contact.utils.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Contactservice {

    @Autowired
    private ContactRepo repository;


    public List<Contact> getAllContact() {
        return repository.findAll();
    }

    public Contact getUserById(Long id) {
        return repository.findById(id).orElse(null); // Makes it possible to return User instead of Optional
    }

    public Contact addUser(Contact contact) throws Exception {
        return repository.save(contact);
    }

    public Contact addUser1(Contact contact) throws UserException {
        Contact u = repository.findByEmail(contact.getEmail());
        if (u != null) {
            throw new UserException("Can't be same!!!!!!");
        } else {
            return repository.save(contact);
        }
    }


//    public Contact findUserByEmailAndPassword(String email, String password) throws UserException {
//        Users u = repository.findByEmailAndPassword(email, password);
//        if (u == null) {
//            throw new UserException("Incorrect USER or PASSWORD");
//        } else {
//            return u;
//        }
//    }

    public Contact updateUser(Contact contact) {
        Contact u = repository.findById(contact.getId()).orElse(null);
        if(u != null){
            u.setName(contact.getName());
            u.setEmail(contact.getEmail());
            return repository.save(u);
        }
       return null;
    }

    public Contact deleteUser(Contact contact) {
        repository.delete(contact);
        return contact;
    }

    public String removeUserById(Long id) {
        repository.deleteById(id);
        return "User with id " + id + " removed.";
    }

}
