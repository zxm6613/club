package com.zxm.club.auth.application.convert;

import com.zxm.club.auth.application.dto.AuthUserDTO;
import com.zxm.club.auth.application.vo.AuthUserVO;
import com.zxm.club.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 主题类别 BoConverter
 *
 * @author zxm
 * @date 2024/10/02
 */
@Mapper
public interface AuthUserBOConverter {
    AuthUserBOConverter INSTANCE = Mappers.getMapper(AuthUserBOConverter.class);
    //dto转BO
    AuthUserBO convertToAuthUserBO(AuthUserDTO authUserDTO);
    //bo转vo
    AuthUserVO convertToAuthUserVO(AuthUserBO authUserBO);
}
