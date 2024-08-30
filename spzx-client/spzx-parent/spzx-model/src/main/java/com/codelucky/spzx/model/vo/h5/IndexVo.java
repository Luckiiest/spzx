package com.codelucky.spzx.model.vo.h5;

import com.codelucky.spzx.model.entity.product.Category;
import com.codelucky.spzx.model.entity.product.ProductSku;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class IndexVo {
    private List<Category> categoryList;
    private List<ProductSku> productSkuList;
}
