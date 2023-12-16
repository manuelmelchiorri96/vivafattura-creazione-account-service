package com.vivafattura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Investimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_investimento")
    private Long idInvestimento;

    @Column(name = "importo_investito", nullable = false, length = 50)
    @DecimalMax(value = "10000.00", message = "L'importo investito non può superare 1.000.000")
    @DecimalMin(value = "0.01", message = "L'importo investito deve essere maggiore di 0.01")
    private Double importoInvestito;

    @Column(name = "guadagno_potenziale", nullable = false, length = 50)
    private Long guadagnoPotenziale;

    @Column(name = "data_investimento", nullable = false, length = 50)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataInvestimento;

    @ManyToOne
    @JoinColumn(name = "investimenti_account", nullable = false)
    private AccountCliente investimentiAccount;


    public Investimento(Double importoInvestito, Long guadagnoPotenziale, LocalDateTime dataInvestimento) {
        this.importoInvestito = importoInvestito;
        this.guadagnoPotenziale = guadagnoPotenziale;
        this.dataInvestimento = dataInvestimento;
    }
}
