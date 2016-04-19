package constran.pleitos.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import constran.pleitos.model.Condicao;
import constran.pleitos.model.Pleito;
import constran.pleitos.model.Status;
import constran.pleitos.model.Tipo;
import constran.pleitos.util.dao.AbstractDAO;

public class PleitoDAO extends AbstractDAO {

	public PleitoDAO(Connection conn) 
	{
		super(conn);
	}

	
	
	public List<Tipo> listarTipos() throws SQLException 
	{
		List<Tipo> listaTipos = new ArrayList<Tipo>();
		Tipo t = null;
		
		rs = conn.prepareStatement("select * from tipos").executeQuery();
		while(rs.next())
		{
			t = new Tipo();
			t.setId(rs.getInt("id_tipo"));
			t.setDescricao(rs.getString("ds_tipo"));
			listaTipos.add(t);
		}
		liberarRecursosBD();
		return listaTipos;
	}
	
	
	
	public List<Status> listarStatus() throws SQLException 
	{
		List<Status> listaStatus = new ArrayList<Status>();
		Status s = null;
		
		rs = conn.prepareStatement("select * from status").executeQuery();
		while(rs.next())
		{
			s = new Status();
			s.setId(rs.getInt("id_status"));
			s.setDescricao(rs.getString("descricao"));
			listaStatus.add(s);
		}
		liberarRecursosBD();		
		return listaStatus;
	}
	
	
	
