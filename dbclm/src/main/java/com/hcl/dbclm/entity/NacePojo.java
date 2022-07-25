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


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NacePojo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7142776809034719356L;
	
	private Long naceOrder;
	
	private String level;
	private String code;
	private String parent;
	private String description;
	private String thisItemIncludes;
	private String thisItemAlsoIincludes;
	private String rulings;
	private String thisItemExcludes;
	private String referenceToISICRev;
	
	

}
