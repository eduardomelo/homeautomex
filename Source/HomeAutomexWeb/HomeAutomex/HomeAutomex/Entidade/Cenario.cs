using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
   public class Cenario : EntidadeBase<int>
    {

        public string Descricao { get; set; }
        public DateTime Cadastro { get; set; }
        public DateTime Alteracao { get; set; }
        public DateTime Exclusao { get; set; }
        public bool Desativado { get; set; }
    }
}
