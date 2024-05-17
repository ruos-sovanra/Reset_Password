package com.example.user.init;

import com.example.user.domain.AccType;
import com.example.user.domain.PostType;
import com.example.user.domain.Role;
import com.example.user.feature.repo.AccTypeRepository;
import com.example.user.feature.repo.PostTypeRepository;
import com.example.user.feature.repo.RoleRepository;
import com.example.user.feature.repo.StatusRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final AccTypeRepository accTypeRepository;
    private final PostTypeRepository postTypeRepository;
    private final StatusRepository statusRepository;

    @PostConstruct
    void initData(){
        roleInit();
        accTypeInit();
        postTypeInit();
        statusInit();
    }

    void roleInit(){
        List<String> roles = List.of("ADMIN","USER","ALUMNI","PARTNER");
        if (roleRepository.count() == 0){
            roles.forEach(role -> {
                Role newRole = new Role();
                newRole.setName(role);
                roleRepository.save(newRole);
            });
        }
    }

    void accTypeInit(){
        List<String> accTypes = List.of("ALUMNI","PARTNER");
        if (accTypeRepository.count() == 0){
            accTypes.forEach(accType -> {
                AccType newAccType = new AccType();
                newAccType.setName(accType);
                accTypeRepository.save(newAccType);
            });
        }
    }

    void postTypeInit(){
        List<String> postTypes = List.of("EVENT","JOB","SOCIAL");
        if (postTypeRepository.count() == 0){
            postTypes.forEach(postType -> {
                PostType newPostType = new PostType();
                newPostType.setType(postType);
                postTypeRepository.save(newPostType);
            });
        }
    }

    void statusInit(){
        List<String> statuses = List.of("URGENT","ACTIVE","FULFILLED","CLOSED");
        if (statusRepository.count() == 0){
            statuses.forEach(status -> {
                com.example.user.domain.Status newStatus = new com.example.user.domain.Status();
                newStatus.setStatus(status);
                statusRepository.save(newStatus);
            });
        }
    }




}
