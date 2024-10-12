package com.zxm.club.auth.domain.convert;

import com.zxm.club.auth.domain.entity.AuthUserBO;
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
public interface AuthUserConverter {
    AuthUserConverter INSTANCE = Mappers.getMapper(AuthUserConverter.class);
    //entity转BO
    AuthUserBO convertToAuthUserBO(AuthUser authUser);
    //bo转entity
    AuthUser convertToAuthUser(AuthUserBO authUserBO);
}
