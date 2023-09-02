package com.evantagesoft.service.user.role.type;

import com.evantagesoft.entities.user.role.RoleType;
import com.evantagesoft.repository.user.role.type.RoleTypeRepository;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.response.Response;
import com.evantagesoft.vo.user.role.RoleTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleTypeService {

    @Autowired
    private RoleTypeRepository roleTypeRepository;

    public Response findAll(){
        Response response = new Response();
        try{
            List<RoleTypeVo> roleTypeVos = new ArrayList<>();
            Iterable<RoleType> roleTypes = this.roleTypeRepository.findAll();
            roleTypes.forEach(roleType -> roleTypeVos.add(RoleTypeVo.getRoleVo(roleType)));
            response.setData("roleTypes", roleTypeVos);
            response.setResponse(EVSResponse.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            response = new Response(EVSResponse.SYSTEM_ERROR, null);
        }
        return response;
    }
}
