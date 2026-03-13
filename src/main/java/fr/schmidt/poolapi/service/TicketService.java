package fr.schmidt.poolapi.service;

import fr.schmidt.poolapi.dto.request.TicketKindRequest;
import fr.schmidt.poolapi.dto.request.TicketRequest;
import fr.schmidt.poolapi.dto.response.TicketKindResponse;
import fr.schmidt.poolapi.dto.response.TicketResponse;
import fr.schmidt.poolapi.exception.ResourceNotFoundException;
import fr.schmidt.poolapi.model.entity.Ticket;
import fr.schmidt.poolapi.model.entity.TicketKind;
import fr.schmidt.poolapi.model.entity.User;
import fr.schmidt.poolapi.repository.TicketKindRepository;
import fr.schmidt.poolapi.repository.TicketRepository;
import fr.schmidt.poolapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    // injections de dépendances
    private final TicketRepository ticketRepository;
    private final TicketKindRepository ticketKindRepository;
    private final UserRepository userRepository;

    // GET /tickets/kinds
    // Retourne la liste des types de tickets disponibles
    public List<TicketKindResponse> findAllKinds(){
        return ticketKindRepository.findAll()
                .stream()
                .map(kind->toKindResponse(kind))
                .toList();
    }


    // POST /tickets/kinds
    // Créer un type de ticket (ADMIN only)
    public TicketKindResponse createKind(TicketKindRequest request){
        TicketKind kind = new TicketKind();
        kind.setName(request.name());
        kind.setPrice(request.price());
        return toKindResponse(ticketKindRepository.save(kind));
    }

    // POST users/:id/tickets
    // Vends un tickets à un user
    public TicketResponse create(Long id, TicketRequest request){
        // 1. Vérifie que le user existe
        User user = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        // 2. Vérifie que le type de ticket existe
        TicketKind kind = ticketKindRepository.findById(request.ticketKindId())
                .orElseThrow(()->new ResourceNotFoundException("TicketKind not found"));
        // 3. Crée le ticket
        Ticket ticket = new Ticket();
        ticket.setPurchaseDate(LocalDate.now());
        ticket.setTicketKind(kind);
        ticket.setUser(user);
        // sauvegarde en BDD et retourne la réponse
        return toResponse(ticketRepository.save(ticket));
    }

    // GET /users/tickets
    // Retourne la liste de tous les tickets
    public List<TicketResponse> findAll(){
        return ticketRepository.findAll()
                .stream()
                .map(ticket -> toResponse(ticket))
                .toList();
    }

    // GET /users/:id/tickets
    // Retourne la liste de tous les tickets d'un user
    public List<TicketResponse> findByUserId(Long id){
        return ticketRepository.findByUserId(id)
                .stream()
                .map(ticket -> toResponse(ticket))
                .toList();
    }

    // Mapping Entity -> DTO
    private TicketResponse toResponse(Ticket ticket){
        return new TicketResponse(
                ticket.getId(),
                ticket.getPurchaseDate(),
                ticket.getUsingDate(),
                toKindResponse(ticket.getTicketKind())
        );
    }

    private TicketKindResponse toKindResponse(TicketKind kind){
        return new TicketKindResponse(
                kind.getId(),
                kind.getName(),
                kind.getPrice()
        );
    }

}
