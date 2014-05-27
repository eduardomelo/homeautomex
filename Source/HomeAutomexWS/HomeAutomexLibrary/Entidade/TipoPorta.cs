using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class TipoPorta : EntidadeBase<int>
   {
        public string Tipo { get; set; }
        public string Identificador { get; set; }
        public virtual bool Desativado { get; set; }
    }
}