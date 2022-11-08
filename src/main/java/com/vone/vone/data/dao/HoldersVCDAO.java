package com.vone.vone.data.dao;

import com.vone.vone.data.dto.VC2IssueDto;
import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.VC;

import java.util.List;

public interface HoldersVCDAO {
    HoldersVC insertHoldersVC(HoldersVC holdersVC);

    List<HoldersVC> getHoldersVCByIssuerId(String issuerId);

    List<HoldersVC> getHoldersVCByHolderId(String holderId);

    List<HoldersVC> getHoldersVCByContext(String context);
}
