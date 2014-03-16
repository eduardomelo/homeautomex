using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class PortaModulo : EntidadeBase<int>
    {
        public string Identificador { get; set; }
        public string NumeroPorta { get; set; }
        public int TipoPorta { get; set; }
        public int Modulo { get; set; }


    }
}
