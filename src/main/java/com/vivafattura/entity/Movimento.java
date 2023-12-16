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
public class Movimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_movimento", nullable = false, length = 50)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataMovimento;

    @Column(nullable = false)
    @DecimalMin(value = "0.01", message = "L'importo deve essere maggiore di 0.01")
    @DecimalMax(value = "10000.00", message = "L'importo non può superare 10000.00")
    private Double importo;

    @Column(name = "tipo_movimento", nullable = false, length = 50)
    @NotBlank(message = "Aggiungere categoria movimento")
    private String tipoMovimento;

    @ManyToOne
    @JoinColumn(name = "movimenti_account", nullable = false)
    private AccountCliente movimentiAccount;

}
