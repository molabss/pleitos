package constran.pleitos.model;

import java.math.BigDecimal;

public class Pleito {

	//------------------------------------------------

	private String _cc;
	private String _tipo;
	private String _status;
	private String _condicao;
	
	private String diretor;
	
	private String _cc_desc;
	private String _cc_participa;
	
	public String get_cc() {
		return _cc;
	}
	public void set_cc(String _cc) {
		this._cc = _cc;
	}
	public String get_tipo() {
		return _tipo;
	}
	public void set_tipo(String _tipo) {
		this._tipo = _tipo;
	}
	public String get_status() {
		return _status;
	}
	public void set_status(String _status) {
		this._status = _status;
	}
	public String get_condicao() {
		return _condicao;
	}
	public void set_condicao(String _condicao) {
		this._condicao = _condicao;
	}
	//------------------------------------------------
	
	
	
	
	
	
	private Integer id;
	
	private CentrosDeCusto cc;
	private Tipo tipo;
	private Condicao condicao;
	private Status status;
	
	private String numDocumento;
	private String descricao;
	private String dtEntrega;
	private BigDecimal colocadoEmP0;
	private BigDecimal reajustadoAteAData;
	private BigDecimal esperadoEmP0_A;
	private BigDecimal esperadoEmP0_B;
	private BigDecimal custoAgregadoNoMinimoEsperadoP0;
	private BigDecimal livreDeCustosNoMinimoEsperadoP0;
	private BigDecimal minimoEsperadoReajustado;
	private BigDecimal custoAgregadoNoMinimoEsperadoReajustado;
	private BigDecimal livreDeCustosNoMinimoEsperadoReajustado;
	
