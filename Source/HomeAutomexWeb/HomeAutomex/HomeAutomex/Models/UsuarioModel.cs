using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace HomeAutomex.Models
{
    public class UsuarioModel : BaseModel
    {

        [Required(ErrorMessage = "Digite o seu Nome."), Column(Order = 1)]
        [MinLength(5, ErrorMessage = "Nome: O tamanho mínimo do nome são 5 caracteres.")]
        [StringLength(200, ErrorMessage = "Nome: O tamanho máximo são 200 caracteres.")]
        public string Nome { get; set; }
        [Required(ErrorMessage = "Digite o seu Login."), Column(Order = 1)]
        [MinLength(5, ErrorMessage = "O tamanho mínimo do nome são 4 caracteres.")]
        [StringLength(200, ErrorMessage = "Login: O tamanho máximo são 200 caracteres.")]
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
        [EmailAddress(ErrorMessage = "E-mail Inválido")]
        public string Email { get; set; }

        
    }
}