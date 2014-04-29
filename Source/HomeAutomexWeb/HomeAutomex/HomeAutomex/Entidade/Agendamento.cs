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

        public virtual List<Usuario> Usuarios { get; set; }

        public virtual DateTime? DataAgendamento { get; set; }

        public int Usuario { get; set; }
        public int Cenario { get; set; }

        public Agendamento() {
            this.Usuarios = new List<Usuario>();
        }


    }
}
