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
    public class UTCenarioNegocio : NegocioBase<UTCenario, int>
    {
      
        private DatabaseContext contexto;
        public UTCenarioNegocio()
        {
            this.contexto = new DatabaseContext();
        }

        public string InserirUTCenario(UTCenario utCenario)
        {

            base.Inserir(utCenario);
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
        public List<UTCenario> ConsultarTodosLog()
        {
            return base.ConsultarTodos().ToList();
        }
        public List<UTCenario> ConsultarPorIntervaloData(DateTime DataInicial, DateTime DataFinal)
        {
            return base.Consultar(e => e.DataCadastro >= DataInicial &&
            e.DataCadastro <= DataFinal).ToList();

        }

    }
}

