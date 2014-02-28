using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class Usuario : EntidadeBase<int>
    {
        public string Nome { get; set; }
        public string Login { get; set; }
        public string Senha { get; set; }
        public string Telefone { get; set; }
        public string Celular { get; set; }
        public string Email { get; set; }
        public DateTime cadastro { get; set; }
        public DateTime alterar { get; set; }
        public DateTime exclusao { get; set; }
        public bool desativado { get; set; }
    }
}
