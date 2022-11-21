package com.vone.vone.data.dao;

import com.vone.vone.data.entity.Context;
import com.vone.vone.data.entity.HoldersVC;
import com.vone.vone.data.entity.Post;

import java.util.List;

public interface ContextDAO {
    Context insertContext(Context context);

    List<Context> selectAllContext();

    Context selectContext(String contextName);
}
