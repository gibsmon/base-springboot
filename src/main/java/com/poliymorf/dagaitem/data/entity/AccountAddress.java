package com.poliymorf.dagaitem.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "m_account_address")
public class AccountAddress extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(name = "address")
  private String address;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "biodata_id", referencedColumnName = "id")
  @JsonBackReference
  private Biodata biodata;

  @Column(name = "main_address")
  private Boolean mainAddress;
}
