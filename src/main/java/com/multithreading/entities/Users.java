package com.multithreading.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users_multi")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Users implements Serializable {

    private static final Long serialVersionUID = 1l;
    @Id
    private Integer userId;
    private String userName;
    private String userAddress;
    private Integer userOrderId;

}
