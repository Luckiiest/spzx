package com.codelucky.spzx.model.entity.product;

import com.codelucky.spzx.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "商品规格实体类")
public class ProductSpec extends BaseEntity {
    @Schema(description = "规格名称")
    private String specName;
    @Schema(description = "规格值")
    private String specValue;
}
