using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public abstract class EntidadeBase<TChave>
    {
        public virtual TChave Chave { get; set; }
        public virtual DateTime? DataCadastro { get; set; }
        public virtual DateTime? DataAlteracao { get; set; }
        public virtual DateTime? DataExclusao { get; set; }
    }
}
