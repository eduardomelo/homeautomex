using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class Modulo : EntidadeBase<int>
    {
        public virtual string IP { get; set; }
        public virtual string Porta { get; set; }
        public virtual string Nome { get; set; }
        public virtual string MAC { get; set; }
        public virtual Residencia Residencia { get; set; }        
    }
}