	public List<Condicao> listarCondicao() throws SQLException 
	{
		List<Condicao> listaCondicao = new ArrayList<Condicao>();
		Condicao c = null;
		
		rs = conn.prepareStatement("select * from condicoes").executeQuery();
		while(rs.next())
		{
			c = new Condicao();
			c.setId(rs.getInt("id_condicao"));
			c.setDescricao(rs.getString("descricao"));
			listaCondicao.add(c);
		}
		liberarRecursosBD();		
		return listaCondicao;
	}
	
	
	public void salvarPleito(Pleito p) throws SQLException 
	{
		StringBuilder insert = new StringBuilder();
		insert.append("insert into pleitos (fk_id_cc,fk_id_tipo,fk_id_status,fk_id_condicao,numDocumento,descricao,dtEntrega, ");
		insert.append("colcadoEmP0, reajustadoAteAData, esperadoEmP0_A, esperadoEmP0_B, custoAgregadoNoMinimoEsperadoP0, ");
		insert.append("livreDeCustosNoMinimoEsperadoP0, minimoEsperadoReajustado, custoAgregadoNoMinimoEsperadoReajustado, ");
		insert.append("livreDeCustosNoMinimoEsperadoReajustado, dtPrevFinalizaAprovacaoCancelada, jaRecebidoAteDataP0, saldoAreceberP0, ");
		insert.append("jaRecebidoAteDTreajus, saldoAreceberReajustado, dtPrevRecebimento, inclusoAPS, obs, nuAdidito_parteDaVerba) ");
		insert.append("values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		
		prepStmt = conn.prepareStatement(insert.toString());
		
		prepStmt.setString(1, p.getCc().getId());
		prepStmt.setInt(2, p.getTipo().getId());
		prepStmt.setInt(3, p.getStatus().getId());
		prepStmt.setInt(4, p.getCondicao().getId());
		prepStmt.setString(5, p.getNumDocumento());
		prepStmt.setString(6, p.getDescricao());
		prepStmt.setString(7, p.getDtEntrega());
		prepStmt.setBigDecimal(8, p.getColocadoEmP0());
		prepStmt.setBigDecimal(9, p.getReajustadoAteAData());
		prepStmt.setBigDecimal(10, p.getEsperadoEmP0_A());
		prepStmt.setBigDecimal(11, p.getEsperadoEmP0_B());
		prepStmt.setBigDecimal(12, p.getCustoAgregadoNoMinimoEsperadoP0());
		prepStmt.setBigDecimal(13, p.getLivreDeCustosNoMinimoEsperadoP0());
		prepStmt.setBigDecimal(14, p.getMinimoEsperadoReajustado());
		prepStmt.setBigDecimal(15, p.getCustoAgregadoNoMinimoEsperadoReajustado());
		prepStmt.setBigDecimal(16, p.getLivreDeCustosNoMinimoEsperadoReajustado());
		prepStmt.setString(17, p.getDtPrevFinalizaAprovacaoCancelada());
		prepStmt.setBigDecimal(18,p.getJaRecebidoAteDataP0());
		prepStmt.setBigDecimal(19, p.getSaldoAreceberP0());
		prepStmt.setBigDecimal(20, p.getJaRecebidoAteDTreajus());
		prepStmt.setBigDecimal(21, p.getSaldoAreceberReajustado());
		prepStmt.setString(22, p.getDtPrevRecebimento());
		prepStmt.setString(23, p.getInclusoAPS());
		prepStmt.setString(24, p.getObs());
		prepStmt.setString(25, p.getNuAdidito_parteDaVerba());
		
		prepStmt.executeUpdate();

		liberarRecursosBD();
	}
	
	
	public int atualizarPleito(Pleito p) throws SQLException
	{
		StringBuilder update = new StringBuilder();
		update.append("update pleitos set fk_id_cc =?,fk_id_tipo=?,fk_id_status=?,fk_id_condicao=?,numDocumento=?,descricao=?,dtEntrega=?, ");
		update.append("colcadoEmP0=?, reajustadoAteAData=?, esperadoEmP0_A=?, esperadoEmP0_B=?, custoAgregadoNoMinimoEsperadoP0=?, ");
		update.append("livreDeCustosNoMinimoEsperadoP0=?, minimoEsperadoReajustado=?, custoAgregadoNoMinimoEsperadoReajustado=?, ");
		update.append("livreDeCustosNoMinimoEsperadoReajustado=?, dtPrevFinalizaAprovacaoCancelada=?, jaRecebidoAteDataP0=?, saldoAreceberP0=?, ");
		update.append("jaRecebidoAteDTreajus=?, saldoAreceberReajustado=?, dtPrevRecebimento=?, inclusoAPS=?, obs=?, nuAdidito_parteDaVerba=? ");
		update.append("where id_pleito = ?");
		
		prepStmt = conn.prepareStatement(update.toString());
		
		prepStmt.setString(1, p.getCc().getId());
		prepStmt.setInt(2, p.getTipo().getId());
		prepStmt.setInt(3, p.getStatus().getId());
		prepStmt.setInt(4, p.getCondicao().getId());
		prepStmt.setString(5, p.getNumDocumento());
		prepStmt.setString(6, p.getDescricao());
		prepStmt.setString(7, p.getDtEntrega());
		prepStmt.setBigDecimal(8, p.getColocadoEmP0());
		prepStmt.setBigDecimal(9, p.getReajustadoAteAData());
		prepStmt.setBigDecimal(10, p.getEsperadoEmP0_A());
		prepStmt.setBigDecimal(11, p.getEsperadoEmP0_B());
		prepStmt.setBigDecimal(12, p.getCustoAgregadoNoMinimoEsperadoP0());
		prepStmt.setBigDecimal(13, p.getLivreDeCustosNoMinimoEsperadoP0());
		prepStmt.setBigDecimal(14, p.getMinimoEsperadoReajustado());
		prepStmt.setBigDecimal(15, p.getCustoAgregadoNoMinimoEsperadoReajustado());
		prepStmt.setBigDecimal(16, p.getLivreDeCustosNoMinimoEsperadoReajustado());
		prepStmt.setString(17, p.getDtPrevFinalizaAprovacaoCancelada());
		prepStmt.setBigDecimal(18,p.getJaRecebidoAteDataP0());
		prepStmt.setBigDecimal(19, p.getSaldoAreceberP0());
		prepStmt.setBigDecimal(20, p.getJaRecebidoAteDTreajus());
		prepStmt.setBigDecimal(21, p.getSaldoAreceberReajustado());
		prepStmt.setString(22, p.getDtPrevRecebimento());
		prepStmt.setString(23, p.getInclusoAPS());
		prepStmt.setString(24, p.getObs());
		prepStmt.setString(25, p.getNuAdidito_parteDaVerba());
		prepStmt.setInt(26, p.getId());
		
		int reg = prepStmt.executeUpdate();
		liberarRecursosBD();		
		return reg;
	}
	
	
	public List<Pleito> listarPleitos(List<String> listaCC) throws SQLException, ParseException
	{
		Pleito p = null;
		List<Pleito> listaPleitos = new ArrayList<Pleito>();
	    StringBuilder select = new StringBuilder();
    
	    select.append("SELECT id_pleito");
		    select.append(",cc.id_cc as cc");
		    select.append(",cc.descricao as cc_desc ");
		    select.append(",cc.porcentagemParticipa as cc_participa ");
		    select.append(",us.nome as diretor");
		    select.append(",t.ds_tipo as tipo");
		    select.append(",s.descricao as statuss");
		    select.append(",c.descricao as condicao");
		    select.append(",numDocumento");
		    select.append(",p.descricao as pleitoDescricao");
		    select.append(",dtEntrega");
		    select.append(",colcadoEmP0");
		    select.append(",reajustadoAteAData");
		    select.append(",esperadoEmP0_A");
		    select.append(",esperadoEmP0_B");
		    select.append(",custoAgregadoNoMinimoEsperadoP0");
		    select.append(",livreDeCustosNoMinimoEsperadoP0");
		    select.append(",minimoEsperadoReajustado");
		    select.append(",custoAgregadoNoMinimoEsperadoReajustado");
		    select.append(",livreDeCustosNoMinimoEsperadoReajustado");
		    select.append(",dtPrevFinalizaAprovacaoCancelada");
		    select.append(",jaRecebidoAteDataP0");
		    select.append(",saldoAreceberP0");
		    select.append(",jaRecebidoAteDTreajus");
		    select.append(",saldoAreceberReajustado");
		    select.append(",dtPrevRecebimento");
		    select.append(",inclusoAPS");
		    select.append(",obs");
		    select.append(",nuAdidito_parteDaVerba");
		    select.append(",excluido");
		    select.append(",excluidoPor");
		    select.append(",excluidoEm ");
	    select.append("FROM [PLEITOS] p ");
	    select.append("inner join tipos t ");
	    	select.append("on p.fk_id_tipo = t.id_tipo ");
	    select.append("inner join status s ");
	    	select.append("on p.fk_id_status = s.id_status ");
	    select.append("inner join condicoes c ");
	    	select.append("on p.fk_id_condicao = c.id_condicao ");
	    	
	    select.append("inner join  centros_de_custos cc ");
	    	select.append("on p.fk_id_cc = cc.id_cc ");
	    	
		 select.append("left join  usuarios us ");
	    	select.append("on cc.fk_id_login_diretor = us.login ");
	    
	    select.append("where p.fk_id_cc in (");

	    for(String cc : listaCC)
	    {
	    	select.append(cc);
	    	if(listaCC.indexOf(cc) < (listaCC.size() -1)) select.append(",");
	    } 
	    select.append(") ");
	    select.append("and p.excluido = ?");
	    
	    prepStmt = conn.prepareStatement(select.toString());
	    prepStmt.setString(1, "N");
	    
	    rs = prepStmt.executeQuery();
	    
	    while(rs.next())
	    {
	    	p = new Pleito();
	    	
	    	p.setId(rs.getInt("id_pleito"));
	    	
	    	p.set_cc(rs.getString("cc"));
	    	p.set_tipo(rs.getString("tipo"));
	    	p.set_status(rs.getString("statuss"));
	    	p.set_condicao(rs.getString("condicao"));
	    	
			p.setNumDocumento(rs.getString("numDocumento"));
			p.setDescricao(rs.getString("pleitoDescricao"));
			
			p.setDiretor(rs.getString("diretor"));
			
			p.set_cc_desc(rs.getString("cc_desc"));
			p.set_cc_participa(rs.getString("cc_participa"));
			
			//-------------------------------------------------------------------------------------------- 
			Date initDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("dtEntrega"));
			SimpleDateFormat formatterDate = new SimpleDateFormat("dd/MM/yyyy");
			String parsedDate = formatterDate.format(initDate);
			  
			p.setDtEntrega(parsedDate);
			//--------------------------------------------------------------------------------------------
			
			p.setColocadoEmP0(rs.getBigDecimal("colcadoEmP0"));
			 
			p.setReajustadoAteAData(rs.getBigDecimal("reajustadoAteAData"));
			 
			p.setEsperadoEmP0_A(rs.getBigDecimal("esperadoEmP0_A"));
			 
			p.setEsperadoEmP0_B(rs.getBigDecimal("esperadoEmP0_B"));
			 
			p.setCustoAgregadoNoMinimoEsperadoP0(rs.getBigDecimal("custoAgregadoNoMinimoEsperadoP0"));
			 
			p.setLivreDeCustosNoMinimoEsperadoP0(rs.getBigDecimal("livreDeCustosNoMinimoEsperadoP0"));
			 
			p.setMinimoEsperadoReajustado(rs.getBigDecimal("minimoEsperadoReajustado"));
			 
			p.setCustoAgregadoNoMinimoEsperadoReajustado(rs.getBigDecimal("custoAgregadoNoMinimoEsperadoReajustado"));
			 
			p.setLivreDeCustosNoMinimoEsperadoReajustado(rs.getBigDecimal("livreDeCustosNoMinimoEsperadoReajustado"));
			
			//--------------------------------------------------------------------------------------------
			initDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("dtPrevFinalizaAprovacaoCancelada"));
			formatterDate = new SimpleDateFormat("dd/MM/yyyy");
			parsedDate = formatterDate.format(initDate);

			p.setDtPrevFinalizaAprovacaoCancelada(parsedDate);
			//--------------------------------------------------------------------------------------------
			 
			 p.setJaRecebidoAteDataP0(rs.getBigDecimal("jaRecebidoAteDataP0"));
			 
			 p.setSaldoAreceberP0(rs.getBigDecimal("saldoAreceberP0"));
			 
			 p.setJaRecebidoAteDTreajus(rs.getBigDecimal("jaRecebidoAteDTreajus"));

			 p.setSaldoAreceberReajustado(rs.getBigDecimal("saldoAreceberReajustado"));
			 
			 //--------------------------------------------------------------------------------------------
			 initDate = new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("dtPrevRecebimento"));
			 formatterDate = new SimpleDateFormat("dd/MM/yyyy");
			 parsedDate = formatterDate.format(initDate);
			 
			 p.setDtPrevRecebimento(parsedDate);
			 //--------------------------------------------------------------------------------------------		    	
	    	
			 p.setObs(rs.getString("obs"));
			 
			 p.setInclusoAPS(rs.getString("inclusoAPS"));
			 
			 p.setNuAdidito_parteDaVerba(rs.getString("nuAdidito_parteDaVerba"));

	    	listaPleitos.add(p);
	    }
		liberarRecursosBD();
		return listaPleitos;
	}
	

	public int deleteFalse(String id) throws SQLException
	{
		StringBuilder update = new StringBuilder();
		update.append("update pleitos set excluido = ? ");
		update.append("where id_pleito = ?");		
		
		prepStmt = conn.prepareStatement(update.toString());
		prepStmt.setString(1, "S");
		prepStmt.setString(2, id);
		
		int reg = prepStmt.executeUpdate();
		
		liberarRecursosBD();
		
		return reg;
	}
}
