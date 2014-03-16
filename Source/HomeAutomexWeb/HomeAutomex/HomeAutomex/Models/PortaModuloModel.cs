using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class PortaModuloModel : BaseModel
    {
        [Required(ErrorMessage = "Digite o identificador."), Column(Order = 1)]
        [MinLength(10, ErrorMessage = "Identificador: O tamanho mínimo do nome são 10 caracteres.")]
        [StringLength(50, ErrorMessage = "Identificador: O tamanho máximo são 50 caracteres.")]
        public string Identificador { get; set; }
        [Required(ErrorMessage = "Digite o número da Porta."), Column(Order = 1)]
        [MinLength(10, ErrorMessage = "Número da Porta: O tamanho mínimo do nome são 10 caracteres.")]
        [StringLength(50, ErrorMessage = "Número da Porta: O tamanho máximo são 50 caracteres.")]
        public string NumeroPorta { get; set; }
        public int TipoPorta { get; set; }
        public int Modulo { get; set; }

    }
}