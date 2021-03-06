package espedsgskat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import no.systema.main.model.SystemaWebUser;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test-espedsgskat-maintenance-service.xml", 
		"classpath:/test-espedsgskat-service.xml","classpath:/test-espedsgskat-servlet.xml",
		"classpath:/WEB-INF/views.xml"})
@TestPropertySource(locations="classpath:test-application.properties")

@WebAppConfiguration
public class MvcControllerTest {

	{
		System.setProperty("catalina.home", "/Library/Tomcat/apache-tomcat-9.0.8");
	}
	
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
    MockHttpSession session;

	
	private MockMvc mockMvc;
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void configTest() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("skatExportHeaderArchiveController"));
	    
	}
	
	
	@Test
	public void test() throws Exception{
		 session.putValue("user", new SystemaWebUser());
		 
		 this.mockMvc.perform(get("/skat_export_renderArchive.do")
			//.cookie(cookies..mockMvc.) //besvärligt, besvärligt	 
		 	.param("fp", "http:/whatever")	 )
		 	.andExpect(status().is3xxRedirection()) //we must add cookies to test
		 	.andDo(print());
		 
	}

	
}
