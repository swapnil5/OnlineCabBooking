package com.onlinecab.BookingDemo.controller;

import com.onlinecab.BookingDemo.entity.Booking;
import com.onlinecab.BookingDemo.entity.Search;
import com.onlinecab.BookingDemo.entity.User;
import com.onlinecab.BookingDemo.entity.Vehicle;
import com.onlinecab.BookingDemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private CustomerService customerservice;

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public ResponseEntity<String> registration(@RequestBody User user){
        if(user.getFirstName().isEmpty()){
            return new ResponseEntity<>("Please provide first name...", HttpStatus.BAD_REQUEST);
        }
        if(user.getLastName().isEmpty()){
            return new ResponseEntity<>("Please provide last name...", HttpStatus.BAD_REQUEST);
        }
        if(user.getGender().isEmpty()){
            return new ResponseEntity<>("Please provide gender info...", HttpStatus.BAD_REQUEST);
        }
        if(!(user.getMobileNumber().length() == 10)){
            return new ResponseEntity<>("Please provide correct mobile number...", HttpStatus.BAD_REQUEST);
        }
        if(user != null){
            customerservice.registration(user);
        }
        return new ResponseEntity<>("User Registration is successful...", HttpStatus.OK);
    }

    @RequestMapping(value="/search", method = RequestMethod.POST)
    public ResponseEntity<Search> search(@RequestBody Search search) {
        System.out.println(search.getSource());
        List<Vehicle> vehicles =  customerservice.searchVehicle(search);

        if(vehicles == null) {
            return new ResponseEntity<>(search, HttpStatus.NOT_FOUND);
        }else{
            search.setVehicles(vehicles);
            return new ResponseEntity<>(search, HttpStatus.OK);
        }
    }

    @RequestMapping(value="/GetSourceAndDestination",method = RequestMethod.GET)
    public ResponseEntity<ArrayList<ArrayList<String>>> BookVehicle(){
        ArrayList<ArrayList<String>> sourceDestination = customerservice.getSourceDestination();
        if(sourceDestination.isEmpty()) {
            return new ResponseEntity<>(sourceDestination, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(sourceDestination, HttpStatus.OK);
        }
    }

    @RequestMapping(value="/ConfirmBooking",method = RequestMethod.POST)
    public ResponseEntity<String> BookCab(@RequestBody Booking booking){
        customerservice.confirmBooking(booking);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @RequestMapping(value="/history",method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Booking>> BookingHistory(@RequestParam Integer user_id){
        ArrayList<Booking> bookings = customerservice.bookingHistory(user_id);
        if(bookings.isEmpty()) {
            return new ResponseEntity<>(bookings, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        }

    }
}
