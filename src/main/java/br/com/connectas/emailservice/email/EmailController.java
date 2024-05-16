package br.com.connectas.emailservice.email;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

	@Autowired
	private IEmailSchedulerService service;
	
	@PostMapping
	public ResponseEntity<String> send(@RequestBody Email email){
		final Long id = this.service.send(email);
		
		return Objects.nonNull(id) ? ResponseEntity.ok("O email está na fila para o envio, e o código é o: "+ id) :
			ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Boolean> isSent(@PathVariable("id")Long id){
		final Boolean sent = this.service.checkStatus(id);
		
		return ResponseEntity.ok(sent);
	}
	
	
}
