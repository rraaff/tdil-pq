package com.tdil.thalamus.client.test;

import java.io.IOException;

import net.sf.json.JSON;
import net.sf.json.util.JSONTokener;

import org.apache.commons.io.IOUtils;

import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.json.beans.PersonFields;

public class TestParsePersonFields extends ThalamusTestCase {

	public void te$stParseFile() throws IOException {
		String content = IOUtils.toString(TestParsePersonFields.class.getResourceAsStream("personfields.json"));
		JSONTokener tokener = new JSONTokener(content);
		JSON rawResponseMessage = (JSON)tokener.nextValue();
		System.out.println(rawResponseMessage.toString(2));
		PersonFields personFields = new PersonFields(rawResponseMessage);
		System.out.println(personFields);
	}
	
	public void testParseFileWithCusto() throws IOException {
		String content = IOUtils.toString(TestParsePersonFields.class.getResourceAsStream("personfields-with-custom.json"));
		JSONTokener tokener = new JSONTokener(content);
		JSON rawResponseMessage = (JSON)tokener.nextValue();
		System.out.println(rawResponseMessage.toString(2));
		PersonFields personFields = new PersonFields(rawResponseMessage);
		System.out.println(personFields);
	}
	
	public void t$estParseService() throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		PersonFields personFields = ThalamusClientBeanFacade.getPersonFields();
		System.out.println(personFields);
	}
}
