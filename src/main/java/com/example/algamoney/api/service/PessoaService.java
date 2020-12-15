package com.example.algamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa atualizarPessoa(Long codigo, Pessoa pessoa) {
		Pessoa pessoaBanco = buscar(codigo);
		BeanUtils.copyProperties(pessoa, pessoaBanco, "codigo");
		
		return pessoaRepository.save(pessoaBanco);
	}

	public void atualizarPessoaAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaBanco = buscar(codigo);
		pessoaBanco.setAtivo(ativo);
		
		pessoaRepository.save(pessoaBanco);
	}
	
	public Pessoa buscar(Long codigo) {
		Optional<Pessoa> optional = pessoaRepository.findById(codigo);
		if(optional.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Pessoa pessoaBanco = optional.get();
		return pessoaBanco;
	}
}
