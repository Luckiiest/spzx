package com.codelucky.spzx.model.vo.system;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(description = "验证码响应结果实体类")
public class ValidateCodeVo {
    @Schema(description = "验证码key")
    private String codeKey;
    @Schema(description = "验证码value")
    private String codeValue;
}
