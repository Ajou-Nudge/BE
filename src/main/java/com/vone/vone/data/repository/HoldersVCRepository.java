package com.vone.vone.data.repository;

import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.VC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoldersVCRepository extends JpaRepository<HoldersVC,Long> {
    List<HoldersVC> findByIssuerId(String issuerId);

    List<HoldersVC> findByHolderId(String holderId);

}
