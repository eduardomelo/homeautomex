using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class Ambiente : EntidadeBase<int>
    {
        
        public string descricao { get; set; }
        public DateTime cadastro { get; set; }
        public DateTime alteracao { get; set; }
        public DateTime exclusao { get; set; }
        public bool desativado { get; set; }

        public Dispositivo dispositivo {get; set;}
        public Residencia residencia {get; set;}

        public Ambiente() {
            this.dispositivo = new Dispositivo();
            this.residencia = new Residencia();
        }

    }
}
