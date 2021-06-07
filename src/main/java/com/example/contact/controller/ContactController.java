package com.example.contact.controller;

import com.example.contact.model.Contact;
import com.example.contact.service.Contactservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/user")
public class ContactController {

    @Autowired
    Contactservice service;

    @GetMapping("/get")
    public List<Contact> getAllContact() {
        return service.getAllContact();
    }

    @GetMapping("/get/id")
    public Contact getUserById(@RequestParam Long id) {
        return service.getUserById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addUser(@RequestBody Contact users) {
        Contact u = null;
        try {
            u = service.addUser(users);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("This E-mail already exist " + e.getMessage() + e.getCause());
        }
        return ResponseEntity.ok(u);
    }




    @PostMapping("/update")
    public Contact updateUser(@RequestBody Contact users) {
        return service.updateUser(users);
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody Contact contact) {
        service.deleteUser(contact);
        String email = contact.getEmail();
        return email + " has been deleted";
    }

    @PostMapping("/delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        return service.removeUserById(id);
    }


}