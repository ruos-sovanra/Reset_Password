package com.example.user.feature.employ;

import com.example.user.domain.EmployType;
import com.example.user.feature.employ.dto.EmployTypeRequest;
import com.example.user.feature.employ.dto.EmployTypeResponse;
import com.example.user.mapper.EmployTypeMapper;
import com.example.user.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployTypeServiceImpl implements EmployTypeService{

    private final EmployTypeRepository employTypeRepository;
    private final EmployTypeMapper employTypeMapper;

    @Override
    public EmployTypeResponse createEmployType(EmployTypeRequest employTypeRequest) {
        EmployType employType = employTypeMapper.requestToEmployType(employTypeRequest);
        employTypeRepository.save(employType);

        return employTypeMapper.toEmployTypeResponse(employType);
    }

    @Override
    public EmployTypeResponse getEmployType(String id) {
        EmployType employType = employTypeRepository.findById(id).orElseThrow();

        return employTypeMapper.toEmployTypeResponse(employType);
    }

    @Override
    public EmployTypeResponse updateEmployType(String id, EmployTypeRequest employTypeRequest) {
        EmployType employType = employTypeRepository.findById(id).orElseThrow();
        employType.setEmployType(employTypeRequest.employType());
        employTypeRepository.save(employType);
        return employTypeMapper.toEmployTypeResponse(employType);
    }

    @Override
    public void deleteEmployType(String id) {
        employTypeRepository.deleteById(id);
    }

    @Override
    public CustomPage<EmployTypeResponse> getEmployTypes(int page, int size,String baseUrl) {

        Pageable pageable = PageRequest.of(page, size);

        Page<EmployType> employTypes = employTypeRepository.findAll(pageable);

        return CustomPagination(employTypes.map(employTypeMapper::toEmployTypeResponse), baseUrl);
    }

    public CustomPage<EmployTypeResponse> CustomPagination(Page<EmployTypeResponse> page, String baseUrl){
        CustomPage<EmployTypeResponse> customPage = new CustomPage<>();
        if(page.hasNext()){
            customPage.setNext(baseUrl + "?page=" + (page.getNumber() + 1) + "&size=" + page.getSize());
        }
        if (page.hasPrevious()){
            customPage.setPrevious(baseUrl + "?page=" + (page.getNumber() - 1) + "&size=" + page.getSize());
        }
        customPage.setTotal((int) page.getTotalElements());
        customPage.setResults(page.getContent());
        return customPage;
    }
}
