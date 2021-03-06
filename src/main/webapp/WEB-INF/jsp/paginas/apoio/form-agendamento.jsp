<%-- 
    Document   : form-agendamento
    Created on : Sep 26, 2017, 4:04:01 PM
    Author     : rhau
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="form-agendamento">
    <c:choose>
        <c:when test="${empty agendamentoUpdate}">
            <h1 class="text-center">Novo agendamento</h1>
            <hr/>     
            <form action="inserirAgendamento" method="post" id="form-agend" class="clearfix">
        </c:when>
        <c:otherwise>
            <h1 class="text-center">Editar agendamento</h1>
            <hr/>     
            <form action="editarAgendamento" method="post" id="form-agend" class="clearfix">
        </c:otherwise>
    </c:choose>
        <input type="hidden" name="id" />
        <div class="form-group">
            <label for="hemocentro">Hemocentro<sup>*</sup></label><br/>
            <select id="hemocentro" name="id_hemocentro" class="select-search" required>
                <option value="" disabled selected hidden></option>
                <c:forEach var="h" items="${hemocentros}">                             
                    <option value="${h.id}">${h.nome}</option>
                </c:forEach>
            </select>    
        </div>            
        <div class="form-group">
            <label for="date">Data<sup>*</sup></label>
            <input type="text" id="data" name="data_agendamento" placeholder="Escolha uma data" class="form-control" required />
        </div>
        <div class="form-group">
            <label for="horario">Faixa de hor�rio<sup>*</sup></label>
            <select id="horario" name="faixaHorario" class="form-control" required>
                <option value="" disabled selected hidden>Prefer�ncia de hor�rio para a doa��o</option>
                <option value="08h00m - 10h00m">08h00m - 10h00m</option>
                <option value="10h00m - 12h00m">10h00m - 12h00m</option>
                <option value="12h00m - 14h00m">12h00m - 14h00m</option>
                <option value="14h00m - 16h00m">14h00m - 16h00m</option>
                <option value="16h00m - 18h00m">16h00m - 18h00m</option>
                <option value="18h00m - 20h00m">18h00m - 20h00m</option>
            </select>           
        </div>
        <div class="form-group">
            <label for="extra">Observa��es</label>
            <textarea id="extra" name="observacoes" rows="2" class="form-control" placeholder="Escreva aqui caso haja particularidades que voc� deseje informar"></textarea>
        </div>
        <button type="submit" class="btn btn-submit pull-right">Confirmar agendamento 
            <i class="fa fa-check" aria-hidden="true"></i>
        </button>                    
    </form>     
</div> <!-- ./form-agendamento -->

<c:if test="${empty agendamentoUpdate}">
    <script>
        $(function() { 
            $("select").css("color", "#999");
            $("select").change(function() {
                $( this ).css("color", "#555");
            });
        });
    </script>
</c:if>

<c:if test="${not empty agendamentoUpdate && agendamentoUpdate.evento == null}">
    <script>
        var $form = $("#form-agend");
        $form.find("input[name='id']").val('${agendamentoUpdate.id}');
        $form.find("select[name='id_hemocentro']").val('${agendamentoUpdate.hemocentro.id}');
        $form.find("input[name='data_agendamento']").val('<fmt:formatDate pattern="dd/MM/yyyy" value="${agendamentoUpdate.data}" />');
        $form.find("textarea[name='observacoes']").val('${agendamentoUpdate.observacoes}');
        var $selectHorario = $form.find("select[name='faixaHorario']");
        $selectHorario.val("${agendamentoUpdate.faixaHorario}");     
    </script>
</c:if>