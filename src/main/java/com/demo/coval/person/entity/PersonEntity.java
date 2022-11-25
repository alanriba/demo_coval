package com.demo.coval.person.entity;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="Person")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="per_id")
	private Long id;

	@Column(name="per_name", length=100)
	private String name;
	
	@Column(name="per_lastName", length=100)
	private String lastName;
	
	@Column(name="per_status", columnDefinition="tinyint(1) default 1")
	private Boolean status;
}
