package com.example.login_spring_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
@RequestMapping ("/rooms")
public class RoomController {

    @PostMapping
    public String addRoom(){
        return "Room added";
    }

    @GetMapping ("/{id}")
    public String getRoomById(@PathVariable Long id){
        return "Room fetched for id:" + id;
    }

    @GetMapping 
    public String getAllRooms(){
        return "All rooms";
    }
}
