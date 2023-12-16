package com.vivafattura.entity;

import com.vivafattura.validation.AdultAge;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Il nome non può essere vuoto")
    @Size(min = 2, max = 50, message = "Il nome deve essere compreso tra 2 e 50 caratteri")
    private String nome;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Il cognome non può essere vuoto")
    @Size(min = 2, max = 50, message = "Il cognome deve essere compreso tra 2 e 50 caratteri")
    private String cognome;

    @Column(name = "codice_fiscale", length = 16, nullable = false)
    @Size(min = 16, max = 16, message = "Il codice fiscale deve essere di 16 caratteri")
    private String codiceFiscale;

    @AdultAge(message = "Devi avere almeno 18 anni")
    @Column(name = "data_nascita", length = 16, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDiNascita;

    @Column(nullable = false, length = 50)
    private String indirizzo;

    @Column(nullable = false, length = 5)
    @Size(min = 5, max = 5, message = "Il cap deve essere formato da 5 cifre")
    private String cap;

    @Column(nullable = false, unique = true, length = 50)
    @Email(message = "L' email deve essere valida")
    private String email;

    @Column(name = "numero_telefono", nullable = false, length = 12)
    @Size(min = 10, max = 10)
    @Pattern(regexp = "\\d{10}", message = "Il numero di telefono deve contenere 10 cifre")
    private String numeroDiTelefono;

    @OneToMany(mappedBy = "accountsCliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountCliente> accounts;

    @Column(nullable = false, unique = true, length = 50)
    @NotBlank(message = "L' username non può essere vuoto")
    @Size(min = 2, max = 50, message = "L' username deve essere compreso tra 2 e 50 caratteri")
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    public Cliente(String nome, String cognome, String codiceFiscale, LocalDate dataDiNascita, String indirizzo, String cap, String email, String numeroDiTelefono, String username, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.email = email;
        this.numeroDiTelefono = numeroDiTelefono;
        this.username = username;
        this.password = password;
    }

}
