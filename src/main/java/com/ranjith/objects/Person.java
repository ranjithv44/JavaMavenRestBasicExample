package com.ranjith.objects;

public class Person {
	
	private String firstName;
	private String lastName;
	private String position;
	private String team;
	private boolean isFavourite;
	
	public Person(String firstName, String lastName, String position, String team, boolean isFavourite){
		this.firstName=firstName;
		this.lastName = lastName;
		this.position = position;
		this.team = team;
		this.isFavourite= isFavourite;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public boolean isFavourite() {
		return isFavourite;
	}
	public void setFavourite(boolean isFavourite) {
		this.isFavourite = isFavourite;
	}
	
	@Override
	public String toString(){
		return firstName+","+lastName+"," + position +"," +team +"," +isFavourite;
	}
	

}
