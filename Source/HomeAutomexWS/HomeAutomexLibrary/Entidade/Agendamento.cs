using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{

    public class Agendamento : EntidadeBase<int>
    {
      
        public string descricao { get; set; }
        public bool ativo { get; set; }
        public DateTime cadastro { get; set; }
        public DateTime altaracao { get; set; }
        public DateTime exclusao { get; set; }
        public bool desativado { get; set; }

        public Usuario usuario { get; set; }
        public Cenario cenario { get; set; }

        public Agendamento()
        {
            this.usuario = new Usuario();
            this.cenario = new Cenario();
        }

    }
}
