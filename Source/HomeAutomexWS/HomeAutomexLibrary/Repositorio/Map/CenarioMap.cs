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
    public class CenarioMap : EntityTypeConfiguration<Cenario>
    {
        public CenarioMap()
        {

            ToTable("CENARIO");

            HasKey(e => e.Chave)
                .Property(e => e.Chave)
                .HasColumnName("CD_CENARIO")
                .HasDatabaseGeneratedOption(DatabaseGeneratedOption.Identity);

            Property(e => e.Descricao).HasColumnName("DS_DESCRICAO").IsRequired();
            Property(e => e.DataCadastro).HasColumnName("DT_CADASTRO");
            Property(e => e.DataAlteracao).HasColumnName("DT_ALTERACAO");
            Property(e => e.DataExclusao).HasColumnName("DT_EXCLUSAO");
            Property(e => e.Desativado).HasColumnName("IS_DESATIVADO");
         
            HasMany(e => e.Dispositivos)
              .WithMany(e => e.Cenario)
              .Map(e =>
              {
                  e.ToTable("DISPOSITIVO_CENARIO");
                  e.MapLeftKey("CD_DISPOSITIVO");
                  e.MapRightKey("CD_CENARIO");
              });
        }

    }
}
