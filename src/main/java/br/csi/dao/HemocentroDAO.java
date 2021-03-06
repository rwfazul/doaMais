/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.csi.dao;

import br.csi.banco.Registros;
import br.csi.model.Hemocentro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rhau
 */

@Repository
public class HemocentroDAO extends Registros<Hemocentro> {

    public HemocentroDAO() {
        setSqlInsercao("INSERT INTO hemocentro (nome_hemocentro, endereco_hemocentro, cep_hemocentro, telefone_hemocentro, email_hemocentro, link_facebook, link_site) VALUES (?, ?, ?, ?, ?, ?, ?)");
        setSqlAlteracao("UPDATE hemocentro SET nome_hemocentro = ?, endereco_hemocentro = ?, cep_hemocentro = ?, telefone_hemocentro = ?, email_hemocentro = ?, link_facebook = ?, link_site = ? WHERE id_hemocentro = ?");
        setSqlExclusao("DELETE FROM hemocentro WHERE id_hemocentro = ?");
        setSqlBuscaChavePrimaria("SELECT * FROM hemocentro WHERE id_hemocentro = ?");
        setSqlBuscaPersonalizada("SELECT * FROM hemocentro WHERE nome_hemocentro = ?");
        setSqlBuscaTodos("SELECT * FROM hemocentro");
    }

    @Override
    protected void preencherInsercao(PreparedStatement ps, Hemocentro h) throws SQLException {
        ps.setString(1, h.getNome());
        ps.setString(2, h.getEndereco());
        ps.setString(3, h.getCep());
        ps.setString(4, h.getTelefone());
        ps.setString(5, h.getEmail());
        ps.setString(6, h.getFacebook());
        ps.setString(7, h.getSite());        
    }

    @Override
    protected void preencherAlteracao(PreparedStatement ps, Hemocentro h) throws SQLException {
        ps.setString(1, h.getNome());
        ps.setString(2, h.getEndereco());
        ps.setString(3, h.getCep());
        ps.setString(4, h.getTelefone());
        ps.setString(5, h.getEmail());
        ps.setString(6, h.getFacebook());
        ps.setString(7, h.getSite());   
        ps.setInt(8, h.getId());
    }

    @Override
    protected void preencherExclusao(PreparedStatement ps, Hemocentro h) throws SQLException {
        ps.setInt(1, h.getId());
    }
    
    @Override
    protected void preencherBuscaPersonalizada(PreparedStatement ps, Hemocentro h) throws SQLException {
        ps.setString(1, h.getNome());
    }

    @Override
    protected Hemocentro preencher(ResultSet rs) throws SQLException {
        Hemocentro h = new Hemocentro();
        h.setId(rs.getInt("id_hemocentro"));
        h.setNome(rs.getString("nome_hemocentro"));
        h.setEndereco(rs.getString("endereco_hemocentro"));
        h.setCep(rs.getString("cep_hemocentro"));
        h.setTelefone(rs.getString("telefone_hemocentro"));
        h.setEmail(rs.getString("email_hemocentro"));
        h.setFacebook(rs.getString("link_facebook"));
        h.setSite(rs.getString("link_site"));
        return h;
    }

    @Override
    protected Collection<Hemocentro> preencherColecao(ResultSet rs) throws SQLException {
        Collection<Hemocentro> hemocentros = new ArrayList<>();
        while (rs.next())
            hemocentros.add(preencher(rs));
        return hemocentros;
    }
    
}
