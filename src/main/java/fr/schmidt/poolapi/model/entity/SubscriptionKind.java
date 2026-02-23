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
public class SubscriptionKind {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriptionKind_seq")
    @SequenceGenerator(name = "subscriptionKind_seq", sequenceName = "subscriptionKind_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer durationDays;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "subscriptionKind")
    private List<Subscription> subscriptions;

}
