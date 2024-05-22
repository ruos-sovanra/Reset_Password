package com.example.user.feature.employ;

import com.example.user.feature.employ.dto.EmployTypeRequest;
import com.example.user.feature.employ.dto.EmployTypeResponse;
import com.example.user.utils.CustomPage;

public interface EmployTypeService {
    EmployTypeResponse createEmployType(EmployTypeRequest employTypeRequest);
    EmployTypeResponse getEmployType(String id);
    EmployTypeResponse updateEmployType(String id, EmployTypeRequest employTypeRequest);
    void deleteEmployType(String id);
    CustomPage<EmployTypeResponse> getEmployTypes(int page, int size,String baseUrl);
}
