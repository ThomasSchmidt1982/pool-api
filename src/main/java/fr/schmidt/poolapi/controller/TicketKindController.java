package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.TicketKindRequest;
import fr.schmidt.poolapi.dto.response.TicketKindResponse;
import fr.schmidt.poolapi.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets/kinds")
@RequiredArgsConstructor
public class TicketKindController {

    private final TicketService ticketService;

    @GetMapping
    public List<TicketKindResponse> findAllKind() {
        return ticketService.findAllKinds();
    }

    @PostMapping
    public TicketKindResponse createKind(@Valid @RequestBody TicketKindRequest request) {
        return ticketService.createKind(request);
    }

}
