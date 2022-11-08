package com.vone.vone.data.dao.impl;

import com.vone.vone.controller.Holder;
import com.vone.vone.data.dao.HoldersVCDAO;
import com.vone.vone.data.dao.VCDAO;
import com.vone.vone.data.entity.Context;
import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.VC;
import com.vone.vone.data.repository.HoldersVCRepository;
import com.vone.vone.data.repository.VCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HoldersVCDAOImpl implements HoldersVCDAO {

    private final HoldersVCRepository holdersVCRepository;

    @Autowired
    public HoldersVCDAOImpl(HoldersVCRepository holdersVCRepository){
        this.holdersVCRepository = holdersVCRepository;
    }

    @Override
    public HoldersVC insertHoldersVC(HoldersVC holdersVC){
        HoldersVC savedHoldersVC = holdersVCRepository.save(holdersVC);

        return savedHoldersVC;
    }

    @Override
    public List<HoldersVC> getHoldersVCByIssuerId(String issuerId){
        List<HoldersVC> selected =  holdersVCRepository.findByIssuerId(issuerId);
        return selected;
    }

    @Override
    public List<HoldersVC> getHoldersVCByHolderId(String holderId){
        List<HoldersVC> selected =  holdersVCRepository.findByHolderId(holderId);
        return selected;
    }

    @Override
    public List<HoldersVC> getHoldersVCByContext(String context){
        List<HoldersVC> selected =  holdersVCRepository.findByContext(context);
        return selected;
    }
}
