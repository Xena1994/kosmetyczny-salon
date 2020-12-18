package com.example.salon_kosmetyczny.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "visits")
@Getter
@Setter
@AllArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Past
    @Column(name="visit_date", nullable = false)
    private Date visitDate;



    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="pracownik_id", nullable = false)
    private Pracownik pracownik;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    private boolean finished;

    public Visit(){
        treatments = new LinkedList<>();
        total = BigDecimal.ZERO;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="visits_treatments",
                joinColumns = @JoinColumn(name = "visit_id"),
                inverseJoinColumns = @JoinColumn(name = "treatment_id"))
    private List<Treatment> treatments;

   @NumberFormat(pattern = "###,###.##")
    private BigDecimal total;

    public BigDecimal calculatePrice(){
        BigDecimal sum = new BigDecimal(0);
        for(Treatment t: treatments){
            sum = sum.add(t.getPrice());
        }
        return sum.setScale(2, BigDecimal.ROUND_CEILING);
    }
}
