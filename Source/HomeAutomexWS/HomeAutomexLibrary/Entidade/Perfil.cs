using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class Perfil : EntidadeBase<int>
    {
        public string nome { get; set; }
        public bool admin { get; set; }
        public DateTime cadastro { get; set; }
        public DateTime alterar { get; set; }
        public DateTime exclusao { get; set; }
        public bool desativado { get; set; }

        public Residencia residencia { get; set; }
   }
}
