package org.karthik.propbookingsys.controllers;

import org.karthik.propbookingsys.models.Bookings;
import org.karthik.propbookingsys.repositories.BookingRepository;
import org.karthik.propbookingsys.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping(params = "userId")
    ResponseEntity<List<Bookings>> getAllBookingsByUserId(@RequestParam Long userId){
        return new ResponseEntity<>(bookingService.getAllBookingsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(params = "bookingId")
    ResponseEntity<Bookings> getBookingById(@RequestParam Long bookingId){
        return new ResponseEntity<>(bookingService.getBookingById(bookingId), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Bookings> bookProperty(@RequestBody Bookings body){
        return new ResponseEntity<>(bookingService.bookProp(body), HttpStatus.CREATED);
    }

    @PutMapping("/cancel")
    ResponseEntity<Bookings> cancelBooking(@RequestParam Long bookingId){
        return new ResponseEntity<>(bookingService.cancelBooking(bookingId),HttpStatus.OK);
    }

    @PutMapping("/checkin")
    ResponseEntity<Bookings>  checkinProperty(@RequestParam Long bookingId){
        return new ResponseEntity<>(bookingService.checkIn(bookingId),HttpStatus.OK);
    }

    @PutMapping("/checkout")
    ResponseEntity<Bookings>  checkoutProperty(@RequestParam Long bookingId){
        return new ResponseEntity<>(bookingService.checkOut(bookingId),HttpStatus.OK);
    }
}
