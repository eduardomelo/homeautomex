using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Negocio
{
    public class AgendamentoNegocio : NegocioBase<Agendamento, int>
    {

        private DatabaseContext contexto;
        private AgendamentoRepositorio agendamentoRepositorio;
        private PortaRepositorio portaRepositorio;
    

        public AgendamentoNegocio(DatabaseContext contexto)
        {
            this.contexto = contexto;
            this.agendamentoRepositorio = new AgendamentoRepositorio(contexto);
            this.portaRepositorio = new PortaRepositorio(contexto);

        }

        public string InserirAgendamento(Agendamento agendamento)
        {
            agendamento.Cadastro = DateTime.Now;
            agendamento.Altaracao = null;
            agendamento.Exclusao = null;
            agendamentoRepositorio.Inserir(agendamento);
            try
            {
                contexto.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {
                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }

        public string AlterarAgendamento(Agendamento agendamento)
        {
            agendamento.Cadastro = DateTime.Now;
            agendamento.Altaracao = null;
            agendamento.Exclusao = null;
            agendamentoRepositorio.Alterar(agendamento);
            try
            {
                contexto.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {

                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }
        public string RemoverAgendamentoPorChave(int chave)
        {
            base.RemoverPorChave(chave);
            try
            {
                base.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {

                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }
        public List<Agendamento> ConsultarTodosAgendamento()
        {
            return base.ConsultarTodos().ToList();
        }       
    }
}

