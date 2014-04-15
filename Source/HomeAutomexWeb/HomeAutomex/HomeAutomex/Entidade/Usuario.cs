using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Entidade
{
    public class Usuario : EntidadeBase<int>
    {
        public virtual string Nome { get; set; }
        public virtual string Login { get; set; }
        public virtual string Senha { get; set; }
        public virtual string Telefone { get; set; }
        public virtual string Celular { get; set; }
        public virtual string Email { get; set; }
        public virtual bool Desativado { get; set; }
        public virtual List<Residencia> Residencias { get; set; }

        public Usuario()
        {
            Residencias = new List<Residencia>();
        }
    }
}
