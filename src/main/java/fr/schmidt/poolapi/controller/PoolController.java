package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.PoolRequest;
import fr.schmidt.poolapi.dto.response.PoolResponse;
import fr.schmidt.poolapi.service.PoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pool")
@RequiredArgsConstructor
public class PoolController {

    //injection de dépendance
    private final PoolService poolService;

    @GetMapping("/status")
    public ResponseEntity<PoolResponse> getStatus(){
        return ResponseEntity.ok(poolService.getStatus());
    }

    @PutMapping
    public ResponseEntity<PoolResponse> update(@Valid @RequestBody PoolRequest request){
        return ResponseEntity.ok(poolService.update(request));
    }
}
