using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using HomeAutomexLibrary.Entidade;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class PortaModuloMap : EntityTypeConfiguration<PortaModulo>
    {
        public PortaModuloMap()
        {

            ToTable("PORTA_MODULO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_PORTA_MODULO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Identificador).HasColumnName("NM_IDENTIFICADOR").IsRequired();
            Property(e => e.NumeroPorta).HasColumnName("NM_PORTA");
            Property(e => e.TipoPorta).HasColumnName("CD_TIPO");
            Property(e => e.Modulo).HasColumnName("CD_MODULO");


            Ignore(e => e.DataCadastro);
            Ignore(e => e.DataAlteracao);
            Ignore(e => e.DataExclusao);
       

        
        }

    }
}
