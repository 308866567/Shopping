package com.xbd.vip.mall.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
//MyBatisPlus表映射注解
@TableName(value = "spu")
public class Spu implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String intro;
    private Integer brandId;
    private Integer categoryOneId;
    private Integer categoryTwoId;
    //三级分类id
    private Integer categoryThreeId;
    private String images;
    private String afterSalesService;
    private String content;
    private String attributeList;
    private Integer isMarketable;
    private Integer isDelete;
    private Integer status;
}
