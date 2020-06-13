package com.onlinecab.BookingDemo.dao;

import com.onlinecab.BookingDemo.entity.Booking;
import com.onlinecab.BookingDemo.entity.Search;
import com.onlinecab.BookingDemo.entity.User;
import com.onlinecab.BookingDemo.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CustomerDao {

     List<Vehicle> searchVehicleDao(Search search);

    ArrayList<ArrayList<String>> getSourceDestinationDao();

    void confirmBookingDao(Booking booking);

    ArrayList<Booking> bookingHistoryDao(Integer user_id);

    String registrationDao(User user);
}
