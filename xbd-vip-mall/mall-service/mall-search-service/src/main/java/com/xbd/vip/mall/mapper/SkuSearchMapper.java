package com.xbd.vip.mall.mapper;

import com.xbd.vip.mall.search.model.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuSearchMapper extends ElasticsearchRepository<SkuEs,String> {
}
