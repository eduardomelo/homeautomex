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
    public class LogNegocio : NegocioBase<Log, int>
    {
      
        private DatabaseContext contexto;
        public LogNegocio()
        {
         
    
            this.contexto = new DatabaseContext();
        }

        public string InserirLog(Log log)
        {
            log.Cd_usuario = 1;
            log.DataCadastro = DateTime.Now;
            base.Inserir(log);
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
        public List<Log> ConsultarTodosLog()
        {
            return base.ConsultarTodos().ToList();
        }
        public List<Log> ConsultarPorIntervaloData(DateTime DataInicial, DateTime DataFinal)
        {
            return base.Consultar(e => e.DataCadastro >= DataInicial && 
            e.DataCadastro <=  DataFinal).ToList();
        
        }


      




        



    }
}

