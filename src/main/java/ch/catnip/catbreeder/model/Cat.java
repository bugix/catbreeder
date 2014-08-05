package ch.catnip.catbreeder.model;

import java.time.LocalDate;

public class Cat {
	
	private String name;
	
	private Breeder breeder;
	
	private Breed breed;
	
	private LocalDate birthDay;
	
	private byte[] picture;
	
	public Cat() {
	}

	public Cat(String name, Breeder breeder, Breed breed, LocalDate birthDay, byte[] picture) {
		this.name = name;
		this.breeder = breeder;
		this.breed = breed;
		this.birthDay = birthDay;
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Breeder getBreeder() {
		return breeder;
	}

	public void setBreeder(Breeder breeder) {
		this.breeder = breeder;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

}
