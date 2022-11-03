package com.example;

enum Country {
	POLAND, USA, FRANCE
}

class MyFactory {
	Person build(Country country) {
		switch (country) {
		case POLAND:
			return new PersonBuilder()
					.setCity("Lodz")
					.setAge(18)
					.setHeight(200)
					.setWidth(80)
					.setName("Marcin")
					.setSurname("Grzejszczak")
					.createPerson();
		case USA:
			return new PersonBuilder()
					.setCity("New York")
					.setAge(28)
					.setHeight(170)
					.setWidth(90)
					.setName("John")
					.setSurname("Smith")
					.createPerson();
		case FRANCE:
			return new PersonBuilder()
					.setCity("Paris")
					.setAge(24)
					.setHeight(160)
					.setWidth(70)
					.setName("Pierre")
					.setSurname("Pierre")
					.createPerson();
		}
		return null;
	}
}

class Person {
	final String name, surname, address;
	final int age, width, height;

	Person(String name, String surname, String address, int age, int width, int height) {
		this.name = name;
		this.surname = surname;
		this.address = address;
		this.age = age;
		this.width = width;
		this.height = height;
	}
}

class PersonBuilder {
	private String name;
	private String surname;
	private String city;
	private int age;
	private int width;
	private int height;

	public PersonBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public PersonBuilder setSurname(String surname) {
		this.surname = surname;
		return this;
	}

	public PersonBuilder setCity(String city) {
		this.city = city;
		return this;
	}

	public PersonBuilder setAge(int age) {
		this.age = age;
		return this;
	}

	public PersonBuilder setWidth(int width) {
		this.width = width;
		return this;
	}

	public PersonBuilder setHeight(int height) {
		this.height = height;
		return this;
	}

	public Person createPerson() {
		return new Person(this.name, this.surname, this.city, this.age, this.width, this.height);
	}
}
