package com.vone.vone.data.dao.impl;

import com.vone.vone.data.dao.HoldersVCDAO;
import com.vone.vone.data.dao.SubmittedVCDAO;
import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.SubmittedVC;
import com.vone.vone.data.repository.HoldersVCRepository;
import com.vone.vone.data.repository.SubmittedVCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubmittedVCDAOImpl implements SubmittedVCDAO {

    private final SubmittedVCRepository submittedVCRepository;

    @Autowired
    public SubmittedVCDAOImpl(SubmittedVCRepository submittedVCRepository){
        this.submittedVCRepository = submittedVCRepository;
    }

    @Override
    public SubmittedVC insertSubmittedVC(SubmittedVC submittedVC){
        SubmittedVC savedSubmittedVC = submittedVCRepository.save(submittedVC);

        return savedSubmittedVC;
    }

    @Override
    public List<SubmittedVC> getSubmittedVCByHolderId(String holder){
        List<SubmittedVC> submittedVCS = submittedVCRepository.findByHolderId(holder);

        return submittedVCS;
    }

    @Override
    public List<SubmittedVC> getSubmittedVCByVerifierId(String verifer, Long postId){
        List<SubmittedVC> submittedVCS = submittedVCRepository.findByVerifierIdAndPostId(verifer, postId);
        return submittedVCS;
    }

}
