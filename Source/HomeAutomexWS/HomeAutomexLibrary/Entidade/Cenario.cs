using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class Cenario : EntidadeBase<int>
    {
        public string Descricao { get; set; }
        public bool Desativado { get; set; }

        public virtual List<Dispositivo> Dispositivo { get; set; }

        public Cenario() {
            this.Dispositivo = new List<Dispositivo>();
        }
      
      
    }
}
