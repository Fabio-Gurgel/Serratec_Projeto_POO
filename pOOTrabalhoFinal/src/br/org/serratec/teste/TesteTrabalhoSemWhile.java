package br.org.serratec.teste;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.org.serratec.enums.RelacaoDependente;
import br.org.serratec.model.Dependente;
import br.org.serratec.model.Funcionario;

public class TesteTrabalhoSemWhile {
	public static void main(String[] args) {

		List <Funcionario> funcionarios = new ArrayList <>();
		List <Dependente> dependentes = new ArrayList<>(); 
		  
		try {
		File arquivo = new File("C:/Users/ASUS/Serratec/pOOTrabalhoFinal/pOOFuncionarios.csv");
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy,MM,dd");

		Scanner sc = new Scanner(arquivo);
		List <String> leituras = new ArrayList<>();
		
	    while (sc.hasNextLine()) {
	    	String linha = sc.nextLine();
	    	leituras.add(linha);
		}
	    sc.close();
	     
	    for (String leitura : leituras) {
		String[] lendo = leitura.split(";");
		if(!leitura.isEmpty()) {
			String nome = lendo[0];
			String cpf = lendo[1];
			LocalDate data = LocalDate.parse(lendo[2], formatoData);
			if(lendo[3].equals(RelacaoDependente.FILHO.getRelacao()) || lendo[3].equals(RelacaoDependente.SOBRINHO.getRelacao()) || lendo[3].equals(RelacaoDependente.OUTROS.getRelacao())) {
				String parentesco = lendo[3];
				dependentes.add(new Dependente(nome, cpf, data, parentesco));
			} else if (Double.parseDouble(lendo[3]) >= 0 || Double.parseDouble(lendo[3]) < 0){
				double salario = Double.parseDouble(lendo[3]);
				dependentes = new ArrayList<>(); 
				funcionarios.add(new Funcionario(nome, cpf, data, salario, dependentes));
			}
		}
		
		}
	     System.out.println(funcionarios);
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Funcionario funcionario : funcionarios) {
			funcionario.calcularSalarioLiquido();
		}
		
		System.out.println(funcionarios);
	}

}