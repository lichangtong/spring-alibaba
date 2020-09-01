package com.alibaba.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Balance implements Serializable {
    private Long id;
    private Long status;
    private Long data;
    private String message;
}
