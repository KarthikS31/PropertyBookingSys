package org.karthik.propbookingsys.repositories;

import org.karthik.propbookingsys.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<User,Long> {
}
