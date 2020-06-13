package com.onlinecab.BookingDemo.service.serviceImpl;

import com.onlinecab.BookingDemo.dao.CustomerDao;
import com.onlinecab.BookingDemo.entity.Booking;
import com.onlinecab.BookingDemo.entity.Search;
import com.onlinecab.BookingDemo.entity.User;
import com.onlinecab.BookingDemo.entity.Vehicle;
import com.onlinecab.BookingDemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Vehicle> searchVehicle(Search search) {
        return customerDao.searchVehicleDao(search);
    }

    @Override
    public ArrayList<ArrayList<String>> getSourceDestination() {
        return customerDao.getSourceDestinationDao();
    }

    @Override
    public void confirmBooking(Booking booking) {
        customerDao.confirmBookingDao(booking);
    }

    @Override
    public ArrayList<Booking> bookingHistory(Integer user_id) {
        return customerDao.bookingHistoryDao(user_id);
    }

    @Override
    public String registration(User user) {
        return customerDao.registrationDao(user);
    }
}
