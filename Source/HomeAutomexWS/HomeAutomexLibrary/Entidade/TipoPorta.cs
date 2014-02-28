using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class TipoPorta : EntidadeBase<int>
    {
        public string tipo { get; set; }
        public string indentificador { get; set; }
    }
}
