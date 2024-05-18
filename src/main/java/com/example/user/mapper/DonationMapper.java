package com.example.user.mapper;

import com.example.user.domain.Donation;
import com.example.user.feature.donation.dto.DonationRequest;
import com.example.user.feature.donation.dto.DonationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonationMapper {

    @Mapping(target = "donationTypeName", source = "donationType.donationType")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "postTypeName", source = "postType.type")
    @Mapping(target = "eventTypeName", source = "eventType.eventType")
    DonationResponse toDonationResponse(Donation donation);

    Donation toDonation(DonationRequest donationRequest);
}
