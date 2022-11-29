package com.vone.vone.data.dao;

import com.vone.vone.data.entity.SelfIssued;
import org.springframework.stereotype.Component;

public interface SelfIssuedDAO {

    SelfIssued insertSelfIssued(SelfIssued selfIssued);

    SelfIssued selectSelfIssued(Long id);
}
