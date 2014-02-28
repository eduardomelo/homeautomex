using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class Porta : EntidadeBase<int>
    {
    
        public string numeroIndentificador { get; set; }
        public string numeroPorta { get; set; }

        public TipoPorta tipoPorta { get; set; }
        public Modulo modulo { get; set; }

        public Porta() {
            this.tipoPorta = new TipoPorta();
            this.modulo = new Modulo();
        }
    }
}
