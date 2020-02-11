package com.graham.settings;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.graham.AbstractTest;
import com.graham.controllers.StaffController;

@SpringBootTest(classes=StaffController.class)
public class StaffListGetControllerTest extends AbstractTest {
	
	private static final String HOST = "http://localhost:8080";
	private static final String URI = "/settings";
	private Client client = ClientBuilder.newClient();
	
	@Override
    @Before
    public void setUp() {
       super.setUp();
    }
	
	@Test
	/** 権限確認 */
	public void 権限テスト() throws Exception {
		// 正常系
		String staffTypeId = "1";
		WebTarget target = client.target(HOST).path(URI).queryParam("staff_type_id", staffTypeId);
		String response = target.request().get(String.class);
		System.out.println(response);
	}
}
