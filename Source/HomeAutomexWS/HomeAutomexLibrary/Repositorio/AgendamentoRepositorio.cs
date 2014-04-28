using HomeAutomexLibrary.Entidade;
using HomeAutomexLibrary.Repositorio.Database;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio
{
    public class AgendamentoRepositorio : RepositorioBase<Agendamento,int>
    {
        public AgendamentoRepositorio(DatabaseContext contexto)
            : base(contexto)
        { }


        /// <summary>
        /// Exemplo
        /// </summary>
     
        /// <returns></returns>
        public IEnumerable<Agendamento> BuscarPorDescricao(string Descricao)
        {
            return base.Consultar(e => e.Descricao.Contains(Descricao));
        }


        //public void AlterarStatus(Dispositivo dispositivo)
        //{
        //    Context.Database.ExecuteSqlCommand("UPDATE DISPOSITIVO SET DS_DESCRICAO = '"+dispositivo.Desativado+"' WHERE CD_DISPOSITIVO ="+ dispositivo.Chave+"");

        //}
    }
}
