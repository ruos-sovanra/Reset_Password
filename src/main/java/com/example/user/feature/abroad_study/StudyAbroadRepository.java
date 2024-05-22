package com.example.user.feature.abroad_study;

import com.example.user.domain.StudyAbroad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyAbroadRepository extends JpaRepository<StudyAbroad, String> {

    StudyAbroad findByCountry(String country);

    Page<StudyAbroad> findAll(Pageable pageable);
}
