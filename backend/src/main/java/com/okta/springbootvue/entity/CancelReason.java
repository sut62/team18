
package com.okta.springbootvue.entity;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="CANCELREASON")
public class CancelReason {
    @Id    
    @SequenceGenerator(name="CANCELREASON_SEQ",sequenceName="CANCELREASON_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CANCELREASON_SEQ")  
    @Column(name="CANCELREASON_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String reason;

	public void setReason(String name) {
        this.reason = name;
	}
}