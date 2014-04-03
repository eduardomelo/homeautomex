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
    public class DispositivoNegocio : NegocioBase<Dispositivo, int>
    {

        private DatabaseContext contexto;
        public DispositivoNegocio()
            //: base(new DispositivoRepositorio(new DatabaseContext()))
        {
            this.contexto = new DatabaseContext();
        }

        public string InserirDispositivo(Dispositivo dispositivo)
        {
            dispositivo.DataAlteracao = null;
            dispositivo.DataCadastro = DateTime.Now;
            dispositivo.DataExclusao = null;
            base.Inserir(dispositivo);

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

        public string AlterarDispositivo(Dispositivo dispositivo)
        {

            dispositivo.DataAlteracao = DateTime.Now; ;
            dispositivo.DataCadastro = null;
            dispositivo.DataExclusao = null;
            base.Alterar(dispositivo);

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
        public string RemoverDispositivoPorChave(int chave)
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
        public List<Dispositivo> ConsultarTodosDispositivo()
        {
            return base.ConsultarTodos().ToList();
        }


    }
}

