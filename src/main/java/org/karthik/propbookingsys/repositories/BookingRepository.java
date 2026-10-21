package org.karthik.propbookingsys.repositories;

import org.karthik.propbookingsys.models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings,Long> {
    List<Bookings> findByUser_id(Long userid);
}
