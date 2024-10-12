package com.zxm.club.auth.domain.convert;

import com.zxm.club.auth.domain.entity.AuthPermissionBO;
import com.zxm.club.auth.domain.entity.AuthUserBO;
import com.zxm.club.auth.infra.basic.entity.AuthPermission;
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
public interface AuthPermissionConverter {
    AuthPermissionConverter INSTANCE = Mappers.getMapper(AuthPermissionConverter.class);
    //entity转BO
    AuthPermissionBO convertToAuthUserBO(AuthPermission authPermission);
    //bo转entity
    AuthPermission convertToAuthUser(AuthPermissionBO authPermissionBO);
}
