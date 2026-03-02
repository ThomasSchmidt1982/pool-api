package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.TicketRequest;
import fr.schmidt.poolapi.dto.response.TicketResponse;
import fr.schmidt.poolapi.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketResponse>> findByUserId(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.findByUserId(id));
    }

    @PostMapping
    public ResponseEntity<TicketResponse> create(@PathVariable Long id, @Valid @RequestBody TicketRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.create(id, request ));
    }
}
