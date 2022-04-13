package com.app.pojo;

import javax.persistence.*;

@Entity
@Table
public class Record {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String stockDetails;
	private String productStatus;	
}
