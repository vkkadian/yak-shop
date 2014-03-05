package com.yak.shop.domain;

import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Stock {
	private Float milk;
	private Integer skins;

	public Stock() {
		super();
	}

	public Stock(Float milkL, Integer skins) {
		this.milk = milkL;
		this.skins = skins;

	}

	public Float getMilk() {
		return this.milk;
	}

	public void setMilk(Float milkL) {
		this.milk = milkL;
	}

	public Integer getSkins() {
		return this.skins;
	}

	public void setSkins(Integer skins) {
		this.skins = skins;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(milk);
		result = prime * result + skins;
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
		Stock other = (Stock) obj;
		
		if (!milk.equals(other.milk))
			return false;
		if (!skins.equals(other.skins))
			return false;
		return true;
	}

	@Override
	public String toString() {
		Float m = (milk != null) ? Float.valueOf(new DecimalFormat("#.000")
				.format(milk)) : 0f;
		Integer s = (skins != null) ? skins : 0;
		return "In Stock:\n\t" + m + " litres of milk\n" + "\t" + s
				+ " skins of wool\n";
	}

}
