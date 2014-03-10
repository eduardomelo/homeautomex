using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class UsuarioModel : BaseModel
    {
        [Required]
        [Display(Name="Login")]
        public string Login { get; set; }
        public string Nome { get; set; }
        public string Senha { get; set; }
        public string Celular { get; set; }
        public string Telefone { get; set; }
        public string Email { get; set; }
    }
}