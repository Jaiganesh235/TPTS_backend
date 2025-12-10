package com.quadcore.tpts.tptsService;

import com.quadcore.tpts.tptsModels.DeliveryAgent;

import java.math.BigDecimal;
import java.util.List;

public interface DeliveryAgentService {

    DeliveryAgent createDeliveryAgent(DeliveryAgent deliveryAgent);

    DeliveryAgent updateDeliveryAgent(Long id, DeliveryAgent deliveryAgent);

    DeliveryAgent getDeliveryAgentById(Long id);

    List<DeliveryAgent> getAllDeliveryAgents();

    List<DeliveryAgent> getDeliveryAgentsByCompany(Long companyId);

    List<DeliveryAgent> getActiveDeliveryAgents(Long companyId);

    List<DeliveryAgent> getAvailableDeliveryAgents(Long companyId);

    DeliveryAgent updateAvailabilityStatus(Long id, Boolean isAvailable);

    DeliveryAgent updateActiveStatus(Long id, Boolean isActive);

    DeliveryAgent updateRating(Long id, BigDecimal newRating);

    void deleteDeliveryAgent(Long id);

    boolean existsByEmail(String email);
}