package com.example.webframeworkhw1.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="authorities")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Authority {

    @Id
    @Column(name="username")
    String username;

    @Column(name="authority")
    String authority;

}
