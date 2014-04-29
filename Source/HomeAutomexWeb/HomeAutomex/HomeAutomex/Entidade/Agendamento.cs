using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{

    public class Agendamento : EntidadeBase<int>
    {

        public string Descricao { get; set; }
        public bool Ativo { get; set; }
        public virtual DateTime? DataAgendamento { get; set; }
        public bool Desativado { get; set; }

        public int Usuario { get; set; }
        public int Cenario { get; set; }


    }
}
