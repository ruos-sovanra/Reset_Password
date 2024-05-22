package com.example.user.feature.employ;

import com.example.user.feature.employ.dto.EmployTypeRequest;
import com.example.user.feature.employ.dto.EmployTypeResponse;
import com.example.user.utils.BaseResponse;
import com.example.user.utils.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employ-type")
@RequiredArgsConstructor
public class EmployTypeRestController {

    private final EmployTypeService employTypeService;

    @GetMapping
    @Operation(summary = "Get all employ types")
    public ResponseEntity<CustomPage<EmployTypeResponse>> getAllEmployTypes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request){
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/api/v1/employ-type";
        CustomPage<EmployTypeResponse> employTypeResponseCustomPage = employTypeService.getEmployTypes(page, size, baseUrl);

        return ResponseEntity.ok(employTypeResponseCustomPage);
    }

    @PostMapping
    @Operation(summary = "Create employ type")
    public BaseResponse<EmployTypeResponse> createEmployType(@RequestBody EmployTypeRequest employTypeRequest){
        return BaseResponse.<EmployTypeResponse>createSuccess()
                .setPayload(employTypeService.createEmployType(employTypeRequest));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employ type by id")
    public BaseResponse<EmployTypeResponse> getEmployType(@PathVariable String id){
        return BaseResponse.<EmployTypeResponse>ok()
                .setPayload(employTypeService.getEmployType(id));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update employ type")
    public BaseResponse<EmployTypeResponse> updateEmployType(@PathVariable String id, @RequestBody EmployTypeRequest employTypeRequest){
        return BaseResponse.<EmployTypeResponse>updateSuccess()
                .setPayload(employTypeService.updateEmployType(id, employTypeRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employ type")
    public BaseResponse<Void> deleteEmployType(@PathVariable String id){
        employTypeService.deleteEmployType(id);
        return BaseResponse.<Void>deleteSuccess();
    }


}