	private String dtPrevFinalizaAprovacaoCancelada;
	private BigDecimal jaRecebidoAteDataP0;
	private BigDecimal saldoAreceberP0;
	private BigDecimal jaRecebidoAteDTreajus;
	private BigDecimal saldoAreceberReajustado;
	private String dtPrevRecebimento;
	private String inclusoAPS;
	private String obs;
	private String nuAdidito_parteDaVerba;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CentrosDeCusto getCc() {
		return cc;
	}
	public void setCc(CentrosDeCusto cc) {
		this.cc = cc;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Condicao getCondicao() {
		return condicao;
	}
	public void setCondicao(Condicao condicao) {
		this.condicao = condicao;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDtEntrega() {
		return dtEntrega;
	}
	public void setDtEntrega(String dtEntrega) {
		this.dtEntrega = dtEntrega;
	}
	public BigDecimal getColocadoEmP0() {
		return colocadoEmP0;
	}
	public void setColocadoEmP0(BigDecimal colocadoEmP0) {
		this.colocadoEmP0 = colocadoEmP0;
	}
	public BigDecimal getReajustadoAteAData() {
		return reajustadoAteAData;
	}
	public void setReajustadoAteAData(BigDecimal reajustadoAteAData) {
		this.reajustadoAteAData = reajustadoAteAData;
	}
	public BigDecimal getEsperadoEmP0_A() {
		return esperadoEmP0_A;
	}
	public void setEsperadoEmP0_A(BigDecimal esperadoEmP0_A) {
		this.esperadoEmP0_A = esperadoEmP0_A;
	}
	public BigDecimal getEsperadoEmP0_B() {
		return esperadoEmP0_B;
	}
	public void setEsperadoEmP0_B(BigDecimal esperadoEmP0_B) {
		this.esperadoEmP0_B = esperadoEmP0_B;
	}
	public BigDecimal getCustoAgregadoNoMinimoEsperadoP0() {
		return custoAgregadoNoMinimoEsperadoP0;
	}
	public void setCustoAgregadoNoMinimoEsperadoP0(
			BigDecimal custoAgregadoNoMinimoEsperadoP0) {
		this.custoAgregadoNoMinimoEsperadoP0 = custoAgregadoNoMinimoEsperadoP0;
	}
	public BigDecimal getLivreDeCustosNoMinimoEsperadoP0() {
		return livreDeCustosNoMinimoEsperadoP0;
	}
	public void setLivreDeCustosNoMinimoEsperadoP0(
			BigDecimal livreDeCustosNoMinimoEsperadoP0) {
		this.livreDeCustosNoMinimoEsperadoP0 = livreDeCustosNoMinimoEsperadoP0;
	}
	public BigDecimal getMinimoEsperadoReajustado() {
		return minimoEsperadoReajustado;
	}
	public void setMinimoEsperadoReajustado(BigDecimal minimoEsperadoReajustado) {
		this.minimoEsperadoReajustado = minimoEsperadoReajustado;
	}
	public BigDecimal getCustoAgregadoNoMinimoEsperadoReajustado() {
		return custoAgregadoNoMinimoEsperadoReajustado;
	}
	public void setCustoAgregadoNoMinimoEsperadoReajustado(
			BigDecimal custoAgregadoNoMinimoEsperadoReajustado) {
		this.custoAgregadoNoMinimoEsperadoReajustado = custoAgregadoNoMinimoEsperadoReajustado;
	}
	public BigDecimal getLivreDeCustosNoMinimoEsperadoReajustado() {
		return livreDeCustosNoMinimoEsperadoReajustado;
	}
	public void setLivreDeCustosNoMinimoEsperadoReajustado(
			BigDecimal livreDeCustosNoMinimoEsperadoReajustado) {
		this.livreDeCustosNoMinimoEsperadoReajustado = livreDeCustosNoMinimoEsperadoReajustado;
	}
	public String getDtPrevFinalizaAprovacaoCancelada() {
		return dtPrevFinalizaAprovacaoCancelada;
	}
	public void setDtPrevFinalizaAprovacaoCancelada(
			String dtPrevFinalizaAprovacaoCancelada) {
		this.dtPrevFinalizaAprovacaoCancelada = dtPrevFinalizaAprovacaoCancelada;
	}

	public BigDecimal getSaldoAreceberP0() {
		return saldoAreceberP0;
	}
	public void setSaldoAreceberP0(BigDecimal saldoAreceberP0) {
		this.saldoAreceberP0 = saldoAreceberP0;
	}
	public BigDecimal getJaRecebidoAteDTreajus() {
		return jaRecebidoAteDTreajus;
	}
	public void setJaRecebidoAteDTreajus(BigDecimal jaRecebidoAteDTreajus) {
		this.jaRecebidoAteDTreajus = jaRecebidoAteDTreajus;
	}
	public BigDecimal getSaldoAreceberReajustado() {
		return saldoAreceberReajustado;
	}
	public void setSaldoAreceberReajustado(BigDecimal saldoAreceberReajustado) {
		this.saldoAreceberReajustado = saldoAreceberReajustado;
	}
	public String getDtPrevRecebimento() {
		return dtPrevRecebimento;
	}
	public void setDtPrevRecebimento(String dtPrevRecebimento) {
		this.dtPrevRecebimento = dtPrevRecebimento;
	}
	public String getInclusoAPS() {
		return inclusoAPS;
	}
	public void setInclusoAPS(String inclusoAPS) {
		this.inclusoAPS = inclusoAPS;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getNuAdidito_parteDaVerba() {
		return nuAdidito_parteDaVerba;
	}
	public void setNuAdidito_parteDaVerba(String nuAdidito_parteDaVerba) {
		this.nuAdidito_parteDaVerba = nuAdidito_parteDaVerba;
	}
	public BigDecimal getJaRecebidoAteDataP0() {
		return jaRecebidoAteDataP0;
	}
	public void setJaRecebidoAteDataP0(BigDecimal jaRecebidoAteDataP0) {
		this.jaRecebidoAteDataP0 = jaRecebidoAteDataP0;
	}
	public String getDiretor() {
		return diretor;
	}
	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}
	public String get_cc_desc() {
		return _cc_desc;
	}
	public void set_cc_desc(String _cc_desc) {
		this._cc_desc = _cc_desc;
	}
	public String get_cc_participa() {
		return _cc_participa;
	}
	public void set_cc_participa(String _cc_participa) {
		this._cc_participa = _cc_participa;
	}
}
