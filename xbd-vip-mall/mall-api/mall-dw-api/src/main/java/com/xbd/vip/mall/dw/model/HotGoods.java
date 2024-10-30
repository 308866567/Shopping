package com.xbd.vip.mall.dw.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mslogs")
public class HotGoods {
    private String ip;

    private String uri;

    @TableField("__time")
    private Date accesstime;

}
