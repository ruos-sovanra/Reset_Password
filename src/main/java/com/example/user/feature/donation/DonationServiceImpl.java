package com.example.user.feature.donation;

import com.example.user.domain.*;
import com.example.user.feature.donation.dto.DonationRequest;
import com.example.user.feature.donation.dto.DonationResponse;
import com.example.user.feature.repo.DonationTypeRepository;
import com.example.user.feature.repo.EventTypeRepository;
import com.example.user.feature.repo.PostTypeRepository;
import com.example.user.feature.user.UserRepository;
import com.example.user.feature.user.dto.UserResponse;
import com.example.user.mapper.DonationMapper;
import com.example.user.utils.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final DonationTypeRepository donationTypeRepository;
    private final PostTypeRepository postTypeRepository;
    private final EventTypeRepository eventTypeRepository;
    private final DonationMapper donationMapper;

    @Override
    public CustomPage<DonationResponse> getAllDonations(int page, int size, String baseUrl) {
        Page<Donation> donations = donationRepository.findAll(Pageable.ofSize(size).withPage(page));
        return CustomPagination(donations.map(donationMapper::toDonationResponse), baseUrl);
    }

    @Override
    public DonationResponse getDonationById(String id) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Donation not found"));
        return donationMapper.toDonationResponse(donation);
    }

    @Override
    public DonationResponse createDonation(DonationRequest donationRequest) {

        Donation donation = donationMapper.toDonation(donationRequest);

        User user = userRepository.findById(donationRequest.userId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        PostType postType = postTypeRepository.findByType("EVENT")
                .orElseThrow(() -> new NoSuchElementException("PostType not found"));

        DonationType donationType = donationTypeRepository.findById(donationRequest.donationTypeId())
                .orElseThrow(() -> new NoSuchElementException("DonationType not found"));

        EventType eventType = eventTypeRepository.findById(donationRequest.eventTypeId())
                .orElseThrow(() -> new NoSuchElementException("EventType not found"));

        donation.setUser(user);

        donation.setPostType(postType);

        donation.setDonationType(donationType);

        donation.setEventType(eventType);

        donationRepository.save(donation);
        return donationMapper.toDonationResponse(donation);
    }


    @Override
    public DonationResponse updateDonation(String id, DonationRequest donationRequest) {
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Donation not found"));
        User user = userRepository.findById(donationRequest.userId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        PostType postType = postTypeRepository.findById("3cfd4e85-2a05-427d-91db-4ee1aa4ce185")
                .orElseThrow(() -> new NoSuchElementException("PostType not found"));

        DonationType donationType = donationTypeRepository.findById(donationRequest.donationTypeId())
                .orElseThrow(() -> new NoSuchElementException("DonationType not found"));

        EventType eventType = eventTypeRepository.findById(donationRequest.eventTypeId())
                .orElseThrow(() -> new NoSuchElementException("EventType not found"));

        donation.setUser(user);
        donation.setPostType(postType);
        donation.setDonationType(donationType);
        donation.setEventType(eventType);
        donation.setAmount(donationRequest.amount());
        donation.setThumbnail(donationRequest.thumbnail());
        donationRepository.save(donation);
        return donationMapper.toDonationResponse(donation);
    }

    @Override
    public void deleteDonation(String id) {
        donationRepository.deleteById(id);
    }

    public CustomPage<DonationResponse> CustomPagination(Page<DonationResponse> page, String baseUrl){
        CustomPage<DonationResponse> customPage = new CustomPage<>();
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
