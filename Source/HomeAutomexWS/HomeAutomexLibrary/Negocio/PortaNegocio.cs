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
    public class PortaNegocio : NegocioBase<Porta,int>
    {
        private PortaRepositorio portaRepositorio;
        private ModuloRepositorio moduloRepositorio;
        private TipoPortaRepositorio tipoPortaRepositorio;
        private DatabaseContext contexto;

        public PortaNegocio(DatabaseContext contexto)
        {
            this.contexto = contexto;
            this.portaRepositorio = new PortaRepositorio(contexto);
            this.moduloRepositorio = new ModuloRepositorio(contexto);
            this.tipoPortaRepositorio = new TipoPortaRepositorio(contexto);
        }

        public string Inserir(Porta porta)
        {            
            porta.Modulo = moduloRepositorio.BuscarPorChave(porta.Modulo.Chave);
            porta.Tipo = tipoPortaRepositorio.BuscarPorChave(porta.Tipo.Chave);
            portaRepositorio.Inserir(porta);

            try
            {
                contexto.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (System.Data.Entity.Validation.DbEntityValidationException e)
            {
                foreach (var eve in e.EntityValidationErrors)
                {
                    Console.WriteLine("Entity of type \"{0}\" in state \"{1}\" has the following validation errors:",
                        eve.Entry.Entity.GetType().Name, eve.Entry.State);
                    foreach (var ve in eve.ValidationErrors)
                    {
                        Console.WriteLine("- Property: \"{0}\", Error: \"{1}\"",
                            ve.PropertyName, ve.ErrorMessage);
                    }
                }
                throw;
            }
            catch (Exception ex)
            {
                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }

        public string Alterar(Porta porta)
        {
            porta.Modulo = moduloRepositorio.BuscarPorChave(porta.Modulo.Chave);
            porta.Tipo = tipoPortaRepositorio.BuscarPorChave(porta.Tipo.Chave);
            portaRepositorio.Alterar(porta);
            try
            {
                contexto.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (System.Data.Entity.Validation.DbEntityValidationException e)
            {
                foreach (var eve in e.EntityValidationErrors)
                {
                    Console.WriteLine("Entity of type \"{0}\" in state \"{1}\" has the following validation errors:",
                        eve.Entry.Entity.GetType().Name, eve.Entry.State);
                    foreach (var ve in eve.ValidationErrors)
                    {
                        Console.WriteLine("- Property: \"{0}\", Error: \"{1}\"",
                            ve.PropertyName, ve.ErrorMessage);
                    }
                }
                throw;
            }
            catch (Exception ex)
            {

                throw new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }
        public string RemoverPorChave(int chave)
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
    }
}
