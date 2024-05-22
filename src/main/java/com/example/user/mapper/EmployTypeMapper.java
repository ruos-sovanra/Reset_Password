package com.example.user.mapper;

import com.example.user.domain.EmployType;
import com.example.user.feature.employ.dto.EmployTypeRequest;
import com.example.user.feature.employ.dto.EmployTypeResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployTypeMapper {

    EmployTypeResponse toEmployTypeResponse(EmployType employType);
    EmployType requestToEmployType(EmployTypeRequest employTypeRequest);
}
