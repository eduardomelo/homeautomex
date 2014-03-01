﻿using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Negocio
{
    public class UsuarioNegocio : NegocioBase<Usuario, int>
    {
        private UsuarioRepositorio repositorio;
        private DatabaseContext contexto;
        public UsuarioNegocio()
            : base(new UsuarioRepositorio(new DatabaseContext()))
        {
            this.contexto = new DatabaseContext();
        }

        public string Inserir(Usuario usuario)
        {
            if (base.Existir(e => e.Login == usuario.Login))
                return "Erro: Usuário inválido";
            base.Inserir(usuario);
            try
            {
                base.SaveChanges();
                return "Operação realizada com sucesso!";
            }
            catch (Exception ex)
            {
                return "Erro: " + new Exception(ex.InnerException.Message != null ? ex.InnerException.Message : ex.Message);
            }
        }
        public void Alterar(Usuario usuario)
        {
            this.repositorio.Alterar(usuario);
        }
        public void Excluir(Usuario usuario)
        {
            this.repositorio.Alterar(usuario);
        }
        public IEnumerable<Usuario> ConsultarTodos()
        {
            return this.repositorio.ConsultarTodos();
        }
        public int ContarTodos()
        {
            return this.repositorio.ContarTodos();
        }        

    }
}
