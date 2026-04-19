package com.champsoft.hrms.guest.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataGuestRepository extends JpaRepository<GuestJpaEntity, String> {
    boolean existsByFullNameIgnoreCase(String fullName);
}