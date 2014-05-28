using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class UTCenario : EntidadeBase<int>
    {

        public int CD_Cenario { get; set; }
        public int CD_Dispositivo { get; set; }
        public bool StatusDispositivo { get; set; }
    }

}