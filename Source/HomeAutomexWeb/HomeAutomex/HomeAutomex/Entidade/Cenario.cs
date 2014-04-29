using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
   public class Cenario : EntidadeBase<int>
    {

        public virtual string Descricao { get; set; }
        public virtual bool Desativado { get; set; }
        public virtual List<Ambiente> Ambientes { get; set; }

        public Cenario()
        {
            Ambientes = new List<Ambiente>();
        }
    }
}
