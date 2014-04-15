using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class Sensor : EntidadeBase<int>
    {
        public Dispositivo dispositivo;

        public string descricao { get; set; }
        public int valor { get; set; }

    }
}
