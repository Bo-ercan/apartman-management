package com.example.ApartmanProject;

import com.example.dto.LoginDTO;
import com.example.model.User;
import com.example.model.Flat;
import com.example.repository.UserRepo;
import com.example.repository.FlatRepository;
import com.example.response.LogInResponse;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApartmanProjectApplicationTests {

	@MockBean
	private FlatRepository flatRepository;

	@MockBean
	private UserRepo userRepository;

	@MockBean
	private UserService userService;

	//daireler get aall testi
	@Test
	public void getFlats(){
		when(flatRepository.findAll()).thenReturn(Stream
				.of(new Flat ("asdasd","2+1","Giriş",1,2,"Klima","sad",54,45,"asdasd","https://source.unsplash.com/collection/44204348/200x200"))
				.collect(Collectors.toList()));
		assertEquals(1,flatRepository.findAll().size());
	}

	//findbyid testi
	@Test
	public void getFlatById(){
		long id = 28;
		when(flatRepository.findById(id)).thenReturn(Optional.of(new Flat("asdasd","2+1","Giriş",1,2,"Klima","sad",54,45,"asdasd","https://source.unsplash.com/collection/44204348/200x200")));

		//yukarıdaki yöntemin aksine burada collect yerine Optional kullanıyoruz. findAll liste döndürür. Optional değerin olup olmadığını kontrol eder.
		Optional<Flat> flatOptional = flatRepository.findById(id);
		//assertTrue metodu ile ispresent'tan true değer dönerse test başarılı olacak
		assertTrue(flatOptional.isPresent()); // Optional içinde değer var mı kontrolü
	}

	@Test
	public void createFlat(){
		Flat flat= new Flat("asdasd","2+1","Giriş",1,2,"Klima","sad",54,45,"asdasd","https://source.unsplash.com/collection/44204348/200x200");
		when(flatRepository.save(flat)).thenReturn(flat);
		assertEquals(flat, flatRepository.save(flat));
	}

	@Test
	public void deleteFlatById(){
		Flat flat= new Flat("asdasd","2+1","Giriş",1,2,"Klima","sad",54,45,"asdasd","https://source.unsplash.com/collection/44204348/200x200");
		flatRepository.delete(flat);
		//verify mockito'dan gelen bir methdd
		verify(flatRepository,times(1)).delete(flat);
	}


	@Test
	public void loginUserSuccess() {
		// Arrange
		LoginDTO loginDTO = new LoginDTO("asd@gmail.com", "asd");

		when(userService.loginUser(loginDTO)).thenReturn(new LogInResponse("Login Success", true));

		// Test
		assertEquals(new LogInResponse("Login Success", true).toString(), userService.loginUser(loginDTO).toString());
	}

	@Test
	public void loginUserFail() {
		// Arrange
		LoginDTO loginDTO = new LoginDTO("asd@gmail.com", "wrong");

		when(userService.loginUser(loginDTO)).thenReturn(new LogInResponse("password does not Match", true));

		// Test
		assertEquals(new LogInResponse("password does not Match", true).toString(), userService.loginUser(loginDTO).toString());
	}

	@Test
	public void createUser(){
		User user=new User(12,"ercan","ercan@gmail.com","ercansifre");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user,userRepository.save(user));
	}

}
