package com.vone.vone.data.repository;

import com.vone.vone.data.entity.Context;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContextRepository extends JpaRepository<Context,Long> {

}
