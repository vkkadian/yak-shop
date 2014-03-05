package com.yak.shop.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.http.HTTPContainerFactory;

public class YakShopRESTServiceTest extends JerseyTest {

    public YakShopRESTServiceTest()throws Exception {

        super(new WebAppDescriptor.Builder("com.yak.shop.service")
        .clientConfig(new DefaultClientConfig())
        .contextPath("/yak-shop")
        .build());
    }

	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new HTTPContainerFactory();
	}
	
	@Test
	public void testGetStockRESTService() throws IOException {
		ClientResponse response = null;
		WebResource service = null;
		
		service = resource().path("stock").path("13");
		response = service.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		assertEquals(200, response.getStatus());
		
	  }

	@Test
	public void testGetHerdRESTService() {
		ClientResponse response = null;
		WebResource service = null;
		
		service = resource().path("herd").path("13");
		response = service.accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		assertEquals(200, response.getStatus());
		response = service.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		assertEquals(200, response.getStatus());
	}

	@Test
	public void testPlaceOrderRESTService1() throws JSONException {
		ClientResponse response = null;
		WebResource service = null;
		
		JSONObject jsOrder = new JSONObject();
		jsOrder.put("milk", 1100);
		jsOrder.put("skins", 3);
		JSONObject customerOrder = new JSONObject();
		customerOrder.put("customer", "Medvedev");
		customerOrder.put("order", jsOrder);
		service = resource().path("order").path("14");
		response = service.accept(MediaType.APPLICATION_JSON).header("Content-Type", MediaType.APPLICATION_JSON).post(ClientResponse.class,customerOrder.toString());
		assertEquals(201, response.getStatus());
		System.out.println(response);
		
	}	
	
	@Test
	public void testPlaceOrderRESTService2() throws JSONException {
		ClientResponse response = null;
		WebResource service = null;
		
		JSONObject jsOrder = new JSONObject();
		jsOrder.put("milk", 1200);
		jsOrder.put("skins", 3);
		JSONObject customerOrder = new JSONObject();
		customerOrder.put("customer", "Medvedev");
		customerOrder.put("order", jsOrder);
		service = resource().path("order").path("14");
		response = service.accept(MediaType.APPLICATION_JSON).header("Content-Type", MediaType.APPLICATION_JSON).post(ClientResponse.class,customerOrder.toString());
		assertEquals(206, response.getStatus());
		System.out.println(response);
		
	}		
	
	@Test
	public void testPlaceOrderRESTService3() throws JSONException {
		ClientResponse response = null;
		WebResource service = null;
		
		JSONObject jsOrder = new JSONObject();
		jsOrder.put("milk", 1200);
		jsOrder.put("skins", 5);
		JSONObject customerOrder = new JSONObject();
		customerOrder.put("customer", "Medvedev");
		customerOrder.put("order", jsOrder);
		service = resource().path("order").path("14");
		response = service.accept(MediaType.APPLICATION_JSON).header("Content-Type", MediaType.APPLICATION_JSON).post(ClientResponse.class,customerOrder.toString());
		assertEquals(404, response.getStatus());
		System.out.println(response);
	}		
		
}
