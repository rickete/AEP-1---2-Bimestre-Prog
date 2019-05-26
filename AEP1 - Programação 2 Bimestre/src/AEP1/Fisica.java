package AEP1;

import java.util.UUID;

public class Fisica extends Pessoa{
	private CPF cpf;
	private String rg;
	
	public Fisica (String nome,String rg, CPF cpf) {
		super(nome);
		this.cpf=cpf;
		this.rg=rg;
		
	}
	

	public Fisica (UUID id, String nome, String rg, CPF cpf) {
		super(id,nome);
		this.cpf=cpf;
		this.rg=rg;
		
	}
	
		
	public String getRg() {
		return rg;
	}
}