package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import  com.okta.springbootvue.entity.TypeName;
import  com.okta.springbootvue.entity.Sex;
import  com.okta.springbootvue.entity.Question;

@Data
@Entity
@NoArgsConstructor
@Table(name="USERREGISTER")
public class UserRegister {

    @Id
    @SequenceGenerator(name="id_register_seq",sequenceName="id_register_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="id_register_seq")
    @Column(name = "REGISTER_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="DATE")
    private @NonNull Date date;

    
    @NotNull
    @Pattern(regexp = "^[A-Za-zก-๙ ]{4,60}$")
    @Column(name="NAME")
    private @NonNull String name;

    @NotNull
    @Pattern(regexp = "\\d{10}")
    @Column(name="TEL")
    private @NonNull String tel;
    
    @Size(max=30 ,message ="email max30")
    @NotNull
    @Email
    @Column(name="EMAIL")
    private @NonNull String email;

    @Size(max=20 ,message ="answer max20")
    @Size(min=2 ,message ="answer min2")
    @NotNull
    @Column(name="Answer")
    private @NonNull String answer;
    
    @Size(max=30 ,message ="password max30")
    @Size(min=8 ,message ="password min8")
    @NotNull
    @Column(name="Password")
    private @NonNull String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Sex.class)
    @JoinColumn(name = "SEX_ID", insertable = true)
    private Sex sex;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Question.class)
    @JoinColumn(name = "QUESTION_ID", insertable = true)
    private Question question;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = TypeName.class)
    @JoinColumn(name = "TYPENAME_ID", insertable = true)
    private TypeName typeName;

	public void setSex(final Sex sex) {
                this.sex = sex;
        }

        public void setTypeName(final TypeName typename) {
                this.typeName = typename;
        }

        public void setQuestion(final Question question) {
                this.question = question;
        }

        public void setName(final String name) {
                this.name = name;
        }

        public void setEmail(final String email) {
                this.email = email;
        }

        public void setPassword(final String password) {
                this.password = password;
        }

        public void setAnswer(final String answer) {
                this.answer = answer;
        }

        public void setTel(final String tel) {
                this.tel = tel;
        }

        public void date(final Date date) {
        this.date = date;      
	}

	public Long getId() {
	return this.id;
	}



	
	

	
        
	

	


    
}

