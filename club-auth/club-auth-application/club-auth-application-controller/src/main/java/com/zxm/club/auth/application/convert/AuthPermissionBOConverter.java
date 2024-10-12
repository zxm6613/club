package com.zxm.club.auth.application.convert;

import com.zxm.club.auth.application.dto.AuthPermissionDTO;
import com.zxm.club.auth.application.dto.AuthRoleDTO;
import com.zxm.club.auth.application.vo.AuthPermissionVO;
import com.zxm.club.auth.application.vo.AuthRoleVO;
import com.zxm.club.auth.domain.entity.AuthPermissionBO;
import com.zxm.club.auth.domain.entity.AuthRoleBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 主题类别 BoConverter
 *
 * @author zxm
 * @date 2024/10/02
 */
@Mapper
public interface AuthPermissionBOConverter {
    AuthPermissionBOConverter INSTANCE = Mappers.getMapper(AuthPermissionBOConverter.class);
    //dto转BO
    AuthPermissionBO convertToAuthPermissionBO(AuthPermissionDTO authPermissionDTO);
    //bo转vo
    AuthPermissionVO convertToAuthPermissionVO(AuthPermissionBO authPermissionBO);
}
