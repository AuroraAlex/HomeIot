package com.cuit.homeiot.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataDTO {
    @JsonFormat(pattern = "yy-MM-dd HH:mm")
    private Date at;
    private String payload;

}
