package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ZONE")
public class Zone {

        @Id
        @SequenceGenerator(name = "zone_seq", sequenceName = "zone_seq")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zone_seq")
        @Column(name = "ZONE_ID", unique = true, nullable = true)
        private @NonNull Long id;

        @NotNull
        @Pattern(regexp = "[A-Z]", message = "must be 1 character")
        @Column(name = "ZONE")
        private String zone;

        @NotNull
        @Min(value = 100, message = "must greater than or equal 100")
        @Max(value = 8000, message = "must less than or equal 8000")
        @Column(name = "PRICE")
        private int price;

        public void setName(String name) {
                zone = name;
        }

        public void setPrice(int a) {
                price = a;
        }

}