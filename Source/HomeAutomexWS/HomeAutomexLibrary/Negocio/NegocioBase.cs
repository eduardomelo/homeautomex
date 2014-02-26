using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Negocio
{
    public class NegocioBase<TEntidade, TChave>
        where TEntidade : EntidadeBase<TChave>
    {

    }
}
