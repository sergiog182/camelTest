package com.jackson.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	@JsonProperty(required = true)
    protected String id;
	@JsonProperty(required = true)
    protected String names;
	@JsonProperty(required = true)
    protected String country;
	@JsonProperty(required = true)
    protected String lastname;
	@JsonProperty(required = true)
    protected String age;	
	@JsonProperty(required = true)	
    protected String gender;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the names
	 */
	public String getNames() {
		return names;
	}
	/**
	 * @param names the names to set
	 */
	public void setNames(String names) {
		this.names = names;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", names=" + names + ", country=" + country + ", lastname=" + lastname + ", age="
				+ age + ", gender=" + gender + "]";
	}
	
	
}
