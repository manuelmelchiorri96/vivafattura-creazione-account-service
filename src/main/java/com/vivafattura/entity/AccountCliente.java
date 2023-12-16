package com.vivafattura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "accounts_cliente")
public class AccountCliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_conto", nullable = false, length = 20)
    private Long numeroConto;

    @DecimalMax(value = "1.0e6", message = "Il saldo non può superare 1.000.000")
    private Double saldo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_account", nullable = false, length = 50)
    @Pattern(regexp = "^(corrente|risparmio)$", message = "Il tipo di account deve essere 'corrente' o 'risparmio'")
    private TipoAccount tipoDiAccount;

    @ManyToOne
    @JoinColumn(name = "accounts_cliente", nullable = false)
    private Cliente accountsCliente;

    @OneToMany(mappedBy = "transazioniAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transazione> transazioni;

    @OneToMany(mappedBy = "beneficiari", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Beneficiario> beneficiari;

    @OneToMany(mappedBy = "investimentiAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Investimento> investimenti;

    @OneToMany(mappedBy = "movimentiAccount", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimento> movimenti;


    public AccountCliente(Long numeroConto) {
        this.numeroConto = numeroConto;
    }

    public AccountCliente(Long numeroConto, Double saldo) {
        this.numeroConto = numeroConto;
        this.saldo = saldo;
    }

}
