

import java.time.LocalDateTime;
import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="TEST")
public class Test {
      
    @Id
    @SequenceGenerator(name="TEST_seq",sequenceName="TEST_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TEST_seq")  
    @Column(name = "TEST_ID", unique = true, nullable = true)
    private @NonNull Long ID;
    
     @Column(name="DATE")
   private @NonNull LocalDateTime date;



}





