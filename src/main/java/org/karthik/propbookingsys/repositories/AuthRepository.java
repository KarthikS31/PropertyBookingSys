package org.karthik.propbookingsys.repositories;

import org.karthik.propbookingsys.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByMail(String mail);
}
