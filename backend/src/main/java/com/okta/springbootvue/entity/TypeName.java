package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.stream.Stream;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.CascadeType;
@Data
@Entity
@NoArgsConstructor
@Table(name="TYPENAME")
public class TypeName {
	@Id
	@SequenceGenerator(name="typename_seq",sequenceName="typename_seq")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="typename_seq")
	@Column(name="TYPENAME_ID",unique = true, nullable = true)
	private @NonNull Long id;
	@NotNull
	private  String type_name;
	
	//@OneToMany(fetch = FetchType.EAGER)
    //private Collection<UserRegister> userregister;

	public void setName(String name) {
		this.type_name = name;
	}
    
	
	//public void save(TypeName typename) {
//}
	//public Stream findAll() {
	//	return null;
//}
	
}
