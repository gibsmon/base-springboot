package com.poliymorf.dagaitem.data.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "m_biodata")
public class Biodata extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "name")
  private String name;

  @Column(name = "birth_date")
  private Date birthDate;

  @Column(name = "experience", nullable = true)
  private BigInteger experience;

  @Column(name = "level", nullable = true)
  private int level;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  private Account account;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "biodata", cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<AccountAddress> listAddress;
}
