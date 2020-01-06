package com.okta.springbootvue.entity;

import lombok.*;
import javax.persistence.*;

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

        private @NonNull String zone;

        private @NonNull int price;

        public void setName(String name) {
                zone = name;
        }

        public void setPrice(int a) {
                price = a;
        }

}