using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class Modulo : EntidadeBase<int>
    {
       
        public string numeroIP { get; set; }
        public string numeroPorta { get; set; }
        public string nome { get; set; }
        public string numeroMac { get; set; }

        public Residencia residencia {get; set;}

        public Modulo() {
            this.residencia = new Residencia();
        }

    }
}
