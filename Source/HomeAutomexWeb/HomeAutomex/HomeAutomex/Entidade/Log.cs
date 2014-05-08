using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class Log : EntidadeBase<int>
    {
        public int Cd_usuario { get; set; }
        public string Descricao { get; set; }
       
        
    }
}
