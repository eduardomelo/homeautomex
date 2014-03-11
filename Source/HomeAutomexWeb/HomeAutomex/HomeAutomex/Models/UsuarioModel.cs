using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class UsuarioModel : BaseModel
    {

        [Required(ErrorMessage = "Nome deve ser preenchido")]
        public string Nome { get; set; }
        [Required(ErrorMessage = "Login deve ser preenchido")]
        public string Login { get; set; }
         [Required(ErrorMessage = "Senha deve ser preenchido")]
        public string Senha { get; set; }        
        [Compare("Senha")]
        [Display(Name = "Confirme sua senha")]
        public string confirmarSenha { get; set; }
        public string Telefone { get; set; }
        public string Celular { get; set; }
        [Display(Name = "E-mail")]
        [Required(ErrorMessage = "E-mail inválido")]
        [EmailAddress(ErrorMessage = "Invalid Email Address")]
        public string Email { get; set; }
    }
}