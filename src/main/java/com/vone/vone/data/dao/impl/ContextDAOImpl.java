package com.vone.vone.data.dao.impl;

import com.vone.vone.data.dao.ContextDAO;
import com.vone.vone.data.entity.Context;
import com.vone.vone.data.repository.ContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContextDAOImpl implements ContextDAO {

    private final ContextRepository contextRepository;

    @Autowired
    public ContextDAOImpl(ContextRepository contextRepository){
        this.contextRepository = contextRepository;
    }

    @Override
    public Context insertContext(Context context){
        Context savedContext = contextRepository.save(context);

        return savedContext;
    }


    @Override
    public List<Context> selectAllContext(){
        List<Context> contexts = contextRepository.findAll();
        return contexts;
    }

}
