using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
   public class Dispositivo : EntidadeBase<int>
    {
       
        public string descricao { get; set; }
        public DateTime cadastro { get; set; }
        public DateTime alterar { get; set; }
        public DateTime exclusao { get; set; }
        public bool desativado { get; set; }
        public bool status { get; set; }

        public Porta porta {get; set;}

        public Dispositivo() {
            this.porta = new Porta();
        }
    }
}
