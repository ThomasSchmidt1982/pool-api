package fr.schmidt.poolapi.controller;

import fr.schmidt.poolapi.dto.request.TicketRequest;
import fr.schmidt.poolapi.dto.response.TicketResponse;
import fr.schmidt.poolapi.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public List<TicketResponse> findByUserId(@PathVariable Long id){
        return ticketService.findByUserId(id);
    }

    @PostMapping
    public TicketResponse create(@PathVariable Long id, @Valid @RequestBody TicketRequest request){
        return ticketService.create(id, request );
    }
}
