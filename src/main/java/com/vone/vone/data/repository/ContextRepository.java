package com.vone.vone.data.repository;

import com.vone.vone.data.entity.Context;
import com.vone.vone.data.entity.HoldersVC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContextRepository extends JpaRepository<Context,Long> {
    Context findByContext(String contextName);
}
