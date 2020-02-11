package com.graham.settings;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.graham.AbstractTest;
import com.graham.controllers.StaffController;

@SpringBootTest(classes=StaffController.class)
public class StaffSignupTest extends AbstractTest {
	
	private static final String HOST = "http://localhost:8080";
	private static final String URI = "/settings";
	private Client client = ClientBuilder.newClient();
	
	@Before
	private void セットアップ() {
		// request header
	}

	@Test
	/** 必須パラメータのパターンチェック */
	public void 必須パラメータ() throws Exception {
		// 正常系
		String staffId = "admin";
		String staffName = "admin";
		String staffTypeId = "1";
		Entity<Form> staff = Entity.entity(new Form().param("staffId", staffId).param("staffName", staffName),
			    MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		WebTarget target = client.target(HOST).path(URI).queryParam("staff_type_id", staffTypeId);
		String response = target.request().post(staff, String.class);
		System.out.println(response);
	}
}
