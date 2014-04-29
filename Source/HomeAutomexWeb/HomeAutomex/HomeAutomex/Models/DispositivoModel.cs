using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomex.Models
{
    public class DispositivoModel : BaseModel
    {
        [Required]
        [Display(Name = "Descrição")]
        public string Descricao { get; set; }

        [Display(Name = "Status")]
        public bool Status { get; set; }

        [Required]
        [Display(Name = "Porta do Modulo")]
        public int ChavePorta { get; set; }
        public PortaModel Porta { get; set; }

        [Required]
        [Display(Name="Ambiente")]
        public int ChaveAmbiente { get; set; }
        public AmbienteModel Ambiente { get; set; }

        [Display(Name = "Favorito")]
        public bool Favorito { get; set; }

        public List<UsuarioModel> Usuarios { get; set; }
     

    }
}