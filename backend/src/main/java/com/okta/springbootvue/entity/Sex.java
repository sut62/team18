package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import java.util.stream.Stream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@NoArgsConstructor
@Table(name="SEX")
public class Sex {
    @Id
    @SequenceGenerator(name="sex_seq",sequenceName="sex_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="sex_seq")
    @Column(name="SEX_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @NotNull
    private  String sex;

    //@OneToMany(fetch = FetchType.EAGER)
    
    //private Collection<UserRegister> userregister;

    public void setName(String name) {
		this.sex = name;
    }
    
}