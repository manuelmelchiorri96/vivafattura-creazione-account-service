package com.vivafattura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficiario implements Serializable {


    @Id
    @Column(name = "conto_beneficiario", nullable = false, length = 50)
    @NotBlank(message = "Inserire beneficiario")
    @Size(min = 1, max = 12, message = "Inserire numero di conto valido, compreso tra 1 e 12 cifre")
    private Long contoBeneficiario;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Il nome non può essere vuoto")
    @Size(min = 2, max = 50, message = "Il nome deve essere compreso tra 2 e 50 caratteri")
    private String nome;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Il cognome non può essere vuoto")
    @Size(min = 2, max = 50, message = "Il cognome deve essere compreso tra 2 e 50 caratteri")
    private String cognome;

    @OneToMany(mappedBy = "beneficiarioTransazioni", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transazione> transazioniBeneficiario;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AccountCliente beneficiari;


    public Beneficiario(String nome, String cognome, Long contoBeneficiario) {
        this.nome = nome;
        this.cognome = cognome;
        this.contoBeneficiario = contoBeneficiario;
    }

}

