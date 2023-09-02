package com.evantagesoft.service.user.role.permission;

import com.evantagesoft.entities.user.permission.Permission;
import com.evantagesoft.repository.user.role.permission.PermissionRepository;
import com.evantagesoft.service.user.role.RoleService;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.response.Response;
import com.evantagesoft.vo.user.permission.PermissionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private PermissionRepository permissionRepository;

    public Response findAll(){
        Response response = new Response();
        try{
            Iterable<Permission> permissions = this.permissionRepository.findAll();
            List<PermissionVo> permissionVos = new ArrayList<>();
            permissions.forEach(permission -> permissionVos.add(PermissionVo.getPermissionVo(permission)));
            response.setResponse(EVSResponse.SUCCESS);
            response.setData("permissions", permissionVos);
        } catch (Exception e){
            e.printStackTrace();
            logger.error(""+e);
            response = new Response(EVSResponse.SYSTEM_ERROR, null);
        }
        return response;
    }
}
