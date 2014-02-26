using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public abstract class EntidadeBase<TChave>
    {
        public TChave Chave { get; set; }
    }
}
