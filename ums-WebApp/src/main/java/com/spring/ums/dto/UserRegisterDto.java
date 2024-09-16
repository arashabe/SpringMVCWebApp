package com.spring.ums.dto;


public class UserRegisterDto {
private String firstName;
private String lastName;
private String skills;
private String courseOfStudy;
private String email;
private String password;
public UserRegisterDto(){
}
public String getFirstName(){
return firstName;
}
public void setFirstName(String firstName){
this.firstName = firstName;
}
public String getLastName(){
return lastName;
}
public void setLastName(String lastName){
this.lastName = lastName;
}
public String getEmail(){
return email;
}
public void setEmail(String email){
this.email = email;
}
public String getPassword(){
return password;
}
public void setPassword(String password){
this.password = password;
  }
public String getSkills() {
	return skills;
}
public void setSkills(String skills) {
	this.skills = skills;
}
public String getCourseOfStudy() {
	return courseOfStudy;
}
public void setCourseOfStudy(String courseOfStudy) {
	this.courseOfStudy = courseOfStudy;
}

}