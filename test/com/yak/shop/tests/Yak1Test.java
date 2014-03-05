package com.yak.shop.tests;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import com.yak.shop.business.YakShopKeeper;
import com.yak.shop.domain.Yak;

public class Yak1Test extends TestCase {

	@Test
	public void testInputHerdXMLLoad() {
		Set<Yak> yaks = YakShopKeeper.getHerd().getYaks();
		assertNotNull(yaks);
		// System.out.println("From inputHerd xml file: ");
		// for (Yak yak : yaks) {
		// System.out.println(yak);
		// }
	}

	@Test
	public void testGetMilkInStock() {
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

		YakShopKeeper.getHerd().setYaks(yaks);

		float milkL = YakShopKeeper.getMilkInStock(13);
		assertEquals(milkL, 1104.48f);
	}

	@Test
	public void testGetSkinsInStock() {
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

		YakShopKeeper.getHerd().setYaks(yaks);

		int skins = YakShopKeeper.getSkinsInStock(28);
		assertEquals(skins, 7);
	}

}
