package com.jordi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="vehicles")
public class Vehicle {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="model", nullable=false, length=45)
	private String model;
	
	@Column(name="registrationNumber", unique=true, nullable=false, length=45)
	private String registrationNumber;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Type type;
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Brand brand;

	public Vehicle(int id, String model, String registrationNumber, Type type, Brand brand) {
		super();
		this.id = id;
		this.model = model;
		this.registrationNumber = registrationNumber;
		this.type = type;
		this.brand = brand;
	}
	
	public Vehicle() {
		super();
		this.id = 0;
		this.model = "";
		this.registrationNumber = "";
		this.type = new Type();
		this.brand = new Brand();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", model=" + model + ", registrationNumber=" + registrationNumber + ", type="
				+ type + ", brand=" + brand + "]";
	}
}
