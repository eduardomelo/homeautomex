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
        private DispositivoRepositorio dispositivoRepositorio;
        private AgendamentoRepositorio agendamentoRepositorio;



        public AgendamentoNegocio(DatabaseContext contexto)
        {
            this.contexto = contexto;
            this.dispositivoRepositorio = new DispositivoRepositorio(contexto);
            this.agendamentoRepositorio = new AgendamentoRepositorio(contexto);


        }

        public string InserirAgendamento(Agendamento agendamento)
        {

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
        public List<Agendamento> ConsultarTodosAgendamentoPorUsuarioChave(int chave)
        {
            return base.Consultar(e => e.Usuario == chave).ToList();
        }

        public List<Agendamento> VerificarTodosAgendamento()
        {

            DateTime dataMomento = DateTime.Now;
            var agendamento = new Agendamento();
            List<Agendamento> ListAgendamento = agendamentoRepositorio.Consultar(e => e.DataAgendamento <= dataMomento).ToList();

            foreach (Agendamento ChaveDispositivo in ListAgendamento)
            {
                var dipositivoNovo = this.dispositivoRepositorio.BuscarPorChave(ChaveDispositivo.Dispositivo);
                dipositivoNovo.Status = true;
                this.dispositivoRepositorio.Alterar(dipositivoNovo);
            }
            return ListAgendamento;
        }
    }

}