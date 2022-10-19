package com.vone.vone.data.dao.impl;

import com.vone.vone.data.dao.ContextDAO;
import com.vone.vone.data.dao.VCDAO;
import com.vone.vone.data.entity.Context;
import com.vone.vone.data.entity.Post;
import com.vone.vone.data.entity.VC;
import com.vone.vone.data.repository.ContextRepository;
import com.vone.vone.data.repository.VCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VCDAOImpl implements VCDAO {

    private final VCRepository vcRepository;

    @Autowired
    public VCDAOImpl(VCRepository vcRepository){
        this.vcRepository = vcRepository;
    }

    @Override
    public VC insertVC(VC vc){
        VC savedVC = vcRepository.save(vc);

        return savedVC;
    }

    @Override
    public VC selectVC(Long id){
        VC selected = vcRepository.getById(id);

        return selected;
    }
}
