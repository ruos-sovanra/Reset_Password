package com.example.user.features.usersocialmedia;

import com.example.user.domain.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSocialMediaRepository extends JpaRepository<SocialMedia,String> {

    Optional<SocialMedia>findSocialMediaById(String id);
}
