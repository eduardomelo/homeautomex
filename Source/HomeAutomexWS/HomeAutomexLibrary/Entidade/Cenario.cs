using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class Cenario : EntidadeBase<int>
    {
      
        public string descricao { get; set; }
        public DateTime cadastro { get; set; }
        public DateTime alteracao { get; set; }
        public DateTime exclusao { get; set; }
        public bool desativado { get; set; } 
    }
}
