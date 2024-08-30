package com.codelucky.spzx.model.entity.order;

import com.codelucky.spzx.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
public class OrderStatistics extends BaseEntity {
    private Date orderDate;
    private BigDecimal totalAmount;
    private Integer totalNum;
}