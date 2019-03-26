package com.builder;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import com.processors.*;
import com.jackson.classes.*;

public class MainRouteBuilder extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		DataFormat jaxb = new JaxbDataFormat("com.jaxb.classes");
		DataFormat jackson = new JacksonDataFormat(User.class);
		
		from("file:files/incoming/XML?noop=true")
		.unmarshal(jaxb)
		.process(new OrderProcessor())
		.wireTap("direct:printRoute")
		.marshal(jaxb)
		.to("file:files/outgoing/XML?fileExist=append");
		
		from("file:files/incoming/JSON?noop=true")
		.unmarshal(jackson)
		.process(new UserProcessor())
		.wireTap("direct:printRoute")
		.marshal(jackson)
		.choice()
			.when(simple("${headers.gender} == 'Male'"))
				.toD("file:files/outgoing/JSON/Male?fileName=${headers.fileName}.json&fileExist=append")
			.otherwise()
				.toD("file:files/outgoing/JSON/Female?fileName=${headers.fileName}.json&fileExist=append")
		.end();
		
		from("direct:printRoute")
		.routeId("mockRoute")
		.log("${body}")
		.setBody(constant("Success"))
		.to("mock:endWireTap");
	}

}
