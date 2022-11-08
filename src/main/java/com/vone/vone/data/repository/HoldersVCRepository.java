package com.vone.vone.data.repository;

import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.VC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoldersVCRepository extends JpaRepository<HoldersVC,Long> {
    List<HoldersVC> findByIssuerId(String issuerId);

    List<HoldersVC> findByHolderId(String holderId);

    List<HoldersVC> findByContext(String context);

}
