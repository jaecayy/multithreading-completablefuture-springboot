package com.multithreading.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonData {

    @Id
    private Integer orderId;
    private String orderName;
    private Integer orderItems;
    private Integer productId;
    private Integer productQuantity;
    private String productCategory;
    private Integer userId;
    private String userName;
    private String userAddress;
}
