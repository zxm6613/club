package com.zxm.club.auth.application.convert;

import com.zxm.club.auth.application.dto.AuthPermissionRoleDTO;
import com.zxm.club.auth.application.dto.AuthRoleDTO;
import com.zxm.club.auth.application.dto.AuthUserDTO;
import com.zxm.club.auth.application.vo.AuthRoleVO;
import com.zxm.club.auth.application.vo.AuthUserVO;
import com.zxm.club.auth.domain.entity.AuthPermissionRoleBO;
import com.zxm.club.auth.domain.entity.AuthRoleBO;
import com.zxm.club.auth.domain.entity.AuthUserBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 主题类别 BoConverter
 *
 * @author zxm
 * @date 2024/10/02
 */
@Mapper
public interface AuthRoleBOConverter {
    AuthRoleBOConverter INSTANCE = Mappers.getMapper(AuthRoleBOConverter.class);
    //dto转BO
    AuthRoleBO convertToAuthUserBO(AuthRoleDTO authRoleDTO);
    //bo转vo
    AuthRoleVO convertToAuthUserVO(AuthRoleBO authRoleBO);


}
