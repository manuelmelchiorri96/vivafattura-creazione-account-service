package com.vivafattura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transazione implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transazione")
    private Long idTransazione;

    @Column(name = "importo_transazione", nullable = false, length = 50)
    @DecimalMax(value = "10000.00", message = "L'importo della transazione non può superare 1.000.000")
    @DecimalMin(value = "0.01", message = "L'importo della transazione deve essere maggiore di 0.01")
    private Double importoTransazione;

    @Column(name = "data_transazione",nullable = false, length = 50)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataTransazione;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Aggiungere causale")
    private String causale;

    @ManyToOne
    @JoinColumn(name = "transazioni_account", nullable = false)
    private AccountCliente transazioniAccount;

    @ManyToOne
    @JoinColumn(name = "beneficiario_transazioni", nullable = false)
    private Beneficiario beneficiarioTransazioni;



    public Transazione(Double importoTransazione, LocalDateTime dataTransazione, String causale, AccountCliente transazioniAccount, Beneficiario beneficiarioTransazioni) {
        this.importoTransazione = importoTransazione;
        this.dataTransazione = dataTransazione;
        this.causale = causale;
        this.transazioniAccount = transazioniAccount;
        this.beneficiarioTransazioni = beneficiarioTransazioni;
    }
    public Transazione(Double importoTransazione, LocalDateTime dataTransazione, String causale, Beneficiario beneficiarioTransazioni) {
        this.importoTransazione = importoTransazione;
        this.dataTransazione = dataTransazione;
        this.causale = causale;
        this.beneficiarioTransazioni = beneficiarioTransazioni;
    }

}
