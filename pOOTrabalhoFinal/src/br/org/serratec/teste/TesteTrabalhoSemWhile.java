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
			
			System.out.println("---------------Arquivo original:----------------\n");
			
		    while (sc.hasNextLine()) {
		    	String linha = sc.nextLine();
		    	leituras.add(linha);
		    	System.out.println(linha);
			}
		    sc.close();
		    Integer i = 0;
		     
		    for (String leitura : leituras) {
			    i++;
				String[] var = leitura.split(";");
				if(!leitura.isEmpty()) {
					String nome = var[0];
					String cpf = var[1];
					LocalDate data = LocalDate.parse(var[2], formatoData);
					if(var[3].toUpperCase().equals(RelacaoDependente.FILHO.getRelacao()) || var[3].toUpperCase().equals(RelacaoDependente.SOBRINHO.getRelacao()) || var[3].toUpperCase().equals(RelacaoDependente.OUTROS.getRelacao())) {
						String parentesco = var[3].toUpperCase();
						dependentes.add(new Dependente(nome, cpf, data, parentesco));
					} else if (Double.parseDouble(var[3]) >= 0 || Double.parseDouble(var[3]) < 0){
						double salario = Double.parseDouble(var[3]);
						dependentes = new ArrayList<>(); 
						funcionarios.add(new Funcionario(nome, cpf, data, salario, dependentes));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\n---------------Pro CSV:----------------\n");
		
		int i = 0;
		for (Funcionario funcionario : funcionarios) {
			i++;
			funcionario.calcularSalarioLiquido();
			funcionario.textoCSV();
			if( i < funcionarios.size()) {
				System.out.println("");
			}
		}
		System.out.println("\n--------Pro usuario:-----------------\n");
		
		for (Funcionario funcionario : funcionarios) {
			System.out.println("Funcionario:\n" + funcionario);
			if(funcionario.getDependentes().size() > 0) {
				System.out.println("Dependente(s): ");
			}
			for (Dependente dependente : funcionario.getDependentes()) {
				System.out.print(dependente + "\n");
			}
			System.out.println("*************************");
		}
	}

}