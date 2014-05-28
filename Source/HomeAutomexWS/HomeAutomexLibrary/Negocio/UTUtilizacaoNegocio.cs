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
    public class UTUtilizacaoNegocio : NegocioBase<UTDispositivo, int>
    {

        private DatabaseContext contexto;
        public UTUtilizacaoNegocio()
        {
            this.contexto = new DatabaseContext();
        }

        public string InserirUTDispositivo(UTDispositivo utDispositivo)
        {
            utDispositivo.Cd_usuario = 1;
            utDispositivo.UT_utilizacao = DateTime.Now;
            base.Inserir(utDispositivo);

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
        public string AlterarUTDispositivo(UTDispositivo utDispositivo)
        {
            utDispositivo.Cd_usuario = 1;
            utDispositivo.UT_utilizacao = DateTime.Now;
            base.Inserir(utDispositivo);

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
        public List<UTDispositivo> ConsultarTodosUTDispositivo()
        {
            return base.ConsultarTodos().ToList();
        }


    }
}

