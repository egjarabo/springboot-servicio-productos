package com.formacionbdi.springboot.app.productos.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.service.IProductoService;

//@ExtendWith(MockitoExtension.class)
@WebMvcTest(ProductoController.class)
@RunWith(JUnitPlatform.class)
public class ProductoControllerTest {
	
//	@InjectMocks
//	ProductoController controller;
	
//	@Mock
	@MockBean
	IProductoService productoService;
	
	
	@Autowired
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() {
		System.out.println("Arrancando ... ");
//		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
		
	@Test
	public void testListar() throws Exception {
		Producto p = new Producto();
		p.setId(25L);
		List<Producto> productos = new ArrayList<Producto>(Arrays.asList(p));
		given(productoService.findAll()).willReturn(productos);
		
		mockMvc.perform(get("/listar").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("[0].id", is(Integer.parseInt(p.getId().toString()))));
	}
	
	@AfterEach
	void tearDown(){
		reset(productoService);
	}
}
