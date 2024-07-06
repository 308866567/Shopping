package com.xbd.vip.mall.util;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.bytes.ByteBufferReference;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.DefaultResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;

import java.nio.ByteBuffer;
import java.util.Map;

public class HighlightResultMapper extends DefaultResultMapper {

    /*
    映射转换,将非高亮数据替换成高亮数据
     */
    @Override
    public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        //1. 获取所有非高亮数据
        SearchHits hits = response.getHits();
        //2.循环非高亮数据集合
        for (SearchHit hit : hits) {
            //非高亮数据,当前的单条数据
            Map<String, Object> sourceMap = hit.getSourceAsMap();
            //3.获取高亮数据
            for (Map.Entry<String, HighlightField> entry : hit.getHighlightFields().entrySet()) {
                //4.将所有非高亮数据替换成高亮数据
                String key = entry.getKey();
                //如果当前非高亮对象中有该高亮数据对应的非高亮对象,则进行替换
                if (sourceMap.containsKey(key)) {
                    //高亮碎片
                    Text[] fragments = entry.getValue().getFragments();
                    sourceMap.put(key, transTextArrayToString(fragments));
                }
            }
            //更新hit的数据

            hit.sourceRef(new ByteBufferReference(
                    ByteBuffer.wrap(
                            JSONObject.toJSONString(sourceMap)
                                    .getBytes())));
        }
        return super.mapResults(response, clazz, pageable);
    }

    /*
     *拼接数据碎片
     */
    public String transTextArrayToString(Text[] fragments) {
        if (null == fragments) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        for (Text fragment : fragments) {
            buffer.append(fragment.string());
        }
        return buffer.toString();
    }
}
