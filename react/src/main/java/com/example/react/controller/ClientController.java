package com.example.react.controller;

import com.example.react.model.Client;
import com.example.react.repository.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("getAll")
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @GetMapping("getClient/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping("add")
    public ResponseEntity addClient(@RequestBody Client client) {
        Client saveClient = clientRepository.save(client);
        return ResponseEntity.ok(saveClient);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity editClient(@PathVariable Long id,
                                     @RequestBody Client client) {
        Client currentClient = clientRepository.findById(id).orElseThrow();
        currentClient.setName(client.getName());
        currentClient.setEmail(client.getEmail());
        currentClient = clientRepository.save(client);
        return ResponseEntity.ok(currentClient);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id) {
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
