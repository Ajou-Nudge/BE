package com.vone.vone.data.repository;

import com.vone.vone.data.entity.Context;
import com.vone.vone.data.entity.VC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VCRepository extends JpaRepository<VC,Long> {

}
