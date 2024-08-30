package com.codelucky.spzx.model.dto.h5;

import com.codelucky.spzx.model.entity.order.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderInfoDto {
    private Long userAddressId;
    private BigDecimal feightFee;
    private String remark;
    private List<OrderItem> orderItemList;
}