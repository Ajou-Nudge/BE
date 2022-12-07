package com.vone.vone.data.dao;

import com.vone.vone.data.entity.Context;
import com.vone.vone.data.entity.Post;
import com.vone.vone.data.entity.VC;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.List;

public interface VCDAO {
    VC insertVC(VC vc);

    VC selectVC(Long id) throws JSONException;
}
