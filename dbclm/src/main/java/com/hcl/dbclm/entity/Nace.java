package com.hcl.dbclm.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="nace")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Nace implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7142776809034719356L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long naceOrder;
	@NotNull(message=" Level must be not null")
	@NotEmpty
	//@Size( min=2 ,message="Level must be not less than 2 chatacter")
	@Size(max=10, message ="Level must not greter than 20 chatacter")
	private String level;
	private String naceCode;
	private String parent;
	private String description;
	private String thisItemIncludes;
	private String thisItemAlsoIincludes;
	private String rulings;
	private String thisItemExcludes;
	private String referenceToISICRev;
}
