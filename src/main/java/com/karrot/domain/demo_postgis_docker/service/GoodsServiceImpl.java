package com.karrot.domain.demo_postgis_docker.service;

import com.karrot.domain.demo_postgis_docker.Goods;
import com.karrot.domain.demo_postgis_docker.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public Goods getGoodsById(UUID id) {
        return goodsRepository.findById(id).orElse(null);
    }

    @Override
    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public List<Goods> getAllGoods() {
        return goodsRepository.findAll();
    }
}
