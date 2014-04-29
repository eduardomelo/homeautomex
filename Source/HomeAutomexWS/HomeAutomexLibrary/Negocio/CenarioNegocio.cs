﻿using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Negocio
{
    public class CenarioNegocio : NegocioBase<Cenario, int>
    {
        private CenarioRepositorio cenarioRepositorio;
        private ResidenciaRepositorio residenciaRepositorio;
        private DatabaseContext contexto;

        public CenarioNegocio(DatabaseContext contexto)
        {
            this.contexto = contexto;
            cenarioRepositorio = new CenarioRepositorio(contexto);
            residenciaRepositorio = new ResidenciaRepositorio(contexto);  
        }
        public string InserirCenario(Cenario cenario)
        {
          
            cenario.Cadastro  = DateTime.Now;
            cenario.Alteracao = null;
            cenario.Exclusao = null;
            cenarioRepositorio.Inserir(cenario);

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

        public string AlterarCenario(Cenario cenario)
        {

            cenario.Cadastro = null;
            cenario.Alteracao = DateTime.Now;
            cenario.Exclusao = null;
            base.Alterar(cenario);
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
        public string RemoverCenarioPorChave(int chave)
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
        public List<Cenario> ConsultarTodosCenarioPorUsuarioChave(int chave)
        {
            //return this.cenarioRepositorio.Consultar(e =>
            //    e.Ambiente.Residencia.Usuarios.Any(u => u.Chave == chave))
            //    .ToList();
            return null;
        }

        public List<Cenario> ConsultarTodosCenario()
        {
            return base.ConsultarTodos().ToList();
        }
    }
}
