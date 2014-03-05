package com.yak.shop.tests;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import com.yak.shop.business.YakShopKeeper;
import com.yak.shop.domain.Herd;
import com.yak.shop.domain.Stock;
import com.yak.shop.domain.Yak;

public class Yak4Test extends TestCase {

	@Test
	public void testGetStock(){
		Yak yak1 = new Yak();
		yak1.setName("Betty-1");
		yak1.setAge(4f);
		yak1.setSex("f");
		yak1.setAgeLastShaved(4f);

		Yak yak2 = new Yak();
		yak2.setName("Betty-2");
		yak2.setAge(8f);
		yak2.setSex("f");
		yak2.setAgeLastShaved(8f);

		Yak yak3 = new Yak();
		yak3.setName("Betty-3");
		yak3.setAge(9.5f);
		yak3.setSex("f");
		yak3.setAgeLastShaved(9.5f);

		Set<Yak> yaks = new HashSet<Yak>();
		yaks.add(yak1);
		yaks.add(yak2);
		yaks.add(yak3);
		Stock stockExpected = new Stock(1104.48f,3);
		Stock stockActual = YakShopKeeper.getStock(13);
		assertEquals(stockExpected,stockActual);
	}
	
	@Test
	public void testGetHerd(){
		Yak yak1 = new Yak();
		yak1.setName("Betty-1");
		yak1.setAge(4f);
		yak1.setSex("f");
		yak1.setAgeLastShaved(4f);

		Yak yak2 = new Yak();
		yak2.setName("Betty-2");
		yak2.setAge(8f);
		yak2.setSex("f");
		yak2.setAgeLastShaved(8f);

		Yak yak3 = new Yak();
		yak3.setName("Betty-3");
		yak3.setAge(9.5f);
		yak3.setSex("f");
		yak3.setAgeLastShaved(9.5f);

		Set<Yak> yaks = new HashSet<Yak>();
		yaks.add(yak1);
		yaks.add(yak2);
		yaks.add(yak3);
		for(int timeElapsed=13;timeElapsed<=28;timeElapsed++){
			Herd herdOnGivenDay = YakShopKeeper.getHerd(timeElapsed);
			System.out.println("Herd at " + timeElapsed + " days:");
			for(Yak yak: herdOnGivenDay.getYaks())
				System.out.println(yak);
			System.out.println("=============================================");
		}
	}
	
}
