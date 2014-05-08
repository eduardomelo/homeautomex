using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace HomeAutomexLibrary.Entidade
{
    public class Dispositivo : EntidadeBase<int>
    {

        public virtual string Descricao { get; set; }
        public virtual DateTime? DataCadastro { get; set; }
        public virtual DateTime? DataAlteracao { get; set; }
        public virtual DateTime? DataExclusao { get; set; }
        public virtual bool Desativado { get; set; }
        public virtual bool Status { get; set; }
        public virtual Ambiente Ambiente { get; set; }
        public virtual Porta Porta { get; set; }
        public virtual bool Favorito { get; set; }
        public virtual List<Usuario> Usuarios { get; set; }
        public virtual List<Cenario> Cenario { get; set; }

        public Dispositivo()
        {
            Usuarios = new List<Usuario>();
            Cenario = new List<Cenario>();

            //Modulos = new List<Modulo>();
        }
    }
}

   //public class DispositivoTeste : EntidadeBase<int>
   //{
   //    public virtual string Identificador { get; set; }
   //    public virtual bool Status { get; set; }
   //}


