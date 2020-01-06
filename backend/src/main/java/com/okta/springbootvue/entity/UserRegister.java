package com.okta.springbootvue.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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


    @Column(name="NAME")
    private @NonNull String name;

    @Column(name="TEL")
    private @NonNull String tel;
    
    @Column(name="EMAIL")
    private @NonNull String email;
    
    @Column(name="Answer")
    private @NonNull String answer;
    
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

	public void setSex(Sex sex) {
        this.sex = sex;
	}

	public void setTypeName(TypeName typename) {
        this.typeName = typename;
	}

	public void setQuestion(Question question) {
        this.question = question;
	}

	public void setName(String name) {
        this.name = name;
	}

	public void setEmail(String email) {
        this.email = email;
	}

	public void setPassword(String password) {
        this.password = password;
	}
 
        public void setAnswer(String answer) {
        this.answer = answer;
        }

        public void setTel(String tel) {
        this.tel = tel;
        }

	public void date(Date date) {
        this.date = date;      
	}
        
	

	


    
}

