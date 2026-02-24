package fr.schmidt.poolapi.service;

import fr.schmidt.poolapi.dto.request.PoolRequest;
import fr.schmidt.poolapi.dto.response.PoolResponse;
import fr.schmidt.poolapi.model.entity.Pool;
import fr.schmidt.poolapi.repository.PoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PoolService {

    // injection de dépendance
    private final PoolRepository poolRepository;

    // GET /pool/status
    public  PoolResponse getStatus() {
        Pool pool = poolRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pool not found"));
        return new PoolResponse(pool.getId(), pool.getMaxCapacity());
    }

    // PUT /pool
    public PoolResponse update(PoolRequest poolRequest){
        Pool pool = poolRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(()->new RuntimeException("Pool not found"));
        pool.setMaxCapacity(poolRequest.maxCapacity());
        return toResponse(poolRepository.save(pool));
    }

    private PoolResponse toResponse(Pool pool){
        return new PoolResponse(pool.getId(), pool.getMaxCapacity());
    }

}
