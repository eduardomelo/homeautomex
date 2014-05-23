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
    public class TipoPortaNeogocio : NegocioBase<TipoPorta, int>
    {

        private DatabaseContext contexto;
        public TipoPortaNeogocio()
            //: base(new TipoPortaRepositorio(new DatabaseContext()))
        {
            this.contexto = new DatabaseContext();
        }

        public string InserirTipoPorta(TipoPorta tipoPorta)
        {
            tipoPorta.Desativado = true;
            base.Inserir(tipoPorta);
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

        public string AlterarTipoPorta(TipoPorta tipoPorta)
        {
            base.Alterar(tipoPorta);
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
        public string RemoverTipoPortaPorChave(int chave)
        {
            TipoPorta tipoPorta = new TipoPorta();
            tipoPorta = BuscarPorChave(chave);
            tipoPorta.Desativado = false;
            base.Alterar(tipoPorta);
           // base.RemoverPorChave(chave);
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
        public List<TipoPorta> ConsultarTodosTipoPorta()
        {
            return base.Consultar(e => e.Desativado == true).ToList();
        }


    }
}

