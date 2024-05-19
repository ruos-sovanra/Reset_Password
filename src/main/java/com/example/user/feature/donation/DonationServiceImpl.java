package com.example.user.feature.donation;

import com.example.user.domain.*;
import com.example.user.feature.donation.dto.DonationRequest;
import com.example.user.feature.donation.dto.DonationResponse;
import com.example.user.feature.repo.DonationTypeRepository;
import com.example.user.feature.repo.EventTypeRepository;
import com.example.user.feature.repo.PostTypeRepository;
import com.example.user.feature.user.UserRepository;
import com.example.user.mapper.DonationMapper;
import lombok.RequiredArgsConstructor;
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
    public List<DonationResponse> getAllDonations() {
        List<Donation> donations = donationRepository.findAll();

        return donations.stream()
                .map(donationMapper::toDonationResponse)
                .toList();
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

        var eventType = eventTypeRepository.findById(donationRequest.eventTypeId())
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
}
