using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class ResidenciaModel : BaseModel
    {
        public string Logradouro { get; set; }
        public string Cidade { get; set; }
        public string Bairro { get; set; }
        public string Cep { get; set; }
        public string Numero { get; set; }
        public string Complemento { get; set; }
    }
}