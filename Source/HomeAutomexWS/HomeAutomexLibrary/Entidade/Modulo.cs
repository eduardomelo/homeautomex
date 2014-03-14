using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class Modulo : EntidadeBase<int>
    {
       
        public string NumeroIP { get; set; }
        public string NumeroPorta { get; set; }
        public string Nome { get; set; }
        public string NumeroMac { get; set; }
        public int Residencia { get; set; }

   

       

    }
}
