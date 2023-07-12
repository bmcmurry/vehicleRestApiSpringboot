package com.example.demo.owners;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnersRepository extends JpaRepository<Owners, Long> {

    @Query("Select s FROM Owners s WHERE s.address = ?1")
    // SELECT * FROM Owners WHERE address = ?
    Optional<Owners> findOwnersByAddress(String address);
}
