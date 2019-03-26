package com.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.jackson.classes.*;

public class UserProcessor implements Processor {

	@Override
	public void process(Exchange ex) throws Exception {
		User user = ex.getIn().getBody(User.class);
		ex.getIn().setHeader("gender", user.getGender());
		ex.getIn().setHeader("fileName", user.getLastname().replace(" ", "_") + "_" + user.getNames().replace(" ", "_"));
	}

}
