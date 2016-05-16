/**
 * 
 */
package com.chenzhida.vo;

import java.io.Serializable;

/**
 * @author Administrator
 *
 */
public class BBSUser implements Serializable {


	private int id;
	private String name;
	private String sex;
	private int age;
	private int phone_number;
	private int score;
	private int exa_number;
	private int parents_Pnumber;
	private String High_School;
	private String art_science;
	private String parents_name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getExa_number() {
		return exa_number;
	}
	public void setExa_number(int exa_number) {
		this.exa_number = exa_number;
	}
	public int getParents_Pnumber() {
		return parents_Pnumber;
	}
	public void setParents_Pnumber(int parents_Pnumber) {
		this.parents_Pnumber = parents_Pnumber;
	}
	public String getHigh_School() {
		return High_School;
	}
	public void setHigh_School(String high_School) {
		High_School = high_School;
	}
	public String getArt_science() {
		return art_science;
	}
	public void setArt_science(String art_science) {
		this.art_science = art_science;
	}
	public String getParents_name() {
		return parents_name;
	}
	public void setParents_name(String parents_name) {
		this.parents_name = parents_name;
	}
	
	
	
	
	
	
}
