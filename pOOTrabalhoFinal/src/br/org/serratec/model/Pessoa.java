package br.org.serratec.model;

import java.time.LocalDate;

public abstract class Pessoa {
	
	protected String nome, cpf;
	protected LocalDate dataNascimento;

	public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Nome: " + nome + ", CPF: " + cpf + ", Data de nascimento: " + dataNascimento + ", ";
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
}
