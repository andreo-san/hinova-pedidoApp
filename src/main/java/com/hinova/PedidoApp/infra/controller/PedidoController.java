package com.hinova.PedidoApp.infra.controller;

import com.hinova.PedidoApp.application.PedidoServiceImpl;
import com.hinova.PedidoApp.domain.Pedido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class PedidoController {

    private final PedidoServiceImpl service;

    public PedidoController(PedidoServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getOrders() {
        List<Pedido> pedidos = service.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @PostMapping
    public ResponseEntity<Pedido> createOrder(@RequestBody Pedido pedido) {
        Pedido created = service.createOrder(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(created); // 201 Created
    }

}
