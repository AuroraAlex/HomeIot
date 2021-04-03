package com.cuit.homeiot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Device_List")
public class Device {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String did;
    private String type;
    private String comm;
    @TableField(value = "`name`")
    private String name;
    @TableField(value = "`key`")
    private String key;
    @TableField(exist = false)
    private Boolean isConnected;

    public Device(String did, String type, String comm, String name, String key) {
        this.did = did;
        this.type = type;
        this.comm = comm;
        this.name = name;
        this.key = key;
    }
}
