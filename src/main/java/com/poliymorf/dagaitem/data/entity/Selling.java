package com.poliymorf.dagaitem.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "t_selling")
public class Selling extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  @Column(name = "price")
  private BigDecimal price;
  @Column(name = "is_sell")
  private Boolean isSell;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id", referencedColumnName = "id")
  @JsonBackReference
  private Account account;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  @JsonBackReference
  private Product product;


}
