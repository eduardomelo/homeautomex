using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class Porta : EntidadeBase<int>
    {    
        public virtual string Identificador { get; set; }
        public virtual string NumeroPorta { get; set; }
        public virtual Modulo Modulo { get; set; }
        public virtual TipoPorta Tipo { get; set; }
    }
}
