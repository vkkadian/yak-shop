package com.yak.shop.domain;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="herd")
public class Herd {
	private Set<Yak> yaks;
	
	@XmlElement(name="labyak")
	public Set<Yak> getYaks() {
		return this.yaks;
	}
	
	public void setYaks(Set<Yak> yaks){
		this.yaks = yaks;
	}

	@Override
	public String toString() {
		return "Herd [yaks=" + yaks + "]";
	}	
	
}
