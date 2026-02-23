package fr.schmidt.poolapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketKind {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticketKind_seq")
    @SequenceGenerator(name = "ticketKind_seq", sequenceName = "ticketKind_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "ticketKind")
    private List<Ticket> tickets;

}
