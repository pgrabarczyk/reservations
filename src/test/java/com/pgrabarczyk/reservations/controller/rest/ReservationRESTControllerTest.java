package com.pgrabarczyk.reservations.controller.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.pgrabarczyk.reservations.ApplicationConfig;
import com.pgrabarczyk.reservations.repository.ReservationRepository;
import com.pgrabarczyk.reservations.util.TestData;
import com.pgrabarczyk.reservations.util.TestUtil;

@RunWith(MockitoJUnitRunner.class)
public class ReservationRESTControllerTest {

    @InjectMocks
    private ReservationRESTController reservationRESTController;
    @Mock
    private ReservationRepository reservationRepository;

    private final SimpleDateFormat sdf = new SimpleDateFormat(ApplicationConfig.PATTERN_DATETIME);

    private MockMvc mockMvc;

    @Before
    public void setUp() {
	this.mockMvc = MockMvcBuilders.standaloneSetup(reservationRESTController).build();
    }

    @Test
    public void testFindByIdWhenIdFound() throws Exception {
	Mockito.when(reservationRepository.findOne(BigInteger.ONE)).thenReturn(TestData.reservationMarian1);

	mockMvc.perform(get("/reservation/findById?id=1"))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.createDate", is(sdf.format(TestData.reservationMarian1.getCreateDate()))))
		.andExpect(jsonPath("$.expiredDate", is(sdf.format(TestData.reservationMarian1.getExpiredDate()))))
		.andExpect(jsonPath("$.comment", is(TestData.reservationMarian1.getComment())))
		.andExpect(jsonPath("$.status", is(TestData.reservationMarian1.getStatus().name())))
		.andExpect(jsonPath("$.owner", notNullValue()))
		.andExpect(jsonPath("$.owner.id", is(1)))
		.andExpect(jsonPath("$.owner.firstName", is(TestData.userMarian.getFirstName())))
		.andExpect(jsonPath("$.owner.lastName", is(TestData.userMarian.getLastName())))
		.andExpect(jsonPath("$.owner.pesel", is(TestData.userMarian.getPesel())));

	verify(reservationRepository, times(1)).findOne(Matchers.any(BigInteger.class));
	verifyNoMoreInteractions(reservationRepository);
    }

    @Test
    public void testFindByIdWhenIdNotFound() throws Exception {
	Mockito.when(reservationRepository.findOne(BigInteger.ONE)).thenReturn(null);

	mockMvc.perform(get("/reservation/findById?id=1"))
		.andExpect(status().isNotFound())
		.andExpect(status().reason("Reservation with id 1 not found"));

	verify(reservationRepository, times(1)).findOne(Matchers.any(BigInteger.class));
	verifyNoMoreInteractions(reservationRepository);
    }

    @Test
    public void testFindByIdWhenIdParamMissing() throws Exception {
	mockMvc.perform(get("/reservation/findById")).andExpect(status().isBadRequest()).andExpect(
		status().reason("Required BigInteger parameter 'id' is not present"));

	verifyZeroInteractions(reservationRepository);
    }

    @Test
    public void testFindByIdWhenIdIsNull() throws Exception {
	mockMvc.perform(get("/reservation/findById?id=null")).andExpect(status().isBadRequest());

	verifyZeroInteractions(reservationRepository);
    }

}
