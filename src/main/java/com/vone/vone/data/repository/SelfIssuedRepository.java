package com.vone.vone.data.repository;

import com.vone.vone.data.entity.SelfIssued;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfIssuedRepository extends JpaRepository<SelfIssued,Long> {
}
