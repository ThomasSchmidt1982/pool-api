package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.TicketRequest;
import fr.schmidt.poolapi.dto.response.TicketResponse;
import fr.schmidt.poolapi.model.entity.Person;
import fr.schmidt.poolapi.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketResponse>> findByUserId(@AuthenticationPrincipal Person person){
        return ResponseEntity.ok(ticketService.findByUserId(person.getId()));
    }

    @PostMapping
    public ResponseEntity<TicketResponse> create(@AuthenticationPrincipal Person person, @Valid @RequestBody TicketRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.create(person.getId(), request ));
    }
}
