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
    public class DispositivoCenarioNegocio : NegocioBase<DispositivoCenario,int>
    {
        private CenarioRepositorio cenarioRepositorio;
        private DispositivoRepositorio disposistivoRepositorio;
        private DispositivoCenarioRepositorio dispositivoCenarioRepositorio;
      
        private DatabaseContext contexto;

        public DispositivoCenarioNegocio(DatabaseContext contexto)
        {
            this.contexto = contexto;
            this.cenarioRepositorio = new CenarioRepositorio(contexto);
            this.disposistivoRepositorio = new DispositivoRepositorio(contexto);
            this.dispositivoCenarioRepositorio = new DispositivoCenarioRepositorio(contexto);
        
        }

        public string InserirDispositivoCenario(DispositivoCenario dispositivoCenario)
        {
            dispositivoCenario.Cenarios = cenarioRepositorio.BuscarPorChave(dispositivoCenario.Cenarios.Chave);
            dispositivoCenario.Dispositivos =disposistivoRepositorio.BuscarPorChave(dispositivoCenario.Dispositivos.Chave);
            this.dispositivoCenarioRepositorio.Inserir(dispositivoCenario);

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

        public string AlterarDispositivoCenario(DispositivoCenario dispositivoCenario)
        {
            dispositivoCenario.Cenarios = cenarioRepositorio.BuscarPorChave(dispositivoCenario.Cenarios.Chave);
            dispositivoCenario.Dispositivos = disposistivoRepositorio.BuscarPorChave(dispositivoCenario.Dispositivos.Chave);
            this.dispositivoCenarioRepositorio.Inserir(dispositivoCenario);
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
