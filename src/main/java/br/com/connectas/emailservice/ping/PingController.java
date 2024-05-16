package br.com.connectas.emailservice.ping;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.connectas.emailservice.filter.UserResolver;

@RestController
@RequestMapping("/api/v1/ping")
public class PingController {
	
	@GetMapping
	public ResponseEntity<String> ping(){
		return ResponseEntity.ok("I'm alive at ".concat(LocalDate.now().toString()));
	}
	
	@GetMapping("/auth")
	public ResponseEntity<String> pingAuthenticated(){
		return ResponseEntity.ok("I'm alive at ".concat(LocalDate.now().toString()).concat(" and my id is ").concat(UserResolver.getUserId().toString()));
	}

}
