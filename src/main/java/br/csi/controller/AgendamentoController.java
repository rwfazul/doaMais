/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.csi.controller;

import br.csi.dao.AgendamentoDAO;
import br.csi.model.Agendamento;
import br.csi.model.Doador;
import br.csi.model.Hemocentro;
import br.csi.model.Usuario;
import br.csi.util.DateUtils;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author rhau
 */

@Controller
public class AgendamentoController {
    
    @Autowired
    AgendamentoDAO adao;
    
    @RequestMapping("inserirAgendamento")
    public String insereAgendamento(Model model, Agendamento a, HttpSession session, @RequestParam("id_hemocentro") Integer id_hemocentro, @RequestParam("data_agendamento") String dataAgendamento) {
        Usuario u = (Usuario) session.getAttribute("usuarioLogado");
        a.setDoador(new Doador(u.getId()));
        a.setHemocentro(new Hemocentro(id_hemocentro));
        a.setData(DateUtils.toDate(dataAgendamento, "dd/MM/yyyy"));
        if (a.getObservacoes().isEmpty())
            a.setObservacoes("N/A");
        try {
            adao.inserir(a);
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:meus-agendamentos";
    }
    
    @RequestMapping("deletarAgendamento")
    public String removeAgendamento(@RequestParam("id_agendamento") Integer id_agendamento) {
        try {
            adao.excluir(new Agendamento(id_agendamento));
        } catch (SQLException ex) {
            Logger.getLogger(AgendamentoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:meus-agendamentos";
    }

}