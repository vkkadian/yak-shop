package com.yak.shop.domain;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Shop {
	private static Herd herd = null;
	static {

		String INPUTHERD_XML = "inputHerd.xml";
		// create JAXB context and instantiate marshaller
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Herd.class);
			// get variables from our XML file, created before
			Unmarshaller um = context.createUnmarshaller();
			herd = ((Herd) um.unmarshal(Herd.class.getClassLoader()
					.getResourceAsStream(INPUTHERD_XML)));

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static Herd getHerd() {
		return herd;
	}

}
