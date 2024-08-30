package com.codelucky.spzx.model.entity.system;

import com.codelucky.spzx.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SysRoleUser extends BaseEntity {
    private Long roleId;
    private Long userId;
}