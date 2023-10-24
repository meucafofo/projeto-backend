package com.tcc.moradiaestudantil.batch;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import com.tcc.moradiaestudantil.enums.PontoEstrategico;
import com.tcc.moradiaestudantil.service.FaculdadeService;
import com.tcc.moradiaestudantil.wsclient.GoogleMapsClient;
import com.tcc.moradiaestudantil.wsclient.dto.LocalizacaoDTO;
import com.tcc.moradiaestudantil.wsclient.enums.BusinessStatus;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component("taskCadastrarFaculdades")
@AllArgsConstructor
@Slf4j
public class TaskCadastrarFaculdades implements Tasklet {

	private static final int RAIO_BRASILIA = 30000;

	private static final double LONGITUDE_BRASILIA = -47.9292D;

	private static final double LATITUDE_BRASILIA = -15.7801D;

	private GoogleMapsClient mapsClient;

	private FaculdadeService faculdadeService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		String next = null;
		var localizacoes = new ArrayList<LocalizacaoDTO>();
		
		do {
			var res = mapsClient.pesquisarLocalizacoes(LATITUDE_BRASILIA, LONGITUDE_BRASILIA, PontoEstrategico.FACULDADES.getKeyword(), RAIO_BRASILIA, next);
			Thread.sleep(2000);
			next = res.getNextPageToken();
			localizacoes.addAll(res.getResults());
		} while(Objects.nonNull(next));
		
		localizacoes.stream().filter(dto -> BusinessStatus.OPERATIONAL.equals(dto.getBusinessStatus()))
				.forEach(dto -> {
					try {
						faculdadeService.cadastrarFaculdade(dto);
					} catch (Exception ex) {
						log.error("Falha ao cadastrar universidade: " + dto.getName(), ex);
					}
				});

		return RepeatStatus.FINISHED;
	}

}
