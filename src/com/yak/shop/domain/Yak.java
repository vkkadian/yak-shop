package com.yak.shop.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;

public class Yak implements Serializable,Cloneable {

	private static final long serialVersionUID = 1L;
	private String name;
	private Float age;
	private String sex;	
	private Float ageLastShaved=age;
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlAttribute(name="age")
	public Float getAge() {
		return age;
	}

	public void setAge(Float age) {
		this.age = age;
	}
	
	@XmlAttribute(name="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@XmlAttribute(name="age-last-shaved")
	public Float getAgeLastShaved() {
		return ageLastShaved;
	}

	public void setAgeLastShaved(Float ageLastShaved) {
		this.ageLastShaved = ageLastShaved;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Yak other = (Yak) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "Yak [name=" + name + ", age=" + age + ", sex=" + sex
				+ ", age-last-shaved=" + ageLastShaved + "]";
	}


}
