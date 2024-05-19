package com.example.user.init;

import com.example.user.domain.*;
import com.example.user.feature.repo.*;
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
    private final DonationTypeRepository donationTypeRepository;
    private final EventTypeRepository eventTypeRepository;


    @PostConstruct
    void initData(){
        roleInit();
        accTypeInit();
        postTypeInit();
        statusInit();
        donationTypeInit();
//        eventTypeInit();
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
        List<String> postTypes = List.of("EVENT","JOB","SOCIAL","DONATION");
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

    void donationTypeInit(){
        List<String> donationTypes = List.of("MONEY","ITEM");
        if (donationTypeRepository.count() == 0){
            donationTypes.forEach(donationType -> {
                DonationType newDonationType = new DonationType();
                newDonationType.setDonationType(donationType);
                donationTypeRepository.save(newDonationType);
            });
        }
    }

//    void eventTypeInit(){
//        List<String> eventTypes = List.of("NATURE","OLD PEOPLE","CHILDREN");
//        if (eventTypeRepository.count() == 0){
//            eventTypes.forEach(eventType -> {
//                EventType newEventType = new EventType();
//                newEventType.setEventType(eventType);
//                eventTypeRepository.save(newEventType);
//            });
//        }
//    }




}
