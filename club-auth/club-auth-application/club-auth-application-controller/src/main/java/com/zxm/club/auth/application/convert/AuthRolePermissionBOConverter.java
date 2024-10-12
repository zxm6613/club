package com.zxm.club.auth.application.convert;

import com.zxm.club.auth.application.dto.AuthPermissionDTO;
import com.zxm.club.auth.application.dto.AuthPermissionRoleDTO;
import com.zxm.club.auth.application.vo.AuthPermissionVO;
import com.zxm.club.auth.domain.entity.AuthPermissionBO;
import com.zxm.club.auth.domain.entity.AuthPermissionRoleBO;
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
public interface AuthRolePermissionBOConverter {
    AuthRolePermissionBOConverter INSTANCE = Mappers.getMapper(AuthRolePermissionBOConverter.class);
    //dto转BO
    List<AuthPermissionRoleBO> convertToAuthRolePermissions(List<AuthPermissionRoleDTO> authPermissionRoleDTOList);
}
