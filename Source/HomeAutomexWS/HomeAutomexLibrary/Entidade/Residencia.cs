using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class Residencia : EntidadeBase<int>
    {
        public virtual string Nome { get; set; }
        public virtual string Logradouro { get; set; }
        public virtual string Cidade { get; set; }
        public virtual string Bairro { get; set; }
        public virtual string Cep { get; set; }
        public virtual string Numero { get; set; }
        public virtual string Complemento { get; set; }
        public virtual bool Desativado { get; set; }
        public virtual List<Usuario> Usuarios { get; set; }
          
        public Residencia()
        {
            Usuarios = new List<Usuario>();
          
        }
   }
}
