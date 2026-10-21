package org.karthik.propbookingsys.services;

import jakarta.transaction.Transactional;
import org.karthik.propbookingsys.models.Bookings;
import org.karthik.propbookingsys.models.Properties;
import org.karthik.propbookingsys.repositories.BookingRepository;
import org.karthik.propbookingsys.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    PropertyRepository propertyRepository;

    public List<Bookings> getAllBookingsByUserId(Long userId){
        return bookingRepository.findByUser_id(userId);
    }

    public Bookings getBookingById(Long bookingId){
        return bookingRepository.findById(bookingId)
                .orElseThrow(()-> new RuntimeException( "Booking not found: "+bookingId));
    }

    public Bookings bookProp(Bookings data){
        return bookingRepository.save(data);
    }

    public Bookings checkIn(Long bookingId){
        Bookings bookings=bookingRepository.findById(bookingId)
                .orElseThrow(()-> new RuntimeException( "Booking not found: "+bookingId));
        bookings.setBookingStatus(Bookings.BookingStatus.CHECKEDIN);

        return  bookingRepository.save(bookings);
    }

    @Transactional
    public Bookings checkOut(Long bookingId){
        Bookings bookings=bookingRepository.findById(bookingId)
                .orElseThrow(()-> new RuntimeException( "Booking not found: "+bookingId));
        bookings.setBookingStatus(Bookings.BookingStatus.COMPLETED);
        Properties property= bookings.getProperty();
        property.setStatus(Properties.Status.AVAILABLE);
        return  bookingRepository.save(bookings);
    }

    @Transactional
    public Bookings cancelBooking(Long bookingId){
        Bookings booking= bookingRepository.findById(bookingId)
                .orElseThrow(()-> new RuntimeException( "Booking not found: "+bookingId));

        Properties property = booking.getProperty();
        booking.setBookingStatus(Bookings.BookingStatus.CANCELLED);
        property.setStatus(Properties.Status.AVAILABLE);

        propertyRepository.save(property);
        return bookingRepository.save(booking);
    }
}
