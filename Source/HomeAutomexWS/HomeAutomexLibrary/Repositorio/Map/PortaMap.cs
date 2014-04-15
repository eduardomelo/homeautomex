using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Data.Entity.ModelConfiguration;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HomeAutomexLibrary.Repositorio.Map
{
    public class PortaMap : EntityTypeConfiguration<Porta>
    {
        public PortaMap()
        {
            ToTable("PORTA_MODULO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_PORTA_MODULO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Identificador).HasColumnName("NM_IDENTIFICADOR");
            Property(e => e.NumeroPorta).HasColumnName("NM_PORTA");

            Ignore(e => e.DataAlteracao);
            Ignore(e => e.DataCadastro);
            Ignore(e => e.DataExclusao);

            HasRequired(e => e.Modulo).WithMany().Map(e => e.MapKey("CD_MODULO"));
            HasRequired(e => e.Tipo).WithMany().Map(e => e.MapKey("CD_TIPO"));
        }
    }
}
