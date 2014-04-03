using HomeAutomexLibrary.Entidade;
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
        private DatabaseContext contexto;
        public UsuarioNegocio()
            //: base(new UsuarioRepositorio(new DatabaseContext()))
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
        public string Alterar(Usuario usuario)
        {
            base.Alterar(usuario);
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
        public string RemoverUsuarioPorChave(int chave)
        {
            {
                base.RemoverPorChave(chave);
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
        }
        public List<Usuario> ConsultarTodosUsuario()
        {
            return base.ConsultarTodos().ToList();
        }
        public int ContarTodosUsuario()
        {
            return base.ContarTodos();
        }

        public string ExisteUsuario(Usuario usuario)
        {
            if (base.Existir(e => e.Login == usuario.Login) || base.Existir(e => e.Senha == usuario.Senha ))
                return usuario.Login;
            else 
                return null;
        }

        public Usuario Logar(Usuario usuario)
        {
            return base.Buscar(e => e.Login == usuario.Login && e.Senha == usuario.Senha && !e.Desativado);
        }
    }
}
