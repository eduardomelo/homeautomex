using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class DispositivoCenario : EntidadeBase<int>
    {

        public virtual bool Status { get; set; }
        public virtual Dispositivo Dispositivos { get; set; }
        public virtual Cenario Cenarios { get; set; }

        public DispositivoCenario() {
            this.Dispositivos = new Dispositivo();
            this.Cenarios = new Cenario();
        }
      
    }
}