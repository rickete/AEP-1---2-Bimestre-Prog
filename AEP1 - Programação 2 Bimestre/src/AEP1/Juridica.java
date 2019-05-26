package AEP1;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Juridica extends Pessoa{
    private Set<CotaSociedade> cotasSociedade = new HashSet<>();
    private Double percentualAtual;
	private CNPJ cnpj;
	private Double capitalSocial;

    
    public Juridica(String nome, CNPJ cnpj, Double capitalSocial) {
        super(nome);
        this.cnpj=cnpj;
        this.capitalSocial=capitalSocial;
		
		
	}
	
	public Juridica(UUID id, String nome, CNPJ cnpj, Double capitalSocial) {
		super(id,nome);
		this.cnpj=cnpj;
        this.capitalSocial=capitalSocial;
	}
	
    
	public void adicionarSocio(Pessoa socio, double percentualDeParticipacao) {
		CotaSociedade novaCota = new CotaSociedade(socio,percentualDeParticipacao);
		
		if (this.percentualAtual + percentualDeParticipacao> 100.00) {
			System.out.println("Não pode ser ultrapassado os 100%");
		}else{
			this.percentualAtual = somarPercentualAtual();
		}
		
		this.cotasSociedade.add(novaCota);
	}
	
	private double somarPercentualAtual() {
		double percentualAtual = 0.00d;
		for (CotaSociedade cotaSociedade : cotasSociedade) {
			percentualAtual += cotaSociedade.percentualDeParticipacao;
		}
		return percentualAtual;
	}
	
	public void removerSocio(Pessoa socioParaRemover) {
		Set<CotaSociedade> aux = new HashSet<>();
		for (CotaSociedade cota : cotasSociedade) {
			if (!cota.socio.equals(socioParaRemover)) {
				aux.add(cota);
			}
		}
		this.cotasSociedade = aux;
	}

    private class CotaSociedade {
		private Pessoa socio;
		private Double percentualDeParticipacao;
		
		public CotaSociedade(Pessoa socio, Double percentual) {
			this.socio=socio;
			this.percentualDeParticipacao=percentual;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CotaSociedade other = (CotaSociedade) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (socio == null) {
				if (other.socio != null)
					return false;
			} else if (!socio.equals(other.socio))
				return false;
			return true;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((socio == null) ? 0 : socio.hashCode());
			return result;
		}
	}

}