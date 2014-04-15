using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class PortaModulo : EntidadeBase<int>
    {
        public virtual string Identificador { get; set; }
        public virtual string NumeroPorta { get; set; }
        public virtual int TipoPorta { get; set; }
        public virtual int Modulo { get; set; }


    }
}
