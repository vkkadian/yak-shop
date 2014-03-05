package com.yak.shop.tests;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import com.yak.shop.business.YakShopKeeper;
import com.yak.shop.domain.Order;
import com.yak.shop.domain.Stock;
import com.yak.shop.domain.Yak;

public class Yak3Test extends TestCase {

	@Test
	public void testgetOrder(){
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
		
		//Scenario1: order fulfilled completely
		Order customerOrder = new Order();
		Stock stockExpected = new Stock(1100f,3);
		customerOrder.setStock(stockExpected);
		Order fulfilledOrder = YakShopKeeper.getOrder(customerOrder,14);
		assertEquals(customerOrder,fulfilledOrder);
		
		//Scenario2: order fulfilled partially
		customerOrder = new Order();
		Stock stock = new Stock(1200f,3);
		customerOrder.setStock(stock);
		fulfilledOrder = YakShopKeeper.getOrder(customerOrder,14);
		assertEquals(customerOrder.getStock().getSkins(),fulfilledOrder.getStock().getSkins());	
		assertNull(fulfilledOrder.getStock().getMilk());
		
		//Scenario3: out-of-stock order can not be fulfilled
		customerOrder = new Order();
		stockExpected = new Stock(1200f,5);
		customerOrder.setStock(stockExpected);
		fulfilledOrder = YakShopKeeper.getOrder(customerOrder,14);
		assertNull(fulfilledOrder.getStock().getMilk());		
		assertNull(fulfilledOrder.getStock().getSkins());
	}
	
}
