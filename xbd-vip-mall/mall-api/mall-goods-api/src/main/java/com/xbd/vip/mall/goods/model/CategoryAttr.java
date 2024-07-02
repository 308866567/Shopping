package com.xbd.vip.mall.goods.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "category_attr")
public class CategoryAttr implements Serializable {
    @TableField
    private Integer categoryId;
    @TableField
    private Integer attrId;
}
