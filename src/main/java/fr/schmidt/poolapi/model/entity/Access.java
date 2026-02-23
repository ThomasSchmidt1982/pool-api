package fr.schmidt.poolapi.model.entity;

import fr.schmidt.poolapi.model.enums.InOut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Access {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "access_seq")
    @SequenceGenerator(name = "access_seq", sequenceName = "access_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private InOut inOut;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "pool_id", nullable = false)
    private Pool pool;
}
