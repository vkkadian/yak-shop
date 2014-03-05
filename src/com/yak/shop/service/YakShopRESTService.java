package com.yak.shop.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.yak.shop.business.YakShopKeeper;
import com.yak.shop.domain.Herd;
import com.yak.shop.domain.Order;
import com.yak.shop.domain.Stock;
import com.yak.shop.domain.Yak;

@Path("/")
public class YakShopRESTService {

	@GET
	@Path("/herd/{timeElapsed}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getHerdByDays_Text(
			@PathParam("timeElapsed") Integer timeElapsed) {
		StringBuffer sb = new StringBuffer("");
		Float milk = YakShopKeeper.getMilkInStock(timeElapsed);
		int skins = YakShopKeeper.getSkinsInStock(timeElapsed);
		sb.append("Output for T = " + timeElapsed + ":\n");
		sb.append("\n");
		sb.append("\tIn Stock:\n");
		sb.append("\t\t" + milk + " litres of milk\n");
		sb.append("\t\t" + skins + " skins of wool\n");
		sb.append("\tHerd:\n");
		for (Yak yak : YakShopKeeper.getHerd().getYaks()) {
			sb.append("\t\t" + yak.getName() + " " + yak.getAge()
					+ " years old\n");
		}
		// System.out.println(sb);
		return sb.toString();
	}

	@GET
	@Path("/herd/{timeElapsed}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHerdByDays_JSON(
			@PathParam("timeElapsed") Integer timeElapsed) throws JSONException {
		Herd herdOnGivenDay = YakShopKeeper.getHerd(timeElapsed);

		JSONArray yaks = new JSONArray();
		for (Yak yak : herdOnGivenDay.getYaks()) {
			JSONObject jsYak = new JSONObject();
			jsYak.put("name", yak.getName());
			jsYak.put("age", yak.getAge());
			jsYak.put("age-last-shaved", yak.getAgeLastShaved());
			yaks.put(jsYak);
		}

		JSONObject jsHerd = new JSONObject().put("herd", yaks);

		return Response.status(200).entity(jsHerd).build();
	}

	@GET
	@Path("/stock/{timeElapsed}")
	@Produces(MediaType.APPLICATION_JSON)
	public Stock getStockByDays_JSON(
			@PathParam("timeElapsed") Integer timeElapsed) {
		return YakShopKeeper.getStock(timeElapsed);
	}

	@POST
	@Path("/order/{timeElapsed}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response placeOrderByDays_JSON(final Order order,
			@PathParam("timeElapsed") Integer timeElapsed) throws JSONException {
		Order fulfilledOrder = YakShopKeeper.getOrder(order, timeElapsed);

		// Set response code 201 for completely fulfilled order
		if (fulfilledOrder.getStock().getMilk() != null
				&& fulfilledOrder.getStock().getSkins() != null) {
			return Response.status(Status.CREATED)
					.entity(fulfilledOrder.getStock()).build();
		}
		// Set response code 206 for partially fulfilled order
		if (fulfilledOrder.getStock().getMilk() == null
				^ fulfilledOrder.getStock().getSkins() == null) {

			if (fulfilledOrder.getStock().getMilk() == null)
				return Response
						.status(206)
						.entity(new JSONObject().put("skins", fulfilledOrder
								.getStock().getSkins())).build();
			else
				return Response
						.status(206)
						.entity(new JSONObject().put("milk", fulfilledOrder
								.getStock().getMilk())).build();

		}
		// Set response code 404 for out of stock
		if (fulfilledOrder.getStock().getMilk() == null
				&& fulfilledOrder.getStock().getSkins() == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.status(Status.BAD_REQUEST).build();
	}
}
