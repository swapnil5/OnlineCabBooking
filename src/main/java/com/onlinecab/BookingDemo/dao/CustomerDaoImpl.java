package com.onlinecab.BookingDemo.dao;

import com.onlinecab.BookingDemo.entity.Booking;
import com.onlinecab.BookingDemo.entity.Search;
import com.onlinecab.BookingDemo.entity.User;
import com.onlinecab.BookingDemo.entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements  CustomerDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Vehicle> searchVehicleDao(Search search) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Vehicle");
        return query.getResultList();
    }

    @Override
    public ArrayList<ArrayList<String>> getSourceDestinationDao() {
        Session session = sessionFactory.getCurrentSession();
        Query sourceQuery = session.createSQLQuery("select source from Route");
        ArrayList<String> sources = (ArrayList<String>) sourceQuery.getResultList();

        Query destinationQuery = session.createSQLQuery("select destination from Route");
        ArrayList<String> destinations = (ArrayList<String>) destinationQuery.getResultList();
        ArrayList<ArrayList<String>> sourceDestination = new ArrayList<ArrayList<String>>();
        sourceDestination.add(sources);
        sourceDestination.add(destinations);

        return sourceDestination;
    }

    @Override
    public void confirmBookingDao(Booking booking) {
        Session session = sessionFactory.getCurrentSession();
        session.save(booking);
    }

    @Override
    public ArrayList<Booking> bookingHistoryDao(Integer user_id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Booking where user_id="+user_id);
        return (ArrayList<Booking>) query.getResultList();
    }

    @Override
    public String registrationDao(User user) {
        Session session =  sessionFactory.getCurrentSession();
        Object o = session.save(user);
        return "SUCCESS";
    }
}
