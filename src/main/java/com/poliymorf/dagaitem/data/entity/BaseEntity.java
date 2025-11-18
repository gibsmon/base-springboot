package com.poliymorf.dagaitem.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  @Column(
      name = "created_date",
      updatable = false
  )
  @CreationTimestamp
  protected Timestamp createdDate;
  @Column(
      name = "created_by",
      length = 50
  )
  protected String createdBy;
  @Column(
      name = "created_by_username",
      length = 50
  )
  protected String createdByUsername;
  @Column(
      name = "updated_date"
  )
  @UpdateTimestamp
  protected Timestamp updatedDate;
  @Column(
      name = "updated_by",
      length = 50
  )
  protected String updatedBy;
  @Column(
      name = "updated_by_username",
      length = 50
  )
  protected String updatedByUsername;
  @Column(
      name = "is_deleted",
      columnDefinition = "boolean default false"
  )
  protected boolean isDeleted = false;


}
