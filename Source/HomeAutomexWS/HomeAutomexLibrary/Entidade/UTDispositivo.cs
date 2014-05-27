using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class UTDispositivo : EntidadeBase<int>
    {
        public int Cd_usuario { get; set; }
        public int Cd_dispositivo { get; set; }
        public DateTime? UT_utilizacao { get; set; }
        public bool status { get; set; }
   }
}
