package AEP1;

import java.util.UUID;

public abstract class Pessoa {
	private UUID id;
	private String nome;

	
	public Pessoa(UUID id, String nome) {
		this.id=id;
		this.nome=nome;
	}
	
	
	public Pessoa(String nome){
		this.id.randomUUID();
		this.nome=nome;
	}
	
	

	public UUID getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
	
	
	
}
