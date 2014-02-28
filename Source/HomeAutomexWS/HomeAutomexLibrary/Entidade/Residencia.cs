using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class Residencia : EntidadeBase<int>
    {
        
        public string logradouro { get; set; }
        public string cidade { get; set; }
        public string bairro { get; set; }
        public string cep { get; set; }
        public string numero { get; set; }
        public string complemento { get; set; }
        public DateTime cadastro { get; set; }
        public DateTime alterar { get; set; }
        public DateTime exclusao { get; set; }
        public bool desativado { get; set; }
   }
}
