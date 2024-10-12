package com.zxm.club.auth.domain.convert;

import com.zxm.club.auth.domain.entity.AuthRoleBO;
import com.zxm.club.auth.domain.entity.AuthUserBO;
import com.zxm.club.auth.infra.basic.entity.AuthRole;
import com.zxm.club.auth.infra.basic.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 主题类别 BoConverter
 *
 * @author zxm
 * @date 2024/10/02
 */
@Mapper
public interface AuthRoleConverter {
    AuthRoleConverter INSTANCE = Mappers.getMapper(AuthRoleConverter.class);
    //entity转BO
    AuthRoleBO convertToAuthRoleBO(AuthRole authRole);
    //bo转entity
    AuthRole convertToAuthRole(AuthRoleBO authRoleBO);
}
