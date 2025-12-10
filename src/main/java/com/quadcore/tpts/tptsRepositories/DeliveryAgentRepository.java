package com.quadcore.tpts.tptsRepositories;

import com.quadcore.tpts.tptsModels.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Long> {

    Optional<DeliveryAgent> findByEmail(String email);

    boolean existsByEmail(String email);

    // Changed from findByCompanyId to findByCompanyCompanyPlatformId
    List<DeliveryAgent> findByCompanyCompanyPlatformId(Long companyPlatformId);

    // Changed from findByCompanyIdAndIsActive to findByCompanyCompanyPlatformIdAndIsActive
    List<DeliveryAgent> findByCompanyCompanyPlatformIdAndIsActive(Long companyPlatformId, Boolean isActive);

    // Changed company.id to company.companyPlatformId in JPQL query
    @Query("SELECT da FROM DeliveryAgent da WHERE da.company.companyPlatformId = :companyPlatformId AND da.isAvailable = true AND da.isActive = true")
    List<DeliveryAgent> findAvailableAgentsByCompany(@Param("companyPlatformId") Long companyPlatformId);
}