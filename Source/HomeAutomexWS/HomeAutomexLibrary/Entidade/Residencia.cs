using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class Residencia : EntidadeBase<int>
    {        
        public string Logradouro { get; set; }
        public string Cidade { get; set; }
        public string Bairro { get; set; }
        public string Cep { get; set; }
        public string Numero { get; set; }
        public string Complemento { get; set; }
        public DateTime? DataCadastro { get; set; }
        public DateTime? DataAlteracao { get; set; }
        public DateTime? DataExclusao { get; set; }
        public bool Desativado { get; set; }
   }
}
