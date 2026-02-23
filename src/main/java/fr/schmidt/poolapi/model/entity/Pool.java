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
@AllArgsConstructor
@NoArgsConstructor
public class Pool {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pool_seq")
    @SequenceGenerator(name = "pool_seq", sequenceName = "pool_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private Integer maxCapacity;

    @OneToMany(mappedBy = "pool")
    private List<Access> accesses;
}
