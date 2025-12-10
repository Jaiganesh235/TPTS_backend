package com.quadcore.tpts.tptsControllers;

import com.quadcore.tpts.tptsModels.DeliveryAgent;
import com.quadcore.tpts.tptsService.DeliveryAgentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/tpts/company/deliveryAgent")
@RequiredArgsConstructor
public class DeliveryAgentController {

    private final DeliveryAgentService deliveryAgentService;

    @PostMapping
    public ResponseEntity<DeliveryAgent> createDeliveryAgent(@Valid @RequestBody DeliveryAgent deliveryAgent) {
        DeliveryAgent created = deliveryAgentService.createDeliveryAgent(deliveryAgent);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryAgent> updateDeliveryAgent(
            @PathVariable Long id,
            @RequestBody DeliveryAgent deliveryAgent) {
        DeliveryAgent updated = deliveryAgentService.updateDeliveryAgent(id, deliveryAgent);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryAgent> getDeliveryAgentById(@PathVariable Long id) {
        DeliveryAgent agent = deliveryAgentService.getDeliveryAgentById(id);
        return ResponseEntity.ok(agent);
    }

    @GetMapping
    public ResponseEntity<List<DeliveryAgent>> getAllDeliveryAgents() {
        List<DeliveryAgent> agents = deliveryAgentService.getAllDeliveryAgents();
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<DeliveryAgent>> getDeliveryAgentsByCompany(@PathVariable Long companyId) {
        List<DeliveryAgent> agents = deliveryAgentService.getDeliveryAgentsByCompany(companyId);
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/company/{companyId}/active")
    public ResponseEntity<List<DeliveryAgent>> getActiveDeliveryAgents(@PathVariable Long companyId) {
        List<DeliveryAgent> agents = deliveryAgentService.getActiveDeliveryAgents(companyId);
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/company/{companyId}/available")
    public ResponseEntity<List<DeliveryAgent>> getAvailableDeliveryAgents(@PathVariable Long companyId) {
        List<DeliveryAgent> agents = deliveryAgentService.getAvailableDeliveryAgents(companyId);
        return ResponseEntity.ok(agents);
    }

    @PatchMapping("/{id}/availability")
    public ResponseEntity<DeliveryAgent> updateAvailability(
            @PathVariable Long id,
            @RequestParam Boolean isAvailable) {
        DeliveryAgent updated = deliveryAgentService.updateAvailabilityStatus(id, isAvailable);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/active-status")
    public ResponseEntity<DeliveryAgent> updateActiveStatus(
            @PathVariable Long id,
            @RequestParam Boolean isActive) {
        DeliveryAgent updated = deliveryAgentService.updateActiveStatus(id, isActive);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/rating")
    public ResponseEntity<DeliveryAgent> updateRating(
            @PathVariable Long id,
            @RequestParam BigDecimal rating) {
        DeliveryAgent updated = deliveryAgentService.updateRating(id, rating);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliveryAgent(@PathVariable Long id) {
        deliveryAgentService.deleteDeliveryAgent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailExists(@RequestParam String email) {
        boolean exists = deliveryAgentService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }
}