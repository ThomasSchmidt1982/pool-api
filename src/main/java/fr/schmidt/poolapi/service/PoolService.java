package fr.schmidt.poolapi.service;

import fr.schmidt.poolapi.dto.request.PoolRequest;
import fr.schmidt.poolapi.dto.response.PoolResponse;
import fr.schmidt.poolapi.exception.ResourceNotFoundException;
import fr.schmidt.poolapi.model.entity.Pool;
import fr.schmidt.poolapi.model.enums.InOut;
import fr.schmidt.poolapi.repository.AccessRepository;
import fr.schmidt.poolapi.repository.PoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PoolService {

    // injection de dépendance
    private final PoolRepository poolRepository;
    private final AccessRepository accessRepository;

    // GET /pool/status
    public  PoolResponse getStatus() {
        Pool pool = poolRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Pool not found"));
        return toResponse(pool);
    }

    // PUT /pool
    public PoolResponse update(PoolRequest poolRequest){
        Pool pool = poolRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException("Pool not found"));
        pool.setMaxCapacity(poolRequest.maxCapacity());
        return toResponse(poolRepository.save(pool));
    }

    private PoolResponse toResponse(Pool pool){
        int entries = accessRepository.countByInOut(InOut.ENTRY);
        int exits = accessRepository.countByInOut(InOut.EXIT);
        int occupancy = entries - exits;
        int currentCapacity = pool.getMaxCapacity()-(entries - exits);
        return new PoolResponse(pool.getId(), pool.getMaxCapacity(), occupancy, currentCapacity);
    }

}
