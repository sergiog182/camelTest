package com.tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFiles extends CamelSpringTestSupport{

	@Produce(uri="direct:printRoute")
	private ProducerTemplate template;
	
	@EndpointInject(uri="mock:endWireTap")
	private MockEndpoint mockWireTap;
	
	@EndpointInject(uri="mock:secondMock")
	private MockEndpoint secondMock;
	
	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}
	
	@Before
	public void before() {
		deleteDirectory("files/outgoign");
	}
	
	@Test
	public void testXmlFiles() throws InterruptedException {
		NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();
		builder.matches(5, TimeUnit.SECONDS);
		
		String path = "files/outgoing/XML";
		File file = new File(path);
		assertTrue(file.isDirectory());
		assertEquals(8,file.listFiles().length);
	}
	
	@Test
	public void testJsonMaleFiles() throws InterruptedException {
		NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();
		builder.matches(5, TimeUnit.SECONDS);
		
		String path = "files/outgoing/JSON/Male";
		File file = new File(path);
		assertTrue(file.isDirectory());
		assertEquals(2,file.listFiles().length);
	}
	
	@Test
	public void testJsonFemaleFiles() throws InterruptedException {
		NotifyBuilder builder = new NotifyBuilder(context).whenDone(1).create();
		builder.matches(5, TimeUnit.SECONDS);
		
		String path = "files/outgoing/JSON/Female";
		File file = new File(path);
		assertTrue(file.isDirectory());
		assertEquals(4,file.listFiles().length);
	}
	
	@Test
	public void mockTest() throws InterruptedException {
		template.sendBody("Prueba de entrada mock");
		String expected = "Success";
		mockWireTap.expectedBodyReceived().constant(expected);
		mockWireTap.assertIsSatisfied();
	}
	
	@Test
	public void testRoute() throws Exception {
		AdviceWithRouteBuilder awrb = new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				interceptSendToEndpoint("mock:endWireTap")
				.skipSendToOriginalEndpoint()
				.to("mock:secondMock");
			}
		};
		
		context.getRouteDefinition("mockRoute").adviceWith(context, awrb);
		context.start();
		
		// Esta prueba debe ser ejecutada primero, para garantizar que no falle
		// por que el mock tenga algun otro mensaje al momento de ejecutarse 
		NotifyBuilder builder = new NotifyBuilder(context).whenDone(0).create();
		builder.matches(0, TimeUnit.SECONDS);
		
		template.sendBody("Prueba de entrada mock");
		String expected = "Success";
		secondMock.expectedBodyReceived().constant(expected);
		secondMock.assertIsSatisfied();
	}
	
	@After
	public void after() throws Exception {
		context.stop();
	}

}
