package com.tcc.moradiaestudantil.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcc.moradiaestudantil.service.ArquivoService;
import com.tcc.moradiaestudantil.service.UsuarioLogadoService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/api/arquivos")
@AllArgsConstructor
public class ArquivosController {
	private ArquivoService arquivoService;

	@GetMapping(value = "/documentos/{id}")
	public ResponseEntity<byte[]> getDocumentos(@PathVariable Long id) {
		var documento = arquivoService.recuperarDocumento(id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documento.getNome() + "\"")
				.header(HttpHeaders.CONTENT_TYPE, documento.getContentType())
				.body(documento.getArquivo());
	}
	
	@GetMapping(value = "/comprovantes/{id}")
	public ResponseEntity<byte[]> getComprovantes(@PathVariable Long id) {
		var comprovante = arquivoService.recuperarComprovante(id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + comprovante.getNome() + "\"")
				.header(HttpHeaders.CONTENT_TYPE, comprovante.getContentType())
				.body(comprovante.getArquivo());
	}
	
	@GetMapping(value = "/fotos/{id}")
	public ResponseEntity<byte[]> getFotos(@PathVariable Long id) {
		var foto = arquivoService.recuperarFoto(id);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + foto.getNome() + "\"")
				.header(HttpHeaders.CONTENT_TYPE, foto.getContentType())
				.body(foto.getArquivo());
	}

	
	@DeleteMapping(value = "/fotos/{id}")
	public ResponseEntity<Void> deleteFotos(@PathVariable Long id) {
		arquivoService.excluirFoto(id, UsuarioLogadoService.autheticated());
		return ResponseEntity.noContent().build();
	}
}
