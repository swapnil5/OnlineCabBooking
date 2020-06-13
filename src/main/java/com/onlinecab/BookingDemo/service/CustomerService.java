package com.onlinecab.BookingDemo.service;

import com.onlinecab.BookingDemo.entity.Booking;
import com.onlinecab.BookingDemo.entity.Search;
import com.onlinecab.BookingDemo.entity.User;
import com.onlinecab.BookingDemo.entity.Vehicle;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {

    List<Vehicle> searchVehicle(Search search);

    ArrayList<ArrayList<String>> getSourceDestination();

    void confirmBooking(Booking booking);

    ArrayList<Booking> bookingHistory(Integer user_id);

    String registration(User user);
}
