package com.spring.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import org.apache.commons.io.IOUtils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.spring.pojo.Employee;
import com.spring.pojo.Employee;
import com.spring.pojo.StudentRequest;
import com.spring.pojo.StudentResponse;


@Controller
/*@RestController*/
@RequestMapping(value = "/testing")
public class LoginController {

	public static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired(required=true)
	private Employee obj;
	
	@Value("#{'WEB-INF/images/kholi2.jpg'}")
	private Resource resource;

	@RequestMapping("/")
	public String home() {
		return "employee";
	}

	@RequestMapping(value = "/add/{a}/{b}", method = RequestMethod.GET)
	public ModelAndView login(@PathVariable(name = "a", required = false) int t1,
			@PathVariable(name = "b", required = false) int t2) {

		logger.info("I am in add controller");
		System.out.println("add controller");

		/*
		 * int i = Integer.parseInt(request.getParameter("a")); int j =
		 * Integer.parseInt(request.getParameter("b"));
		 */

		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		mv.addObject("result", t1 + t2);
		return mv;
	}

	@GetMapping(value = "/test")
	ResponseEntity<String> test(@RequestParam("a") int a) {

		HttpHeaders header = new HttpHeaders();
		header.add("encoding", "utf-8");

		return ResponseEntity.badRequest().header("Encoding", "UTF-8").body("Hello");

	}

	@RequestMapping(value = { "/student", "/teacher" }, method = RequestMethod.POST)
	public StudentResponse student(@RequestBody StudentRequest student) {

		
		String str="Sachin";
		
		System.out.println("************************");
		System.out.println(student.getName());
		StudentResponse stu = new StudentResponse();
		stu.setHobbie("gyming");
		stu.setPercentage("90%");
		return stu;

	}

	
	@GetMapping(value="/employee")
	public void setEmployee(){
		
		
		System.out.println("**********************Inside setEmployee function");
		System.out.println(obj.getId());
		System.out.println(obj.getName());
	}
	
	/*@GetMapping(value="/kholi")
	public void imageDownload(HttpServletResponse response)throws IOException{
		 
		InputStream in = resource.getInputStream();
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    IOUtils.copy(in, response.getOutputStream());
		
		
	}
	*/
	
	
	/*@GetMapping(value="/kholi1")
	public ResponseEntity<byte[]> imageDownload1() throws IOException{
		
		
		
		InputStream in = resource.getInputStream();
		try{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG_VALUE);
		headers.setContentType(MediaType.IMAGE_JPEG);
		
		return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers,
				HttpStatus.OK);
		}catch(Exception ex){
			
		}
		
		
		return null;
		
	}*/
	
	
	@GetMapping(value="/kholi2",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] imageDownloand2() throws IOException{
		
		System.out.println("I am inside imageDownload2 function");
		
		InputStream in = resource.getInputStream();
		
		return IOUtils.toByteArray(in);
	}
	
	@ModelAttribute
	public Employee addAttribute(){
		
		System.out.println("I am inside model attribute function");
		//Employee obj = new Employee();
		obj.setId("36992");
		obj.setName("Sachin");
		return obj;
	}

	
	
	@PostMapping(value="/addEmployee")
	public void addEmployee(@ModelAttribute("employee") Employee employee){
		
		System.out.println(employee.getName());
		System.out.println(employee.getId());
		
		
	}

	public int calculateLenght(char[] str){
		
		return str.length;
		
	}
	

}
