package com.yak.shop.business;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

import com.yak.shop.domain.Herd;
import com.yak.shop.domain.Order;
import com.yak.shop.domain.Shop;
import com.yak.shop.domain.Stock;
import com.yak.shop.domain.Yak;

public class YakShopKeeper {
	private static Herd herd = Shop.getHerd();
	
	public static Herd getHerd() {
		return herd;
	}

	public static Stock getStock(int timeElapsed) {
		float milkL = getMilkInStock( timeElapsed);
		int skins = getSkinsInStock( timeElapsed);
		return new Stock(milkL, skins);
	}

	public static Herd getHerd(int timeElapsed) {
		if(timeElapsed==0){
			return herd;
		}
		Herd herdOnGivenDay = new Herd();
		Set<Yak> newYaks = new HashSet<Yak>();
		try {
			for (Yak yak : herd.getYaks()) {
				Yak newYak = (Yak) yak.clone();
				int ageLastShaved = (int) (yak.getAgeLastShaved() * 100);
				int age = (int) (yak.getAge() * 100);
				for (int D = age, i = 0; D < age + timeElapsed - 1 && D >= 100
						&& D < 1000; D += (8 + (D * 0.01)), i++) {
					if (i > 0) {
						ageLastShaved = D + 1;
						D = ageLastShaved + 1;
					}
				}
				newYak.setAge(Float.valueOf(new DecimalFormat("#.00")
						.format(yak.getAge() + timeElapsed * 0.01f)));
				newYak.setAgeLastShaved(Float.valueOf(new DecimalFormat("#.00")
						.format(ageLastShaved * 0.01f)));
				newYaks.add(newYak);
			}
			herdOnGivenDay.setYaks(newYaks);

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return herdOnGivenDay;
	}

	public static float getMilkInStock(int timeElapsed) {
		float milkL = 0.0f;
		for (Yak yak : herd.getYaks()) {
			float start = yak.getAge() * 100;
			for (float D = start; D < start + timeElapsed; D++)
				milkL += 50 - (D * 0.03f);
		}
		return Float.valueOf(new DecimalFormat("#.000").format(milkL));
	}

	public static int getSkinsInStock(int timeElapsed) {
		int skins = 0;
		for (Yak yak : herd.getYaks()) {
			float age = yak.getAge() * 100;
			for (float D = age; D < age + timeElapsed - 1 && D >= 100
					&& D < 1000; D += (8 + (D * 0.01f))) {
				skins += 1;
			}
		}
		return skins;
	}

	public static Order getOrder(Order customerOrder, int timeElapsed) {

		
		Stock totalStock = getStock(timeElapsed);
		Stock orderedStock = customerOrder.getStock();
//		System.out.println("Stock in hand");
//		System.out.println(totalStock);
//		System.out.println("Customer order");
//		System.out.println(customerOrder);
		Float remainingMilk = totalStock.getMilk() - orderedStock.getMilk();
		Integer remainingSkins = totalStock.getSkins() - orderedStock.getSkins();
		Order fulfilledOrder = new Order();
		fulfilledOrder.setStock(new Stock());
		//Scenario1: Full order can be fulfilled
		if(remainingMilk>=0 && remainingSkins >=0){
			fulfilledOrder = customerOrder;
		}
		//Scenario2: Partial order can be fulfilled
		if(remainingMilk>=0 && remainingSkins < 0){
			fulfilledOrder.getStock().setMilk(customerOrder.getStock().getMilk());
			fulfilledOrder.getStock().setSkins(null);
		}
		if(remainingMilk < 0 && remainingSkins >= 0){
			fulfilledOrder.getStock().setMilk(null);
			fulfilledOrder.getStock().setSkins(customerOrder.getStock().getSkins());
		}
		//Scenario3: Out of stock, order can not be fulfilled
		if(remainingMilk < 0 && remainingSkins < 0){
			fulfilledOrder.getStock().setMilk(null);
			fulfilledOrder.getStock().setSkins(null);
		}

		return fulfilledOrder;
	}

}
