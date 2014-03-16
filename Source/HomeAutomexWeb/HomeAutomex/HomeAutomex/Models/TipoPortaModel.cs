using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class TipoPortaModel : BaseModel
    {
        [Required(ErrorMessage = "Digite o tipo."), Column(Order = 1)]
        [MinLength(5, ErrorMessage = "Complemento: O tamanho mínimo do tipo da residência são 10 caracteres.")]
        [StringLength(200, ErrorMessage = "Complemento: O tamanho máximo são 200 caracteres.")]
        public string Tipo { get; set; }
        public string Identificador { get; set; }
    }
}