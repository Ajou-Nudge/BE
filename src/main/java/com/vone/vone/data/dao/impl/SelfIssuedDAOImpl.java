package com.vone.vone.data.dao.impl;

import com.vone.vone.data.dao.SelfIssuedDAO;
import com.vone.vone.data.dao.VCDAO;
import com.vone.vone.data.entity.SelfIssued;
import com.vone.vone.data.entity.VC;
import com.vone.vone.data.repository.SelfIssuedRepository;
import com.vone.vone.data.repository.VCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelfIssuedDAOImpl implements SelfIssuedDAO {

    private final SelfIssuedRepository selfIssuedRepository;

    @Autowired
    public SelfIssuedDAOImpl(SelfIssuedRepository selfIssuedRepository){
        this.selfIssuedRepository = selfIssuedRepository;
    }

    @Override
    public SelfIssued insertSelfIssued(SelfIssued selfIssued){
        SelfIssued savedVC = selfIssuedRepository.save(selfIssued);

        return savedVC;
    }

    @Override
    public SelfIssued selectSelfIssued(Long id){
        SelfIssued selected = selfIssuedRepository.getById(id);

        return selected;
    }
}
