package com.cuit.homeiot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Rule_List")
public class RuleList {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String tdid;
    private String sdid;
    private String des;
}
