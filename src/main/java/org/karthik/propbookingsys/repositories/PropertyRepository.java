package org.karthik.propbookingsys.repositories;


import org.karthik.propbookingsys.models.Properties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Properties,Long> {
}
