package br.com.magnolevi.servicex.servico.resources;

import br.com.magnolevi.servicex.servico.domain.Servico;
import br.com.magnolevi.servicex.servico.domain.ServicoDTO;
import br.com.magnolevi.servicex.servico.services.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/servicos")
@Tag(name = "Serviços", description = "Funcionalidade das operações de Serviços")
public class ServicoResource {

    @Autowired
    private ServicoService servicoService;

    @Operation(summary = "Cadasrar Serviço", description = "O recurso permite cadastrar um serviço atrelando-o à uma categoria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastro com sucesso",
                    content = @Content(
                            examples = @ExampleObject(value = "{\n" +
                                    "  \"nome\": \"Logotipo\",\n" +
                                    "  \"valor\": 500, \n" +
                                    "  \"categoria\": {\n" +
                                    "  \"idCategoria\": 3\n" +
                                    "  }\n" +
                                    "}")
                    )),
            @ApiResponse(responseCode = "401", description = "Token ausente, inválido ou expirado"),
            @ApiResponse(responseCode = "403", description = "Rota exclusiva para administradores (administrador = true)")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody ServicoDTO servicoDTO) {
        Servico servico = servicoService.fromDTOService(servicoDTO);
        servico = servicoService.criarServico(servico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(servico.getIdServico()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Listar serviços", description = "Lista todos os seviços.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        List<Servico> servicos = servicoService.listarServico();
        return new ResponseEntity<>(servicos, HttpStatus.OK);
    }

    @Operation(summary = "Buscar serviço", description = "Busca um serviço pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{idServico}")
    public ResponseEntity<Servico> buscarServico(@PathVariable Integer idServico) {
        Servico servico = servicoService.buscarServico(idServico);
        return ResponseEntity.ok().body(servico);
    }

    @Operation(summary = "Atualizar serviço", description = "Atualiza um serviço pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{idServico}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Integer idServico, @RequestBody ServicoDTO servicoDTO) {
        Servico servico = servicoService.fromDTOService(servicoDTO);
        servico.setIdServico(idServico);
        servicoService.atualizarServico(servico);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deletar serviço", description = "Deleta um serviço pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Não encontrado")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idServico}")
    public ResponseEntity<Void> deletarServico(@PathVariable Integer idServico) {
        servicoService.deletarServico(idServico);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
