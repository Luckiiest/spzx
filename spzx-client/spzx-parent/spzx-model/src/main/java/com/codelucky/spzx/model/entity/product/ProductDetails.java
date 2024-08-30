package com.codelucky.spzx.model.entity.product;

import com.codelucky.spzx.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDetails extends BaseEntity {
    private Long productId;
    private String imageUrls;
}