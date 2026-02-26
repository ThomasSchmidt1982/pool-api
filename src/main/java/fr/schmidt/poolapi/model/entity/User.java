package fr.schmidt.poolapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends Person {

    @OneToMany(mappedBy = "user")
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        if (isAdmin()){
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return  List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
