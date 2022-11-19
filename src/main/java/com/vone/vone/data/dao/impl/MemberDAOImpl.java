package com.vone.vone.data.dao.impl;

import com.vone.vone.data.dao.MemberDAO;
import com.vone.vone.data.entity.Member;
import com.vone.vone.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberDAOImpl implements MemberDAO {

    private final UserRepository userRepository;

    @Autowired
    public MemberDAOImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Member join(Member member){
        Member savedMember = userRepository.save(member);
        return savedMember;
    }
}
