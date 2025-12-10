package com.quadcore.tpts.tptsServiceImpl;

import com.quadcore.tpts.tptsModels.DeliveryAgent;
import com.quadcore.tpts.tptsRepositories.DeliveryAgentRepository;
import com.quadcore.tpts.tptsService.DeliveryAgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


@Service
public class DeliveryAgentServiceImpl implements DeliveryAgentService {

    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;


    @Override
    public DeliveryAgent createDeliveryAgent(DeliveryAgent deliveryAgent) {
        if (existsByEmail(deliveryAgent.getEmail())) {
            throw new RuntimeException("Delivery agent with email " + deliveryAgent.getEmail() + " already exists");
        }

        if (deliveryAgent.getIsAvailable() == null) {
            deliveryAgent.setIsAvailable(true);
        }
        if (deliveryAgent.getIsActive() == null) {
            deliveryAgent.setIsActive(true);
        }
        if (deliveryAgent.getAvgRating() == null) {
            deliveryAgent.setAvgRating(BigDecimal.ZERO);
        }
        if (deliveryAgent.getTotalDeliveries() == null) {
            deliveryAgent.setTotalDeliveries(0);
        }
        if (deliveryAgent.getRole() == null) {
            deliveryAgent.setRole("DELIVERY_AGENT");
        }

        return deliveryAgentRepository.save(deliveryAgent);
    }

    @Override
    public DeliveryAgent updateDeliveryAgent(Long id, DeliveryAgent deliveryAgent) {
        DeliveryAgent existing = getDeliveryAgentById(id);

        // Update only non-null fields
        if (deliveryAgent.getFullName() != null) {
            existing.setFullName(deliveryAgent.getFullName());
        }
        if (deliveryAgent.getEmail() != null && !deliveryAgent.getEmail().equals(existing.getEmail())) {
            if (existsByEmail(deliveryAgent.getEmail())) {
                throw new RuntimeException("Email already in use");
            }
            existing.setEmail(deliveryAgent.getEmail());
        }
        if (deliveryAgent.getPhoneNumber() != null) {
            existing.setPhoneNumber(deliveryAgent.getPhoneNumber());
        }
        if (deliveryAgent.getVehicleType() != null) {
            existing.setVehicleType(deliveryAgent.getVehicleType());
        }
        if (deliveryAgent.getVehicleNumber() != null) {
            existing.setVehicleNumber(deliveryAgent.getVehicleNumber());
        }
        if (deliveryAgent.getLicenseNumber() != null) {
            existing.setLicenseNumber(deliveryAgent.getLicenseNumber());
        }

        return deliveryAgentRepository.save(existing);
    }

    @Override
    public DeliveryAgent getDeliveryAgentById(Long id) {
        return deliveryAgentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery agent not found with id: " + id));
    }

    @Override
    public List<DeliveryAgent> getAllDeliveryAgents() {
        return deliveryAgentRepository.findAll();
    }

    @Override
    public List<DeliveryAgent> getDeliveryAgentsByCompany(Long companyId) {
        return deliveryAgentRepository.findByCompanyCompanyPlatformId(companyId);
    }

    @Override
    public List<DeliveryAgent> getActiveDeliveryAgents(Long companyId) {
        return deliveryAgentRepository.findByCompanyCompanyPlatformIdAndIsActive(companyId, true);
    }

    @Override
    public List<DeliveryAgent> getAvailableDeliveryAgents(Long companyId) {
        return deliveryAgentRepository.findAvailableAgentsByCompany(companyId);
    }

    @Override
    public DeliveryAgent updateAvailabilityStatus(Long id, Boolean isAvailable) {
        DeliveryAgent agent = getDeliveryAgentById(id);
        agent.setIsAvailable(isAvailable);
        return deliveryAgentRepository.save(agent);
    }

    @Override
    public DeliveryAgent updateActiveStatus(Long id, Boolean isActive) {
        DeliveryAgent agent = getDeliveryAgentById(id);
        agent.setIsActive(isActive);
        return deliveryAgentRepository.save(agent);
    }

    @Override
    public DeliveryAgent updateRating(Long id, BigDecimal newRating) {
        DeliveryAgent agent = getDeliveryAgentById(id);

        // Calculate new average rating
        BigDecimal currentAvg = agent.getAvgRating();
        int totalDeliveries = agent.getTotalDeliveries();

        BigDecimal totalRating = currentAvg.multiply(BigDecimal.valueOf(totalDeliveries));
        totalRating = totalRating.add(newRating);

        int newTotalDeliveries = totalDeliveries + 1;
        BigDecimal newAvg = totalRating.divide(BigDecimal.valueOf(newTotalDeliveries), 2, RoundingMode.HALF_UP);

        agent.setAvgRating(newAvg);
        agent.setTotalDeliveries(newTotalDeliveries);

        return deliveryAgentRepository.save(agent);
    }

    @Override
    public void deleteDeliveryAgent(Long id) {
        if (!deliveryAgentRepository.existsById(id)) {
            throw new RuntimeException("Delivery agent not found with id: " + id);
        }
        deliveryAgentRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return deliveryAgentRepository.existsByEmail(email);
    }
}