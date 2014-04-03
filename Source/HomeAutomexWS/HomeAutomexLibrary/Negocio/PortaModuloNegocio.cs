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
    public class PortaModuloNegocio : NegocioBase<PortaModulo, int>
    {

        private DatabaseContext contexto;
        public PortaModuloNegocio()
            //: base(new PortaModuloRepositorio(new DatabaseContext()))
        {
            this.contexto = new DatabaseContext();
        }

        public string InserirPortaModulo(PortaModulo portaModulo)
        {

            base.Inserir(portaModulo);

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

        public string AlterarPortaModulo(PortaModulo portaModulo)
        {
            base.Alterar(portaModulo);
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
        public string RemoverPortaModuloPorChave(int chave)
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
        public List<PortaModulo> ConsultarTodosPortaModulo()
        {
            return base.ConsultarTodos().ToList();
        }
    }
}

