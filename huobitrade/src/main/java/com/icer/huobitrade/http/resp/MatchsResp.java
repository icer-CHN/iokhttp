package com.icer.huobitrade.http.resp;

import com.icer.huobitrade.entity.Match;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cljlo on 2018/1/21.
 */

public class MatchsResp extends BaseResp implements Serializable {
    private List<Match> data;

    public List<Match> getData() {
        return data;
    }

    public void setData(List<Match> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MatchsResp{" +
                "data=" + data +
                '}';
    }
}
