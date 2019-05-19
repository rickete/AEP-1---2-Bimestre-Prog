import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.UUID;
import java.util.Scanner;

public class Juridica extends Pessoa {
	private ArrayList socios = new ArrayList<>();
	private Double limiteInferiorCapital;
	private Double limiteSuperiorCapital;
	private String cnpj;
	private Double capitalSocial;
	
	public Juridica(String nome, String cnpj, Double capitalSocial, Double limiteInferiorCapital, Double limiteSuperiorCapital) {
		super(nome);
		if(validaCNPJ(cnpj)==true && cnpj != null) {
			this.cnpj=cnpj;
			if(validaCapitalSocial(capitalSocial, limiteInferiorCapital, limiteSuperiorCapital) == true) {
				this.capitalSocial=capitalSocial;
				this.limiteInferiorCapital=limiteInferiorCapital;
				this.limiteSuperiorCapital=limiteSuperiorCapital;
			}
			
		}
		
	}
	
	public Juridica(UUID id, String nome, String cnpj, Double capitalSocial, Double limiteInferiorCapital, Double limiteSuperiorCapital) {
		super(id,nome);
		if(validaCNPJ(cnpj)==true && cnpj != null) {
			this.cnpj=cnpj;
			if(validaCapitalSocial(capitalSocial, limiteInferiorCapital, limiteSuperiorCapital) == true) {
				this.capitalSocial=capitalSocial;
				this.limiteInferiorCapital=limiteInferiorCapital;
				this.limiteSuperiorCapital=limiteSuperiorCapital;
			}
			
		}
	}
		
	
	
	public Double getCapitalSocial() {
		return capitalSocial;
	}

	public String getCnpj() {
		return cnpj;
	}
	
	
	
	
	
	public boolean validaCNPJ(String CNPJ) {
		    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
		        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
		        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
		        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
		        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
		       (CNPJ.length() != 14))
		       return(false);
		 
		    char dig13, dig14;
		    int sm, i, r, num, peso;
		 
		    try {
		      sm = 0;
		      peso = 2;
		      for (i=11; i>=0; i--) {
		        num = (int)(CNPJ.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10) {
		           peso = 2;
		        }
		      }
		 
		      r = sm % 11;
		      if ((r == 0) || (r == 1)) {
		         dig13 = '0';
		      }else {
		    	  dig13 = (char)((11-r) + 48);
		      }
		 
		
		      sm = 0;
		      peso = 2;
		      for (i=12; i>=0; i--) {
		        num = (int)(CNPJ.charAt(i)- 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10) {
		           peso = 2;
		        }
		      }
		 
		      r = sm % 11;
		      if ((r == 0) || (r == 1)) {
		         dig14 = '0';
		      }else {
		    	  dig14 = (char)((11-r) + 48);
		      }
		 
		      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
		         return(true);
		      }else {
		    	  return(false);
		      }
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		  }
		
	public boolean validaCapitalSocial(Double capital, Double limiteInferior, Double limiteSuperior) {
		this.limiteInferiorCapital=limiteInferior;
		this.limiteSuperiorCapital=limiteSuperior;
		if((capital<limiteInferior && capital>limiteSuperior) || capital == null) {
			return false;
		}else {
			return true;
		}
		
	}
	
	
	public String getLimites() {
		return ("Limite inferior do Capital: "+ this.limiteInferiorCapital +"   Limite Superior do Capital"+ this.limiteSuperiorCapital);
	}
	
	
	
	
	
	
	
	public void adicionarSocio(Pessoa socio, Double percentualDeParticipacao) {
		Scanner scanner = new Scanner(System.in);
		Double limiteMenor;
		Double limiteMaior;
		Boolean control = false;
			do {
				System.out.println("Digite o Limite Inferior e Superior");
				limiteMenor = scanner.nextDouble();
				limiteMaior = scanner.nextDouble();
				if(limiteMenor>limiteMaior||limiteMenor < 0 || limiteMaior == null || limiteMenor==null) {
					control = true;
				}
					
			}while(control == false);
		
		
		CotaSociedade sociedade = new CotaSociedade(socio, percentualDeParticipacao, limiteMenor, limiteMaior);
		socios.add(socio);
	}
	
	public void removerSocio(Pessoa socio) {
		if(socios.contains(socio)) {
			socios.remove(socio);
		}
	}
	
	
	
	
	
	
	
	
	private class CotaSociedade {
		private ArrayList listaSocios = new ArrayList<>();
		private Double limiteInferiorPercentual;
		private Double limiteSuperiorPercentual;
		private Pessoa socio;
		private Double percentualDeParticipacao;
		
		public CotaSociedade(Pessoa socio, Double percentual, Double limiteInferiorPercentual, Double limiteSuperiorPercentual) {
			
			if(validaPercentual(percentual, limiteInferiorPercentual, limiteSuperiorPercentual)==true) {
				this.percentualDeParticipacao=percentual;
				this.socio=socio;
				this.limiteInferiorPercentual=limiteInferiorPercentual;
				this.limiteSuperiorPercentual=limiteSuperiorPercentual;
			}
		}
		
		public boolean validaPercentual(Double percentual, Double  limiteInferior, Double limiteSuperior) {
			if((percentual < limiteInferior || percentual > limiteSuperior) || percentual == null) {
				return false;
			}else {
				this.limiteInferiorPercentual=limiteInferior;
				this.limiteSuperiorPercentual=limiteSuperior;
				this.percentualDeParticipacao=percentual;
				return true;
			}
		}
		
		public String getLimitesSociedade() {
			return ("Limite inferior do Capital: "+ this.limiteInferiorPercentual +"   Limite Superior do Capital"+ this.limiteSuperiorPercentual);
		}
	}



	


}
