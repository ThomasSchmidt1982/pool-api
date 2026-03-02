package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.TicketKindRequest;
import fr.schmidt.poolapi.dto.response.TicketKindResponse;
import fr.schmidt.poolapi.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets/kinds")
@RequiredArgsConstructor
public class TicketKindController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<TicketKindResponse>> findAllKind() {
        return ResponseEntity.ok(ticketService.findAllKinds());
    }

    @PostMapping
    public ResponseEntity<TicketKindResponse> createKind(@Valid @RequestBody TicketKindRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createKind(request));
    }

}