package com.vone.vone.data.dao;

import com.vone.vone.data.dto.UserInfoDto;
import com.vone.vone.data.entity.Member;

public interface MemberDAO {
    Member join(Member member);

    UserInfoDto info();
}
