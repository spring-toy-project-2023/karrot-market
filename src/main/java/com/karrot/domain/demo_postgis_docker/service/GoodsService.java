package com.karrot.domain.demo_postgis_docker.service;

import com.karrot.domain.demo_postgis_docker.Goods;

import java.util.List;
import java.util.UUID;

public interface GoodsService {

    public Goods getGoodsById(UUID id);

    public List<Goods> getAllGoods();

    public Goods save(Goods goods);
}
